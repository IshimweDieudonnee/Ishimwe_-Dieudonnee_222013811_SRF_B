package app.staff;

import app.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {

    private static  final String INSERT_STAFF = "INSERT INTO staff (fname, lname, email, phone, country, city, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_STAFF = "";
    private static final String GET_USERS = "SELECT * FROM staff";


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

    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        try(Connection conn = Config.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_USERS)) {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String address = rs.getString("address");

                staffList.add(new Staff(fname, lname, email, phone, country, city, address));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return staffList;
    }

}
