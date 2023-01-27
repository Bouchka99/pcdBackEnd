package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Email;
import com.example.demo.repository.EmailRepository;
import com.example.demo.service.EmailSenderService;


@RestController
@RequestMapping("/email/")
public class EmailController {
	
	/*@Autowired
	private EmailSenderService mailsenderservice;
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/createemail")
	public Email createemail(@RequestBody Email email) {
		
		System.out.println("sent email");
		return mailsenderservice.sendemail(email);
	}*/
	@Autowired
	private JavaMailSender mailsender;
	
	@Autowired
	private JavaMailSenderImpl mailsenderimpl;
	
	
	
	@Autowired
	private EmailRepository mailrepository;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/createemail")
	public Email createemail(@RequestBody Email email) {
		
		System.out.println("sent email");
		SimpleMailMessage message = new SimpleMailMessage();
		mailsenderimpl.setUsername(email.getEmail());
		mailsenderimpl.setPassword(email.getPassword());
		message.setFrom(email.getEmail());
		message.setTo(email.getEmaild());
		message.setText(email.getObject());
		message.setSubject(email.getSubject());
		System.out.println("email send successfuly");
		mailsender.send(message);
		return mailrepository.save(email);
	}

}