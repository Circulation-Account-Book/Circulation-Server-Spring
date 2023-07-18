package com.example.firstproject.controller;

import com.example.firstproject.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class EmailController {

    // 회원가입 메일 서비스
    @Autowired
    EmailServiceImpl EmailServiceImpl;

    @PostMapping("login/mailConfirm")
    @ResponseBody
    String mailConfirm(@RequestParam("email") String email) throws Exception {

        String code = EmailServiceImpl.sendSimpleMessage(email);
        System.out.println("인증코드 : " + code);
        return code;
    }

}
