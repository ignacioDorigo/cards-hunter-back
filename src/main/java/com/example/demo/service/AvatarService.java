package com.example.demo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.modelo.Avatar;
import com.example.demo.repository.AvatarRepository;

@Service
public class AvatarService {

	@Autowired
	AvatarRepository avatarRepository;

	public String guardarAvatar(String uuid, MultipartFile imagen) throws IOException {
		if (imagen == null || imagen.isEmpty()) {
			throw new IllegalArgumentException("El archivo de imagen no puede estar vacío");
		}

		Avatar avatar = new Avatar();
		avatar.setUuid(uuid);
		avatar.setImagen(imagen.getBytes());
		avatarRepository.save(avatar);

		return "Avatar guardado con exito";
	}

}
