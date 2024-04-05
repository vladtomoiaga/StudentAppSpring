package com.example.StudentAppSpring.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@NamedQuery(name="findByCourseName", query="SELECT course FROM Course course WHERE LOWER (course.courseName) = :name")
@NamedQuery(name="findByCourseOwner", query="SELECT course FROM Course course WHERE LOWER (course.courseOwner) = :owner")
@NamedQuery(name="findByCourseRoom", query="SELECT course FROM Course course WHERE course.courseRoom = :room")
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcourse")
    private int idCourse;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_owner")
    private String courseOwner;

    @Column(name = "course_room")
    private int courseRoom;

    @OneToMany(mappedBy = "favoriteCourse",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

    public Course() {
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseOwner() {
        return courseOwner;
    }

    public void setCourseOwner(String courseOwner) {
        this.courseOwner = courseOwner;
    }

    public int getCourseRoom() {
        return courseRoom;
    }

    public void setCourseRoom(int courseRoom) {
        this.courseRoom = courseRoom;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "idcourse=" + idCourse +
                ", courseName='" + courseName + '\'' +
                ", courseOwner='" + courseOwner + '\'' +
                ", courseRoom=" + courseRoom +
                '}';
    }
}
