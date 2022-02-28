package com.travel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Booknow")
public class Booknow extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Booknow()
    {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");  
        PrintWriter pw = response.getWriter();  
        String connectionURL = "jdbc:mysql://localhost:3306/travel"; 
        Connection connection;  
        
        try
        {  
          String Destination = request.getParameter("destination");
          String Check_in = request.getParameter("check-in");
          String Check_out = request.getParameter("check-out");
          String Guests = request.getParameter("guests");
          int cout = Integer.parseInt(Check_out.substring(0,2));
          int cin = Integer.parseInt(Check_in.substring(0,2));
          int days = cout-cin;
          int Guest = Integer.parseInt(Guests);  
          Class.forName("com.mysql.cj.jdbc.Driver");  
          connection = DriverManager.getConnection(connectionURL, "root", "12345");  
          PreparedStatement pst = connection.prepareStatement("insert into reservations values(?,?,?,?,?)");
          
          Statement stmt = connection.createStatement();   
		  ResultSet rs = stmt.executeQuery("select * from packages");
		  double price = 0;
		  while(rs.next())
		  {
			  if (rs.getString("destination").equalsIgnoreCase(Destination))
			  price = rs.getDouble("price");
		  }
		  
		  double totalcost = days*Guest*price;
		  
          pst.setString(1,Destination);
          pst.setString(2,Check_in); 
          pst.setString(3,Check_out); 
          pst.setInt(4,Guest);  
          pst.setDouble(5,totalcost);
          
          
          int i = pst.executeUpdate();  
          if(i!=0)
          {  
            pw.println("<br>Record has been inserted");  
            out.println("<br><br>");
            out.println("<tr><td>" + Destination + "&nbsp&nbsp&nbsp&nbsp</td><td>" + Check_in + "&nbsp&nbsp&nbsp&nbsp</td><td>" + Check_out + "&nbsp&nbsp&nbsp&nbsp</td><td>" + Guests  + "&nbsp&nbsp&nbsp&nbsp</td><td>" + totalcost +"</td></tr>"); 
		    out.println("<br><br>");
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


