package fr.fms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.fms.entities.Article;
import fr.fms.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
	public List<Category> findAll();
	public void deleteById(long id);
	public List<Category> findByName(String name);
	
	@Transactional@Modifying@Query("update Category set name = :N where id = :id")
	void update(@Param("N") String name, @Param("id") Long id);
	
}
