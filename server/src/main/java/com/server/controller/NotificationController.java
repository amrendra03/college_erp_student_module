package com.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.server.dto.ApiResponse;
import com.server.dto.NotificationDto;
import com.server.service.NotificationService;

@RestController
@RequestMapping("/notification")
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
   public ResponseEntity<List<NotificationDto>> getAll( @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int pageSize) {

      List<NotificationDto> x = this.notificationService.getall(page, pageSize);

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
