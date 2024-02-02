package login_register;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PreparedStatement ps;
    Connection con;
    String result;
    

    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String email=request.getParameter("email");
		String name=request.getParameter("name");
		String cpw=request.getParameter("cpw");
		String rdo=request.getParameter("rdo");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver connection okay!");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/job","root","moe142");
		    System.out.println("Connect..");
		    
		    String checkEmailSql = "SELECT COUNT(*) FROM user WHERE `email`='"+email+"'";
		    ps=con.prepareStatement(checkEmailSql);
		    ResultSet resultSet = ps.executeQuery();
	          resultSet.next(); // Move the cursor to the first row
	          int emailCount = resultSet.getInt(1);

	          if (emailCount > 0) {
	              result = "Email already exists";
	              
	              
	          }
	          else
	          {
	        	  result = "Email not exists";  
	          }
	          if (result.equals("Email already exists")) {
	        	  
	        		// Email already exists, display an alert box
	        	    out.println("<script type='text/javascript'>");
	        	    out.println("alert('Email already exists. Please choose a different email.');");
	        	    out.println("window.location.href = 'login1.jsp';"); // Redirect to registration page
	        	    out.println("</script>");
	        	} 
	          else {
		    
		    String insertSql = "INSERT INTO `user` (`name`, `email`, `password`,`type`) VALUES (?, ?, ?, ?)";
             ps = con.prepareStatement(insertSql);
            
          
            ps.setString(1, name);
            ps.setString(2,email);
            ps.setString(3,cpw);
            ps.setString(4,rdo);
            
            ps.executeUpdate();
            out.println("<script type='text/javascript'>");
    	    out.println("alert('Register complete..');");
    	    out.println("window.location.href = 'login1.jsp';"); // Redirect to registration page
    	    out.println("</script>");
	        	}
            
		} 	
		catch (Exception e) {
			System.out.print(e);
		}
		}
		
		
	    
	    
	  }
	
	

