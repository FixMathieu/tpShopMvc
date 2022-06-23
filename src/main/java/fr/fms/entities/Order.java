package fr.fms.entities;

import java.util.Collection;

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
public class Order {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id;

	@ManyToOne
	private Customer customer;
	
	@OneToMany
	private Collection<Article> articles;

}