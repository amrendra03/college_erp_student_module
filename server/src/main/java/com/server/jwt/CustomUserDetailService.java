package com.server.jwt;

import com.server.entities.student.StudentDetail;
import com.server.repository.student.StudentDetailRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private StudentDetailRepo studentDetailRepo;

    private Logger log  = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Processing Authentication CustomUserDetailService Stage.");

        StudentDetail user = this.studentDetailRepo.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        log.info("CustomUserDetailService stage completed successfully.");
        UserDetails res = User.builder().username(user.getEmail()).password(user.getPassword()).roles(user.getRole()).build();
        return res;
    }
}
