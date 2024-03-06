package com.server.otp;
import lombok.Data;

@Data
public class OTPVerifyDTO {

    private String username;

    private String OTP;
}
