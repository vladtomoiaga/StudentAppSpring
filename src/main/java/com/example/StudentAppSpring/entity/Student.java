package com.example.StudentAppSpring.entity;

import jakarta.persistence.*;

@NamedQuery(name="findByFirstName", query="SELECT student FROM Student student WHERE student.firstName = :name")
@NamedQuery(name="findByLastName", query="SELECT student FROM Student student WHERE student.lastName = :name")
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstudent")
    private int idStudent;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "favorite_course", referencedColumnName = "idcourse")
    private Course favoriteCourse;

    public Student() {
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Course getFavoriteCourse() {
        return favoriteCourse;
    }

    public void setFavoriteCourse(Course favoriteCourse) {
        this.favoriteCourse = favoriteCourse;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idstudent=" + idStudent +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", favoriteCourse=" + favoriteCourse +
                '}';
    }
}
