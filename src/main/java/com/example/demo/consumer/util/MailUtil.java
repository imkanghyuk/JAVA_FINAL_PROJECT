package com.example.demo.consumer.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.example.demo.consumer.dto.Mail;

@Component
public class MailUtil {
	@Autowired
	private JavaMailSender javaMailSender;
	
	private void sendMail(Mail mail) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, false, "utf-8");
			helper.setFrom(mail.getFrom());
			helper.setTo(mail.getTo());
			helper.setSubject(mail.getSubject());
			helper.setText(mail.getText(), true);
			javaMailSender.send(message);
		} catch(MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void sendFindIdMail(String from, String to, String cId) {
		Mail mail = Mail.builder().from(from).to(to).subject("아이디 확인 메일").build();
		String message = new StringBuffer("<p>아이디를 찾았습니다</p>")
				.append("<p> 고객님의 아이디 : ")
				.append(cId)
				.append("</p>")
				.append("<p> <a href='http://localhost:8087/consumer/consumer/login'>로그인 하러 가기</a> </p>")
				.toString();
		sendMail(mail.setText(message));
	}
	
	public void sendFindPwMail(String from, String to, String newPassword) {
		Mail mail = Mail.builder().from(from).to(to).subject("임시비밀번호 발급 메일").build();
		String message = new StringBuffer("<p>임시비밀번호를 발급했습니다.</p>")
				.append("<p> 임시비밀번호 코드 : ")
				.append(newPassword)
				.append("</p>")
				.append("<p> <a href='http://localhost:8087/consumer/consumer/login'>로그인 하러 가기</a> </p>")
				.toString();
		sendMail(mail.setText(message));
	}
}
