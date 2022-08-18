package com.favshare.controller;

import java.util.HashMap;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.EmailPasswordDto;
import com.favshare.dto.UserAccountDto;
import com.favshare.dto.UserSignUpDto;
import com.favshare.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user/password")
public class UserPasswordController {

	@Autowired
	private JavaMailSender mailSender;

	private final int COUNT = 15;

	private static final String FROM_ADDRESS = "<favshare@ssafy.com>";

	@Autowired
	private UserService userService;

	@ApiOperation(value = "비밀번호 재설정", response = ResponseEntity.class)
	@PutMapping
	public ResponseEntity changePassword(EmailPasswordDto emailPasswordDto) {
		try {
			userService.updatePassword(emailPasswordDto);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		}
	}

	@ApiOperation(value = "인증번호 발송 클릭 시 ", response = ResponseEntity.class)
	@GetMapping("/sendAuth/{email}")
	public ResponseEntity<String> sendAuth(@PathVariable("email") String email) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom("favshare" + FROM_ADDRESS);
			helper.setTo(email);
			helper.setSubject("favshare 인증번호 안내 메일입니다.");
			String theAlphaNumericS;
			StringBuilder builder;

			theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
			// create the StringBuffer
			builder = new StringBuilder(COUNT);
			for (int m = 0; m < COUNT; m++) {
				int myindex = (int) (theAlphaNumericS.length() * Math.random());
				builder.append(theAlphaNumericS.charAt(myindex));
			}
			String auth = builder.toString();
			helper.setText("인증번호는 " + auth + " 입니다.", true);
			
			MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
            MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(MailcapCmdMap);
			
			mailSender.send(message);
//			userService.updateAuth(email, auth);
			return new ResponseEntity(auth, HttpStatus.OK);
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}

	}

	@ApiOperation(value = "인증번호 입력 후 확인 클릭시", response = ResponseEntity.class)
	@PostMapping("/auth")
	public ResponseEntity<String> checkAuth(@RequestBody HashMap<String, String> authInfo) {
		// 인증번호 완료 후 작업 필요
		try {
			String result = userService.getUserAuthByEmail(authInfo.get("email"));
			if (authInfo.get("auth").equals(result)) {
				return new ResponseEntity("success",HttpStatus.OK);
			}else {
				return new ResponseEntity("fail", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

}
