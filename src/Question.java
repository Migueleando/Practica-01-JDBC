import java.io.Serializable;

public class Question implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String text;
	private int id;

	public Question(int id, String text) {
		this.id = id;
		this.text = text;		
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public  void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}
