package login_register;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
    )

public class companyServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  Connection con;
  PreparedStatement ps; 
    
    public companyServlet() {
        super();
      
    }

  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
  //  response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out=response.getWriter();
    
    String com_nm=request.getParameter("com-name");
    String add=request.getParameter("add");
    String ph=request.getParameter("ph");
    String job_nm=request.getParameter("job-name");
    String job_ty=request.getParameter("job-type");
    int sal=Integer.parseInt(request.getParameter("sal"));
    String position=request.getParameter("position");
    String deg=request.getParameter("degree");
    int age=Integer.parseInt(request.getParameter("age"));
    int qty=Integer.parseInt(request.getParameter("qty"));
    String email=request.getParameter("email");
    String gender=request.getParameter("gender");
    String web=request.getParameter("web");
    Part image=request.getPart("image");
    
    String path="C:\\Users\\95988\\OneDrive - University of Computer Studies (Thaton)\\Documents\\Desktop\\one\\Online_Job_Portal\\src\\main\\webapp\\image\\"+image.getSubmittedFileName();
    image.write(path);
    
    int exp=Integer.parseInt(request.getParameter("exp"));
    if(request.getParameter("submit").equals("Post"))
    {
      try {
    	  Class.forName("com.mysql.cj.jdbc.Driver");
    	    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/job", "root", "moe142");

    	    // Insert company
    	    String insertCom = "INSERT INTO company (com_name, com_add, com_ph, com_email, com_web, image) VALUES (?, ?, ?, ?, ?, ?)";
    	    try (PreparedStatement psCom = con.prepareStatement(insertCom)) {
    	        psCom.setString(1, com_nm);
    	        psCom.setString(2, add);
    	        psCom.setString(3, ph);
    	        psCom.setString(4, email);
    	        psCom.setString(5, web);
    	        psCom.setString(6, image.getSubmittedFileName());
    	        int rowsInserted = psCom.executeUpdate();
    	        if (rowsInserted > 0) {
    	            System.out.println("Company inserted successfully.");
    	        } else {
    	            System.out.println("Company not inserted.");
    	        }
    	    }

    	    // Insert job
    	    String insertJOB = "INSERT INTO job (job_name, job_type, salary, position, deg, age, qty, gender, exp, image, `add`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	    try (PreparedStatement psJob = con.prepareStatement(insertJOB)) {
    	        psJob.setString(1, job_nm);
    	        psJob.setString(2, job_ty);
    	        psJob.setInt(3, sal);
    	        psJob.setString(4, position);
    	        psJob.setString(5, deg);
    	        psJob.setInt(6, age);
    	        psJob.setInt(7, qty);
    	        psJob.setString(8, gender);
    	        psJob.setInt(9, exp);
    	        psJob.setString(10, image.getSubmittedFileName());
    	        psJob.setString(11, add);

    	        int rowsInserted = psJob.executeUpdate();
    	        if (rowsInserted > 0) {
    	            System.out.println("Job inserted successfully.");
    	        } else {
    	            System.out.println("Job not inserted.");
    	        }}
      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          try {
              if (ps != null) {
                  ps.close();
              }
              if (con != null) {
                  con.close();
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
      response.sendRedirect("companyTest.jsp");
    }
    doGet(request, response);
  
  
  }

}