package login_register;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  Connection con;
  PreparedStatement st,st1,st2;
    
    public DeleteServlet() {
        super();
        
    }

  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out=response.getWriter();
    response.setContentType("text/html");
    
    
    
    
    
    HttpSession session=request.getSession();
    String name=(String)session.getAttribute("delete");
    int job=(Integer)session.getAttribute("job");
    String pk=(String)session.getAttribute("pk");
    
    System.out.print(name);
    System.out.print(job);
    System.out.print(pk);
    
    
    
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      System.out.println("Driver Connection OK!");
      con=DriverManager.getConnection("jdbc:mysql://localhost:3306/job","root","moe142");
      System.out.println("Connection with Database");
      if(name!=null) {
    st=con.prepareStatement("delete from `user` where `name` ='"+name+"'" );
    st.executeUpdate();
    response.sendRedirect("usemanagement.jsp");
      }
      else if(job!=0)
      {
        st1=con.prepareStatement("delete from `job` where `job_id` ='"+job+"'" );
        st2=con.prepareStatement("delete from `company` where `com_id` ='"+job+"'" );
        st1.executeUpdate();
        st2.executeUpdate();
        response.sendRedirect("jopManagement.jsp");
      }
      else if(pk!=null)
      {
        st=con.prepareStatement("delete from `package` where `email` ='"+pk+"'" );
        st.executeUpdate();
        response.sendRedirect("package.jsp");
      }
      
    
      
    }
    catch(Exception e) {
      System.out.println(e);
    }
    
  
    
    // TODO Auto-generated method stub
    //response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    doGet(request, response);
  }

}