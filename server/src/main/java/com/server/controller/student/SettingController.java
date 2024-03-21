package com.server.controller.student;

import com.server.constant.API;
import com.server.dto.student.StudentDetailDTO;
import com.server.service.setting.SettingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(API.student+"/setting")
public class SettingController {

    private SettingService settingService;

    @PostMapping("/{rollNo}")
    public ResponseEntity<?> updateStudentDetail(@PathVariable String rollNo, @RequestBody StudentDetailDTO req){
        StudentDetailDTO res = settingService.update(rollNo,req);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
