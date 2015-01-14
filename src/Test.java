import java.io.Serializable;

public class Test implements Serializable {
    private int id, score, numQuest;
    private String name, date;

    public Test() {
	id = 0;
	score = 0;
	numQuest = 0;
	name = "";
	date = "";
    }

    public Test(int id, String name, String date, int score, int numQuest) {
	this.id = id;
	this.name = name;
	this.date = date;
	this.score = score;
	this.numQuest = numQuest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumQuest() {
        return numQuest;
    }

    public void setNumQuest(int numQuest) {
        this.numQuest = numQuest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    } 
    
}
