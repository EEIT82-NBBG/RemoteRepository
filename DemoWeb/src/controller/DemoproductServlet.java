package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductService;


@WebServlet("/pages/product.controller")
public class DemoproductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ProductService productService= new ProductService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//接收資料
		String temp1 = request.getParameter("id");   
		String name  = request.getParameter("name") ;
        String temp2 = request.getParameter("price");   
        String temp3 = request.getParameter("make");   
        String temp4 = request.getParameter("expire") ;  
        String prodaction= request.getParameter("prodaction");   
//驗證資料
        
		Map<String,String> errors = new HashMap<String,String>();
		request.setAttribute("errors", errors);
		
		
		if("Insert".equals(prodaction)||"Update".equals(prodaction)||"Delete".equals(prodaction)){
			if(temp1==null||temp1.length()==0){
				errors.put("id", "輸入ID以便執行"+prodaction);
			}
		}
//轉換資料
		
		
		int id = 0;
		if(temp1!=null&&temp1.length()!=0){
		    id=ProductBean.convertInt(temp1);
			if(id==-1000){
				errors.put("id", "Id必須為整數");
				
			}
		}
		
		

		double price = 0;
		if(temp2!=null&&temp2.length()!=0){
			price=ProductBean.convertDouble(temp2);
			if(price==-1000){
				errors.put("price", "price必須為數字");
				
			}
		}
		
		java.util.Date make=null;
		if(temp3!=null&&temp3.length()!=0){
			make=ProductBean.convertDate(temp3);
			
			if(make.equals(new java.util.Date(0))){
				errors.put("make","make必須是日期必且擁有yyyy-MM-dd的格式");
				
			}
		}
		
		
		int expire = 0;
		if(temp4!=null&&temp4.length()!=0){
			try {	expire = Integer.parseInt(temp4);
				
				} catch (Exception e) {
					e.printStackTrace();
					errors.put("expire", "expire必須為數字");
				}
				
			}
	

		if(errors!=null&&!errors.isEmpty()){
			RequestDispatcher rd = request.getRequestDispatcher("/pages/product.jsp");
			rd.forward(request, response);
			return;
		}
		
//呼叫model		
		
		ProductBean bean = new ProductBean();
		bean.setId(id);
		bean.setName(name);
		bean.setPrice(price);
		bean.setMake(make);
        bean.setExpire(expire);		
        
//根據Model執行結果，呼叫View	
        
        if("Select".equals(prodaction)){
        List<ProductBean> result= productService.select(bean);
        
		request.setAttribute("select", result);
		request.getRequestDispatcher("/pages/display.jsp").
		forward(request, response);
		
	   }else if("Insert".equals(prodaction)){
		 ProductBean result = productService.insert(bean);
		 request.getRequestDispatcher("/pages/display.jsp").
			forward(request, response);
		 
	   }else if("Update".equals(prodaction)) {
			ProductBean result = productService.update(bean);
			request.getRequestDispatcher("/pages/display.jsp").
			forward(request, response);
			
			
	   } else if("Delete".equals(prodaction)) {
			boolean result = productService.delete(bean);
			request.getRequestDispatcher("/pages/display.jsp").
			forward(request, response);
		}
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  this.doGet(request, response);
        
	}

}
