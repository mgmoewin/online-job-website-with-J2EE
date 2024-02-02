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


public class jobUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  Connection con;
  PreparedStatement st; 
   
    public jobUpdateServlet() {
        super();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      PrintWriter out=response.getWriter();
      response.setContentType("text/html");
      
      int id=Integer.parseInt(request.getParameter("id"));
      String name=request.getParameter("name");
      String type=request.getParameter("type");
      int sal=Integer.parseInt(request.getParameter("sal"));
      String position=request.getParameter("position");
      String deg=request.getParameter("deg");
      int age=Integer.parseInt(request.getParameter("age"));
      int qty=Integer.parseInt(request.getParameter("qty"));
      String gender=request.getParameter("gender");
      String add=request.getParameter("add");
      int exp=Integer.parseInt(request.getParameter("exp"));
      
      
      try {
          Class.forName("com.mysql.cj.jdbc.Driver"); // Note: Updated driver class name for MySQL Connector/J
          System.out.println("Driver Connection OK!");
          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/job", "root", "moe142");
          System.out.println("Connection with Database");
          
          // Use parameterized query with placeholders
          String s = "UPDATE job SET job_name=?, job_type=?, salary=?, position=?, deg=?, qty=?, age=?, gender=?, exp=?, `add`=? WHERE job_id=?";
          st = con.prepareStatement(s);
          
          // Set values for placeholders
          st.setString(1, name);
          st.setString(2, type);
          st.setInt(3, sal);
          st.setString(4, position);
          st.setString(5, deg);
          st.setInt(6, qty);
          st.setInt(7, age);
          st.setString(8, gender);
          st.setInt(9, exp);
          st.setString(10, add);
          st.setInt(11, id);
          
          st.executeUpdate();
          out.println("Update successful!");
          System.out.print("HI");
          con.close();
      } catch (Exception e) {
          System.out.println(e);
      }
      
          response.sendRedirect("jopManagement.jsp");
      }
    //response.getWriter().append("Served at: ").append(request.getContextPath());
  

  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    doGet(request, response);
  }

}