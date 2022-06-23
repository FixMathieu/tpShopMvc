package fr.fms.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import fr.fms.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public List<User> findAll();
}
