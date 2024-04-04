package fr.fms;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import fr.fms.buisness.buisnessArticle;
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
	}

	//@Override
	public void run(String... args) throws Exception
	{  
		buisnessArticle buisnessA = new buisnessArticle();
		
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
		
		Scanner scan = new Scanner(System.in);
		boolean leave = false;
		
		//System.out.println("IDENTIFIANT              DESCRIPTION              MARQUE              PRIX              CATEGORY");
		System.out.println("bienvenue dans notre appli de gestion d'articles ! vivement la couche web ..... \n");
		
		while( leave != true)
		{
			System.out.println("\n1 : Afficher tout les articles sans pagination \n" +
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
							   "12 : sortir du programme \n");
			
			String choice = scan.nextLine();
			
			switch (Integer.parseInt(choice)) 
			{
				case 1 :
						System.out.println("-----------------------------------------------------------------------------");
						System.out.println("ID     DESCRIPTION     MARQUE     PRIX     CATEGORY");
						for ( Article article : articleRepository.findAll())
						{
							System.out.println(article);
						}
						System.out.println("----------------------------------------------------------------------------- \n");
					break;
				case 2 :
						
					break;
				case 3 :
						System.out.println("pour ajouter un article entrez la description : ");
						String descAdd = scan.nextLine();
						System.out.println("entrez la marque : ");
						String brandAdd = scan.nextLine();
						System.out.println("entrez le prix : ");
						String priceAdd = scan.nextLine();
						System.out.println("entrez la category : ");
						String catAdd = scan.nextLine();
						Category catchoice = catAdd.equals("pc") ? pc : catAdd.equals("tablet") ? tablet : smartphone;
						articleRepository.save( new Article(descAdd , brandAdd , Integer.parseInt(priceAdd) , catchoice));
					break;
				case 4 :
						System.out.println("entrez l'id de l'article que vous voulez voir : ");
						String articleChoice = scan.nextLine();
						System.out.println("-----------------------------------------------------------------------------");
						System.out.println("voici l'article selectionner : \n"+
										   "ID     DESCRIPTION     MARQUE     PRIX     CATEGORY \n"
											+articleRepository.findById(Integer.parseInt(articleChoice)));
						System.out.println("----------------------------------------------------------------------------- \n");
					break;
				case 5 :
						System.out.println("entrez l'id de l'article que vous voulez supprimer : ");
						String deleteChoice = scan.nextLine();
						articleRepository.deleteById(Integer.parseInt(deleteChoice));
					break;
				case 6 :
						System.out.println("entrez l'id de l'article a modifier : ");
						long idUpdate = scan.nextLong();
						System.out.println("entrez la description : ");
						scan.nextLine();
						String descUpdate = scan.nextLine();
						System.out.println("entrez la marque : ");
						String brandUpdate = scan.nextLine();
						System.out.println("entrez le prix : ");
						String priceUpdate = scan.nextLine();
						articleRepository.update(descUpdate, brandUpdate, Integer.parseInt(priceUpdate), idUpdate);
					break;
				case 7 :
						System.out.println("entrez le nom de la nouvelle category : ");
						String newCat = scan.nextLine();
						categoryRepository.save(new Category(newCat));
					break;
				case 8 :
						System.out.println("entrez le nom de la category : ");
						String catView = scan.nextLine();
						System.out.println("-----------------------------------------------------------------------------");
						System.out.println(categoryRepository.findByName(catView));
						System.out.println("----------------------------------------------------------------------------- \n");
					break;
				case 9 :
					 	System.out.println("-----------------------------------------------------------------------------");
						for( Category cat : categoryRepository.findAll())
						{
							System.out.println(cat);
						}
						System.out.println("----------------------------------------------------------------------------- \n");
						System.out.println("entrez l'id de la category a supprimer : ");
						Long removeCat = scan.nextLong();
						scan.nextLine();
						categoryRepository.deleteById(removeCat);
					break;
				case 10 :
						System.out.println("entrez l'id de la category a modifier : ");
						Long catUpdateLong = scan.nextLong();
						scan.nextLine();
						System.out.println("entrez la nouvelle valeur : ");
						String catUpdatestString = scan.nextLine();
						categoryRepository.update(catUpdatestString, catUpdateLong);
					break;
				case 11 :
						System.out.println("-----------------------------------------------------------------------------");
						for( Category cat : categoryRepository.findAll())
						{
							System.out.println(cat);
						}
						System.out.println("----------------------------------------------------------------------------- \n");
						System.out.println("\n");
						System.out.println("entrez l'id de la categorie souhaiter : ");
						Long searchCat = scan.nextLong();
						scan.nextLine();
						System.out.println("\n");
						System.out.println("-----------------------------------------------------------------------------");
						for( Article article : articleRepository.findByCategoryId(searchCat))
						{
							System.out.println(article);
						}
						System.out.println("----------------------------------------------------------------------------- \n");
					break;
				case 12 :
						leave = true;
						System.out.println("----------------------------------------------------------------------------->");
						System.out.println("a bientot ! ");
						System.out.println("<-----------------------------------------------------------------------------");
						scan.close();
					break;
				default:
					break;
			}
		}
	}
}