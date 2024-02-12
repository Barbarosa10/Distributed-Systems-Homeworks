import ejb.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        // pregatire EntityManager
        EntityManagerFactory factory =   Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        StudentEntity student = em.find(StudentEntity.class, id);

        em.getTransaction().begin();
        em.remove(student);
        em.getTransaction().commit();

        // inchidere EntityManager
        em.close();
        factory.close();

        // trimitere raspuns inapoi la client
        response.setContentType("text/html");
        response.getWriter().println("Stergerea a fost produsa cu succes in baza de date." +
                "<br /><br /><a href='./'>Inapoi la meniul principal</a>");
    }
}
