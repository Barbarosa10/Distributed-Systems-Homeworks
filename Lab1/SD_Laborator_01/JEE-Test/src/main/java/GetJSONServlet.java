import beans.StudentBean;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetJSONServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Creating a JSONObject object
        JSONObject jsonObject = new JSONObject();

        //Creating a JSONArray object
        JSONArray array = new JSONArray();

        ResultSet rs;
        try {
            rs = SQLiteConnection.selectAll();
        } catch (SQLException | ClassNotFoundException e) {
            response.sendError(500, e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            while (rs.next()) {
                JSONObject record = new JSONObject();
                //Inserting key-value pairs into the json object
                record.put("id", rs.getInt("id"));
                record.put("firstname", rs.getString("firstname"));
                record.put("lastname", rs.getString("lastname"));
                record.put("varsta", rs.getInt("age"));
                array.add(record);
            }
        }catch (SQLException e){
            response.sendError(500, e.getMessage());
            return;
        }

//        jsonObject.put("students_data", array);

        try {
            FileWriter file = new FileWriter("/home/student/1307B/Duciuc Danut/studentJSON.json");
            file.write(array.toString());
            file.close();
        }catch (IOException e) {
            e.printStackTrace();
        }


        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.append(array.toString());
        out.close();

    }
}
