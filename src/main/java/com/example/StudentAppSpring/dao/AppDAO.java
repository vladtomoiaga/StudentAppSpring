package com.example.StudentAppSpring.dao;

import com.example.StudentAppSpring.entity.Course;
import com.example.StudentAppSpring.entity.Student;

public interface AppDAO {

    void printActions();
    void action();
    void insertStudent();
    void updateStudent();
    void deleteStudent();
    void findStudentByID();
    void findStudentByFirstName();
    void findStudentByLastName();
    void insertCourse();
    void updateCourse();
    void deleteCourse();
    void findCourseByID();
    void findCourseByName();
    void findCourseByOwner();
    void findCourseByRoom();

}
