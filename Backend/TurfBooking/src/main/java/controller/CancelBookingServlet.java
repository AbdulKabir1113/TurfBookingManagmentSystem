package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookingDAO;

@WebServlet("/cancelBooking")
public class CancelBookingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setContentType("application/json");

        try {

            int bookingId = Integer.parseInt(request.getParameter("bookingId"));

            BookingDAO dao = new BookingDAO();

            boolean status = dao.cancelBooking(bookingId);

            if (status) {

                response.getWriter().write(
                        "{\"success\":true,\"message\":\"Booking Cancelled Successfully\"}");

            } else {

                response.getWriter().write(
                        "{\"success\":false,\"message\":\"Cancellation Failed\"}");

            }

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().write(
                    "{\"success\":false,\"message\":\"Server Error\"}");

        }

    }

    @Override
    protected void doOptions(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        response.setStatus(HttpServletResponse.SC_OK);

    }

}