import java.io.Serializable;

public class User implements Serializable {
	private String name, email;

	public User() {
		name = "";
		email = "";
	}
	
	public User(String name, String email) {
		this.name = name;
		this.email = email;

}
}
