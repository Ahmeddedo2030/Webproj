package buchladen;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;


@WebServlet("/BookDetail")
public class DetailPageTemplateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    

    public DetailPageTemplateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TemplateEngine engine = thymeleaf.config.TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		WebContext context = new WebContext(request,response, request.getServletContext());
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		context.setVariable("username", username);
		
		int id = (int) session.getAttribute("userid");
		int Warenkorbcount = WarenKorbImpl.getCount(id);
		context.setVariable("count", Warenkorbcount);
		
		List<Kategorie> allCategories = KategorieImpl.getKategorieAll();
		
		if(allCategories!=null) {
			
			context.setVariable("categoryList", allCategories);
		}
		
		Buch book = null;
		int bookID = 0;
		String parameterBookID = request.getParameter("requestedBookID");
		try {
			bookID = Integer.parseInt(parameterBookID);
		}catch (NumberFormatException e) {
			System.out.println(parameterBookID);
			bookID = 0;
		}
		if(bookID != 0) {
			book = BuchImpl.getBuchByID(bookID);
		}

		if(book!=null) {
			context.setVariable("pageTitle", book.getTitel());
			context.setVariable("bookID", book.getBook_id());
			context.setVariable("bookTitle", book.getTitel());
			context.setVariable("bookPrice", book.getPreis());
			context.setVariable("bookImgPth", book.getBildpfad());
			context.setVariable("bookLang", book.getSprache());
			context.setVariable("bookISBN", book.getISBN());
			context.setVariable("bookDescription","Platzhalter fuer den Beschreibungstext.");
			
			Autor author = AutorImpl.getAutorByID(book.getAutor_id());
			if(author != null) {
				context.setVariable("authorName", author.getAutor_name());
			}
			
			Verlag publisher = VerlagImpl.getVerlagByID(book.getVerlag_id());
			if(publisher != null) {
				context.setVariable("verlagName", publisher.getVerlag_name());
			}
			
			List<Kategorie> categories = JunctionBuchKategorie.getKategorieByBuchID(book.getBook_id());
			if(categories != null) {
				context.setVariable("bookCategories", categories);
			}
		}
		
		engine.process("Details.html", context, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
