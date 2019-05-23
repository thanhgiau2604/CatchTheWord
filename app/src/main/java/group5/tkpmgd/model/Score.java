package group5.tkpmgd.model;

public class Score {
    private int playtime;
    private int score;
    private int current;

    public Score() {
    }

    public Score(int playtime, int score, int current) {
        this.playtime = playtime;
        this.score = score;
        this.current = current;
    }

    public int getPlaytime() {
        return playtime;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
