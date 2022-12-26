package Adapters.StudentAdapter;

import Adapters.RegistrationAdapters.RegisterUser;
import Models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentData {
    List<Student> studentList = new ArrayList<>();
    Student student = new Student();

    public void copyStudentData(long StudentID, long phone, String name) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
        String sql = "insert into students values(?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, StudentID);
        statement.setString(2, name);
        statement.setLong(3, phone);
        statement.executeUpdate();
        System.out.println("inserted data into students");
    }

    public void fetchAll() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String sql = "select * from students";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int StudentID = rs.getInt(1);
                String StudentName = rs.getString(2);
                int StudentPhone = rs.getInt(3);
                studentList.add(new Student(StudentID, StudentName, StudentPhone));
            }
            statement.close();
            conn.close();

            System.out.println("| Student ID" + "|" + "Student name" + "|" + "Student phone |");
            System.out.println("--------------------------------------------------------------");
            for (Student student : studentList){
                System.out.println( "\t\t" + student.getId() + "\t\t" + student.getName() + "\t\t" + student.getPhone());
            }
            System.out.println("--------------------------------------------------------------");






        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void removeStudent(int id){
        RegisterUser ru = new RegisterUser();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String sql = "delete from students where sid = ?" ;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
            conn.setAutoCommit(true);
            ru.removeCourseName(id);
            ru.removeStudentID(id);
            System.out.println("Student removed !!");
            statement.close();
            conn.close();

        }catch (Exception e){e.printStackTrace();}
    }
}

