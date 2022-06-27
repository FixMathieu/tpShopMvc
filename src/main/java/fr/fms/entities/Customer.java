package fr.fms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Customer {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
private Long id;
private String adress;
private String email;
private String firstName;
private String lastName;
private long phone;

	@Override
	public String toString() {
		return firstName + " " + lastName  ;
	}
	
}