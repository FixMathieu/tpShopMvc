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
public class Category {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
private Long id;
	String name;

	@OneToMany(mappedBy ="category")
	private Collection<Article> articles;
	
	public Category(String name) {
		this.name=name;
	}

	public Category(Long id, String name) {
		this.id=id;
		this.name=name;
	}

	@Override
	public String toString() {
		return name ;
	}
	
}