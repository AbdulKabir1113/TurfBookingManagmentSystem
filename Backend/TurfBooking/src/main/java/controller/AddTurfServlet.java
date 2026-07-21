package controller;



import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.TurfDAO;
import dto.Turf;

@WebServlet("/addTurf")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 5 * 1024 * 1024,
    maxRequestSize = 20 * 1024 * 1024
)
public class AddTurfServlet extends HttpServlet {

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

            int ownerId = Integer.parseInt(request.getParameter("ownerId"));

            String turfName = request.getParameter("turfName");
            String location = request.getParameter("location");
            String city = request.getParameter("city");
            double pricePerHour = Double.parseDouble(request.getParameter("pricePerHour"));
            String openingTime = request.getParameter("openingTime");
            String closingTime = request.getParameter("closingTime");
            String description = request.getParameter("description");

            Part imagePart = request.getPart("thumbnail");

            String uploadPath = "C:\\TurfUploads";

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String fileName = Paths.get(imagePart.getSubmittedFileName())
                    .getFileName()
                    .toString();

            String uniqueFileName =
                    System.currentTimeMillis() + "_" + fileName;

            imagePart.write(uploadPath + File.separator + uniqueFileName);

            String thumbnailUrl = uniqueFileName;
            Turf turf = new Turf();

            turf.setOwnerId(ownerId);
            turf.setTurfName(turfName);
            turf.setLocation(location);
            turf.setCity(city);
            turf.setPricePerHour(pricePerHour);
            turf.setOpeningTime(openingTime);
            turf.setClosingTime(closingTime);
            turf.setDescription(description);
            turf.setThumbnailUrl(thumbnailUrl);
            turf.setRating(0.0);
            turf.setStatus("ACTIVE");

            TurfDAO dao = new TurfDAO();

            boolean status = dao.addTurf(turf);

            Gson gson = new Gson();

            if (status) {

                response.getWriter().write(
                        gson.toJson(
                                new AddTurfResponse(
                                        true,
                                        "Turf Added Successfully"
                                )
                        )
                );

            } else {

                response.getWriter().write(
                        gson.toJson(
                                new AddTurfResponse(
                                        false,
                                        "Failed To Add Turf"
                                )
                        )
                );

            }

        } catch (Exception e) {

            e.printStackTrace();

            Gson gson = new Gson();

            response.getWriter().write(
                    gson.toJson(
                            new AddTurfResponse(
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

    class AddTurfResponse {

        boolean success;
        String message;

        AddTurfResponse(boolean success, String message) {

            this.success = success;
            this.message = message;

        }

    }

}