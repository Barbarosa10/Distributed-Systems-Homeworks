import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class UpdateStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String varsta = request.getParameter("varsta");

        if(!prenume.equals("") && !nume.equals("") && !varsta.equals("") && !id.equals("")) {
            try {
                SQLiteConnection.updateStudent(Integer.parseInt(id), prenume, nume, Integer.parseInt(varsta));
            } catch (SQLException | ClassNotFoundException e) {
                response.sendError(500, e.getMessage());
                throw new RuntimeException(e);
            }
        }
        List<StudentBean> studentBeanList = new ArrayList<>();

        ResultSet rs;
        try {
            rs = SQLiteConnection.selectAll();
        } catch (SQLException | ClassNotFoundException e) {
            response.sendError(500, e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            while (rs.next()) {
                studentBeanList.add(new StudentBean(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"), rs.getInt("age")));
            }
        }catch (SQLException e){
            response.sendError(500, e.getMessage());
        }
        request.setAttribute("studentBeans", studentBeanList);

        request.getRequestDispatcher("./update-student.jsp").forward(request, response);
    }
}
