package com.server.jwt;

import com.server.constant.API;
import com.server.dto.student.StudentDetailDTO;
import com.server.dto.student.StudentRegisterDTO;
import com.server.otp.OTPModel;
import com.server.otp.OTPRepository;
import com.server.service.student.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(API.auth)
@Validated
public class JwtController {

    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentService studentService;

    private Logger log = LoggerFactory.getLogger(JwtController.class);

    @Autowired
    private OTPRepository otpRepository;

    @PostMapping(API.login)
    public ResponseEntity<JwtResponseDTO> login(@RequestBody JwtRequestDTO jwtRequestDTO) throws Exception{

        log.debug("Auth Request: {}",jwtRequestDTO);
//        log.info("stage 1/3 controller login jwt authenticated processing...");
        try{
        this.authenticate(jwtRequestDTO.getUsername(),jwtRequestDTO.getPassword());
//        log.info("stage 1/3 controller login jwt authenticated completed.");
//        log.info("stage 2/3 controller login jwt UserDetail processing...");
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequestDTO.getUsername());
//        log.info("stage 2/3 controller login jwt UserDetail  completed.");
//        log.info("stage 3/3 controller login jwtToken generateToken processing...");
        String token = this.jwtToken.generateToken(userDetails);
//        log.info("stage 3/3 controller login jwtToken generateToken completed.");

        JwtResponseDTO res = new JwtResponseDTO();
        res.setToken(token);

        log.info("login completed jwt token generated and send in response.");
        return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (BadCredentialsException ex) {
            log.info("Authentication failed: Incorrect username or password.");
            JwtResponseDTO res = new JwtResponseDTO();
            res.setToken("Incorrect username and password !!!");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle other exceptions as needed
            JwtResponseDTO res = new JwtResponseDTO();
            res.setToken("Internal Server error !!!");
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);  }
    }

    private void authenticate (String username, String password)throws Exception{

//        log.info("stage 1.1/3  authenticate method controller processing...");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        try{
//            log.info("stage 1.2/3 authenticate method controller successfully confirm user credential.");
            this.authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException ex){
//            log.info("stage 1.2/3 authenticate method controller failed not find user credential.");
            log.info("Invalid Detail !!");
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @PostMapping(API.register)
    public ResponseEntity<?> create(@Valid @RequestBody StudentRegisterDTO st) {

        log.info("Creating a new student:{}",st);

        StudentDetailDTO res = this.studentService.creat(st);

        log.info("Created student: {}",res);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping(API.checkToken)
    public ResponseEntity<?> checkTokenValidity(@RequestHeader("Authorization") String token) {

        Map<String,String> res = new HashMap<>();

        // Extract token from the Authorization header (remove "Bearer " prefix)
        String jwtToken = token.substring(7);

        // Log token for debugging
        log.debug("Received token: {}", jwtToken);

        // Get the username from the token
        String username = this.jwtToken.getUsernameFromToken(jwtToken);

        res.put("Username",username);

        try {

            // Log username for debugging
            log.debug("Username extracted from token: {}", username);

            // Retrieve UserDetails from your user service or repository
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // Log UserDetails for debugging
            log.debug("UserDetails retrieved: {}", userDetails);

            //Check token is valid or not

            Date date = this.jwtToken.getExpirationDateFromToken(jwtToken);
            res.put("Expiration Time",""+date.toString());

            // Validate the token
            if (this.jwtToken.validateToken(jwtToken, userDetails)) {
                res.put("token",jwtToken);
                res.put("Valid","true");
                log.info("Token is valid for user: {}", username);
                return new ResponseEntity<>(res,HttpStatus.OK);
            } else {
                log.warn("Token validation failed for user: {}", username);
                return ResponseEntity.badRequest().body("Token is invalid.");
            }
        } catch (Exception e) {
            // Log exception for debugging
            log.error("Exception occurred while processing token", e);

            res.put("error",e.getMessage());

            // Handle exceptions, e.g., if the Authorization header is missing or the token is not well-formed
            return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/forget_password")
//    public ResponseEntity<?> forgetPassword(@RequestBody String password){
//        OTPModel res = otpRepository.findByUsername(req.getUsername());
//
//    }
}
