package Adapters.CoursesAdapter;

import java.sql.*;

public class CourseIDGenerator {
    private long id;
    public int getCourseId() throws ClassNotFoundException, SQLException {
        long value ;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
        String query  = "SELECT CID FROM courses WHERE CID = (SELECT max(CID) FROM courses) ";
        Statement statement  = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        if(rs.next()) {
            id = rs.getLong(1);
        }
        value = id + 1;

        return (int) value;

    }
}
