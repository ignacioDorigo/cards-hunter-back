package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.User;
import com.example.demo.repository.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailSenderService emailSenderService;

	public String login(String email, String password) {
		List<User> usuarioList = userRepository.findByEmail(email);
		if (!usuarioList.isEmpty()) {
			// Si entra aca significa que ya encontro el mail, falta validar password
			User user = usuarioList.get(0);
			String passwordHasheado = user.getPassword();
			if (checkearPassword(password, passwordHasheado)) {
				return user.getUuid();
			} else {
				return "Contrasena incorrecta";
			}

		}
		return "Usuario no encontrado";
	}

	public String register(String email, String password, String nombre, String apellido) {
		User userMail = buscarUsuarioMail(email);
		System.out.println(userMail);
		if (userMail == null) {
			User nuevo = new User();
			nuevo.setUuid(generarUuid());
			nuevo.setEmail(email);
			nuevo.setPassword(hashearPassword(password));
			nuevo.setName(nombre);
			nuevo.setSurname(apellido);

//			Datos para enviar el mail
			String mailDestino = email;
			String motivoMail = "Registro en Cards Hunter";
			String cuerpoMail = "Felicidades " + nombre + " " + apellido + " por registrarte en Cards Hunter ";
			emailSenderService.enviarEmail(mailDestino, motivoMail, cuerpoMail);
			userRepository.save(nuevo);

			return "Registro exitoso";
		}
		return "El mail ya se encuentra registrado";
	}

	public String forgotPassword(String mail) {
		User user = buscarUsuarioMail(mail);
		if (user != null) {

			String passwordProvisorio = generarPasswordProvisorio(8);
			String passwordHasheado = hashearPassword(passwordProvisorio);
			user.setPassword(passwordHasheado);
			userRepository.save(user);

			String mailDestino = user.getEmail();
			String motivoMail = "Recupero de contrasenia";
			String cuerpoMail = "Tu contrasena provisoria es " + passwordProvisorio;
			emailSenderService.enviarEmail(mailDestino, motivoMail, cuerpoMail);
			return "Por favor verifique su casilla de correo para continuar con el procedimiento ";
		}
		return "Usuario no encontrado";

	}

	public static String generarPasswordProvisorio(int length) {
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(caracteres.length());
			password.append(caracteres.charAt(index));
		}

		return password.toString();
	}

	public static void generarYEncriptarPassword() {
		String passwordProvisorio = generarPasswordProvisorio(8);
		String hashedPassword = hashearPassword(passwordProvisorio);
	}

	private String generarUuid() {
		UUID uuid = UUID.randomUUID();
		String uuidString = uuid.toString();
		return uuidString;
	}

	public User buscarUsuarioMail(String mail) {
		User user = null;
		List<User> userList = userRepository.findByEmail(mail);
		if (!userList.isEmpty()) {
			user = userList.get(0);
			return user;
		}
		return null;
	}

	private static String hashearPassword(String original) {
		return BCrypt.withDefaults().hashToString(12, original.toCharArray());
	}

	private static boolean checkearPassword(String original, String hasheado) {
		return BCrypt.verifyer().verify(original.toCharArray(), hasheado).verified;
	}

}
