
// Record class that represents a Record object storing a unique key value, the score and the level
public class Record {

    private String key;
    private int score, level;

    // Constructor that initialzes private instance variables
    public Record(String key, int score, int level) {
        this.key = key;
        this.score = score;
        this.level = level;
    }

    // Getter Methods
    public String getKey() {
        return this.key;
    }

    public int getScore() {
        return this.score;
    }

    public int getLevel() {
        return this.level;
    }

}