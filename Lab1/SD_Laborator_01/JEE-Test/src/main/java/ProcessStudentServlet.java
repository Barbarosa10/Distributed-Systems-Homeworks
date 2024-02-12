import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.sql.SQLException;
import java.time.Year;

public class ProcessStudentServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // se citesc parametrii din cererea de tip POST
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));


        /*
        procesarea datelor - calcularea anului nasterii
         */
        int anCurent = Year.now().getValue();
        int anNastere = anCurent - varsta;

//         initializare serializator Jackson
//        XmlMapper mapper = new XmlMapper();

//         creare bean si populare cu date
//        StudentBean bean = new StudentBean();
//        bean.setNume(nume);
//        bean.setPrenume(prenume);
//        bean.setVarsta(varsta);

        try {
            SQLiteConnection.insertStudent(prenume, nume, varsta);
        } catch (SQLException | ClassNotFoundException e) {
            response.sendError(500, e.getMessage());
            return;
        }

        // serializare bean sub forma de string XML
//        mapper.writeValue(new File("/home/student/1307B/Duciuc Danut/student.xml"), bean);

        // se trimit datele primite si anul nasterii catre o alta pagina JSP pentru afisare
        request.setAttribute("nume", nume);
        request.setAttribute("prenume", prenume);
        request.setAttribute("varsta", varsta);
        request.setAttribute("anNastere", anNastere);
        request.getRequestDispatcher("./info-student.jsp").forward(request, response);
    }
}