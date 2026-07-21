package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;

import dao.OwnerDAO;
import dto.Booking;

@WebServlet("/getOwnerBookings")
public class GetOwnerBookingsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        response.setContentType("application/json");

        try {

            int ownerId = Integer.parseInt(request.getParameter("ownerId"));

            OwnerDAO dao = new OwnerDAO();

            List<Booking> bookings = dao.getOwnerBookings(ownerId);

            Gson gson = new Gson();

            response.getWriter().write(gson.toJson(bookings));

        }

        catch (Exception e) {

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
