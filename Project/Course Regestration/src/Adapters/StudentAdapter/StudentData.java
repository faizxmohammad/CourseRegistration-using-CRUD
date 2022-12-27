package Adapters.StudentAdapter;

import Adapters.RegistrationAdapters.RegisterUser;
import Models.Student;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentData {
    List<Student> studentList = new ArrayList<>();
    Student student = new Student();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Scanner in  = new Scanner(System.in);

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
            statement.close();
            conn.close();

        }catch (Exception e){e.printStackTrace();}
    }
    public void updateStudent(int id){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            System.out.println("\nChoose parameters which are needed to be updated\n");
            System.out.println("1.Student name \t\t 2.Student phone number");
            int n = in.nextInt();
            String sql = "";
            if(n == 1){
                System.out.println("Enter Student new Name");
                String name = br.readLine();
                sql = "update students set sname = '" + name + "' where sid = " + id;
            }else if(n == 2){
                System.out.println("Enter student new phone");
                Long phone = in.nextLong();
                sql = "update students set sphone = " + phone + "where sid = " + id;
            } else{
                System.out.println(" Please enter a valid choice!! ");
                System.exit(0);
            }
            Statement statement= conn.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            conn.close();
        }catch (Exception e){e.printStackTrace();}


    }
    public void getByID(int id){

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String sql =  "SELECT s.sid, s.sname, s.sphone ,c.coursename FROM students s JOIN studentcourses c ON s.sid = c.sid WHERE s.sid = ?";
            String data = "select * from students where sid = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            conn.setAutoCommit(true); // we need to add this else the data wont get printed

            System.out.println("| Student ID " + "|" + " Student name " + "|" + " Phone number " +  "|" + " Registered course |");
            System.out.println("--------------------------------------------------------------");
            while(rs.next()){
                int sid = rs.getInt("sid");
                String name = rs.getString("sname");
                long phone = rs.getLong("sphone");
                String course = rs.getString("coursename");

                System.out.println("\t\t"+sid + "\t\t  " + name + "   \t\t" + phone +"\t\t\t " + course );

            }
            System.out.println("--------------------------------------------------------------");



            rs.close();
            statement.close();
            conn.close();


        }catch(Exception e){e.printStackTrace();}

    }

    public void writeToFile() throws IOException {
        FileWriter file = new FileWriter("E:\\Spring5Learning\\Project\\Course Regestration\\src\\Files\\Students\\allStudents.txt");


    }


}

