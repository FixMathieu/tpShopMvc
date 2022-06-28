package fr.fms.business;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.dao.CommandeRepository;
import fr.fms.dao.DetailsRepository;
import fr.fms.dao.RoleRepository;
import fr.fms.dao.UserRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.Commande;
import fr.fms.entities.Customer;
import fr.fms.entities.Details;
import fr.fms.entities.User;


@Service
public class IBusinessImpl implements IBusiness{
	@Autowired
	public static HashMap<Long, Integer> cart = new HashMap<Long, Integer>();
	
	@Autowired
	public ArticleRepository articleRepository;
	
	@Autowired
	public CategoryRepository categoryRepository;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public CommandeRepository commandeRepository;
	
	@Autowired
	public DetailsRepository detailsRepository;

	@Autowired
	public RoleRepository roleRepository;
	
	@Override
	@PostConstruct
	public List<Article> getAllArticles() {
		return articleRepository.findAll();
	}
	
    @Override
    public void createArticle(String brand,String description, double price,int quantity,Category category, String image) {
        articleRepository.save(new Article(brand,description,price,quantity,category, image));
    }
	
	@Override
	public void deleteArticleById(Long id) {
		articleRepository.deleteById(id);
	}
	
    @Override
    public void updateArticle(Long id, String brand, String description, double price, int quantity, String catName, String image) {
        articleRepository.save(new Article(id,brand,description,price,quantity,categoryRepository.findByName(catName), image));
    }
	
	@Override
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	@Override
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@Override
	public void createCategory(String name) {
		categoryRepository.save(new Category(name));
	}
	
	@Override
	public void deleteCategoryById(Long id) {
		categoryRepository.deleteById(id);
	}
	
	@Override
	public void updateCategory(Long id, String name) {
		categoryRepository.save(new Category(id,name));
	}
	
	@Override
	public Page<Article> getArticlesPages(Pageable pageable) throws Exception {
		return articleRepository.findAll(pageable);
	}
	
	/**
	 * Ajoute un article au panier via l'ID de l'article
	 * @param id de l'article à ajouter
	 */
	public void addToCart(Long id) {
		
		if(cart.get(id) != null) {
		cart.put(id, cart.get(id)+1);
		}else {
			cart.put(id, 1);
		}
	}
	
	/**
	 * Retire un article au panier via l'ID de l'article
	 * @param id de l'article à retirer
	 */
	public void removeFromCart(Long id) {
		if(cart.get(id)>1) {
			cart.put(id, cart.get(id)-1);
		}else if(cart.get(id)==1){
			cart.remove(id);
		}
	}
	
	/**
	 * Renvoie le contenu du panier.
	 */
	public HashMap<Long, Integer> getCart() {
		return cart;
	}
	
	/**
	 * Enregistre la commande en base et vide le panier
	 */
	public Commande placeCommande(Customer customer) {
		
//		cart.forEach((idArticle,quantity)-> 
//		detailsRepository.save(new Details(null, idArticle,price, quantity)));
		// Details details = new Details();
		
		
		Commande commande = new Commande();
		commandeRepository.save(commande);
		double totalAmount = 0;
		
		for(Entry<Long, Integer> entry : cart.entrySet()){		
			Article article = articleRepository.findById(entry.getKey()).get();
			Details details = new Details();
			details.setPrice(article.getPrice());
			details.setQuantity(entry.getValue());
			details.setCommande(commande);
			details.setArticle(article);

//			double price = article.getPrice();
//			int quantity = entry.getValue();
			detailsRepository.save(details);
			double detailAmount = article.getPrice()*entry.getValue();
			totalAmount+=detailAmount;
		}
		commande.setDate(new Date());
		commande.setTotalAmount(totalAmount);
		commande.setCustomer(customer);
		 commandeRepository.save(commande);
	
		 return commande;
//		cart.clear();
	}
}