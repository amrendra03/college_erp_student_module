package com.server.controller.response;

import com.server.constant.API;
import com.server.dto.StudentResponseDto;
import com.server.jwt.JwtToken;
import com.server.service.reponse.ResponseStudentDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API.student)
public class ResponseStudentDataController {

    private Logger log = LoggerFactory.getLogger(ResponseStudentDataController.class);

    @Autowired
    private ResponseStudentDataService responseStudentDataService;
    @Autowired
    private JwtToken jwtToken;

    @GetMapping("/data")
    public ResponseEntity<?>getStudentData(@RequestHeader("Authorization") String authorizationHeader){
        log.info("request the student detail.");
        String token = authorizationHeader.replace("Bearer","");
        String email = jwtToken.getUsernameFromToken(token);
        StudentResponseDto res =  responseStudentDataService.getStudentData(email);
        log.info("Successfully fetched student data. {}",res);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
