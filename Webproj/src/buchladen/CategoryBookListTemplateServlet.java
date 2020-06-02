package buchladen;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

@WebServlet("/CategoryBookList")
public class CategoryBookListTemplateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CategoryBookListTemplateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TemplateEngine engine = thymeleaf.config.TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		WebContext context = new WebContext(request, response, request.getServletContext());

		HttpSession session = request.getSession();

		if (session.getAttribute("username") != null && session.getAttribute("userid") != null) {

			String username = (String) session.getAttribute("username");
			context.setVariable("username", username);

			int id = (int) session.getAttribute("userid");

			if (request.getParameter("submit") != null) {

				String submittype = request.getParameter("submit");
				
				if(submittype.equals("del")) {
					
					WarenKorbImpl.delWarenkorb(id);
				}
			}
			
			int Warenkorbcount = WarenKorbImpl.getCount(id);
			context.setVariable("count", Warenkorbcount);

		List<Kategorie> allCategories = KategorieImpl.getKategorieAll();

		if (allCategories != null) {
			context.setVariable("categoryList", allCategories);
		}

		Kategorie category = null;
		int categoryID = 0;
		String parameterCategoryID = request.getParameter("requestedCategoryID");
		if (parameterCategoryID == null) {
			context.setVariable("categoryName", "Bitte eine Kategorie auswaehlen");
		} else {

			List<Buch> books = null;
			Map<Integer, Autor> authors = null;

			try {
				categoryID = Integer.parseInt(parameterCategoryID);
			} catch (NumberFormatException e) {
				System.out.println(parameterCategoryID);
				categoryID = 0;
			}
			category = KategorieImpl.getKategorieByID(categoryID);

			if (category != null) {

				context.setVariable("pageTitle", category.getName());
				context.setVariable("categoryName", category.getName());

				books = JunctionBuchKategorie.getBuchByKategorieID(category.getCat_id());
				context.setVariable("bookList", books);

				authors = new HashMap<Integer, Autor>();
				for (Buch tmp : books) {
					authors.put(tmp.getAutor_id(), AutorImpl.getAutorByID(tmp.getAutor_id()));
				}
				context.setVariable("authorMap", authors);
			} else {
				context.setVariable("categoryName", "Bitte eine gültige Kategorie auswählen");
			}
		}

		engine.process("index.html", context, response.getWriter());
		
		}else {
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
