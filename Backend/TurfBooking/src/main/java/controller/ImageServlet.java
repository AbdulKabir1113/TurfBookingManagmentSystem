package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_PATH = "C:\\TurfUploads";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	

    	System.out.println("===== Image Servlet Called =====");

    	String fileName = request.getPathInfo();

    	System.out.println("PathInfo : " + fileName);

    	if (fileName == null || fileName.equals("/")) {
    	    response.sendError(HttpServletResponse.SC_NOT_FOUND);
    	    return;
    	}

    	fileName = java.net.URLDecoder.decode(fileName.substring(1), "UTF-8");

    	System.out.println("Decoded File : " + fileName);

    	File imageFile = new File(UPLOAD_PATH, fileName);

    	System.out.println("Absolute Path : " + imageFile.getAbsolutePath());

    	System.out.println("Exists : " + imageFile.exists());
//        String fileName = request.getPathInfo();
//
//        if (fileName == null || fileName.equals("/")) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//
//        fileName = java.net.URLDecoder.decode(fileName.substring(1), "UTF-8");
//
//        File imageFile = new File(UPLOAD_PATH, fileName);
//
//        if (!imageFile.exists()) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//
//        String mimeType = getServletContext().getMimeType(imageFile.getName());
//
//        if (mimeType == null) {
//            mimeType = "image/png";
//        }
//
//        response.setContentType(mimeType);
//        response.setContentLengthLong(imageFile.length());

        try (
            FileInputStream fis = new FileInputStream(imageFile);
            OutputStream os = response.getOutputStream()
        ) {

            byte[] buffer = new byte[8192];
            int count;

            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
            }

            os.flush();
        }
    }
}