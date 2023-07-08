package com.example.firstproject.service.impl;

import com.example.firstproject.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service //bean을 만들고, 객체를 생성
public class EmailServiceImpl implements EmailService {//extends는 클래스 확장, implements는 인터페이스를 구현

    @Autowired
    JavaMailSender emailSender;//의존성 주입

    private String ePw; //인증번호

    @Override
    public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);  //보내는 대상
        message.setSubject("Froot 회원가입 이메일 인증");

        String msg = "";
        msg += "<div style='margin:100px'>";
        msg += "<h1>안녕하세요</h1>";
        msg += "<h3>회원가입 인증 코드 입니다.</h3>";
        msg += "<br>";
        msg += ePw + "<div><br/>";
        msg += "</div>";

        // 내용, charset 타입, subtype / StringBuffer 타입 인자로 못들어간다.
        message.setText(msg, "utf-8", "html");// 내용, charset 타입, subtype
        // 보내는 사람의 이메일 주소, 보내는 사람 이름
        message.setFrom(new InternetAddress("yongwoo1207@naver.com", "Froot admin"));// 보내는 사람

        return message;
    }

    // 랜덤 인증 코드 만들기
    @Override
    public String createKey() {
        Random random = new Random();
        int num = random.nextInt(8) * 12345;
        String key = String.valueOf(num);
        return key;
    }

    // 메일 발송
    // sendSimpleMessage 의 매개변수로 들어온 to 는 곧 이메일 주소가 되고,
    // MimeMessage 객체 안에 내가 전송할 메일의 내용을 담는다.
    // 그리고 bean 으로 등록해둔 javaMail 객체를 사용해서 이메일 send!!
    @Override
    public String sendSimpleMessage(String to) throws Exception {
        ePw = createKey();
        MimeMessage message = createMessage(to); //메일 발송
        try { //여긴 예외`처리 그냥 복사해옴.
            emailSender.send(message);
        } catch (MailException es){
            es.printStackTrace();
            throw new IllegalAccessException();
        }

        return ePw; // 메일로 보냈던 인증 코드를 서버로 반환
    }
}



/*    @Override
    public void sendEmail(String to, String subject, String message){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("yongwoo1207@naver.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        this.mailSender.send(simpleMailMessage);
    }*/