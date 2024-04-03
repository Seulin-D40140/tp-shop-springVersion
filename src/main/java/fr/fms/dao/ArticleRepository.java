package fr.fms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.fms.entities.Article;

public interface ArticleRepository  extends JpaRepository<Article,Long>
{
	public List<Article> findByBrand(String brand);
	public List<Article> findByBrandContains(String brand);
	public List<Article> findByBrandAndPrice(String brand , double price); 
	
	@Query("select A from Article A where A.brand like %:x% and A.price > :y")
	public List<Article> searchArticle(@Param("x") String brand , @Param("y")double price);
	
	public List<Article> findByCategoryId(int categoryId);
	
	//exo 1.2
	public List<Article> findByDescription(String description);
	public List<Article> findAll();
	
	//exo 1.3
	public List<Article> findByBrandAndDescriptionContains(String brand , String description); 
	public List<Article> findByBrandAndDescription(String brand , String description); 
	
	//exo 1.4
	public void deleteById(long id);
	
	//exo 1.5
	@Transactional@Modifying@Query("update Article set description = :D, brand = :B, price = :P where id = :id")
	void update(@Param("D") String description, @Param("B") String brand, @Param("P") double price , @Param("id") Long id);
	
	//exo01.6
	public List<Article> findAllByOrderByCategoryAsc();
	public List<Article> findAllByOrderByCategoryDesc();
	
	//exo 1.7
}
