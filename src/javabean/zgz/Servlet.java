package Upload_file;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.Part;
import java.text.*;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "Servlet", urlPatterns = "/Servlet")
@MultipartConfig
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("TEXT/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h3>OK!!");
        out.println("<hr>");
        String desc = request.getParameter("desc");
        Part file = request.getPart("file");
        String header = file.getHeader("content-disposition");
        out.println("头部信息" + header);
        String filename = ((header.split(";")[2]).split("=")[1]).replaceAll("\"", "");
        out.println("filename" + filename);
        String extname = filename.substring(filename.lastIndexOf('\\') + 1);
        System.out.println(extname);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String date = df.format(new Date());

        String newfilename = date + "_" + extname;
        System.out.println(newfilename);
        out.println("newfiletname" + newfilename);
        String uploadpath = getServletContext().getRealPath("/upload");
        try {
            file.write(uploadpath + File.separator + newfilename);
            out.println("one file upload!");
            out.println("file description:" + desc);

        } catch (IOException e) {
            out.println("Unable to upload the file.<br>");
            out.println("error：" + e.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

