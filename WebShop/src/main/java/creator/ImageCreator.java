package creator;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ImageCreator {
    private Path imgPath;

    public ImageCreator(Path imgPath) {
        this.imgPath = imgPath;
    }

    public String add(Part part, String userName) {
        try {
            String fileName = userName + "." + getFileExtension(part);
            part.write(imgPath.toString() + "/" + fileName);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public File get(String fileName) {
        return new File(imgPath.toString(), fileName);
    }

    private String getFileExtension(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("Part Header = " + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('.') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}