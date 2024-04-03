package com.example.StudentAppSpring.dao;

import com.example.StudentAppSpring.entity.Course;
import com.example.StudentAppSpring.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Scanner;

@Repository
@Transactional
public class AppDAOImpl implements AppDAO{

    Scanner scanner = new Scanner(System.in);

    private EntityManager entityManager;

    int idStudent;
    String firstName;
    String lastName;
    Course favoriteCourse;
    int idCourse;
    String courseName;
    String courseOwner;
    int courseRoom;


//    public AppDAOImpl() {
//    }

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void printActions() {

        System.out.println("\nAvailable actions:\n");
        System.out.println("0  - to shutdown\n" +
                "1  - insert a new course\n" +
                "2  - update an existing course\n" +
                "3  - delete an existing course\n" +
                "4  - find an existing course by id\n" +
                "5  - find an existing course by name\n" +
                "6  - find an existing course by course owner\n" +
                "7  - find an existing course by course room\n" +
                "8  - insert a new student\n" +
                "9  - update an existing student\n" +
                "10 - delete an existing student\n" +
                "11 - find an existing student by id\n" +
                "12 - find an existing student by first name\n" +
                "13 - find an existing student by last name\n" +
                "14 - print a list of available actions.");
        System.out.println();
        System.out.println("Choose your action: ");

    }

    @Override
    public void action() {

        boolean flag = true;
        while (flag) {

            System.out.println("\nEnter action:");

            try {
                int action = scanner.nextInt();
                scanner.nextLine();

                switch (action) {
                    case 0:
                        System.out.println("\nApp is closing...");
                        flag = false;
                        break;

                    case 1:
                        insertCourse();
                        break;

                    case 2:
                        updateCourse();
                        break;

                    case 3:
                        deleteCourse();
                        break;

                    case 4:
                        findCourseByID();
                        break;

                    case 5:
                        findCourseByName();
                        break;

                    case 6:
                        findCourseByOwner();
                        break;

                    case 7:
                        findCourseByRoom();
                        break;

                    case 8:
                        insertStudent();
                        break;

                    case 9:
                        updateStudent();
                        break;

                    case 10:
                        deleteStudent();
                        break;

                    case 11:
                        findStudentByID();
                        break;

                    case 12:
                        findStudentByFirstName();
                        break;

                    case 13:
                        findStudentByLastName();
                        break;

                    case 14:
                    default:
                        printActions();
                        break;
                }

            } catch (Exception e) {
                System.out.println("Error when typing the action: " + e.getMessage());
                flag = false;
            }
        }

    }


    @Override
    @Transactional
    public void insertStudent() {

        String firstName;
        String lastName;
        Course favoriteCourse;
        String idCourseString;
        int favoriteIDCourse;

        Student student = new Student();

        System.out.println("Do you want to add a new student?");

        System.out.println("Enter first name:");
        firstName = scanner.nextLine();

        System.out.println("Enter last name:");
        lastName = scanner.nextLine();

        System.out.println("Enter favorite course:");

        // Verify if the user type from console a String and then convert to an Int
        if (scanner.hasNextLine()) {
            idCourseString = scanner.nextLine();

            try {
                //Convert the String into an Int
                favoriteIDCourse = Integer.parseInt(idCourseString);
                favoriteCourse = entityManager.find(Course.class, favoriteIDCourse);

                if (favoriteCourse == null) {
                    System.out.println("Favorite idcourse " + favoriteIDCourse + " does not exist.");

                } else {

                    student.setFirstName(firstName);
                    student.setLastName(lastName);
                    student.setFavoriteCourse(favoriteCourse);
                    favoriteCourse.getStudents().add(student);
                    entityManager.persist(student);

                }

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input for idcourse: " + ex.getMessage());
            }

        }
    }

