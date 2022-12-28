package Adapters.CoursesAdapter;


import Models.Courses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AllCourses {
List<Courses> courses = new ArrayList<>();
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
Scanner in = new Scanner(System.in);
    public void fetchAll(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String sql = "select * from courses";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int courseId = rs.getInt(1);
                String name = rs.getString(2);
                int fee = rs.getInt(3);
                int duration = rs.getInt(4);
                courses.add(new Courses(courseId,name,fee,duration));
            }
            statement.close();
            conn.close();

            System.out.println("| course ID" + "|" + "course name" + "|" + " course fee" + "|" +"course duration |");
            System.out.println("--------------------------------------------------------------");
            for (Courses course : courses){
                System.out.println( "\n\t\t" + course.getCourseId() + "\t\t" + course.getCourseName() + "\t\t" + course.getCourseFee() + "\t\t" +course.getCourseDuration() );
            }
            System.out.println("--------------------------------------------------------------");

        }catch (Exception e){e.printStackTrace();}
    }
    public void addCourse(String name , int fee , int duration) {
        CourseIDGenerator cid = new CourseIDGenerator();
        try{
            int id = cid.getCourseId();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String sql = "insert into courses values(?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setString(2,name);
            statement.setInt(3,fee);
            statement.setInt(4,duration);
            statement.executeUpdate();
            System.out.println("course added!!!");
            statement.close();
            conn.close();

        }catch (Exception e) {e.printStackTrace();}
    }
    public void updateCourse(int id){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            System.out.println("\nChoose parameters which are needed to be updated\n");
            System.out.println("1.Course name \t\t 2.Course fee \t\t 3.Course Duration");
            int n = in.nextInt();
            String sql = "";
            if(n == 1){
                System.out.println("Enter your new course name");
                String courseName = br.readLine();
                sql = "update courses set cname = '" + courseName + "' where cid = " +id ;
            }
            else if(n == 2){
                System.out.println("Enter your new course fee");
                int courseFee = in.nextInt();
                sql = "update courses set cfee = '" + courseFee + "' where cid = " +id ;
            }else if(n == 3){
                System.out.println("Enter your new course duration");
                int courseDuration = in.nextInt();
                sql = "update courses set cduration = '" + courseDuration + "' where cid = " +id ;
            }
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        }catch (Exception e){e.printStackTrace();}

    }
    public void deleteRecord(int id){

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String sql = "delete from courses where cid = ?" ;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            conn.setAutoCommit(true);
            statement.executeUpdate();
            System.out.println("Course deleted!!");
            statement.close();
            conn.close();

        }catch (Exception e){e.printStackTrace();}

    }
    public void getByID(int id){

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String sql = "select * from courses where cid = " + id;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            System.out.println("| course ID " + "|" + " course name " + "|" + " course fee " + "|" +" course duration |");
            System.out.println("--------------------------------------------------------------");
            while (rs.next()) {
//                int courseId = rs.getInt(1);
                System.out.println("\t"+rs.getInt(1)+"\t\t\t"+  rs.getString(2) + "\t\t\t" + rs.getInt(3) + "\t\t\t  " +rs.getInt(4) );
            }
            System.out.println("--------------------------------------------------------------");
        }catch (Exception e){e.printStackTrace();}

    }
    public ArrayList<String> getCoursesNameOnly() {
        ArrayList<String> courses = new ArrayList<>();
        try{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
        String sql = "select cname from courses";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            String courseName = rs.getString("cname");
            courses.add(courseName);
        }
    }catch (Exception e){e.printStackTrace();}

        return courses;

    }

    public void AllCourses(){
        try {
            ArrayList<ArrayList<String>> tableData = new ArrayList<>();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String sql = "SELECT * from courses";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                ArrayList<String> rowData = new ArrayList<>();
                for(int col = 1 ; col <= rs.getMetaData().getColumnCount() ; col++){
                    rowData.add(rs.getString(col));
                }
                tableData.add(rowData);
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\Spring5Learning\\Project\\Course Regestration\\src\\Files\\Courses\\allcourses.txt"));
            for (ArrayList<String> rowData : tableData) {
                for (String cell : rowData) {
                    writer.append(cell + ",");
                }
                writer.newLine();
            }

            writer.close();
            rs.close();
            statement.close();
            conn.close();




        }catch (Exception e){e.printStackTrace();}
    }



}
