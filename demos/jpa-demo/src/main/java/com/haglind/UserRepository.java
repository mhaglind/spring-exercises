package com.haglind;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByName(String name);
	List<User> findByNameLike(String name);
	
}
