package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import model.CustomerBean;
import model.CustomerService;
import model.ProductBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");   
		String password = request.getParameter("password") ;
      
		Map<String,String> errors = new HashMap<String,String>();
		request.setAttribute("errors", errors);
        
		if(username==null||username.length()==0){
			errors.put("id", "輸入ID以便執行");
		}
	
		
	
		if(password==null||password.length()==0){
			errors.put("pwd", "輸入密碼以便執行");
		}
		
		

		if(errors!=null&&!errors.isEmpty()){
			RequestDispatcher rd = request.getRequestDispatcher("/secure/login.jsp");
			rd.forward(request, response);
			return;
		
		}
		
		CustomerService customer = new CustomerService();
		
		CustomerBean bean = customer.login(username, password);
		
		if(bean==null){
			errors.put("password","登入失敗,請再輸入一次");
			request.getRequestDispatcher("/secure/login.jsp").forward(request,response);
			
		}else{
			HttpSession session = request.getSession(); 
			session.setAttribute("user", bean);
			
			String path = request.getContextPath();
			response.sendRedirect(path+"/index.jsp");
		}
		System.out.print("aaaa");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
