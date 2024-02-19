package app.classes;

public class SClass {

    private String level;
    private String title;


    public SClass(String level, String title) {
        this.level = level;
        this.title = title;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }
}
