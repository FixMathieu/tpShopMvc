package fr.fms.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Order {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id;
	
	private Date date;
	
	private double totalAmount;

	@ManyToOne
	private Customer customer;
	
	@ManyToMany
	private Collection<Article> articles;

}