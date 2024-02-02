package login_register;

import javax.print.DocFlavor.STRING;

public class job {
	
	String name,type,position,deg,location,gender,image;
	int salary,age,exp,qty;
	public job(String name, String type, String position, String deg,int salary,
			int age, int exp, int qty,String gender,String location,String image) {
		super();
		this.name = name;
		this.type = type;
		this.position = position;
		this.deg = deg;
		this.image=image;
		this.gender=gender;
		this.salary = salary;
		this.age = age;
		this.exp = exp;
		this.qty = qty;
		this.location = location;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDeg() {
		return deg;
	}
	public void setDeg(String deg) {
		this.deg = deg;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	

	
}
	

