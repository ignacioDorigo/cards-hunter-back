package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;

@RestController
@RequestMapping("/cardshunter")
public class Controller {

	@Autowired
	UserService userService;

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

}
