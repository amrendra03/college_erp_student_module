package com.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dto.ApiResponse;
import com.server.dto.StatusDto;
import com.server.service.StatusService;

@RestController
@RequestMapping("/status")
public class StatusController {

   @Autowired
   private StatusService statusService;

   // create
   @PostMapping("/")
   public ResponseEntity<StatusDto> create(@RequestBody StatusDto status) {

      System.out.println("from create... status");
      System.out.println(status);

      StatusDto x = this.statusService.create(status);

      return new ResponseEntity<>(x, HttpStatus.CREATED);
   }

   // update
   @PutMapping("/{rollNo}")
   public ResponseEntity<StatusDto> update(@RequestBody StatusDto status, @PathVariable Long rollNo) {
      StatusDto x = this.statusService.update(status, rollNo);

      return new ResponseEntity<>(x, HttpStatus.OK);
   }

   // get all list
   @GetMapping("/{rollNo}")
   public ResponseEntity<List<StatusDto>> getAll(@PathVariable Long rollNo) {

      System.out.println("from status list............");

      List<StatusDto> x = this.statusService.getAll(rollNo);

      for (StatusDto i : x) {
         System.out.println(i);
      }

      return new ResponseEntity<>(x, HttpStatus.OK);
   }

   // delete

   @DeleteMapping("/{rollNo}/{id}")
   public ResponseEntity<?> delete(@PathVariable Long rollNo, @PathVariable Long id) {

      System.out.println("from delete status....");
      System.out.println(rollNo + " " + id);

      this.statusService.delete(id, rollNo);
      ApiResponse res = new ApiResponse("Successfully delete", true);

      return new ResponseEntity<>(res, HttpStatus.OK);
   }

}
