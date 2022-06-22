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
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class User {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
private Long id;
private String firstName;
private String lastName;
private String adress;
private String email;
private long phone;
private String password;
private boolean isAdmin;
	
public User(String firstName, String lastName, String email) {
	this.firstName=firstName;
	this.lastName=lastName;
}
	public User(String email, String password) {
		this.email=email;
	}

	public User(Long id, String email, String password) {
		this.id=id;
		this.email=email;
		this.password=password;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName ;
	}
	
}
