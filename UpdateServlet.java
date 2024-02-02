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


public class UpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  Connection con;
  PreparedStatement st; 
   
    public UpdateServlet() {
        super();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      PrintWriter out=response.getWriter();
      response.setContentType("text/html");
      
      String oldem=request.getParameter("oldem");
      String name=request.getParameter("name");
      String em=request.getParameter("email");
      String pass=request.getParameter("pass");
      
      
          try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver Connection OK!");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/job","root","moe142");
        System.out.println("Connection with Database");
        String  s="update user set name='"+name+"',email='"+em+"',password='"+pass+"' where email='"+oldem+"'";
        st=con.prepareStatement(s);
        
        st.executeUpdate();
        out.println("update succsssfully!!!");
        con.close();
      }
      catch(Exception e) {
        System.out.println(e);
      }
          response.sendRedirect("usemanagement.jsp");
      }
    //response.getWriter().append("Served at: ").append(request.getContextPath());
  

  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    doGet(request, response);
  }

}