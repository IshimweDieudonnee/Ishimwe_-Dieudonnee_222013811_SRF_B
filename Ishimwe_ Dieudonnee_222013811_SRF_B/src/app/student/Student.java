package app.student;

public class Student {

    private String fname;
    private String lname;
    private String email;
    private String sex;

    private String nationality;
    private String studyMode;

    private String sClass;

    public Student(String fname, String lname, String email, String sex, String nationality, String studyMode, String sClass) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.sex = sex;
        this.nationality = nationality;
        this.studyMode = studyMode;
        this.sClass = sClass;
    }

    public Student(String fname, String lname, String sex, String studyMode, String sClass) {
        this.fname = fname;
        this.lname = lname;
        this.sex = sex;
        this.studyMode = studyMode;
        this.sClass = sClass;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setStudyMode(String studyMode) {
        this.studyMode = studyMode;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }

    public String getNationality() {
        return nationality;
    }

    public String getStudyMode() {
        return studyMode;
    }

    public String getsClass() {
        return sClass;
    }
}
