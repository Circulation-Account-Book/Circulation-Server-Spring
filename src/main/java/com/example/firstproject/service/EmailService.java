package com.example.firstproject.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public interface EmailService {

    //메일 내용 작성
    MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException;
    //MessagingException는 메세지 관련 예외처리를 위한 예외 클래스
    //UnsupportedEncodingException는 지원되지 않는 인코딩을 처리하기 위한 예외 클래스

    // 랜덤 인증 코드 전송
    String createKey();

    //메일 전송
    String sendSimpleMessage(String to) throws Exception;
}
