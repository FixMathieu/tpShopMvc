package fr.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Details;

public interface DetailsRepository extends JpaRepository<Details, Long>{

}
