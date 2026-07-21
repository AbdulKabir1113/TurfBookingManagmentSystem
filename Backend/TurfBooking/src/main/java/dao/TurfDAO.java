package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import dto.Turf;
import util.DBConnection;

public class TurfDAO {

	public boolean addTurf(Turf turf) {

		boolean status = false;

		String query = "INSERT INTO turfs(owner_id, turf_name, location, city, price_per_hour, opening_time, closing_time, description, thumbnail_url, rating, status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, turf.getOwnerId());
			ps.setString(2, turf.getTurfName());
			ps.setString(3, turf.getLocation());
			ps.setString(4, turf.getCity());
			ps.setDouble(5, turf.getPricePerHour());
			ps.setString(6, turf.getOpeningTime());
			ps.setString(7, turf.getClosingTime());
			ps.setString(8, turf.getDescription());
			ps.setString(9, turf.getThumbnailUrl());
			ps.setDouble(10, turf.getRating());
			ps.setString(11, turf.getStatus());

			int rows = ps.executeUpdate();

			if (rows > 0) {
				status = true;
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}
	
	public List<Turf> getAllTurfs() {

	    List<Turf> turfList = new ArrayList<>();

	    String query = "SELECT * FROM turfs";

	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(query);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            Turf turf = new Turf();

	            turf.setTurfId(rs.getInt("turf_id"));
	            turf.setOwnerId(rs.getInt("owner_id"));
	            turf.setTurfName(rs.getString("turf_name"));
	            turf.setLocation(rs.getString("location"));
	            turf.setCity(rs.getString("city"));
	            turf.setPricePerHour(rs.getDouble("price_per_hour"));
	            turf.setOpeningTime(rs.getString("opening_time"));
	            turf.setClosingTime(rs.getString("closing_time"));
	            turf.setDescription(rs.getString("description"));
	            turf.setThumbnailUrl(rs.getString("thumbnail_url"));
	            turf.setRating(rs.getDouble("rating"));
	            turf.setStatus(rs.getString("status"));

	            turfList.add(turf);
	        }

	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return turfList;
	}
	
	public List<Turf> searchTurfs(String search) {

	    List<Turf> turfList = new ArrayList<>();

	    String query =
	            "SELECT * FROM turfs " +
	            "WHERE turf_name LIKE ? " +
	            "OR city LIKE ? " +
	            "ORDER BY turf_id DESC";

	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(query);

	        ps.setString(1, "%" + search + "%");
	        ps.setString(2, "%" + search + "%");

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            Turf turf = new Turf();

	            turf.setTurfId(rs.getInt("turf_id"));
	            turf.setOwnerId(rs.getInt("owner_id"));
	            turf.setTurfName(rs.getString("turf_name"));
	            turf.setLocation(rs.getString("location"));
	            turf.setCity(rs.getString("city"));
	            turf.setPricePerHour(rs.getDouble("price_per_hour"));
	            turf.setOpeningTime(rs.getString("opening_time"));
	            turf.setClosingTime(rs.getString("closing_time"));
	            turf.setDescription(rs.getString("description"));
	            turf.setThumbnailUrl(rs.getString("thumbnail_url"));
	            turf.setRating(rs.getDouble("rating"));
	            turf.setStatus(rs.getString("status"));

	            turfList.add(turf);

	        }

	        rs.close();
	        ps.close();
	        con.close();

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	    return turfList;

	}
}
