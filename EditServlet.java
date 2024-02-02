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


public class EditServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  Connection con;
  PreparedStatement st;
  ResultSet rs;
    
    public EditServlet() {
        super();
       
    }
    
    boolean add(int id,String name,String email,int pass,String birth,String gender)
  {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      System.out.println("Driver Connection OK!");
      con=DriverManager.getConnection("jdbc:mysql://localhost:3306/job","root","moe142");
      System.out.println("Connection with Database");
       String s="insert into user values(?,?,?,?,?,?,?)";
       PreparedStatement smt=con.prepareStatement(s);
       smt.setInt(1,id);
       smt.setString(2,name);
       smt.setString(3,email);
       smt.setInt(4,pass);
       smt.setString(5,birth);
       smt.setString(6,gender);
       smt.setString(7, "null");
       
       smt.executeUpdate();
       //System.out.println("Insert is Successfully");
       return true;
    }
    catch(Exception e)
    {
      System.out.print(e);
    }
    return false;
  
  // TODO Auto-generated method stub
  //response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out=response.getWriter();
    response.setContentType("text/html");
    int id=Integer.parseInt(request.getParameter("id"));
    String name=request.getParameter("name");
    String email=request.getParameter("email");
    int pass=Integer.parseInt(request.getParameter("pass"));
    String birth=request.getParameter("bd");
    String gender=request.getParameter("gd");
    if(request.getParameter("submit").equals("Add"))
    {
      boolean flag=add(id,name,email,pass,birth,gender);
      if(flag) {
        System.out.println("user inserted!");
        request.getRequestDispatcher("usemanagement.jsp").forward(request, response);
      }
    }
    
    else if(request.getParameter("submit").equals("Display"))
    {
      try{
          Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver Connection OK!");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/job","root","moe142");
        System.out.println("Connection with Database");
        String sql="select*from user";
        PreparedStatement stmt=con.prepareStatement(sql);
        ResultSet rs=stmt.executeQuery();
        if(rs.next()==false)
        {
          out.println("No record found in the table");
        }
        else{
          out.println(" <table class='table user-list'>"+
          "<thead><tr>"+
                  
                 "<th><span>ID</span></th>"+
                 " <th><span>Name</span></th>"+
                 " <th><span>Email</span></th>"+
                 "<th><span>Password</span>"+
                  "<th><span>Birthday</span></th>"+
                  "<th><span>Gender</span></th>"+
                  "<th><span>Profile</span></th>"+
               " </tr> </thead>");
              
              do {
              out.println(" <tbody><tr>");
              out.println("<td>"+rs.getString(1)+"</td>");
              out.println("<td>"+rs.getString(2)+"</td>");
              out.println("<td>"+rs.getString(3)+"</td>");
              out.println("<td>"+rs.getString(4)+"</td>");
              out.println("<td>"+rs.getString(5)+"</td>");
              out.println("<td>"+rs.getString(6)+"</td>");
              
              out.println("<td style=width:20%;>");
                    
              out.println("<a href='Edit.jsp' class='table-link'>"+"  <span class='fa-stack'>"+
                      "   <i class='fa fa-square fa-stack-2x'></i>"+
                      "  <i class='fa fa-pencil fa-stack-1x fa-inverse'></i>"+
                       "</span>"+
                    " </a>"+
                   " <a href='Delete.jsp' class='table-link danger'>"+
                     "  <span class='fa-stack'>"+
                       "<i class='fa fa-square fa-stack-2x'></i>"+
                       "  <i class='fa fa-trash-o fa-stack-1x fa-inverse'></i>"+
                     "  </span>"+
                   "  </a>"+
                   "</td>"+
                  
                  "</tr>"+
                 " </tbody>");
                  
               
               }while(rs.next());
         
               
          
         }
       }
         
         catch(Exception e) {
         e.getStackTrace();
       } 
         }
       }
  
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     // TODO Auto-generated method stub
     doGet(request, response);
   }

 }