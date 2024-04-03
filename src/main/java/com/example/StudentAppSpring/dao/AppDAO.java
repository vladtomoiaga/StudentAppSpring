package com.example.StudentAppSpring.dao;

import com.example.StudentAppSpring.entity.Course;
import com.example.StudentAppSpring.entity.Student;

public interface AppDAO {

    void printActions();
    void action();
    void insertStudent();
    void updateStudent();
    void deleteStudent();
    Student findStudentByID();
    Student findStudentByFirstName();
    Student findStudentByLastName();
    void insertCourse();
    void updateCourse();
    void deleteCourse();
    Object findCourseByID();
    Course findCourseByName();
    Course findCourseByOwner();
    Course findCourseByRoom();

}
