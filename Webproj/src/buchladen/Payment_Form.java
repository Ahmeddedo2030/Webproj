package buchladen;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Payment_Form
 */
@WebServlet("/Payment_Form")
public class Payment_Form extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Payment_Form() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("CategoryBookList").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		if(request.getParameter("submit") != null) {

		String submittype = request.getParameter("submit");
		Customerimpl customimpl = new Customerimpl();

		if (submittype.equals("transaktionen")) {

			ArrayList<Transaction> tranarr = customimpl.getTransactions(request.getParameter("username"));

			if (request.getParameter("username").equals("")) {

				out.print("Sie haben kein Benutzername eingegeben,bitte versuchen Sie nocheinmal.");

				// request.setAttribute("transactions","Sie haben kein Benutzername
				// eingegeben,bitte versuchen Sie nocheinmal");
				// request.getRequestDispatcher("ControlPanel.jsp").forward(request, response);

			}

			else if (tranarr.size() == 0) {

				out.print("Die eingegebene Benutzername ist falsch oder hat keine Transaktionen.");

				// request.setAttribute("transactions","Die eingegebene Benutzername ist falsch
				// oder hat keine Transaktionen");

			} else {

				out.print(Customerimpl.showTransactions(tranarr));

				// request.setAttribute("transactions", Customerimpl.showTransactions(tranarr));
			}

			// request.getRequestDispatcher("ControlPanel.jsp").forward(request, response);

		} else if (submittype.equals("showusers")) {

			out.print(Customerimpl.getallUsers());

			// request.setAttribute("users", Customerimpl.getallUsers());
			// request.getRequestDispatcher("ControlPanel.jsp").forward(request, response);

		}else if(submittype.equals("kasse")) {
			
			HttpSession session = request.getSession();
			int id = (int) session.getAttribute("userid");
			
			int total = WarenKorbImpl.getTotalpreisAllitems(id);
			
			if(total == 0) {
				
				request.setAttribute("message", "Warenkorb ist Leer, Sollen Sie erst etwas bestellen");
				
			}
			
		} else if (submittype.equals("bezahlen")) {
			
			HttpSession session = request.getSession();
			int id = (int) session.getAttribute("userid");

			request.setAttribute("kartbesitzer", request.getParameter("kartbesitzer"));
			request.setAttribute("kartnummer", request.getParameter("kartnummer"));
			request.setAttribute("date", request.getParameter("date"));
			request.setAttribute("cvc", request.getParameter("cvc"));
			request.setAttribute("adresse", request.getParameter("adresse"));

			if (request.getParameter("kartbesitzer") == "" || request.getParameter("kartnummer") == ""
					|| request.getParameter("date") == "" || request.getParameter("cvc") == ""
					|| request.getParameter("adresse") == "") {

				request.setAttribute("message", "Ein Feld wurde von ihnen noch nicht eingegeben");
				request.getRequestDispatcher("bezahlen.jsp").forward(request, response);

			} else if (request.getParameter("kartnummer").length() < 19) {

				request.setAttribute("message", "Sie haben die Kartennummer nicht vollständig eingegeben");
				request.getRequestDispatcher("bezahlen.jsp").forward(request, response);

			} else if (request.getParameter("cvc").length() < 3) {

				request.setAttribute("message", "Sie haben die CVCNummer nicht vollständig eingegeben");
				request.getRequestDispatcher("bezahlen.jsp").forward(request, response);

			} else if (request.getParameter("date").length() < 5) {

				request.setAttribute("message", "Sie haben das Ablaufdatum falsch eingegeben");
				request.getRequestDispatcher("bezahlen.jsp").forward(request, response);

			} else if(WarenKorbImpl.getTotalpreisAllitems(id) == 0){
				
				request.setAttribute("message", "Warenkorb ist leer,bitte erst etwas bestellen");
				request.getRequestDispatcher("bezahlen.jsp").forward(request, response);
				
			}else {
				
				request.setAttribute("kartbesitzer", request.getParameter("kartbesitzer"));
				request.setAttribute("kartnummer", request.getParameter("kartnummer"));
				request.setAttribute("date", request.getParameter("date"));
				request.setAttribute("cvc", request.getParameter("cvc"));
				request.setAttribute("adresse", request.getParameter("adresse"));
				request.setAttribute("total", WarenKorbImpl.getTotalpreisAllitems(id));
				request.getRequestDispatcher("Bestaetigung.jsp").forward(request, response);

			}

		} else if (submittype.equals("bank_bezahlen")) {

			HttpSession session = request.getSession();
			int id = (int) session.getAttribute("userid");
			
			request.setAttribute("kontoinhaber", request.getParameter("kontoinhaber"));
			request.setAttribute("iban", request.getParameter("iban"));
			request.setAttribute("bic", request.getParameter("bic"));
			request.setAttribute("adresse", request.getParameter("adresse"));

			if (request.getParameter("kontoinhaber") == "" || request.getParameter("iban") == ""
					|| request.getParameter("bic") == "" || request.getParameter("adresse") == "") {

				request.setAttribute("message", "Ein Feld wurde von ihnen noch nicht eingegeben");
				request.getRequestDispatcher("bank_bezahlen.jsp").forward(request, response);

			} else if (request.getParameter("iban").length() < 27) {

				request.setAttribute("message", "Sie haben falsche IBAN eingegeben");
				request.getRequestDispatcher("bank_bezahlen.jsp").forward(request, response);

			} else if(WarenKorbImpl.getTotalpreisAllitems(id) == 0){
				
				request.setAttribute("message", "Warenkorb ist leer,bitte erst etwas bestellen");
				request.getRequestDispatcher("bank_bezahlen.jsp").forward(request, response);
				
			} else {

				request.setAttribute("kontoinhaber", request.getParameter("kontoinhaber"));
				request.setAttribute("iban", request.getParameter("iban"));
				request.setAttribute("bic", request.getParameter("bic"));
				request.setAttribute("adresse", request.getParameter("adresse"));
				request.setAttribute("total", WarenKorbImpl.getTotalpreisAllitems(id));
				request.getRequestDispatcher("Bank_Bestaetigung.jsp").forward(request, response);

			}

		} else if (submittype.equals("warencorp")) {

			HttpSession session = request.getSession();
			SimpleDateFormat sdf = new SimpleDateFormat();
			Calendar cal = Calendar.getInstance();

			int id = (int) session.getAttribute("userid");
			String date = sdf.format(cal.getTime());
			String kartbesitzer = request.getParameter("kbesitzer");
			String kartnummer = request.getParameter("knummer");
			String kartablaufsdatum = request.getParameter("ablaufsdatum");
			int kartcvc = Integer.parseInt(request.getParameter("kcvc"));
			String kartadresse = request.getParameter("kadresse");
			int karttotal = Integer.parseInt(request.getParameter("ktotal"));

			Transaction tran = new Transaction();

			tran.setCustomer_id(id);
			tran.setT_date(date);
			tran.setKarte_besitzer(kartbesitzer);
			tran.setKarte_nummer(kartnummer);
			tran.setKarte_ablauf_datum(kartablaufsdatum);
			tran.setCvc(kartcvc);
			tran.setLieferungs_adresse(kartadresse);
			tran.setTotal_preis(karttotal);
			
			ArrayList<WarenKorb> arrwk = WarenKorbImpl.getWarenkorb(id);

			TransactionImpl.insertTransaction(tran,arrwk);
			
			WarenKorbImpl.delWarenkorb(id);

			request.getRequestDispatcher("abschluss.jsp").forward(request, response);

		}else if (submittype.equals("bank_warencorp")) {

			HttpSession session = request.getSession();
			SimpleDateFormat sdf = new SimpleDateFormat();
			Calendar cal = Calendar.getInstance();

			int id = (int) session.getAttribute("userid");
			String date = sdf.format(cal.getTime());
			String kontoinhaber = request.getParameter("kontoinhaber");
			String iban = request.getParameter("iban");
			String bic = request.getParameter("bic");
			String bankadresse = request.getParameter("badresse");
			int banktotal = Integer.parseInt(request.getParameter("btotal"));

			Bank_Transaction tran = new Bank_Transaction();

			tran.setCustomer_id(id);
			tran.setDate(date);
			tran.setKonto_Inhaber(kontoinhaber);
			tran.setIBAN(iban);
			tran.setBIC(bic);
			tran.setLieferungs_adresse(bankadresse);
			tran.setTotal_preis(banktotal);
			
			ArrayList<WarenKorb> arrwk = WarenKorbImpl.getWarenkorb(id);

			TransactionImpl.insertBankTransaction(tran, arrwk);
			
			WarenKorbImpl.delWarenkorb(id);

			request.getRequestDispatcher("abschluss.jsp").forward(request, response);

		}
		
		}
	}
}
