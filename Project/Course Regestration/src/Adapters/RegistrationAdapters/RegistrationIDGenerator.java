package Adapters.RegistrationAdapters;

import java.sql.*;

public class RegistrationIDGenerator {
     private long id;

    public long generateId() throws SQLException, ClassNotFoundException {

        long value;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
        String query  = "SELECT ID FROM registration WHERE ID = (SELECT max(ID) FROM registration) ";
        Statement statement  = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        if(rs.next()) {
           id = rs.getLong(1);
        }
        value = id + 1;

        return value;
    }


}

