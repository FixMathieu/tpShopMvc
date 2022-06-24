package fr.fms.entities;

import java.util.Collection;

import javax.persistence.Column;
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
public class Customer {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name="id")
	private Long id;
	@Column(name="firstName")
	private String firstName;
	@Column(name="lastName")
	private String lastName;
	@Column(name="adress")
	private String adress;
	@Column(name="email")
	private String email;
	@Column(name="phone")
	private long phone;

	@OneToMany(mappedBy ="customer")
	private Collection<Commande> commandes;

	public Customer(String firstName, String lastName, String adress, String email, long phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.adress = adress;
		this.email = email;
		this.phone = phone;
	}
	
}