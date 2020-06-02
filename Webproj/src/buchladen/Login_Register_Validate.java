package buchladen;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login_Register_Validate
 */
@WebServlet("/Login_Register_Validate")
public class Login_Register_Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login_Register_Validate() {
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
		
		Customerimpl cimpl = new Customerimpl();
		 
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String submittype = request.getParameter("submit");
		Customer c = cimpl.getCustomer(username, password);
		
    if(submittype.equals("login") && c != null && c.getUsername() != null) {
    	
    	HttpSession session = request.getSession();
    	session.setAttribute("userid", c.getC_id());
    	session.setAttribute("username", c.getUsername());
			
			//request.setAttribute("message", c.getF_name() +' '+ c.getL_name());
			request.getRequestDispatcher("CategoryBookList").forward(request, response);
			
      }else if(submittype.equals("registeration")){
    	  
    	  request.setAttribute("name", request.getParameter("name"));
    	  request.setAttribute("nachname", request.getParameter("nachname"));
    	  request.setAttribute("username", username);
    	  request.setAttribute("password", password);
    	  request.setAttribute("date", request.getParameter("date"));
    	  request.setAttribute("email", request.getParameter("email"));
    	  request.setAttribute("Geschlecht", request.getParameter("Geschlecht"));
    	  request.setAttribute("Ort", request.getParameter("Ort"));
    	  request.setAttribute("PLZ", request.getParameter("PLZ"));
    	  request.setAttribute("Strasse", request.getParameter("Strasse"));
    	  
    	  if(request.getParameter("name") == "" || request.getParameter("nachname") == "" ||
    		 username == "" || password == "" || request.getParameter("date") == "" ||
    		 request.getParameter("email") == "" || request.getParameter("Geschlecht") == "" ||
    		 request.getParameter("Ort") == "" || request.getParameter("PLZ") == "" ||
    		 request.getParameter("Strasse") == "" || request.getParameter("Beruf") == "") {
    		  
    		request.setAttribute("message","Ein Feld wurde noch nicht eingegeben...");
  			request.getRequestDispatcher("register.jsp").forward(request, response);
    		  
    	  }else {
    	  
    	  Customer c2 =  new Customer();
		    
    	   c2.setF_name(request.getParameter("name"));
    	   c2.setL_name(request.getParameter("nachname"));
    	   c2.setUsername(username);
    	   c2.setPassword(password);
    	   c2.setB_date(request.getParameter("date"));
    	   c2.setEmail(request.getParameter("email"));
    	   c2.setGender(request.getParameter("Geschlecht"));
    	   c2.setOrt(request.getParameter("Ort"));
    	   c2.setPLZ(request.getParameter("PLZ"));
    	   c2.setStrasse(request.getParameter("Strasse"));
    	   c2.setBeruf(request.getParameter("Beruf"));
    	  
			cimpl.insertCustomer(c2);
			request.setAttribute("message",c2.getF_name()+' '+c2.getL_name()+"  You have successfully created your Account.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
    	  }
		
	}else {
		
		request.setAttribute("username", username);
		request.setAttribute("password", password);
    	
    	request.setAttribute("message", "Ihr Benutzer Name oder Passwort sind unkorrekt bitte versuchen Sie nocheinmal.");
		request.getRequestDispatcher("login.jsp").forward(request, response);
    	
    }
		
	}

}
