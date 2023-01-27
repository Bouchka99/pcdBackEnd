package com.example.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Email;
import com.example.demo.repository.EmailRepository;

@Service
@Transactional
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender mailsender;
	
	@Autowired
	private JavaMailSenderImpl mailsenderimpl;
	
	/*@Autowired
	private MailProperties mailproperties;
	*/
	@Autowired
	private EmailRepository mailrepository;
	
	public Email sendemail (Email email) {
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

