package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dto.Booking;
import util.DBConnection;

public class BookingDAO {

    public boolean bookSlot(Booking booking) {

        boolean status = false;

        String query = "INSERT INTO bookings(user_id, turf_id, booking_date, slot_start, slot_end, total_amount, booking_status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, booking.getUserId());
            ps.setInt(2, booking.getTurfId());
            ps.setDate(3, booking.getBookingDate());
            ps.setTime(4, booking.getSlotStart());
            ps.setTime(5, booking.getSlotEnd());
            ps.setDouble(6, booking.getTotalAmount());
            ps.setString(7, booking.getBookingStatus());

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

    public boolean isSlotAvailable(int turfId,
            java.sql.Date bookingDate,
            java.sql.Time slotStart,
            java.sql.Time slotEnd) {

        boolean available = true;
        String query =
        	    "SELECT * FROM bookings " +
        	    "WHERE turf_id=? " +
        	    "AND booking_date=? " +
        	    "AND booking_status!='CANCELLED' " +
        	    "AND (? < slot_end AND ? > slot_start)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, turfId);
            ps.setDate(2, bookingDate);

            ps.setTime(3, slotStart);
            ps.setTime(4, slotEnd);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                available = false;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return available;
    }
    
    public List<String> getBookedSlots(int turfId, Date bookingDate) {

        List<String> bookedSlots = new ArrayList<>();

        String query = "SELECT slot_start, slot_end FROM bookings WHERE turf_id=? AND booking_date=? AND booking_status!='CANCELLED'";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, turfId);
            ps.setDate(2, bookingDate);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String slot = rs.getTime("slot_start") + " - " +
                              rs.getTime("slot_end");

                bookedSlots.add(slot);

            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookedSlots;
    }
    
    public List<Booking> getBookingsByUserId(int userId) {

        List<Booking> bookings = new ArrayList<>();

        String query =
        	    "SELECT b.*, t.turf_name, t.location, t.city, t.thumbnail_url " +
        	    "FROM bookings b " +
        	    "JOIN turfs t ON b.turf_id = t.turf_id " +
        	    "WHERE b.user_id=? " +
        	    "ORDER BY b.booking_date DESC, b.slot_start ASC";
        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Booking booking = new Booking();

                booking.setBookingId(rs.getInt("booking_id"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setTurfId(rs.getInt("turf_id"));
                booking.setBookingDate(rs.getDate("booking_date"));
                booking.setSlotStart(rs.getTime("slot_start"));
                booking.setSlotEnd(rs.getTime("slot_end"));
                booking.setTotalAmount(rs.getDouble("total_amount"));
                booking.setBookingStatus(rs.getString("booking_status"));

                // Turf Details
                booking.setTurfName(rs.getString("turf_name"));
                booking.setTurfLocation(rs.getString("location"));
                booking.setCity(rs.getString("city"));
                booking.setThumbnailUrl(rs.getString("thumbnail_url"));

                bookings.add(booking);

            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return bookings;

    }
    
    public boolean cancelBooking(int bookingId) {

        boolean status = false;

        String query =
                "UPDATE bookings SET booking_status='CANCELLED' WHERE booking_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, bookingId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                status = true;

            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return status;

    }
}