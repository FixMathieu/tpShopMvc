package fr.fms.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id;
	//private String firstName;
	//private String lastName;
	//private String adress;
	//private String email;
	//private long phone;
	private String username;
	private String password;
	private String role;
	private boolean active;

	@Override
	public String toString() {
		return username ;
	}

}