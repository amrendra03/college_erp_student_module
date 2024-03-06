package com.server.otp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPRepository extends JpaRepository<OTPModel,Long> {

    OTPModel findByUsername(String email);
}
