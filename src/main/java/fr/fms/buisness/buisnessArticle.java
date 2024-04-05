package fr.fms.buisness;

import java.util.List;
import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;

public class buisnessArticle implements buisness<Article>
{
	private ArticleRepository articleRepository;
	
	@Override
	public List<Article> showS() 
	{
		return  articleRepository.findAll();
	}
}
