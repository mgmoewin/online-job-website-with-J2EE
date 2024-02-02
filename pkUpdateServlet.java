package login_register;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class pkUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  Connection con;
  PreparedStatement st; 
   
    public pkUpdateServlet() {
        super();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      PrintWriter out=response.getWriter();
      response.setContentType("text/html");
      String oldEmail=request.getParameter("oldEmail");
      String email=request.getParameter("email");
      String code=request.getParameter("code");
      int time=Integer.parseInt(request.getParameter("time"));
      String status=request.getParameter("status");
      try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver Connection OK!");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/job","root","moe142");
        System.out.println("Connection with Database");
        String  s="update package set email='"+email+"',code='"+code+"',time='"+time+"',status='"+status+"' where email='"+oldEmail+"'";
        st=con.prepareStatement(s);
        
        st.executeUpdate();
        out.println("update succsssfully!!!");
        con.close();
      }
      catch(Exception e) {
        System.out.println(e);
      }
      response.sendRedirect("package.jsp");
      }
    //response.getWriter().append("Served at: ").append(request.getContextPath());
  

  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    doGet(request, response);
  }

}