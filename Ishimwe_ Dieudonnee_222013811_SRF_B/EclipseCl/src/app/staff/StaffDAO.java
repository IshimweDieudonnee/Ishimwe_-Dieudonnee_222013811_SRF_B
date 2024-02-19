package app.staff;

import app.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StaffDAO {

    private static  final String INSERT_STAFF = "INSERT INTO staff (fname, lname, email, phone, coutry, city, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_STAFF = "";


    public void insertStaff(Staff staff) {
        try(Connection conn = Config.getConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_STAFF)) {
            pstmt.setString(1, staff.getFname());
            pstmt.setString(2, staff.getLname());
            pstmt.setString(3, staff.getEmail());
            pstmt.setString(4, staff.getPhone());
            pstmt.setString(5, staff.getCountry());
            pstmt.setString(6, staff.getCity());
            pstmt.setString(7, staff.getAddress());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
