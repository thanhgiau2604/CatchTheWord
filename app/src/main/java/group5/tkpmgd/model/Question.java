package group5.tkpmgd.model;

public class Question {
    private int idquestion;
    private byte[] imgquestion;
    private String suggestion1;
    private String suggestion2;
    private String suggestion3;
    private String vocabulary;
    private String pronounce;
    private String meaning;
    private int finished;
    private int current;
    private int saved;

    public Question() {
    }

    public Question(int idquestion, byte[] imgquestion, String suggestion1, String suggestion2,
                    String suggestion3, String vocabulary, String pronounce, String meaning,
                    int finished, int current, int saved) {
        this.idquestion = idquestion;
        this.imgquestion = imgquestion;
        this.suggestion1 = suggestion1;
        this.suggestion2 = suggestion2;
        this.suggestion3 = suggestion3;
        this.vocabulary = vocabulary;
        this.pronounce = pronounce;
        this.meaning = meaning;
        this.finished = finished;
        this.current = current;
        this.saved = saved;
    }

    public int getIdquestion() {
        return idquestion;
    }

    public void setIdquestion(int idquestion) {
        this.idquestion = idquestion;
    }

    public byte[] getImgquestion() {
        return imgquestion;
    }

    public void setImgquestion(byte[] imgquestion) {
        this.imgquestion = imgquestion;
    }

    public String getSuggestion1() {
        return suggestion1;
    }

    public void setSuggestion1(String suggestion1) {
        this.suggestion1 = suggestion1;
    }

    public String getSuggestion2() {
        return suggestion2;
    }

    public void setSuggestion2(String suggestion2) {
        this.suggestion2 = suggestion2;
    }

    public String getSuggestion3() {
        return suggestion3;
    }

    public void setSuggestion3(String suggestion3) {
        this.suggestion3 = suggestion3;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSaved() {
        return saved;
    }

    public void setSaved(int saved) {
        this.saved = saved;
    }
}
