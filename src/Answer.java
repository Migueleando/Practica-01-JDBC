import java.io.Serializable;

public class Answer implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id_question;
	private String text;
	private boolean is_correct;

	public Answer(int id_question, String text, boolean is_correct) {
		this.id_question = id_question;
		this.text = text;
		this.is_correct = is_correct;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	public void setIdQuestion (int id_question) {
		this.id_question = id_question;
	}
	
	public int getIdQuestion () {
		return id_question;
	}
	
	public void set_isCorrect(boolean is_correct) {
		this.is_correct = is_correct;
	}

	public boolean isCorrect() {
		return is_correct;
	}
}
