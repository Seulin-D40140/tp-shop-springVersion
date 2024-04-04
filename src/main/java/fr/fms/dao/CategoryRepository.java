package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
	public List<Category> findAll();
	public void deleteById(long id);
	public List<Category> findByName(String name);
}
