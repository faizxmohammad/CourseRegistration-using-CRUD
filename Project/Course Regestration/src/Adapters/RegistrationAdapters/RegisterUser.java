package Adapters.RegistrationAdapters;


import Adapters.CoursesAdapter.AllCourses;
import Adapters.StudentAdapter.StudentData;
import Adapters.StudentAdapter.StudentIDGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterUser {
    Scanner in  = new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StudentData sdata = new StudentData();
    Long phone;
    String name;
    int courseID;
    long millis=System.currentTimeMillis();
    Date date = new Date(millis);
    AllCourses ac = new AllCourses();
    public boolean checkPhoneInDB(long phone){
        List<Long> list = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select phone from registration");
            while (rs.next()) {
                list.add((rs.getLong("phone")));
            }
            rs.close();
            statement.close();
            conn.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return list.contains(phone);
    }
    public void register() throws SQLException, ClassNotFoundException, IOException {
        System.out.println("\n Enter phone number");
        long phoneN0 = in.nextLong();
        if(checkPhoneInDB(phoneN0)){
            System.out.println("Mobile number already linked with anther account\n" +
                    "please login or use different account!");
            System.exit(0);
        }
        else {
            StudentIDGenerator sid = new StudentIDGenerator();
            System.out.println("We offer following courses: \n");
            System.out.println("Do you want to register? please enter yes/no");
            String isWilling = in.next();
            isWilling = isWilling.toLowerCase();
            String value;

            if (isWilling.equals("yes") || isWilling.contains("y")) {
                phone = phoneN0;
                System.out.println("Enter your name: \n");
                name = br.readLine();
                System.out.println("Available courses are : \n");
                ArrayList<String> courseNames = ac.getCoursesNameOnly();
                for (int i = 0; i < courseNames.size(); i++) {
                    System.out.println(i + "." + courseNames.get(i));
                }

                System.out.println("Enter course id");
                courseID = in.nextInt();

                value = courseNames.get(courseID);

                System.out.println("you have selected " + value);
                long studentID = sid.generateStudentId();
                insertDataIntoRegistrationTable(value, phone, name, courseID, studentID, date);
            }
            else{
                System.out.println("Check out our courses!");
                AllCourses ac = new AllCourses();
                ac.fetchAll();
            }
        }

    }
    public void insertDataIntoRegistrationTable(String value, long phone, String name, int courseID , long studentID , Date date ) throws SQLException , ClassNotFoundException {

        RegistrationIDGenerator idGenerator = new RegistrationIDGenerator();
        long id = idGenerator.generateId();

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
                String QUERY = "insert into registration values (?,?,?,?,?,?,?)";
                PreparedStatement statement = conn.prepareStatement(QUERY);
                long time = date.getTime();

                statement.setLong(1, id);
                statement.setString(2, value);
                statement.setLong(3, studentID);
                statement.setLong(4, phone);
                statement.setInt(5, courseID);
                statement.setDate(6,new java.sql.Date(time));
                statement.setString(7,name);

                statement.executeUpdate();
                System.out.println("inserted");
                System.out.println("Do you want to register for other course?");
                sdata.copyStudentData(studentID,phone,name);
                statement.close();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void removeCourseName(int id){

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
                String sql = "update registration set r_course = null where id = " + id ;
                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
//                PreparedStatement statementOne = conn.prepareStatement(sql);
//                statementOne.setInt(2,id);
//                conn.setAutoCommit(true);
//                statementOne.executeUpdate();
                System.out.println("Course deleted!!");
//                statementOne.close();
                conn.close();
            }catch (Exception e){e.printStackTrace();}

        }
        public void removeStudentID(int id){
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
                String sql = "update registration set s_id = null where id = " + id ;
                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
//                PreparedStatement statementOne = conn.prepareStatement(sql);
//                statementOne.setInt(3,id);
//                conn.setAutoCommit(true);
//                statementOne.executeUpdate();
                System.out.println("student sid removed!!");
//                statementOne.close();
                conn.close();
            }catch (Exception e){e.printStackTrace();}


        }



    }










