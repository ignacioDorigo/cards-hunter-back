package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	public void enviarEmail(String toEmail, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("sipiuade2024@gmail.com"); // desde donde
		message.setTo(toEmail); // para quien
		message.setSubject(subject); // El asunto del correo
		message.setText(body); // El cuerpo del correo electronico

		mailSender.send(message);
		System.out.println("MAIL ENVIADO CORRECTAMENTE");
	}

}
