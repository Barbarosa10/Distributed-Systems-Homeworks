import beans.StudentBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StudentBean> studentBeanList = new ArrayList<>();

        if(!request.getParameter("prenume").equals("") && !request.getParameter("nume").equals("") && !request.getParameter("varsta").equals("")) {
            ResultSet rs;
            try {
                rs = SQLiteConnection.selectStudent(request.getParameter("prenume"), request.getParameter("nume"), Integer.parseInt(request.getParameter("varsta")));
            } catch (SQLException | ClassNotFoundException e) {
                response.sendError(500, e.getMessage());
                throw new RuntimeException(e);
            }
            try {
                while (rs.next()) {
                    studentBeanList.add(new StudentBean(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"), rs.getInt("age")));
                }
            } catch (SQLException e) {
                response.sendError(500, e.getMessage());
            }
        }
        request.setAttribute("studentBeans", studentBeanList);

        // redirectionare date catre pagina de afisare a informatiilor studentului
        request.getRequestDispatcher("./update-student.jsp").forward(request, response);

    }
}
