import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReadStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // deserializare student din fisierul XML de pe disc
//        File file = new File("/home/student/1307B/Duciuc Danut/student.xml");
//
//        if (!file.exists()) {
//            response.sendError(404, "Nu a fost gasit niciun student serializat pe disc!");
//            return;
//        }

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

        // redirectionare date catre pagina de afisare a informatiilor studentului
        request.getRequestDispatcher("./update-student.jsp").forward(request, response);
    }
}
