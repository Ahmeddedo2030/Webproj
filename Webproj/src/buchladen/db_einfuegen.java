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
 * Servlet implementation class db_einfuegen
 */
@WebServlet("/db_einfuegen")
public class db_einfuegen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static ArrayList<String> kategorien = new ArrayList<String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public db_einfuegen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();

		String submittype = request.getParameter("submit");

		Buch b = new Buch();
		
		if(submittype.equals("kateinfugen")) {
			
			if(request.getParameter("kat_name") == "") {
				
		 request.setAttribute("katadd", "Kategoriename Feld wurde noch nicht eingegeben...");
		 request.getRequestDispatcher("Datenbank_einfuegen.jsp").forward(request, response);
				
			}else {
				
				Kategorie k = new Kategorie();
				
				k.setName(request.getParameter("kat_name"));
				KategorieImpl.insertKategorie(k);
				
				request.setAttribute("katadd",k.getName()+" Kategorie wurde erfolgreich eingefügt...");
				request.getRequestDispatcher("Datenbank_einfuegen.jsp").forward(request, response);
				
			}
			
		} else if (submittype.equals("verlageinfugen")) {
			
			if (request.getParameter("verlag_name") == "" || request.getParameter("verlag_adresse") == ""
					|| request.getParameter("verlag_kontakt") == "") {
				
				request.setAttribute("verlagadd", "Ein Feld wurde noch nicht eingegeben...");
				request.setAttribute("verlag_name", request.getParameter("verlag_name"));
				request.setAttribute("verlag_adresse", request.getParameter("verlag_adresse"));
				request.setAttribute("verlag_kontakt", request.getParameter("verlag_kontakt"));
				request.getRequestDispatcher("Datenbank_einfuegen.jsp").forward(request, response);
				
			}else {
				
				Verlag v = new Verlag();
				
				v.setVerlag_name( request.getParameter("verlag_name"));
				v.setVerlag_adresse(request.getParameter("verlag_adresse"));
				v.setVerlag_kontakt(request.getParameter("verlag_kontakt"));
				VerlagImpl.insertVerlag(v);
				
				request.setAttribute("verlagadd",v.getVerlag_name()+" Verlag wurde erfolgreich eingefügt...");
				request.getRequestDispatcher("Datenbank_einfuegen.jsp").forward(request, response);
				
			}
			
		} else if (submittype.equals("autoreinfugen")) {
			
			if (request.getParameter("autor_name") == "" || request.getParameter("autor_kontakt") == "") {
				
				request.setAttribute("autoradd", "Ein Feld wurde noch nicht eingegeben...");
				request.setAttribute("autor_name", request.getParameter("autor_name"));
				request.setAttribute("autor_kontakt", request.getParameter("autor_kontakt"));
				request.getRequestDispatcher("Datenbank_einfuegen.jsp").forward(request, response);
				
			}else {
				
				Autor a = new Autor();
				
				a.setAutor_name( request.getParameter("autor_name"));
				a.setAutor_kontakt(request.getParameter("autor_kontakt"));
						
				AutorImpl.insertAutor(a);
				
				request.setAttribute("autoradd",a.getAutor_name()+" Autor wurde erfolgreich eingefügt...");
				request.getRequestDispatcher("Datenbank_einfuegen.jsp").forward(request, response);
				
			}
			
		} else if (submittype.equals("bucheinfugen")) {

			if (request.getParameter("buch_titel") == "" || request.getParameter("buch_preis") == ""
					|| request.getParameter("buch_isbn") == "" || request.getParameter("buch_sprache") == ""
					|| request.getParameter("buch_bild") == "" || request.getParameter("buch_anzahl") == "") {

				request.setAttribute("buchadd", "Ein Feld wurde noch nicht eingegeben...");
				request.setAttribute("katliste", KategorieImpl.kategorieList(kategorien));
				request.setAttribute("kat", request.getParameter("buch_titel"));
				request.setAttribute("preis", request.getParameter("buch_preis"));
				request.setAttribute("isbn", request.getParameter("buch_isbn"));
				request.setAttribute("sprache", request.getParameter("buch_sprache"));
				request.setAttribute("bild", request.getParameter("buch_bild"));
				request.setAttribute("anzahl", request.getParameter("buch_anzahl"));
				request.getRequestDispatcher("Datenbank_einfuegen.jsp").forward(request, response);

			} else if (kategorien.size() == 0) {
				
				request.setAttribute("buchadd", "Keine Kategorie wurde ausgewählt...");
				request.setAttribute("katliste", KategorieImpl.kategorieList(kategorien));
				request.setAttribute("kat", request.getParameter("buch_titel"));
				request.setAttribute("preis", request.getParameter("buch_preis"));
				request.setAttribute("isbn", request.getParameter("buch_isbn"));
				request.setAttribute("sprache", request.getParameter("buch_sprache"));
				request.setAttribute("bild", request.getParameter("buch_bild"));
				request.setAttribute("anzahl", request.getParameter("buch_anzahl"));
				request.getRequestDispatcher("Datenbank_einfuegen.jsp").forward(request, response);
				
				
			} else if (!regex_funk.chekPreis(request.getParameter("buch_preis"))) {
				
				request.setAttribute("buchadd", "Sie haben ein ungültiges Preis eingegeben...");
				request.setAttribute("katliste", KategorieImpl.kategorieList(kategorien));
				request.setAttribute("kat", request.getParameter("buch_titel"));
				request.setAttribute("preis", request.getParameter("buch_preis"));
				request.setAttribute("isbn", request.getParameter("buch_isbn"));
				request.setAttribute("sprache", request.getParameter("buch_sprache"));
				request.setAttribute("bild", request.getParameter("buch_bild"));
				request.setAttribute("anzahl", request.getParameter("buch_anzahl"));
				request.getRequestDispatcher("Datenbank_einfuegen.jsp").forward(request, response);
				
			}else if (!regex_funk.chekPreis(request.getParameter("buch_anzahl"))) {
				
				request.setAttribute("buchadd", "Sie haben eine ungültige Anzahl eingegeben...");
				request.setAttribute("katliste", KategorieImpl.kategorieList(kategorien));
				request.setAttribute("kat", request.getParameter("buch_titel"));
				request.setAttribute("preis", request.getParameter("buch_preis"));
				request.setAttribute("isbn", request.getParameter("buch_isbn"));
				request.setAttribute("sprache", request.getParameter("buch_sprache"));
				request.setAttribute("bild", request.getParameter("buch_bild"));
				request.setAttribute("anzahl", request.getParameter("buch_anzahl"));
				request.getRequestDispatcher("Datenbank_einfuegen.jsp").forward(request, response);
				
			} else if (Integer.parseInt(request.getParameter("buch_preis")) > 500) {

				request.setAttribute("buchadd", "Das Preis des Buchs kann nicht über 500 Euro angelegt werden...");
				request.setAttribute("katliste", KategorieImpl.kategorieList(kategorien));
				request.setAttribute("kat", request.getParameter("buch_titel"));
				request.setAttribute("preis", request.getParameter("buch_preis"));
				request.setAttribute("isbn", request.getParameter("buch_isbn"));
				request.setAttribute("sprache", request.getParameter("buch_sprache"));
				request.setAttribute("bild", request.getParameter("buch_bild"));
				request.setAttribute("anzahl", request.getParameter("buch_anzahl"));
				request.getRequestDispatcher("Datenbank_einfuegen.jsp").forward(request, response);

			} else if (Integer.parseInt(request.getParameter("buch_preis")) < 5) {

				request.setAttribute("buchadd", "Das Preis des Buchs kann nicht unter 5 Euro angelegt werden...");
				request.setAttribute("katliste", KategorieImpl.kategorieList(kategorien));
				request.setAttribute("kat", request.getParameter("buch_titel"));
				request.setAttribute("preis", request.getParameter("buch_preis"));
				request.setAttribute("isbn", request.getParameter("buch_isbn"));
				request.setAttribute("sprache", request.getParameter("buch_sprache"));
				request.setAttribute("bild", request.getParameter("buch_bild"));
				request.setAttribute("anzahl", request.getParameter("buch_anzahl"));
				request.getRequestDispatcher("Datenbank_einfuegen.jsp").forward(request, response);

			} else {

				String titel = request.getParameter("buch_titel");
				int preis = Integer.parseInt(request.getParameter("buch_preis"));
				String isbn = request.getParameter("buch_isbn");
				String sprache = request.getParameter("buch_sprache");
				String bildpfad = request.getParameter("buch_bild");
				int anzahl = Integer.parseInt(request.getParameter("buch_anzahl"));
				String verlag = request.getParameter("verlag1");
				String autor = request.getParameter("autor1");
				String lager = request.getParameter("lager1");

				b.setTitel(titel);
				b.setPreis(preis);
				b.setISBN(isbn);
				b.setSprache(sprache);
				b.setBildpfad(bildpfad);
				b.setAnzahl(anzahl);
				b.setVerlag_id(VerlagImpl.getVerlagid(verlag));
				b.setAutor_id(AutorImpl.getAutorid(autor));
				b.setLager_id(BuchImpl.getLagerid(lager));

				BuchImpl.insertBuch(b, kategorien);
				kategorien.clear();

				request.setAttribute("buchadd", "Ein Buch wurde Erfolgreich eingefügt...");
				request.getRequestDispatcher("Datenbank_einfuegen.jsp").forward(request, response);

			}
		} else if (submittype.equals("kategorieliste")) {

			String kat1 = request.getParameter("kategorie1");

			boolean exist = false;

			for (int i = 0; i < kategorien.size(); i++) {

				if (kat1.equals(kategorien.get(i))) {

					exist = true;

				}

			}

			if (!exist) {

				if (kat1 != null) {

					kategorien.add(kat1);

				}
			}

			out.print(KategorieImpl.kategorieList(kategorien));

		} else if (submittype.equals("kategorieloeschen")) {

			kategorien.clear();

			out.print(KategorieImpl.kategorieList(kategorien));

		}
	}
}
