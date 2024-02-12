import ejb.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FetchStudentListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // pregatire EntityManager
        EntityManagerFactory factory =   Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        StringBuilder responseText = new StringBuilder();
        responseText.append("<h2>Lista studenti</h2>");
        responseText.append("<table border='1'><thead><tr><th>ID</th><th>Nume</th><th>Prenume</th><th>Varsta</th><th>Action</th></thead>");
        responseText.append("<tbody>");

        // preluare date studenti din baza de date
        TypedQuery<StudentEntity> query = em.createQuery("select student from StudentEntity student", StudentEntity.class);
        List<StudentEntity> results = query.getResultList();
        for (StudentEntity student : results) {
            // se creeaza cate un rand de tabel HTML pentru fiecare student gasit
            responseText.append("<form action=\"./update-student\" method=\"get\"><tr><td>"
                    + student.getId() + "</td><td contenteditable>" + "<input type=\"text\" name=\"nume\" value=\"" + student.getNume() +"\"/>" +
                    "</td><td contenteditable>" + "<input type=\"text\" name=\"prenume\" value=\"" + student.getPrenume() +"\"/>" +
                    "</td><td contenteditable>" + "<input type=\"number\" name=\"varsta\" value=\"" + student.getVarsta() +"\"/>" + "</td>" +
                    "<td><input type=\"submit\" value=\"Actualizeaza\">" +
                    "<input formaction=\"delete-student\" type=\"submit\" value=\"Sterge\"></td></tr>" +
                    "<input type=\"hidden\" name=\"id\" value=\"" + student.getId() +"\"/></form>");

        }

        responseText.append("</tbody></table><br /><br /><a href='./'>Inapoi la meniul principal</a>");

        // inchidere EntityManager
        em.close();
        factory.close();

        // trimitere raspuns la client
        response.setContentType("text/html");
        response.getWriter().print(responseText.toString());
    }
}
