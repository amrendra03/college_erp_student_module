package com.server.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NotificationDto {
   private Long id;
   private String message;
   private Date timestamp;
   private boolean active;
}
