import java.io.Serializable;

public class User implements Serializable {
	private String name, email;

	public User() {
		name = "";
		email = "";
	}

	public User(String name, String email) {
		super();
		this.name = name;
		this.email = email;

	}
	
	public String setName(String name) {
		return this.name = name;
	}

	public String getName(String name) {
		return name;
	}
	
	public String setEmail(String email) {
		return this.email = email;
	}

	public String getEmail(String email) {
		return email;
	}
}
