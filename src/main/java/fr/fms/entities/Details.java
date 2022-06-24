package fr.fms.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Details implements Serializable {
	private static final long serialVersionUID = 1L; 
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id;

	@DecimalMin("50")
	private double price;

	private int quantity;
	
//	@OneToMany
//	private Collection<Commande> commandes;
//	
//	@OneToMany
//	private Collection<Article> articles;

	@ManyToOne
	private Commande commande;
	
	@ManyToOne
	private Article article;
}