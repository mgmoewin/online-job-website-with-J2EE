package login_register;

public class user {
  private static String name;
  private static String email;
  private static String password;
 
  public user(String name, String email, String password) {
    
    super();
  
  
  this.name=name;
  this.email=email;
  this.password=password;
  
  
  }
  
  public static String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public static String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

 

 
  
}
  
