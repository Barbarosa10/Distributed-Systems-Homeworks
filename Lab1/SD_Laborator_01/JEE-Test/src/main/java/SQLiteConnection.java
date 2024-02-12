import beans.StudentBean;

import javax.management.remote.rmi._RMIConnection_Stub;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteConnection {
    private static boolean isConnected = false;
    private static Connection conn = null;

    public static Connection connect() throws SQLException, ClassNotFoundException {

        if(!isConnected){
            Class.forName("org.sqlite.JDBC");
            // SQL statement for creating a new table
            String sql = "CREATE TABLE IF NOT EXISTS student (\n"
                    + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                    + "	firstname text NOT NULL,\n"
                    + "	lastname text NOT NULL,\n"
                    + "	age integer NOT NULL\n"
                    + ");";

            conn = DriverManager.getConnection("jdbc:sqlite:student.db");
            Statement stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);

            isConnected = true;
        }
        return conn;
    }
    public static void insertStudent(String firstname, String lastname, Integer age) throws SQLException, ClassNotFoundException {
        connect();


        PreparedStatement ps = conn.prepareStatement("INSERT INTO student(firstname, lastname, age) VALUES(?,?,?)");
        ps.setString(1, firstname);
        ps.setString(2, lastname);
        ps.setInt(3, age);
        ps.executeUpdate();

    }

    public static ResultSet selectAll() throws SQLException, ClassNotFoundException {
        connect();
        String sql = "SELECT id, firstname, lastname, age FROM student";

        Statement stmt  = conn.createStatement();

        return stmt.executeQuery(sql);
    }
    public static ResultSet selectStudent(String firstname, String lastname, int age) throws SQLException, ClassNotFoundException {
        connect();

        PreparedStatement ps = conn.prepareStatement("SELECT firstname, lastname, age, id from student where firstname=? and lastname=? and age=?");

        ps.setString(1, firstname);
        ps.setString(2, lastname);
        ps.setInt(3, age);
        ResultSet rs = ps.executeQuery();

        return rs;
    }
    public static void updateStudent(int id, String firstname, String lastname, int age) throws SQLException, ClassNotFoundException {
        connect();

        PreparedStatement ps = conn.prepareStatement("UPDATE student set firstname=?, lastname=?, age=? where id=?");

        ps.setString(1, firstname);
        ps.setString(2, lastname);
        ps.setInt(3, age);
        ps.setInt(4, id);

        ps.executeUpdate();
    }
    public static void deleteStudent(int id) throws SQLException, ClassNotFoundException{
        connect();

        PreparedStatement ps = conn.prepareStatement("DELETE from student where id=?");

        ps.setInt(1, id);

        ps.executeUpdate();

    }
}
