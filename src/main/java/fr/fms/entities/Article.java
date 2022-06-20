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
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
private Long id;
	
@NotNull
@Size(min=1,max=20)
private String Brand;

@NotNull
@Size(min=2,max=20)
private String description;

@DecimalMin("50")
private double price;

private int quantity;

@ManyToOne
private Category category;
}
