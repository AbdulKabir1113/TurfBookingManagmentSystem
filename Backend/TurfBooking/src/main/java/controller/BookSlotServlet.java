package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.BookingDAO;
import dto.Booking;
import dto.BookingRequest;
import dto.SlotDTO;

@WebServlet("/bookSlot")
public class BookSlotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 System.out.println("BookSlotServlet Called");   // <-- Add this line here

	        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
	        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
	        response.setContentType("application/json");


		response.setContentType("application/json");

		// Read JSON Request
		StringBuilder json = new StringBuilder();

		BufferedReader reader = request.getReader();

		String line;

		while ((line = reader.readLine()) != null) {
			json.append(line);
		}

		Gson gson = new Gson();

		BookingRequest bookingRequest = gson.fromJson(json.toString(), BookingRequest.class);

		BookingDAO dao = new BookingDAO();

		Date bookingDate = Date.valueOf(bookingRequest.getBookingDate());

		double amountPerSlot = bookingRequest.getTotalAmount() / bookingRequest.getSlots().size();

		// Loop through every selected slot
		for (SlotDTO slot : bookingRequest.getSlots()) {

			Time slotStart = Time.valueOf(slot.getSlotStart());

			Time slotEnd = Time.valueOf(slot.getSlotEnd());

			boolean available = dao.isSlotAvailable(
					bookingRequest.getTurfId(),
					bookingDate,
					slotStart,
					slotEnd);

			if (!available) {

				response.getWriter().write(
						"{\"success\":false,\"message\":\"One or more slots are already booked.\"}");

				return;
			}

			Booking booking = new Booking();

			booking.setUserId(bookingRequest.getUserId());

			booking.setTurfId(bookingRequest.getTurfId());

			booking.setBookingDate(bookingDate);

			booking.setSlotStart(slotStart);

			booking.setSlotEnd(slotEnd);

			booking.setTotalAmount(amountPerSlot);

			booking.setBookingStatus("CONFIRMED");

			dao.bookSlot(booking);

		}

		response.getWriter().write(
				"{\"success\":true,\"message\":\"Booking Successful\"}");
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
		response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");

		response.setStatus(HttpServletResponse.SC_OK);
	}
}