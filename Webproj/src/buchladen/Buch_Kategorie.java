package buchladen;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Buch_Kategorie
 */
@WebServlet("/Buch_Kategorie")
public class Buch_Kategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ArrayList<String> kategorien = new ArrayList<String>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Buch_Kategorie() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String submittype = request.getParameter("submit");

		Buch b = new Buch();

		if (submittype.equals("buchaktualisieren")) {

			if (request.getParameter("buch_titel2") == "" || request.getParameter("buch_preis2") == ""
					|| request.getParameter("buch_isbn2") == "" || request.getParameter("buch_sprache2") == ""
					|| request.getParameter("buch_bild2") == "" || request.getParameter("buch_anzahl2") == "") {

				request.setAttribute("buchaktu", "Ein Feld wurde noch nicht eingegeben...");
				request.setAttribute("katliste2", KategorieImpl.kategorieList(kategorien));
				request.setAttribute("kat2", request.getParameter("buch_titel2"));
				request.setAttribute("preis2", request.getParameter("buch_preis2"));
				request.setAttribute("isbn2", request.getParameter("buch_isbn2"));
				request.setAttribute("sprache2", request.getParameter("buch_sprache2"));
				request.setAttribute("bild2", request.getParameter("buch_bild2"));
				request.setAttribute("anzahl2", request.getParameter("buch_anzahl2"));
				request.getRequestDispatcher("ControlPanel.jsp").forward(request, response);

			} else if (kategorien.size() == 0) {
				
				request.setAttribute("buchaktu", "Keine Kategorie wurde ausgewählt...");
				request.setAttribute("katliste2", KategorieImpl.kategorieList(kategorien));
				request.setAttribute("kat2", request.getParameter("buch_titel2"));
				request.setAttribute("preis2", request.getParameter("buch_preis2"));
				request.setAttribute("isbn2", request.getParameter("buch_isbn2"));
				request.setAttribute("sprache2", request.getParameter("buch_sprache2"));
				request.setAttribute("bild2", request.getParameter("buch_bild2"));
				request.setAttribute("anzahl2", request.getParameter("buch_anzahl2"));
				request.getRequestDispatcher("ControlPanel.jsp").forward(request, response);
				
				
			}  else if (!regex_funk.chekPreis(request.getParameter("buch_preis2"))) {
				
				request.setAttribute("buchaktu", "Sie haben ein ungültiges Preis eingegeben...");
				request.setAttribute("katliste2", KategorieImpl.kategorieList(kategorien));
				request.setAttribute("kat2", request.getParameter("buch_titel2"));
				request.setAttribute("preis2", request.getParameter("buch_preis2"));
				request.setAttribute("isbn2", request.getParameter("buch_isbn2"));
				request.setAttribute("sprache2", request.getParameter("buch_sprache2"));
				request.setAttribute("bild2", request.getParameter("buch_bild2"));
				request.setAttribute("anzahl2", request.getParameter("buch_anzahl2"));
				request.getRequestDispatcher("ControlPanel.jsp").forward(request, response);
				
			}  else if (Integer.parseInt(request.getParameter("buch_preis2")) > 500) {

				request.setAttribute("buchaktu", "Das Preis des Buchs kann nicht über 500 Euro angelegt werden...");
				request.setAttribute("katliste2", KategorieImpl.kategorieList(kategorien));
				request.setAttribute("kat2", request.getParameter("buch_titel2"));
				request.setAttribute("preis2", request.getParameter("buch_preis2"));
				request.setAttribute("isbn2", request.getParameter("buch_isbn2"));
				request.setAttribute("sprache2", request.getParameter("buch_sprache2"));
				request.setAttribute("bild2", request.getParameter("buch_bild2"));
				request.setAttribute("anzahl2", request.getParameter("buch_anzahl2"));
				request.getRequestDispatcher("ControlPanel.jsp").forward(request, response);

			} else if (Integer.parseInt(request.getParameter("buch_preis2")) < 5) {

				request.setAttribute("buchaktu", "Das Preis des Buchs kann nicht unter 5 Euro angelegt werden...");
				request.setAttribute("katliste2", KategorieImpl.kategorieList(kategorien));
				request.setAttribute("kat2", request.getParameter("buch_titel2"));
				request.setAttribute("preis2", request.getParameter("buch_preis2"));
				request.setAttribute("isbn2", request.getParameter("buch_isbn2"));
				request.setAttribute("sprache2", request.getParameter("buch_sprache2"));
				request.setAttribute("bild2", request.getParameter("buch_bild2"));
				request.setAttribute("anzahl2", request.getParameter("buch_anzahl2"));
				request.getRequestDispatcher("ControlPanel.jsp").forward(request, response);

			} else {

				String titel = request.getParameter("buch_titel2");
				int preis = Integer.parseInt(request.getParameter("buch_preis2"));
				String isbn = request.getParameter("buch_isbn2");
				String sprache = request.getParameter("buch_sprache2");
				String bildpfad = request.getParameter("buch_bild2");
				int anzahl = Integer.parseInt(request.getParameter("buch_anzahl2"));
				String verlag = request.getParameter("verlag2");
				String autor = request.getParameter("autor2");
				String lager = request.getParameter("lager2");

				b.setTitel(titel);
				b.setPreis(preis);
				b.setISBN(isbn);
				b.setSprache(sprache);
				b.setBildpfad(bildpfad);
				b.setAnzahl(anzahl);
				b.setVerlag_id(VerlagImpl.getVerlagid(verlag));
				b.setAutor_id(AutorImpl.getAutorid(autor));
				b.setLager_id(BuchImpl.getLagerid(lager));

				BuchImpl.buchUpdate(request.getParameter("buch1"), b, kategorien);
				kategorien.clear();

				request.setAttribute("buchaktu", "Das ausgewählte Buch wurde Erfolgreich aktualisiert...");
				request.getRequestDispatcher("ControlPanel.jsp").forward(request, response);

			}

		} else if (submittype.equals("buchloeschen")) {

			String buch = request.getParameter("buch2");

			BuchImpl.delBuch(BuchImpl.getBuchid(buch));

			request.setAttribute("buchdel", "Das ausgewählte Buch wurde erfolgreich gelöscht...");
			request.getRequestDispatcher("ControlPanel.jsp").forward(request, response);

		} else if (submittype.equals("buecherzeigen")) {

			ArrayList<Buch> bucharr = KategorieImpl.getBuecherarr(request.getParameter("kategorie3"));

			out.print(KategorieImpl.showBuecherInkategorie(bucharr));

			// request.setAttribute("Buecher",
			// KategorieImpl.showBuecherInkategorie(bucharr));
			// request.getRequestDispatcher("ControlPanel.jsp").forward(request, response);

		} else if (submittype.equals("allebuecher")) {

			out.print(BuchImpl.getalleBuecher());

			// request.setAttribute("allebuecher", BuchImpl.getalleBuecher());
			// request.getRequestDispatcher("ControlPanel.jsp").forward(request, response);
		} else if (submittype.equals("kategorieliste2")) {

			String kat2 = request.getParameter("kategorie2");

			boolean exist = false;

			for (int i = 0; i < kategorien.size(); i++) {

				if (kat2.equals(kategorien.get(i))) {

					exist = true;

				}

			}

			if (!exist) {

				if (kat2 != null) {

					kategorien.add(kat2);

				}
			}

			out.print(KategorieImpl.kategorieList(kategorien));

		} else if (submittype.equals("kategorieloeschen")) {

			kategorien.clear();

			out.print(KategorieImpl.kategorieList(kategorien));
			
		}
	}

}
