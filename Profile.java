public class Profile {
	private int id;
	private String name, email, password;
	
	public Profile(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean checkPassword(String inputPassword) {
		return inputPassword.equals(password);
	}
	
	public void resetPassword(String newPassword) {
		this.password = newPassword;
		System.out.println("Password berhasil di ubah");
	}
}
