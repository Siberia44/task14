package servlet;

import constant.Constant;
import creator.ImageCreator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/img")
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ImageCreator imageProvider = (ImageCreator) getServletContext().getAttribute(Constant.AVATAR);
        String imageName = req.getParameter(Constant.AVATAR);
        File image = imageProvider.get(imageName);
        resp.setContentType(getServletContext().getMimeType(imageName));
        try (FileInputStream in = new FileInputStream(image);
             OutputStream out = resp.getOutputStream()) {
            int bufferSize = 1024;
            byte[] buf = new byte[bufferSize];
            int count;
            while ((count = in.read(buf)) >= 0) {
                out.write(buf, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
