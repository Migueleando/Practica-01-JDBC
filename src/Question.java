import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String text;
	private int id;
	private ArrayList<Answer> answers;
	
	public Question(int id, String text, ArrayList<Answer> answers) {
		this.id = id;
		this.text = text;	
		this.answers = answers;
	}

	public Question(int id, String text) {
		this(id,text,new ArrayList<Answer>())	;	
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
