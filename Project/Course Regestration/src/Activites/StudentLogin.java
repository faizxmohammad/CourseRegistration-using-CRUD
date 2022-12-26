package Activites;

import Adapters.StudentAdapter.StudentData;

import java.sql.*;
public class StudentLogin {
    StudentData studentData = new StudentData();
    public void checkDetails(long phone){
        int id =  getMyID(phone);
        studentData.getByID(id);
    }
    public int getMyID(long phone) {
        int id = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String sql = "select sid from students where sphone = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, phone);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                id = rs.getInt("sid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public void optOut(int id){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String sql = "update registration set r_course = null where id = " + id ;
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            conn.close();
        }catch (Exception e){e.printStackTrace();}


    }


}
