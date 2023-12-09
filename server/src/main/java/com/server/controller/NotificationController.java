package com.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dto.ApiResponse;
import com.server.dto.NotificationDto;
import com.server.service.NotificationService;

@RestController
@RequestMapping("/notifi")
public class NotificationController {

   @Autowired
   private NotificationService notificationService;

   // create
   @PostMapping("/")
   public ResponseEntity<NotificationDto> create(@RequestBody NotificationDto notifi) {

      NotificationDto x = this.notificationService.create(notifi);

      return new ResponseEntity<NotificationDto>(x, HttpStatus.OK);
   }

   // get all
   @GetMapping("/")
   public ResponseEntity<List<NotificationDto>> getAll() {

      List<NotificationDto> x = this.notificationService.getall();

      return new ResponseEntity<>(x, HttpStatus.OK);
   }

   // delete
   @DeleteMapping("/{id}")
   public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
      this.notificationService.delete(id);

      return new ResponseEntity<ApiResponse>(new ApiResponse("Successfully deleted Notification.", true),
            HttpStatus.OK);
   }

}
