package fr.fms.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Article implements Serializable {
	private static final long serialVersionUID = 1L; 
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
private Long id;
	
@NotNull
@Size(min=1,max=20)
private String description;

@NotNull
@Size(min=1,max=20)
private String brand;

@DecimalMin("50")
private double price;

private int quantity;

@ManyToOne
private Category category;

private String image;

public Article(String image) {
	this.image=image;
	
}

public Article(String brand,String description,  double price) {
	this.brand = brand;
	this.description = description;
	this.price = price;
	
}

public Article(String brand,String description,  double price,int quantity,Category category,String image) {
	this.brand = brand;
	this.description = description;
	this.price = price;
	this.quantity=quantity;
	this.category=category;
	this.image=image;
}
}