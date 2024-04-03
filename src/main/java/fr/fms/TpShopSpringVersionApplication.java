package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class TpShopSpringVersionApplication implements CommandLineRunner
{
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ArticleRepository articleRepository;
	public static void main(String[] args) 
	{
		SpringApplication.run(TpShopSpringVersionApplication.class, args);
		
		//System.out.println("IDENTIFIANT              DESCRIPTION              MARQUE              PRIX              CATEGORY");
		System.out.println("bienvenue dans notre appli de gestion d'articles ! vivement la couche web .....");
		System.out.println("1 : Afficher tout les articles sans pagination \n" +
						   "2 : Afficher tout les articles avec pagination \n" +
						   "***** ***** ***** ***** **  \n" + 
						   "3 : Ajouter un article  \n" +
						   "4 : Afficher un article  \n" +
						   "5 : Supprimer un article  \n" +
						   "6 : Modifier un article  \n" +
						   "***** ***** ***** ***** **  \n" +
						   "7 : Ajouter une categorie  \n" +
						   "8 : Afficher une categorie  \n" +
						   "9 : Supprimer une categorie  \n" +
						   "10 : Modifier une categorie  \n" +
						   "11 : Afficher les articles d'une categorie  \n" +
						   "***** ***** ***** ***** ***** ******  \n" +
						   "12 : sortir du programme");
	}

	//@Override
	public void run(String... args) throws Exception
	{  
		Category smartphone = categoryRepository.save(new Category("smartphone"));
		Category tablet = categoryRepository.save(new Category("tablet"));
		Category pc = categoryRepository.save(new Category("pc"));
		
		articleRepository.save( new Article("s9" , "Samsung" , 250 , smartphone));
		articleRepository.save( new Article("x1" , "Sony" , 350 , smartphone));
		articleRepository.save( new Article("s10" , "Samsung" , 450 , smartphone));
		articleRepository.save( new Article("s20" , "Samsung" , 500 , smartphone));
		articleRepository.save( new Article("xz" , "Sony" , 150 , smartphone));
		articleRepository.save( new Article("s21" , "Samsung" , 1000 , smartphone));
		articleRepository.save( new Article("predator" , "msi" , 2600 , pc));
		articleRepository.save( new Article("galaxy tab" , "Samsung" , 650 , tablet));
	}
}