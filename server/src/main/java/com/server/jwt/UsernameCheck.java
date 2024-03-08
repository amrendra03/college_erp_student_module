package com.server.jwt;


import com.server.entities.student.StudentDetail;
import com.server.repository.student.StudentDetailRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsernameCheck {


    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private StudentDetailRepo studentDetailRepo;

    private Logger log = LoggerFactory.getLogger(UsernameCheck.class);

    public boolean checkUsernameWithRollNo(String token,String rollNo){
        log.info("Validating the username from  token to to student id.");

        try{
            String email = jwtToken.getUsernameFromToken(token);
            log.info("username: {}",email);
            StudentDetail studentDetail = studentDetailRepo.findByEmail(email);
            log.info("student RollNo: {}",studentDetail.getStudentId());
            if(studentDetail.getRollNo().equals(rollNo)){
                log.info("token: {} equal userInput: {}",studentDetail.getStudentId(),rollNo);
                return true;
            }else{
                throw  new BadCredentialsException("Token is invalid with userData");
            }
        }catch (Exception ex){
            throw  new BadCredentialsException("Token is invalid with userData");
        }
    }
    public boolean checkUsernameWithStudentId(String token,Long studentId){
        log.info("Validating the username from  token to to student id.");
        try{
            String email = jwtToken.getUsernameFromToken(token);
            log.info("username: {}",email);
            StudentDetail studentDetail = studentDetailRepo.findByEmail(email);
            log.info("student Id: {}",studentDetail.getStudentId());

            if(studentDetail.getStudentId()==studentId){
                log.info("token: {} equal userInput: {}",studentDetail.getStudentId(),studentId);
                return true;
            }else{
                log.info("not matched token and student ");
                throw  new BadCredentialsException("Token is invalid with userData");
            }
        }catch (Exception ex){
            throw  new BadCredentialsException("Token is invalid with userData");
        }

    }

}
