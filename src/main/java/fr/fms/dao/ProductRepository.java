package fr.fms.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import fr.fms.entities.Article;

public interface ProductRepository extends PagingAndSortingRepository<Article, Long>
{
	Pageable pageableof5 = PageRequest.of(0, 5);
	Pageable pageableOf7 = PageRequest.of(0, 7);
	public Page<Article> findAll(Pageable pageable);
}
