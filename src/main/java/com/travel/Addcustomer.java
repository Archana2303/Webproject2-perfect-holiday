package com.travel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Addcustomer")
public class Addcustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Addcustomer()
    {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		response.setContentType("text/html");  
        PrintWriter pw = response.getWriter();  
        String connectionURL = "jdbc:mysql://localhost:3306/travel"; 
        Connection connection;  
        
        try
        {  
          //System.out.print(request.getParameterMap());
          String Username = request.getParameter("username");
          String Email = request.getParameter("email");
          String Contact = request.getParameter("contact");
          String City = request.getParameter("city");
          String Password= request.getParameter("password");  
     
          Class.forName("com.mysql.cj.jdbc.Driver");  
          connection = DriverManager.getConnection(connectionURL, "root", "12345");  
          PreparedStatement pst = connection.prepareStatement("insert into customerinfo values(?,?,?,?,?)");
          pst.setString(1,Username);
          pst.setString(2,Email); 
          pst.setString(3,Contact); 
          pst.setString(4,City); 
          pst.setString(5,Password);        
          
          int i = pst.executeUpdate();  
          if(i!=0)
          {  
            pw.println("<br>Record has been inserted");  
          }  
          else
          {  
            pw.println("failed to insert the data");  
           }  
        }  
        catch (Exception e)
        {  
          pw.println(e);  
        }  
      }  
     

	}

