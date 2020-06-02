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

@WebServlet("/warenkorb")

public class WarenKorbServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public WarenKorbServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		TemplateEngine engine = thymeleaf.config.TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		WebContext context = new WebContext(request, response, request.getServletContext());
		HttpSession session = request.getSession();
		
		if(request.getParameter("submit") != null) {

		String submit = request.getParameter("submit");

		if (submit.equals("details")) {

			int id = (int) session.getAttribute("userid");
			int bookid = Integer.parseInt(request.getParameter("addedBookID"));
			String bookitle = request.getParameter("booktitle");
			int bookprice = Integer.parseInt(request.getParameter("bookpreis"));
			String bookimg = request.getParameter("bookimg");

			if (WarenKorbImpl.chekExistedBookid(bookid, id)) {

				WarenKorb wk = new WarenKorb();

				wk.setCustomerid(id);
				wk.setBookid(bookid);
				wk.setTitle(bookitle);
				wk.setPrice(bookprice);
				wk.setImagepath(bookimg);
				wk.setCount(1);
				wk.setTotalpreis(bookprice);

				WarenKorbImpl.insertWarenkorb(wk);

			}
			
			
			List<WarenKorb> arr = WarenKorbImpl.getWarenkorb(id);
			context.setVariable("warenkorb", arr);
			
			String username = (String) session.getAttribute("username");
			context.setVariable("username", username);
			int Warenkorbcount = WarenKorbImpl.getCount(id);
			context.setVariable("count", Warenkorbcount);
			
			context.setVariable("total","Total : "+WarenKorbImpl.getTotalpreisAllitems(id)+" $");
			
			engine.process("Warenkorb.html", context, response.getWriter());

		}else if(submit.equals("del")) {
			
			int bookid = Integer.parseInt(request.getParameter("bookid"));
			String username = (String) session.getAttribute("username");
			context.setVariable("username", username);
			int id = (int) session.getAttribute("userid");
			
			WarenKorbImpl.delSelectedItem(id, bookid);
			
			List<WarenKorb> arr = WarenKorbImpl.getWarenkorb(id);
			context.setVariable("warenkorb", arr);
			int Warenkorbcount = WarenKorbImpl.getCount(id);
			context.setVariable("count", Warenkorbcount);
			
			context.setVariable("total","Total : "+WarenKorbImpl.getTotalpreisAllitems(id)+" $");
			
			engine.process("Warenkorb.html", context, response.getWriter());
		
		}else if(submit.equals("add")) {
			
			int bookid = Integer.parseInt(request.getParameter("bookid"));
			String username = (String) session.getAttribute("username");
			context.setVariable("username", username);
			int id = (int) session.getAttribute("userid");
			
			WarenKorbImpl.increSelectedItem(id, bookid);
			WarenKorbImpl.setTotalpreis(id, bookid);
			
			List<WarenKorb> arr = WarenKorbImpl.getWarenkorb(id);
			context.setVariable("warenkorb", arr);
			int Warenkorbcount = WarenKorbImpl.getCount(id);
			context.setVariable("count", Warenkorbcount);
			
			context.setVariable("total","Total : "+WarenKorbImpl.getTotalpreisAllitems(id)+" $");
			
			engine.process("Warenkorb.html", context, response.getWriter());
			
		}else if(submit.equals("sub")) {
			
			int bookid = Integer.parseInt(request.getParameter("bookid"));
			String username = (String) session.getAttribute("username");
			context.setVariable("username", username);
			int id = (int) session.getAttribute("userid");
			
			WarenKorbImpl.decreSelectedItem(id, bookid);
			WarenKorbImpl.setTotalpreis(id, bookid);
			
			List<WarenKorb> arr = WarenKorbImpl.getWarenkorb(id);
			context.setVariable("warenkorb", arr);
			int Warenkorbcount = WarenKorbImpl.getCount(id);
			context.setVariable("count", Warenkorbcount);
			
			context.setVariable("total","Total : "+WarenKorbImpl.getTotalpreisAllitems(id)+" $");
			
			engine.process("Warenkorb.html", context, response.getWriter());
			
		}else if(submit.equals("warenkorb")) {
			
			String username = (String) session.getAttribute("username");
			context.setVariable("username", username);
			int id = (int) session.getAttribute("userid");
			
			List<WarenKorb> arr = WarenKorbImpl.getWarenkorb(id);
			context.setVariable("warenkorb", arr);
			int Warenkorbcount = WarenKorbImpl.getCount(id);
			context.setVariable("count", Warenkorbcount);
			
			context.setVariable("total","Total : "+WarenKorbImpl.getTotalpreisAllitems(id)+" $");
			
			engine.process("Warenkorb.html", context, response.getWriter());
			
		}
		
		}else {
			
			if(session.getAttribute("username") != null && session.getAttribute("userid") != null) {
		
			String username = (String) session.getAttribute("username");
			context.setVariable("username", username);
			int id = (int) session.getAttribute("userid");
			int Warenkorbcount = WarenKorbImpl.getCount(id);
			context.setVariable("count", Warenkorbcount);
			
			List<WarenKorb> arr = WarenKorbImpl.getWarenkorb(id);
			context.setVariable("warenkorb", arr);
			
			context.setVariable("total","Total : "+WarenKorbImpl.getTotalpreisAllitems(id)+" $");
			
			engine.process("Warenkorb.html", context, response.getWriter());
			
			}else {
				
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}
			
		}

	}
}
