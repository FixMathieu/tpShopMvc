package fr.fms.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Commande {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="commandeDate")
	private Date date;
	
	private double totalAmount;

	@ManyToOne
	private Customer customer;
	
	@OneToMany(mappedBy ="commande")
	private Collection<Details> details;
	
	public Commande(Long id, Date date, double totalAmount, Customer customer) {
		this.id=id;
		this.date=date;
		this.totalAmount=totalAmount;
		this.customer=customer;		
	}

}