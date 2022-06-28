package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Category;
import fr.fms.entities.Details;

public interface DetailsRepository extends JpaRepository<Details, Long>{
	public List<Details> findByCommandeId(long commandeId);
}
