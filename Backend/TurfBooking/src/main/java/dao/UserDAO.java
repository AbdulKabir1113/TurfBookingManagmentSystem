package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;
import util.DBConnection;
import java.sql.Connection;
import dto.LoginResult;

public class UserDAO {

	public String registerUser(User user) {

	    String query = "SELECT * FROM users WHERE email=? OR phone=?";

	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(query);

	        ps.setString(1, user.getEmail());
	        ps.setString(2, user.getPhone());

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            if (user.getEmail().equals(rs.getString("email"))) {
	                con.close();
	                return "Email already registered";
	            }

	            if (user.getPhone().equals(rs.getString("phone"))) {
	                con.close();
	                return "Phone number already registered";
	            }

	        }

	        String insert =
	                "INSERT INTO users(full_name,email,phone,password,role,profile_image,account_status) VALUES(?,?,?,?,?,?,?)";

	        ps = con.prepareStatement(insert);

	        ps.setString(1, user.getFullName());
	        ps.setString(2, user.getEmail());
	        ps.setString(3, user.getPhone());
	        ps.setString(4, user.getPassword());
	        ps.setString(5, user.getRole());
	        ps.setString(6, user.getProfileImage());
	        ps.setString(7, user.getAccountStatus());

	        int rows = ps.executeUpdate();

	        con.close();

	        if (rows > 0) {
	            return "SUCCESS";
	        }

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	    return "Registration Failed";
	}
    
	public LoginResult loginUser(String email, String password) {

	    LoginResult result = new LoginResult();

	    String query = "SELECT * FROM users WHERE email=?";

	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(query);

	        ps.setString(1, email);

	        ResultSet rs = ps.executeQuery();

	        if (!rs.next()) {

	            result.setSuccess(false);
	            result.setMessage("Email is not registered");

	            con.close();

	            return result;
	        }

	        if (!rs.getString("password").equals(password)) {

	            result.setSuccess(false);
	            result.setMessage("Incorrect password");

	            con.close();

	            return result;
	        }

	        if (!"ACTIVE".equalsIgnoreCase(rs.getString("account_status"))) {

	            result.setSuccess(false);
	            result.setMessage("Your account is inactive");

	            con.close();

	            return result;
	        }

	        User user = new User();

	        user.setUserId(rs.getInt("user_id"));
	        user.setFullName(rs.getString("full_name"));
	        user.setEmail(rs.getString("email"));
	        user.setPhone(rs.getString("phone"));
	        user.setRole(rs.getString("role"));
	        user.setProfileImage(rs.getString("profile_image"));
	        user.setAccountStatus(rs.getString("account_status"));

	        result.setSuccess(true);
	        result.setMessage("Login Successful");
	        result.setUser(user);

	        con.close();

	    } catch (Exception e) {

	        e.printStackTrace();

	        result.setSuccess(false);
	        result.setMessage("Server Error");
	    }

	    return result;
	}
}