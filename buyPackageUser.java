package login_register;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class buyPackageUser extends HttpServlet {
	
	Connection con;
	PreparedStatement ps;
	String result;
	private static final long serialVersionUID = 1L;
       
    
    public buyPackageUser() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		
		HttpSession session=request.getSession();
		String user = (String) session.getAttribute("user");
		String code=user+"002";
		int time=Integer.parseInt(request.getParameter("time"));
		
//		session.setAttribute("userTime",time);
//		session.setAttribute("final",user);
		
      

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver connection okay!");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/job","root","moe142");
		    System.out.println("Connect..");
		    
		    String checkEmailSql = "SELECT COUNT(*) FROM `package` WHERE `email`='"+user+"'";
		    ps=con.prepareStatement(checkEmailSql);
		    ResultSet resultSet = ps.executeQuery();
	          resultSet.next(); // Move the cursor to the first row
	          int idCount = resultSet.getShort(1);

	          if (idCount > 0) {
	              result = "Account already exists";
	              
	              
	          }
	          else
	          {
	        	  result = "Account not exists";  
	          }
	          if (result.equals("Account already exists")) {
	        	  
	        	  session.setAttribute("user","exist");
	               response.sendRedirect("buyPackageUser.jsp");
	        		// Email already exists, display an alert box
//	        	    out.println("<script type='text/javascript'>");
//	        	    out.println("alert('Accountl already exists. Account choose a different email.');");
//	        	    out.println("window.location.href = 'buyPackageUser.jsp';"); // Redirect to registration page
//	        	    out.println("</script>");
	        	} 
	          else {
		    
		    String insertSql = "INSERT INTO `package` (`email`, `code`, `time`) VALUES (?, ?, ?)";
             ps = con.prepareStatement(insertSql);
            
          
            ps.setString(1,user);
            ps.setString(2,code);
            ps.setInt(3,time);
           
            
            ps.executeUpdate();
            out.println("<script type='text/javascript'>");
    	    out.println("alert('Buying package complete..');");
    	    out.println("window.location.href = 'buyPackageUser.jsp';"); // Redirect to registration page
    	    out.println("</script>");
	        	}
            
		} 	
		catch (Exception e) {
			System.out.print(e);
		}
		
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
