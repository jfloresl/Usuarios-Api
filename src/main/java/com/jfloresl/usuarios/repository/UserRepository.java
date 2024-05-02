package com.jfloresl.usuarios.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jfloresl.usuarios.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User,UUID>{
	User findByEmail(String email);
	
	void deleteByIdAndToken(UUID id, String token);
	
	
	
	
}
