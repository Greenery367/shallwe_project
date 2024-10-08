package com.example.demo.service;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.config.RedisConfig;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSendService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private RedisConfig redisConfig;
    private int authNumber;

    /* 이메일 인증에 필요한 정보 */
    @Value("${spring.mail.username}")
    private String serviceName;

    /* 랜덤 인증번호 생성 */
    public void makeRandomNum() {
        Random r = new Random();
        String randomNumber = "";
        for(int i = 0; i < 6; i++) {
            randomNumber += Integer.toString(r.nextInt(10));
        }

        authNumber = Integer.parseInt(randomNumber);
    }
    
    /* 아이디 찾기 메일 전송 */
    public void idMailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
            helper.setFrom(setFrom); // service name
            helper.setTo(toMail); // customer email
            helper.setSubject(title); // email title
            helper.setText(content,true); // content, html: true
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // 에러 출력
        }
    }
    
    /* 임시 비밀번호 메일 전송 */
    public String pwMailSend(String email) {
        makeRandomNum();
        String customerMail = email;
        String title = "쉘위 비밀번호 찾기 요청 메일";
        String content =
        		"안녕하세요! 회원님. <br>" +
    	        		"회원님의 임시 비밀번호는 " + authNumber + "입니다. <br>" +
    	        		"보안을 위해 로그인 후 비밀번호 변경을 추천드립니다. <br>" +
    	        		"감사합니다.";
        mailSend(serviceName, customerMail, title, content);
        return Integer.toString(authNumber);
    }
    
    /* 이메일 전송 */
    public void mailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
            helper.setFrom(setFrom); // service name
            helper.setTo(toMail); // customer email
            helper.setSubject(title); // email title
            helper.setText(content,true); // content, html: true
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // 에러 출력
        }
        // redis에 5분 동안 이메일과 인증 코드 저장
        ValueOperations<String, String> valOperations = redisConfig.redisTemplate().opsForValue();
        valOperations.set(toMail, Integer.toString(authNumber), 300, TimeUnit.SECONDS);
    }

    /* 이메일 작성 */
    public String joinEmail(String email) {
        makeRandomNum();
        String customerMail = email;
        String title = "쉘위 이메일 인증번호 안내";
        String content =
                "안녕하세요 사용자님<br>" +
                        "<br>" +
                		"요청하신 이메일 인증 번호를 안내 드립니다.<br>" +
                		"아래 번호를 입력하여 쉘위 인증 절차를 마무리 해 주세요<br>" +
                        "인증 번호 : " + authNumber + "<br>" +
                        "<br>" +
                        "본 인증 번호는 5분 간 유효합니다.";
        mailSend(serviceName, customerMail, title, content);
        return Integer.toString(authNumber);
    }
    /* 인증번호 확인 */
    public Boolean checkAuthNum(String email, String authNum) {
        ValueOperations<String, String> valOperations = redisConfig.redisTemplate().opsForValue();
        String code = valOperations.get(email);
        if (Objects.equals(code, authNum)) {
            return true;
        } else return false;
    }
}
