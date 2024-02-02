package login_register;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
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
import java.sql.SQLException;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String type;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/job","root","moe142");
				String email=request.getParameter("email");
				String password=request.getParameter("password");
				PreparedStatement ps=con.prepareStatement("select `email` , `password` ,`type` from `user` where `email`='"+email+"' and `password`='"+password+"'");
			//	ps.setString(1,email);
			//ps.setString(2,password);
				ResultSet rs=ps.executeQuery();
				
				if(rs.next())
				{	
				  type=rs.getString("type");
				
				 
				 HttpSession user=request.getSession();
				 user.setAttribute("type",type);
				 user.setAttribute("user", email);
				 
			//	out.println("<html><body><script>alert('Login complete..')</script></body></html>");
					RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
					rd.forward(request, response);
					 
				}
				else {
					
					
		                
					
					request.getRequestDispatcher("login1.jsp").include(request, response);
					out.println("<html><body><script>alert('Sorry username or password error')</script></body></html>");
					System.out.print("Hey..");
					//out.println("<font color=red size=18>Login Failed!!<br><a href=login.jsp>Try Again!!</a>");
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}


