package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Commande;
import fr.fms.entities.Details;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
	public List<Commande> findById(long id);
}
