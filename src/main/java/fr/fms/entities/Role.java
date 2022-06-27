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
public class Role {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
private String role;

	

	@Override
	public String toString() {
		return role ;
	}
	
}