import java.io.Serializable;

public class Answer implements Serializable {
	private String text;
	private boolean is_correct;

	public Answer() {
		text = "";
		is_correct = false;
	}

	public Answer(String text, boolean is_correct) {
		super();
		this.text = text;
		this.is_correct = is_correct;
	}

	public String setText(String text) {
		return this.text = text;
	}

	public String getText() {
		return text;
	}

	public boolean setIs_correct(boolean is_correct) {
		return this.is_correct = is_correct;

	}

	public boolean isCorrect() {
		return is_correct;

	}

}
