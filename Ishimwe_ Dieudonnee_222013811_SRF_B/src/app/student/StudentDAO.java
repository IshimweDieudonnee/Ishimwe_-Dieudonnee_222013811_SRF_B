package app.student;

import app.Config;
import app.classes.SClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public static final String GET_CLASSES = "SELECT * FROM class";
    public static final String INSERT_STUDENT = "INSERT INTO student (fname, lname, email, sex, nationality, studymode, class) VALUES (?,?,?,?,?,?,?)";
    public static final String SELECT_STUDENTS = "SELECT * FROM student";

    public List<SClass> getClasses() {
        List<SClass> sClassList = new ArrayList<>();
        try(Connection conn = Config.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_CLASSES)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String level = rs.getString("level");
                String title = rs.getString("title");

                sClassList.add(new SClass(level, title));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sClassList;
    }

    public void insertStudent(Student student) {
        try(Connection conn = Config.getConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_STUDENT)) {
            pstmt.setString(1, student.getFname());
            pstmt.setString(2, student.getLname());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getSex());
            pstmt.setString(5, student.getNationality());
            pstmt.setString(6, student.getStudyMode());
            pstmt.setString(7, student.getsClass());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  List<Student> getStudents() {
        List<Student> studentList = new ArrayList<>();
        try(Connection conn = Config.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SELECT_STUDENTS)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String sclass = rs.getString("class");
                String sex = rs.getString("sex");
                String smode = rs.getString("studymode");

                studentList.add(new Student(fname, lname, sex, smode, sclass));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return studentList;
    }

}
