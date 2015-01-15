import java.io.Serializable;

public class User implements Serializable {
	private int id;
	private String name, email;

	public User() {
		id=0;
		name = "";
		email = "";
	}

	public User(int id,String name, String email) {
		super();
		this.id=id;
		this.name = name;
		this.email = email;

	}
	
	public void setId(int num) {
		this.id=num;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}
