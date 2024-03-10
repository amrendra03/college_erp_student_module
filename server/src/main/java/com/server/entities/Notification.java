package com.server.entities;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Notification {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String message;

   @Column(nullable = false)
   private Date timestamp;

   @Column(nullable = false)
   private boolean active;

   public boolean isOlderThanFiveDays() {
      long currentTimeMillis = System.currentTimeMillis();
      long notificationTimeMillis = timestamp.getTime();
      long fiveDaysInMillis = 5 * 24 * 60 * 60 * 1000; // 5 days in milliseconds

      return currentTimeMillis - notificationTimeMillis > fiveDaysInMillis;
   }
}
