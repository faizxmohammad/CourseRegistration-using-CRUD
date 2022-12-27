package Adapters.PaymentAdapters;

import java.sql.*;

public class PaymentsData {
    public void getPaymentsData(int id){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String query  = " select * from payments where sid = "+ id;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            System.out.println("\t | First Installment | \t\t Second Installment  | \t\t Sid |");
            System.out.println("--------------------------------------------------------");
            while(rs.next()){
                String first = rs.getString("first");
                String second = rs.getString("second");
                int sid = rs.getInt("sid");
                System.out.println(first + "\t\t\t" + second + "\t\t\t" + sid);


            }
            System.out.println("--------------------------------------------------------");

            rs.close();
            statement.close();
            conn.close();

        }catch (Exception e){e.printStackTrace();}
    }
    public void studentsPaidFirst(){
        int count = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String query = "select count(first) from payments where first is not null";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                 count = rs.getInt(1);
                System.out.println("total no of students paid first installment are : "+ count);
            }

            rs.close();
            statement.close();
            conn.close();

        }catch(Exception e){e.printStackTrace();}
    }
    public void studentsPaidBoth(){
        int count = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");

            String query = "Select count(*) from payments where first is not null and  second  is not null";

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                count = rs.getInt(1);
            }
            System.out.println("total no of students paid both installment are : "+ count);

            rs.close();
            statement.close();
            conn.close();
        }catch(Exception e){e.printStackTrace();}

    }
    public void notPaidFirst(){
        int count = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String query = "Select count(*) from payments where first is  null";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                count = rs.getInt(1);
            }
            System.out.println("total no of students have not first installment are : "+ count);


            rs.close();
            statement.close();
            conn.close();
        }catch(Exception e){e.printStackTrace();}
    }
    public void notPaidSecond(){
        int count = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String query = "Select count(*) from payments where second is  null";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                count = rs.getInt(1);
            }
            System.out.println("total no of students have not second installment are :"+ count);

            rs.close();
            statement.close();
            conn.close();

        }catch(Exception e){e.printStackTrace();}
    }
    public void notPaidBoth(){
        int count = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String columnOne = "first";
            String colunmTwo = "second";
            String query = "Select count(*) from payments where first is null and second is  null";

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                count = rs.getInt(1);
            }
            System.out.println("total no of students have not paid both installment are : "+ count);
            rs.close();
            statement.close();
            conn.close();

        }catch(Exception e){e.printStackTrace();}
    }
    public void SinglePaidStudentIDS(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String query = "select sid from payments where first is not null";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            System.out.println("Student who paid first installment are :");
            while(rs.next()){
                int id =  rs.getInt(1);
                System.out.println("Student id : "+ id);
            }
            rs.close();
            statement.close();
            conn.close();
        }catch(Exception e){e.printStackTrace();}
    }
    public void SinglenotPaidStudentIDS(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String query = "select sid from payments where second is  null";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            System.out.println("Student who did not paid second installment are :");
            while(rs.next()){
                int id =  rs.getInt(1);
                System.out.println("Student id : "+ id);
            }
            rs.close();
            statement.close();
            conn.close();

        }catch(Exception e){e.printStackTrace();}
    }
    public void BothPaidStudentIDS(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String query = "Select sid from payments where first is not null and second is not null";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt(1);
                System.out.println("Student is : "+ id);
            }
            rs.close();
            statement.close();
            conn.close();
        }catch(Exception e){e.printStackTrace();}
    }
    public void BothNotPaidStudentIDS(){

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "faiz", "faiz");
            String query = "Select sid from payments where first is null and second is  null";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt(1);
                System.out.println("Student is : "+ id);
            }
            rs.close();
            statement.close();
            conn.close();

        }catch(Exception e){e.printStackTrace();}


    }

}
