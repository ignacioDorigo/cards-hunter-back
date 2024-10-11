package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.modelo.User;
import com.example.demo.service.AvatarService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/cardshunter")
public class Controller {

	@Autowired
	UserService userService;

	@Autowired
	AvatarService avatarService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestParam String email, @RequestParam String password,
			@RequestParam String nombre, @RequestParam String apellido) {
		String resultado = userService.register(email, password, nombre, apellido);
		if (resultado.contains("Registro exitoso")) {
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.badRequest().body(resultado);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
		String resultado = userService.login(email, password);
		if (resultado.contains("-")) {
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.status(401).body("Credenciales incorrectas");
		}
	}

	@GetMapping("/forgotPassword")
	public ResponseEntity<String> forgotPassword(@RequestParam String email) {
		String resultado = userService.forgotPassword(email);
		if (resultado.contains("Por favor verifique")) {
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.badRequest().body(resultado);
		}
	}

	@GetMapping("/profile")
	public User profile(@RequestParam String uuid) {
		return userService.profile(uuid);
	}

	@PutMapping("/changeName")
	public ResponseEntity<String> changeName(@RequestParam String uuid, @RequestParam String name) {
		String resultado = userService.changeName(uuid, name);
		if (resultado.contains("Nombre cambiado con exito")) {
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.badRequest().body(resultado);
		}
	}

	@PutMapping("/changeSurname")
	public ResponseEntity<String> changeSurname(@RequestParam String uuid, @RequestParam String surname) {
		String resultado = userService.changeSurname(uuid, surname);
		if (resultado.contains("Apellido cambiado con exito")) {
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.badRequest().body(resultado);
		}
	}

	@PutMapping("/changeEmail")
	public ResponseEntity<String> changeEmail(@RequestParam String uuid, @RequestParam String email) {
		String resultado = userService.changeEmail(uuid, email);
		if (resultado.contains("Mail actualizado")) {
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.badRequest().body(resultado);
		}
	}

	@PutMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestParam String uuid, @RequestParam String original,
			@RequestParam String nueva1, @RequestParam String nueva2) {
		String resultado = userService.changePassword(uuid, original, nueva1, nueva2);
		if (resultado.contains("Contrasena cambiada con exito")) {
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.badRequest().body(resultado);
		}
	}

	@PostMapping("/uploadAvatar")
	public ResponseEntity<String> uploadAvatar(@RequestParam String uuid, @RequestParam MultipartFile imagen)
			throws IOException {
		String resultado = avatarService.guardarAvatar(uuid, imagen);
		if (resultado.contains("Avatar guardado con exito")) {
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.badRequest().body("Error al intenter guardar el avatar");
		}
	}

}
