import java.io.Serializable;

public class Test implements Serializable {
    private int idTest, score, numQuest;
    private String nameTest, date;

    public Test() {
	idTest = 0;
	score = 0;
	numQuest = 0;
	nameTest = "";
	date = "";
    }

    public Test(int idTest, String nameTest, String date, int score, int numQuest) {
	this.idTest = idTest;
	this.nameTest = nameTest;
	this.date = date;
	this.score = score;
	this.numQuest = numQuest;
    }

    public int getId() {
	return idTest;
    }

    public void setId(int id) {
	this.idTest = id;
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
	return nameTest;
    }

    public void setName(String name) {
	this.nameTest = name;
    }

    public String getDate() {
	return date;
    }

    public void setDate(String date) {
	this.date = date;
    }

}
