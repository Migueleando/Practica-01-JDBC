import java.io.Serializable;


public class Question implements Serializable {
    private String text;
    
    public Question(){
	text = "";
    }
    
    public Question(String text){
	this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    

}
