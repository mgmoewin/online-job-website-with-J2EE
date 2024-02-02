package login_register;

public class company {
	String name,address,ph,email,web,image;

	public company(String name, String address, String ph,String email,String web,String image) {
		super();
		this.name = name;
		this.address = address;
		this.email=email;
		this.ph = ph;
		this.web=web;
		this.image=image;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	
	public void setWeb(String Web) {
		this.web = web;
	}

	public String getWeb() {
		return web;
	}
	
}
