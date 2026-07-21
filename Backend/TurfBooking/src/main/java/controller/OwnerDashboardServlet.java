package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.OwnerDAO;
import dto.OwnerDashboard;

@WebServlet("/ownerDashboard")
public class OwnerDashboardServlet extends HttpServlet {

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

            OwnerDashboard dashboard = dao.getDashboardData(ownerId);

            Gson gson = new Gson();

            response.getWriter().write(
                    gson.toJson(dashboard)
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.getWriter().write(
                    "{\"message\":\"Server Error\"}"
            );

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