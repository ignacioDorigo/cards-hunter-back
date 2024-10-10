package com.example.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	private String uuid;
	private String email;
	private String password;
	private String name;
	private String surname;

	public User() {

	}

	public User(String uuid, String email, String password, String name, String surname) {
		super();
		this.uuid = uuid;
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "User [uuid=" + uuid + ", email=" + email + ", password=" + password + ", name=" + name + ", surname="
				+ surname + "]";
	}

}