    @Override
    @Transactional
    public void updateStudent() {

        int idStudent;
        String firstName;
        String lastName;
        Course favoriteCourse;
        String idStudentString;
        String idCourseString;
        int favoriteIDCourse;

        Student student = new Student();

        System.out.println("Enter the idstudent which you need to update:");

        // Verify if the user type from console a String and then convert to an Int
        if (scanner.hasNextLine()) {
            idStudentString = scanner.nextLine();

            try {
                //Convert the String into an Int
                idStudent = Integer.parseInt(idStudentString);
                student = entityManager.find(Student.class, idStudent);

                if (student == null) {
                    System.out.println("Student with idstudent " + idStudent + " does not exist.");

                } else {

                    System.out.println("Enter the new first name: ");
                    firstName = scanner.nextLine();

                    System.out.println("Enter the new last name: ");
                    lastName = scanner.nextLine();

                    System.out.println("Enter the new favorite idcourse: ");

                    // Verify if the user type from console a String and then convert to an Int
                    if (scanner.hasNextLine()) {
                        idCourseString = scanner.nextLine();

                        try {
                            //Convert the String into an Int
                            favoriteIDCourse = Integer.parseInt(idCourseString);
                            favoriteCourse = entityManager.find(Course.class, favoriteIDCourse);

                            if (favoriteCourse == null) {
                                System.out.println("Favorite idcourse " + favoriteIDCourse + " does not exist.");

                            } else {
                                student.setFirstName(firstName);
                                student.setLastName(lastName);
                                student.setFavoriteCourse(favoriteCourse);
                                favoriteCourse.getStudents().add(student);
                                entityManager.merge(student);
                            }

                        } catch (NumberFormatException ex) {
                            System.out.println("Invalid input: " + ex.getMessage());
                        }
                    }
                }

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input: " + ex.getMessage());
            }

        }
    }

    @Override
    @Transactional
    public void deleteStudent() {

        int idStudent;
        String idStudentString;

        Student student = new Student();

        System.out.println("Enter the idstudent which you need to delete:");

        // Verify if the user type from console a String and then convert to an Int
        if (scanner.hasNextLine()) {
            idStudentString = scanner.nextLine();

            try {
                // Convert the String into an Int
                idStudent = Integer.parseInt(idStudentString);
                student = entityManager.find(Student.class, idStudent);

                if (student == null) {
                    System.out.println("Student with idstudent " + idStudent + " does not exist.");

                } else {
                    entityManager.remove(idStudent);
                    System.out.println("Student with idstudent " + idStudent + " was deleted.");
                }

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input: " + ex.getMessage());

            }
        }

    }

    @Override
    public Student findStudentByID() {

        int idStudent;
        String idStudentString;

        Student student = new Student();

        System.out.println("Enter the idstudent which you need to find:");

        // Verify if the user type from console a String and then convert to an Int
        if (scanner.hasNextLine()) {
            idStudentString = scanner.nextLine();

            try {
                // Convert the String into an Int
                idStudent = Integer.parseInt(idStudentString);
                student = entityManager.find(Student.class, idStudent);

                if (student == null) {
                    System.out.println("Student with idstudent " + idStudent + " does not exist.");

                } else {
                    return entityManager.find(Student.class, idStudent);
                }

                System.out.println(student);

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input: " + ex.getMessage());
                return null;
            }

        }
        return null;

    }

    @Override
    public Student findStudentByFirstName() {

        System.out.println("Enter the first name which you need to find:");
        firstName = scanner.nextLine().toLowerCase();
        return entityManager.find(Student.class, firstName);
    }

    @Override
    public Student findStudentByLastName() {

        System.out.println("Enter the last name which you need to find:");
        lastName = scanner.nextLine().toLowerCase();
        return entityManager.find(Student.class, lastName);
    }

    @Override
    @Transactional
    public void insertCourse() {

    }

    @Override
    @Transactional
    public void updateCourse() {

    }

    @Override
    @Transactional
    public void deleteCourse() {
        System.out.println("Enter the idcourse which you need to delete:");
        idCourse = scanner.nextInt();
        Course course = entityManager.find(Course.class, idCourse);
        entityManager.remove(course);
    }

    @Override
    public Course findCourseByID() {

        System.out.println("Enter the idcourse which you need to find:");

        // Verify if the user type from console a String and then convert to an Int
        if (scanner.hasNextLine()) {
            String idCourseString = scanner.nextLine();

            try {
                // Convert the String into an Int
                idCourse = Integer.parseInt(idCourseString);

                return entityManager.find(Course.class, idCourse);

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input: " + ex.getMessage());
            }

        } else {
            return null;
        }
        return null;
    }

    @Override
    public Course findCourseByName() {

        System.out.println("Enter the course name which you need to find:");
        courseName = scanner.nextLine().toLowerCase();
        return entityManager.find(Course.class, courseName);
    }

    @Override
    public Course findCourseByOwner() {

        System.out.println("Enter the course owner which you need to find:");
        courseOwner = scanner.nextLine().toLowerCase();
        return entityManager.find(Course.class, courseOwner);
    }

    @Override
    public Course findCourseByRoom() {

        System.out.println("Enter the course room which you need to find:");
        courseRoom = scanner.nextInt();
        return entityManager.find(Course.class, courseRoom);
    }
}
