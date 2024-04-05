package com.example.StudentAppSpring.dao;

import com.example.StudentAppSpring.entity.Course;
import com.example.StudentAppSpring.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;

@Repository
@Transactional
public class AppDAOImpl implements AppDAO{

    Scanner scanner = new Scanner(System.in);

    private EntityManager entityManager;

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

        Student student;

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

        Student student;

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
                    entityManager.remove(student);
                    System.out.println("Student with idstudent " + idStudent + " was deleted.");
                }

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input: " + ex.getMessage());
            }

        }

    }

    @Override
    public void findStudentByID() { //void

        int idStudent;
        String idStudentString;

        Student student;

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
                    System.out.println(student);
                }

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input: " + ex.getMessage());
            }

        }

    }

    @Override
    public void findStudentByFirstName() {

        String firstName;

        System.out.println("Enter the first name which you need to find:");
        firstName = scanner.nextLine();

        TypedQuery<Student> query = entityManager.createNamedQuery("findByFirstName", Student.class);
        query.setParameter("name", firstName);
        List<Student> results = query.getResultList();
        System.out.println(results);
    }

    @Override
    public void findStudentByLastName() {

        String lastName;

        System.out.println("Enter the last name which you need to find:");
        lastName = scanner.nextLine();

        TypedQuery<Student> query = entityManager.createNamedQuery("findByLastName", Student.class);
        query.setParameter("name", lastName);
        List<Student> results = query.getResultList();
        System.out.println(results);
    }

    @Override
    @Transactional
    public void insertCourse() {

        String courseName;
        String courseOwner;
        int courseRoom;
        String courseRoomString;

        Course course = new Course();

        System.out.println("Do you want to add a new course?");

        System.out.println("Enter the course name:");
        courseName = scanner.nextLine();

        System.out.println("Enter the course owner:");
        courseOwner = scanner.nextLine();

        System.out.println("Enter the course room:");

        // Verify if the user type from console a String and then to convert to an Int
        if (scanner.hasNextLine()) {
            courseRoomString = scanner.nextLine();

            try {
                // Convert the String into an Int
                courseRoom = Integer.parseInt(courseRoomString);

                course.setCourseName(courseName);
                course.setCourseOwner(courseOwner);
                course.setCourseRoom(courseRoom);
                entityManager.persist(course);

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input: " + ex.getMessage());
            }

        }

    }

    @Override
    @Transactional
    public void updateCourse() {

        int idCourse;
        String courseName;
        String courseOwner;
        int courseRoom;
        String idCourseString;
        String courseRoomString;

        Course course = new Course();

        System.out.println("Enter the idcourse which you need to update:");

        // Verify if the user type from console a String and then convert to an Int
        if (scanner.hasNextLine()) {
            idCourseString = scanner.nextLine();

            try {
                // Convert the String to an Int
                idCourse = Integer.parseInt(idCourseString);
                course = entityManager.find(Course.class, idCourse);

                if (course == null) {
                    System.out.println("Course with idcourse " + idCourse + " does not exist.");

                } else {
                    System.out.println("Enter the new course name: ");
                    courseName = scanner.nextLine();

                    System.out.println("Enter the new course owner: ");
                    courseOwner = scanner.nextLine();

                    System.out.println("Enter the new course room: ");

                    // Verify if the user type from console a number
                    if (scanner.hasNextLine()) {
                        courseRoomString = scanner.nextLine();

                        try {
                            // Convert the String into an Int
                            courseRoom = Integer.parseInt(courseRoomString);

                            course.setCourseName(courseName);
                            course.setCourseOwner(courseOwner);
                            course.setCourseRoom(courseRoom);
                            entityManager.merge(course);

                        } catch (NumberFormatException ex) {
                            System.out.println("Invalid input: " + ex.getMessage());
                        }

                    } else {
                        System.out.println("Please enter a correct value for course room.");
                    }
                }

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input: " + ex.getMessage());
            }

        }

    }

    @Override
    @Transactional
    public void deleteCourse() {

        int idCourse;
        String idCourseString;

        Course course = new Course();

        System.out.println("Enter the idcourse which you need to delete:");

        if (scanner.hasNextLine()) {
            idCourseString = scanner.nextLine();

            try {
                // Convert the String to an Int
                idCourse = Integer.parseInt(idCourseString);
                course = entityManager.find(Course.class, idCourse);

                if (course == null) {
                    System.out.println("Course with idcourse " + idCourse + " does not exist.");

                } else {
                    entityManager.remove(course);
                    System.out.println("Course with idCourse: " + idCourse + " was deleted.");
                }

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input: " + ex.getMessage());
            }

        }

    }

    @Override
    public void findCourseByID() {

        int idCourse;
        String idCourseString;

        Course course;

        System.out.println("Enter the idcourse which you need to find:");

        // Verify if the user type from console a String and then convert to an Int
        if (scanner.hasNextLine()) {
            idCourseString = scanner.nextLine();

            try {
                // Convert the String into an Int
                idCourse = Integer.parseInt(idCourseString);
                course = entityManager.find(Course.class, idCourse);

                if (course == null) {
                    System.out.println("Course with idcourse " + idCourse + " does not exist.");

                } else {
                    System.out.println(course);
                }

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input: " + ex.getMessage());
            }
        }

    }

    @Override
    public void findCourseByName() {

        String courseName;

        System.out.println("Enter the course name which you need to find:");
        courseName = scanner.nextLine();

        TypedQuery<Course> query = entityManager.createNamedQuery("findByCourseName", Course.class);
        query.setParameter("name", courseName);
        List<Course> results = query.getResultList();
        System.out.println(results);

    }

    @Override
    public void findCourseByOwner() {

        String courseOwner;

        System.out.println("Enter the course owner which you need to find:");
        courseOwner = scanner.nextLine();

        TypedQuery<Course> query = entityManager.createNamedQuery("findByCourseOwner", Course.class);
        query.setParameter("owner", courseOwner);
        List<Course> results = query.getResultList();
        System.out.println(results);

    }

    @Override
    public void findCourseByRoom() {

        int courseRoom;
        String courseRoomString;

        System.out.println("Enter the course room which you need to find:");

        // Verify if the user type from console a number
        if (scanner.hasNextLine()) {
            courseRoomString = scanner.nextLine();

            try {
                // Convert the String into an Int
                courseRoom = Integer.parseInt(courseRoomString);

                TypedQuery<Course> query = entityManager.createNamedQuery("findByCourseRoom", Course.class);
                query.setParameter("room", courseRoom);
                List<Course> results = query.getResultList();
                System.out.println(results);

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input: " + ex.getMessage());
            }

        }

    }

}
