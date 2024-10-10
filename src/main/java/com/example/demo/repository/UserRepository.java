package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	List<User> findByEmail(String email);

}
