package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import resource.EmailResource;

import java.util.Properties;

@Configuration
public class EmailConfig {
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(EmailResource.getMailserver()); // 메일 도메인 서버 주소
        javaMailSender.setUsername(EmailResource.getUsername()); // 메일 유저 이름
        javaMailSender.setPassword(EmailResource.getUserpwd()); // 메일 패스워드
        javaMailSender.setPort(465); // 메일 인증서버 포트

        return javaMailSender;
    }
}