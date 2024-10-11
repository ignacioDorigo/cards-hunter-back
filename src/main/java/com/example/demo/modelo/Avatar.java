package com.example.demo.modelo;

import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "avatars")
public class Avatar {

	@Id
	private String uuid;

	@Lob
	private byte[] imagen;

	public Avatar() {

	}

	public Avatar(String uuid, byte[] imagen) {
		super();
		this.uuid = uuid;
		this.imagen = imagen;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Avatar [uuid=" + uuid + ", imagen=" + Arrays.toString(imagen) + "]";
	}

}
