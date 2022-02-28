package com.travel;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Customerinfo")
public class Customerinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Customerinfo() {
        super();
       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String id = request.getParameter("uname");
		String pass = request.getParameter("password");
		if(id.equals("abc")&&pass.equals("12345"))
		{
		 try 
		    {
		  Class.forName("com.mysql.cj.jdbc.Driver");                
		  
		  Connection con = DriverManager.getConnection 
		  ("jdbc:mysql://localhost:3306/travel","root","12345");    
		  Statement stmt = con.createStatement();                   
		  ResultSet rs = stmt.executeQuery("select * from customerinfo");   
		
		 out.println("<br>details: <br>");
		 out.println();
		  while(rs.next())
		  {
			  String name = rs.getString("username");  
		      String email = rs.getString("email");  
		      String contact = rs.getString("contact");
		      String city = rs.getString("city");

		      out.println("<tr><td>" + name + "&nbsp&nbsp&nbsp&nbsp</td><td>" + email + "&nbsp&nbsp&nbsp&nbsp</td><td>" + contact + "&nbsp&nbsp&nbsp&nbsp</td><td>" + city + "</td></tr>"); 
		      out.println("<br>end ..<br>");
		  }  
		   
		  out.println("</html></body>");  
		  con.close();  
		    }catch(Exception e)
		 {
		    	
		 }
		} 
		 else
		 {
			 
			 out.println("Invalid ID or Password");
		 }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
