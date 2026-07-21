package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDAO;
import dto.User;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

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

            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");

            User user = new User();

            user.setFullName(fullName);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPassword(password);
            user.setRole("USER");
            user.setProfileImage(null);
            user.setAccountStatus("ACTIVE");

            UserDAO dao = new UserDAO();

            String result = dao.registerUser(user);

            Gson gson = new Gson();

            if ("SUCCESS".equals(result)) {

                response.getWriter().write(
                        gson.toJson(
                                new SignupResponse(
                                        true,
                                        "Registration Successful"
                                )
                        )
                );

            } else {

                response.getWriter().write(
                        gson.toJson(
                                new SignupResponse(
                                        false,
                                        result
                                )
                        )
                );

            }

        } catch (Exception e) {

            e.printStackTrace();

            Gson gson = new Gson();

            response.getWriter().write(
                    gson.toJson(
                            new SignupResponse(
                                    false,
                                    "Server Error"
                            )
                    )
            );

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

    class SignupResponse {

        boolean success;
        String message;

        SignupResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

    }

}