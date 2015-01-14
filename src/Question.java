import java.io.Serializable;

public class Question implements Serializable {
    private String text;
    private int id;

    public Question() {
	text = "";
	id = 0;
    }

    public Question(String text, int id) {
	this.text = text;
	this.id = id;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

}
