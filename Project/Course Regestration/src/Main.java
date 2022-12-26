import Activites.AdminLogin;
import Adapters.RegistrationAdapters.RegisterUser;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        AdminLogin admin = new AdminLogin();
        RegisterUser rg = new RegisterUser();
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome");
        System.out.println("1: Login \t\t 2: REGISTER \t\t 3: checkout our courses?");
        int login = in.nextInt();
        if (login == 1) {
            System.out.println("1.Login  as Admin ");
            System.out.println("2.Login as student ");
            int loginAs = in.nextInt();


            // admin login operations are done here :-

            if (loginAs == 1) {
                // ---------------------------------  course operations -----------------------------------

                System.out.println("Which operation you want to perform?\n ");
                System.out.println("1.Course Operation \t\t 2. Student Operation");
                int operationType = in.nextInt();
                if (operationType == 1) {
                    System.out.println("1.Fetch all Course Details \t\t 2.Update Course \t\t 3.Remove Course \t\t 4.Add Course \t\t 5.Get Course by id");
                    int courseInput = in.nextInt();
                    while (true) {

                        // Fetch all courses
                        if (courseInput == 1) {
                            admin.fetchAllCourses();
                            break;
                        }

                        // Update course
                        else if (courseInput == 2) {
                            admin.changeCourseDetails();
                            break;
                        }
                        // Remove course
                        else if (courseInput == 3) {
                            admin.removeCourse();
                            break;
                        }

                        // Add new course
                        else if (courseInput == 4) {
                            admin.addNewCourse();
                            break;
                        }

                        // Get course details by id
                        else if (courseInput == 5) {
                            admin.getByID();
                            break;
                        } else {
                            System.out.println("Please enter a valid choice");
                            System.out.println("1.Fetch all Course Details \t\t 2.Update Course \t\t 3.Remove Course \t\t 4.Add Course \t\t 5.Get Course by id");
                            courseInput = in.nextInt();
                        }
                    }
                }else if(operationType == 2){
                    System.out.println("1.Fetch all Student Details \t\t 2.Update Course \t\t 3.Remove Student \t\t 4.Add Course \t\t 5.Get Course by id");
                    int studentInput = in.nextInt();
                    if(studentInput == 1) {
                        admin.fetchAllStudents();
                    }
                    else if(studentInput == 3){
                        admin.removeStudent();
                    }
                }
            }



































            else if(loginAs == 2){
                // create student class and add its operations
                long phone = in.nextLong();
                if(rg.checkPhoneInDB(phone) == true){
                    // show studentDetails();
                }
                else {
                    System.out.println("Account doesn't exist! " +
                            " create new account?"
                            + "Enter y for yes and n for no");
                    String willing = in.next();
                    willing = willing.toLowerCase();
                    if (willing.equals("yes") || willing.contains("y")) {
                        rg.register();
                    }
                }
            }
        }
        else if (login == 2) {
            rg.register();
            System.out.println("Want to check our courses? enter YES/NO ");
            String checkOut = in.next();
            checkOut = checkOut.toLowerCase();
            if(checkOut.equals("yes") || checkOut.equals("y")){
                admin.fetchAllCourses();
            }

        } else if(login == 3){
            System.out.println("currently we offer following courses!");
            admin.fetchAllCourses();

        }
        else{
            System.out.println("Please enter a valid choice!!");
            System.exit(0);
        }

        in.close();


        }

}