package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.BookingDAO;
import dto.Booking;

@WebServlet("/getMyBookings")
public class GetMyBookingsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setContentType("application/json");

        try {

            int userId = Integer.parseInt(request.getParameter("userId"));

            BookingDAO dao = new BookingDAO();

            List<Booking> bookings = dao.getBookingsByUserId(userId);

            Gson gson = new Gson();

            response.getWriter().write(gson.toJson(bookings));

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().write("[]");

        }

    }

    @Override
    protected void doOptions(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        response.setStatus(HttpServletResponse.SC_OK);

    }

}
