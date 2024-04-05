package fr.fms;


import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.dao.ProductRepository;

@SpringBootApplication
public class TpShopSpringVersionApplication implements CommandLineRunner
{
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ArticleRepository articleRepository;
	Boolean paginationStringOut = false;
	int pagesevenfive = 1;
	int pageNb = 0;
	int sizePage = 5;
	Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		SpringApplication.run(TpShopSpringVersionApplication.class, args);
	}

	//@Override
	public void run(String... args) throws Exception
	{  
		String tableHead = String.format("%s  %17s  %14s  %12s  %12s ", "ID", "DESCRIPTION" , "MARQUE", "PRIX" , "CAT");
//		buisnessArticle buisnessA = new buisnessArticle();
		
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
		
		
		boolean leave = false;
		
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
				case 1 ://afficher tout les articles
						System.out.println("-----------------------------------------------------------------------------");
						System.out.println(tableHead);
						for ( Article art : articleRepository.findAll())
						{
							System.out.printf("%s  %13s  %18s  %14s  %18s %n", art.getId() , art.getDescription() , art.getBrand() , art.getPrice() , art.getCategory());
						}
						System.out.println("----------------------------------------------------------------------------- \n");
					break;
				case 2 ://afficher articles par pagination
						while(paginationStringOut != true)
						{									
							Pageable numByPage = PageRequest.of(pageNb, sizePage);
							
							if(pagesevenfive == 1 && paginationStringOut != true)
							{		
								System.out.println("-----------------------------------------------------------------------------");//afficher artitcles 5 par page
								displayMenuFuction(5);
								System.out.println("----------------------------------------------------------------------------- \n");
								
								System.out.println(tableHead);
								displayTableFuction(productRepository.findAll(numByPage));
								System.out.println("tapez ici : ");
								String pageSeven = scan.nextLine();
								
								choiceUserPaginationFunction( pageSeven , 7 , 2 , numByPage);
							}
							else if(pagesevenfive == 2 && paginationStringOut != true)
							{    
								System.out.println("-----------------------------------------------------------------------------");//afficher articles 7 par page
								displayMenuFuction(7);
								System.out.println("----------------------------------------------------------------------------- \n");
								
								System.out.println(tableHead);
								displayTableFuction(productRepository.findAll(numByPage));
								System.out.println("tapez ici : ");
								String pageSeven = scan.nextLine();
								
								choiceUserPaginationFunction( pageSeven , 5 , 1 , numByPage);
							}
						}
					break;
				case 3 : //ajouter un article
						System.out.println("pour ajouter un article entrez la description : ");
						String descAdd = scan.nextLine();
						System.out.println("entrez la marque : ");
						String brandAdd = scan.nextLine();
						System.out.println("entrez le prix : ");
						String priceAdd = scan.nextLine();
						System.out.println("entrez la category : ");
						String catAdd = scan.nextLine();
						Category catchoice = catAdd.equals("pc") ? pc : catAdd.equals("tablet") ? tablet : catAdd.equals("Smartphone") ? smartphone : categoryRepository.save(new Category(catAdd));;
						articleRepository.save( new Article(descAdd , brandAdd , Integer.parseInt(priceAdd) , catchoice));
					break;
				case 4 ://afficher un seul article par son id
						System.out.println("entrez l'id de l'article que vous voulez voir : ");
						String articleChoice = scan.nextLine();
						System.out.println("-----------------------------------------------------------------------------");
						System.out.println("voici l'article selectionner : \n"+
										   "ID     DESCRIPTION     MARQUE     PRIX     CATEGORY \n"
											+articleRepository.findById(Integer.parseInt(articleChoice)));
						System.out.println("----------------------------------------------------------------------------- \n");
					break;
				case 5 ://supprimer un article par son id
						System.out.println("entrez l'id de l'article que vous voulez supprimer : ");
						String deleteChoice = scan.nextLine();
						articleRepository.deleteById(Integer.parseInt(deleteChoice));
					break;
				case 6 ://modification d'un article par son id
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
				case 7 ://ajout d'une category
						System.out.println("entrez le nom de la nouvelle category : ");
						String newCat = scan.nextLine();
						categoryRepository.save(new Category(newCat));
					break;
				case 8 ://afficher une seul categorie par son nom 
						System.out.println("entrez le nom de la category : ");
						String catView = scan.nextLine();
						
						System.out.println("-----------------------------------------------------------------------------");
						System.out.println(categoryRepository.findByName(catView));
						System.out.println("----------------------------------------------------------------------------- \n");
					break;
				case 9 ://suppression d'une categorie par son id
						System.out.println("-----------------------------------------------------------------------------");
						displayCategoryFunction(categoryRepository.findAll());
						System.out.println("----------------------------------------------------------------------------- \n");
						
						System.out.println("entrez l'id de la category a supprimer : ");
						Long removeCat = scan.nextLong();
						scan.nextLine();
						categoryRepository.deleteById(removeCat);
					break;
				case 10 ://mise a jour d'une category par son id
						System.out.println("----------------------------------------------------------------------------- ");
						displayCategoryFunction(categoryRepository.findAll());
						System.out.println("----------------------------------------------------------------------------- \n");
						
						System.out.println("entrez l'id de la category a modifier : ");
						Long catUpdateLong = scan.nextLong();
						scan.nextLine();
						System.out.println("entrez la nouvelle valeur : ");
						String catUpdatestString = scan.nextLine();
						categoryRepository.update(catUpdatestString, catUpdateLong);
					break;
				case 11 :// affichage article d'une category par id category
						System.out.println("-----------------------------------------------------------------------------");
						displayCategoryFunction(categoryRepository.findAll());
						System.out.println("----------------------------------------------------------------------------- \n");
						
						System.out.println("\n");
						System.out.println("entrez l'id de la categorie souhaiter : ");
						Long searchCat = scan.nextLong();
						scan.nextLine();
						
						System.out.println("\n -----------------------------------------------------------------------------");
						System.out.println(tableHead);
						for( Article article : articleRepository.findByCategoryId(searchCat))
						{
							System.out.printf("%s  %13s  %18s  %14s  %18s %n", article.getId() , article.getDescription() , article.getBrand() , article.getPrice() , article.getCategory());
						}
						System.out.println("----------------------------------------------------------------------------- \n");
					break;
				case 12 : // retour menu principal
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
	public void displayMenuFuction(int nb) // fonction affichage du menu pagination
	{
		System.out.println("-----------------------------------------------------------------------------\n" +
								   "EXIT pour sortir \n" +
								   "PREV pour page precedente \n" +
								   "NEXT pour page suivante \n" +
								   "PAGE puis "+nb+" pour afficher "+nb+" articles par page \n" +
				"----------------------------------------------------------------------------- ");
	}
	
	public void displayTableFuction(Page<Article> page) // function affichage articles pagination
	{
		System.out.println("-----------------------------------------------------------------------------");
		for (Article art : page)
		{
			System.out.printf("%s  %13s  %18s  %14s  %18s %n", art.getId() , art.getDescription() , art.getBrand() , art.getPrice() , art.getCategory());
		}
		System.out.println("-----------------------------------------------------------------------------");
	}
	
	public void choiceUserPaginationFunction( String pageSeven , Integer pageSize , int nbArticleByPage , Pageable numByPage) // function choix user pagination
	{
		if(pageSeven.equals("PAGE"))
		{
			System.out.println("tapez "+pageSize+" : ");
			String seven = scan.nextLine();
			if (Integer.parseInt(seven) == pageSize )
			{
				pageNb = 0;
				sizePage = pageSize;
				pagesevenfive = nbArticleByPage;
			}
		}
		else if (pageSeven.equals("NEXT")) 
		{
			if(productRepository.findAll(numByPage).hasNext())
			{
				pageNb ++;
			}
			else 
			{
				System.out.println("il n'y a plus de page !");
			}
		}
		else if (pageSeven.equals("PREV")) 
		{
			if(productRepository.findAll(numByPage).hasPrevious())
			{
				pageNb --;
			}
			else 
			{
				System.out.println("c'est la premiere page !");
			}
		}
		else if (pageSeven.equals("EXIT")) 
		{
			paginationStringOut = true;
		}
	}
	
	public void displayCategoryFunction(List<Category> listCategory)
	{
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf(" %s %19s %n", "ID","CAT");
		for( Category cat :  listCategory)
		{
			System.out.printf(" %s %20s %n",cat.getId() , cat.getName());
		}
		System.out.println("----------------------------------------------------------------------------- ");
	}
}