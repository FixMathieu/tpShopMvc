package fr.fms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import fr.fms.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>  {
	public List<Category> deleteById(long id);
	Page<Category> findByNameContains(String name, Pageable pageable);
}
