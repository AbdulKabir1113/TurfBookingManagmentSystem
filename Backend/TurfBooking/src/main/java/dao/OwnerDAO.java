package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.OwnerDashboard;
import util.DBConnection;

import java.util.ArrayList;
import java.util.List;
import dto.Turf;
import dto.Booking;
import dto.User;

public class OwnerDAO {

    public OwnerDashboard getDashboardData(int ownerId) {

        OwnerDashboard dashboard = new OwnerDashboard();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = DBConnection.getConnection();

            // ===========================
            // Total Turfs
            // ===========================

            String totalTurfsQuery =
                    "SELECT COUNT(*) AS total FROM turfs WHERE owner_id=?";

            ps = con.prepareStatement(totalTurfsQuery);

            ps.setInt(1, ownerId);

            rs = ps.executeQuery();

            if (rs.next()) {

                dashboard.setTotalTurfs(
                        rs.getInt("total")
                );

            }

            rs.close();
            ps.close();

            // ===========================
            // Today's Bookings
            // ===========================

            String todayBookingsQuery =
                    "SELECT COUNT(*) AS total " +
                    "FROM bookings b " +
                    "JOIN turfs t ON b.turf_id=t.turf_id " +
                    "WHERE t.owner_id=? " +
                    "AND b.booking_date=CURDATE() " +
                    "AND b.booking_status='CONFIRMED'";

            ps = con.prepareStatement(todayBookingsQuery);

            ps.setInt(1, ownerId);

            rs = ps.executeQuery();

            if (rs.next()) {

                dashboard.setTodaysBookings(
                        rs.getInt("total")
                );

            }

            rs.close();
            ps.close();

            // ===========================
            // Upcoming Bookings
            // ===========================

            String upcomingBookingsQuery =
                    "SELECT COUNT(*) AS total " +
                    "FROM bookings b " +
                    "JOIN turfs t ON b.turf_id=t.turf_id " +
                    "WHERE t.owner_id=? " +
                    "AND b.booking_date > CURDATE() " +
                    "AND b.booking_status='CONFIRMED'";

            ps = con.prepareStatement(upcomingBookingsQuery);

            ps.setInt(1, ownerId);

            rs = ps.executeQuery();

            if (rs.next()) {

                dashboard.setUpcomingBookings(
                        rs.getInt("total")
                );

            }

            rs.close();
            ps.close();

            // ===========================
            // Total Revenue
            // ===========================

            String revenueQuery =
                    "SELECT IFNULL(SUM(total_amount),0) AS revenue " +
                    "FROM bookings b " +
                    "JOIN turfs t ON b.turf_id=t.turf_id " +
                    "WHERE t.owner_id=? " +
                    "AND b.booking_status='CONFIRMED'";

            ps = con.prepareStatement(revenueQuery);

            ps.setInt(1, ownerId);

            rs = ps.executeQuery();

            if (rs.next()) {

                dashboard.setTotalRevenue(
                        rs.getDouble("revenue")
                );

            }

            rs.close();
            ps.close();
            
         // ===========================
         // Recent Bookings
         // ===========================

         java.util.List<dto.Booking> recentBookings =
                 new java.util.ArrayList<>();

         String recentBookingsQuery =
                 "SELECT " +
                 "u.full_name, " +
                 "t.turf_name, " +
                 "b.booking_date, " +
                 "b.slot_start, " +
                 "b.slot_end, " +
                 "b.total_amount, " +
                 "b.booking_status " +
                 "FROM bookings b " +
                 "JOIN users u ON b.user_id=u.user_id " +
                 "JOIN turfs t ON b.turf_id=t.turf_id " +
                 "WHERE t.owner_id=? " +
                 "ORDER BY b.created_at DESC " +
                 "LIMIT 5";

         ps = con.prepareStatement(recentBookingsQuery);

         ps.setInt(1, ownerId);

         rs = ps.executeQuery();

         while (rs.next()) {

             dto.Booking booking = new dto.Booking();

             booking.setCustomerName(
                     rs.getString("full_name"));

             booking.setTurfName(
                     rs.getString("turf_name"));

             booking.setBookingDate(
                     rs.getDate("booking_date"));

             booking.setSlotStart(
                     rs.getTime("slot_start"));

             booking.setSlotEnd(
                     rs.getTime("slot_end"));

             booking.setTotalAmount(
                     rs.getDouble("total_amount"));

             booking.setBookingStatus(
                     rs.getString("booking_status"));

             recentBookings.add(booking);

         }

         dashboard.setRecentBookings(recentBookings);

         rs.close();
         ps.close();

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return dashboard;

    }

    public List<Turf> getOwnerTurfs(int ownerId) {

        List<Turf> turfList = new ArrayList<>();

        String query =
                "SELECT * FROM turfs WHERE owner_id=? ORDER BY turf_id DESC";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, ownerId);

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
    public List<Booking> getOwnerBookings(int ownerId) {

        List<Booking> bookingList = new ArrayList<>();

        String query =
                "SELECT " +
                "b.booking_id, " +
                "u.full_name, " +
                "u.phone, " +
                "t.turf_name, " +
                "b.booking_date, " +
                "b.slot_start, " +
                "b.slot_end, " +
                "b.total_amount, " +
                "b.booking_status " +
                "FROM bookings b " +
                "JOIN users u ON b.user_id=u.user_id " +
                "JOIN turfs t ON b.turf_id=t.turf_id " +
                "WHERE t.owner_id=? " +
                "ORDER BY b.booking_date DESC, b.slot_start ASC";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, ownerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Booking booking = new Booking();

                booking.setBookingId(
                        rs.getInt("booking_id"));

                booking.setCustomerName(
                        rs.getString("full_name"));

                booking.setCustomerPhone(
                        rs.getString("phone"));

                booking.setTurfName(
                        rs.getString("turf_name"));

                booking.setBookingDate(
                        rs.getDate("booking_date"));

                booking.setSlotStart(
                        rs.getTime("slot_start"));

                booking.setSlotEnd(
                        rs.getTime("slot_end"));

                booking.setTotalAmount(
                        rs.getDouble("total_amount"));

                booking.setBookingStatus(
                        rs.getString("booking_status"));

                bookingList.add(booking);

            }

            rs.close();
            ps.close();
            con.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return bookingList;

    }
    
    public User getOwnerProfile(int ownerId) {

        User owner = null;

        String query =
                "SELECT * FROM users WHERE user_id=? AND role='OWNER'";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, ownerId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                owner = new User();

                owner.setUserId(rs.getInt("user_id"));
                owner.setFullName(rs.getString("full_name"));
                owner.setEmail(rs.getString("email"));
                owner.setPhone(rs.getString("phone"));
                owner.setRole(rs.getString("role"));
                owner.setProfileImage(rs.getString("profile_image"));
                owner.setAccountStatus(rs.getString("account_status"));
                owner.setCreatedAt(rs.getTimestamp("created_at"));

            }

            rs.close();
            ps.close();
            con.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return owner;

    }
}
