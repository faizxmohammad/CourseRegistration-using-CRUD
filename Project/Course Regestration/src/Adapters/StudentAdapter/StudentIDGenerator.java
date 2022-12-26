package Adapters.StudentAdapter;

import java.sql.*;

public class StudentIDGenerator {
    private long id ;

    public  long generateStudentId() throws SQLException, ClassNotFoundException  {
        long value;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
        String query  = "SELECT max(sID) FROM students";
        Statement statement  = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        if(rs.next()) {
            id = rs.getLong(1);
        }
        value = id + 1;

        return value;
    }
}
