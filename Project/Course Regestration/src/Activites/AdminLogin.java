package Activites;

import Adapters.CoursesAdapter.AllCourses;
import Adapters.StudentAdapter.StudentData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AdminLogin {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Scanner in = new Scanner(System.in);
    AllCourses ac = new AllCourses();
    StudentData studentData = new StudentData();

     /*-------------------------  Course operations------------------------------ */
    public void fetchAllCourses(){
        ac.fetchAll();
    }
    public void addNewCourse() throws IOException {

        System.out.println("Enter course name");
        String name = br.readLine();
        System.out.println("Enter courseFee");
        int fee = in.nextInt();
        System.out.println("Enter course duration");
        int duration = in.nextInt();
        System.out.println("New course added");

        ac.addCourse(name,fee,duration);
    }
    public void changeCourseDetails(){
        System.out.println("Enter the course ID");
        int id  = in.nextInt();
        ac.updateCourse(id);
        System.out.println("course updated");
    }
    public void removeCourse(){
        System.out.println("Select the course id which you want to remove ?");
        int removeID = in.nextInt();
        System.out.println("course deleting......");
        ac.deleteRecord(removeID);
    }
    public void getByID(){
        System.out.println("Enter the id of the course");
        int id = in.nextInt();
        ac.getByID(id);
    }

    /*-------------------------  Student operations------------------------------ */


    public void fetchAllStudents(){
        studentData.fetchAll();
    }

    public  void removeStudent(){
        System.out.println("Enter the student id which needs to be removed");
        int id = in.nextInt();
        studentData.removeStudent(id);
    }







}
