import java.io.Serializable;

public class Answer implements Serializable {
	private String text;
	private boolean is_correct;
	
	public Answer() {
		text = "";
		is_correct = false;
	}
	
	public Answer(String text, boolean is_correct) {
		this.text = text;
		this.is_correct = is_correct;
	}
}
