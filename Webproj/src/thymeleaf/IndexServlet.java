package thymeleaf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import thymeleaf.config.TemplateEngineUtil;

@WebServlet("/Thymeleaf")
public class IndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
       
        context.setVariable("recipient", "World");
        List<String> staedte = new ArrayList<>();
        staedte.add("Emden"); staedte.add("Leer"); staedte.add("Aurich");
        context.setVariable("staedte", staedte);
        context.setVariable("modul", request.getServletContext().getInitParameter("modul"));
        
        engine.process("index.html", context, response.getWriter());
    }
}
