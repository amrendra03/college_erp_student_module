package com.server.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Student {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long rollNo;

   private String name;

   private String course;

   private int sem;

   private double fee;

   private double fine;

   private int score;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "course_id")
   private Course enrolledCourse;

   @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Status> statuses;

}
