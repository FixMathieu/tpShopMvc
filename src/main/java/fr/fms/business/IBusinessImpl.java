package fr.fms.business;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.dao.CommandeRepository;
import fr.fms.dao.CustomerRepository;
import fr.fms.dao.DetailsRepository;
import fr.fms.dao.RoleRepository;
import fr.fms.dao.UserRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.Commande;
import fr.fms.entities.Customer;
import fr.fms.entities.Details;
import fr.fms.entities.Role;
import fr.fms.entities.User;
import fr.fms.security.SecurityConfig;

@Service
public class IBusinessImpl implements IBusiness {

	public HashMap<Long, Integer> cart;

	public User userCurrent;

	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CommandeRepository commandeRepository;

	@Autowired
	DetailsRepository detailsRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	SecurityConfig securityConfig;

	public IBusinessImpl() {
		this.cart = new HashMap<Long, Integer>();
		this.userCurrent = null;
	}

	public void setUserCurrent(User user) {
		userCurrent = user;
	}

	public User getUserCurrent() {
		return userCurrent;
	}

	@Override
	@PostConstruct
	public List<Article> getAllArticles() {
		return articleRepository.findAll();
	}

	@Override
	public void createArticle(String brand, String description, double price, int quantity, Category category,
			String image) {
		articleRepository.save(new Article(brand, description, price, quantity, category, image));
	}

	@Override
	@Transactional
	public void deleteArticleById(Long id) {
		detailsRepository.deleteByArticle_id(id);
		articleRepository.deleteById(id);
	}

	@Override
	public void updateArticle(Long id, String brand, String description, double price, int quantity, String catName,
			String image) {
		articleRepository.save(
				new Article(id, brand, description, price, quantity, categoryRepository.findByName(catName), image));
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public List<User> getAllUsers() {
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
		categoryRepository.save(new Category(id, name));
	}

	@Override
	public Page<Article> getArticlesPages(Pageable pageable) throws Exception {
		return articleRepository.findAll(pageable);
	}

	/**
	 * Ajoute un article au panier via l'ID de l'article
	 * 
	 * @param id de l'article à ajouter
	 */
	public void addToCart(Long id) {
		Authentication authentified = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentified);
		if (cart.get(id) != null) {
			cart.put(id, cart.get(id) + 1);
		} else {
			cart.put(id, 1);
		}
	}

//	public void userCurrentId(String userName) {
//	String userNameAuth= SecurityContextHolder.getContext().getAuthentication().getName();
////	String userNameBdd = userRepository.findByUsername(userName);
////	if(user)
//		
//	}
	public User userCurrent() {
		String userNameAuth = SecurityContextHolder.getContext().getAuthentication().getName();
		User userNameBdd = userRepository.findByUsername(userNameAuth).get(0);
		return userNameBdd;
	}

	/**
	 * Retire un article au panier via l'ID de l'article
	 * 
	 * @param id de l'article à retirer
	 */
	public void removeFromCart(Long id) {
		if (cart.get(id) > 1) {
			cart.put(id, cart.get(id) - 1);
		} else if (cart.get(id) == 1) {
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

		for (Entry<Long, Integer> entry : cart.entrySet()) {
			Article article = articleRepository.findById(entry.getKey()).get();
			Details details = new Details();
			details.setPrice(article.getPrice());
			details.setQuantity(entry.getValue());
			details.setCommande(commande);
			details.setArticle(article);

//			double price = article.getPrice();
//			int quantity = entry.getValue();
			detailsRepository.save(details);
			double detailAmount = article.getPrice() * entry.getValue();
			totalAmount += detailAmount;
		}
		commande.setDate(new Date());
		commande.setTotalAmount(totalAmount);
		commande.setUser(userCurrent());
		commande.setCustomer(customer);
		commandeRepository.save(commande);
		cart.clear();
		return commande;

	}

	public double getTotalAmount() {

		double totalAmount = 0;
		for (Entry<Long, Integer> entry : cart.entrySet()) {
			Article article = articleRepository.findById(entry.getKey()).get();
			totalAmount += article.getPrice() * entry.getValue();
		}
		return totalAmount;
	}

	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public String great() {
		return "Hello World";
	}
	

	
	public void generateValues() {
		
		detailsRepository.deleteAll();
		articleRepository.deleteAll();
		categoryRepository.deleteAll();
		commandeRepository.deleteAll();
		customerRepository.deleteAll();
		userRepository.deleteAll();
		roleRepository.deleteAll();
		
		Category smartphone = categoryRepository.save(new Category("Smartphone"));
		Category pc = categoryRepository.save(new Category("Ordinateur"));
		Category tablet = categoryRepository.save(new Category("Tablette"));
		Category printer = categoryRepository.save(new Category("Imprimante"));
		Category camera = categoryRepository.save(new Category("Camera"));
		Category tv = categoryRepository.save(new Category("TV"));

		articleRepository.save(new Article("S8", "Samsung", 250, 1, smartphone, "samsung.jpg"));
		articleRepository.save(new Article("S9", "Samsung", 300, 1, smartphone, "samsung.jpg"));
		articleRepository.save(new Article("iPhone 10", "Apple", 500, 1, smartphone, "iphone.jpg"));
		articleRepository.save(new Article("MI11", "Xiaomi", 100, 1, smartphone, "xiaomi.jpg"));
		articleRepository.save(new Article("9 Pro", "OnePlus", 200, 1, smartphone, "oneplus.jpg"));
		articleRepository.save(new Article("Pixel 5", "Google", 350, 1, smartphone, "googleSp.jpg"));
		articleRepository.save(new Article("F3", "Poco", 150, 1, smartphone, "poco.jpg"));

		articleRepository.save(new Article("810", "Dell", 550, 1, pc, "dellpc.jpg"));
		articleRepository.save(new Article("F756", "Asus", 600, 1, pc, "asuspc.jpg"));
		articleRepository.save(new Article("E80", "Asus", 700, 1, pc, "asuspc.jpg"));
		articleRepository.save(new Article("Pro", "MacBook", 1000, 1, pc, "macbook.jpg"));
		articleRepository.save(new Article("Air", "MacBook", 1200, 1, pc, "macbook.jpg"));
		articleRepository.save(new Article("XL 5", "IPad", 300, 1, tablet, "ipad.jpg"));
		articleRepository.save(new Article("XL 7", "IPad", 500, 1, tablet, "ipad.jpg"));

		articleRepository.save(new Article("MG30", "Canon", 50, 1, printer, "canon-mg30.jpg"));
		articleRepository.save(new Article("MG50", "Canon", 60, 1, printer, "canon-mg50.jpg"));
		articleRepository.save(new Article("OfficeJet 6950", "HP", 50, 1, printer, "hp-6950.jpg"));
		articleRepository.save(new Article("WF 2830", "Epson", 100, 1, printer, "wf-2830.jpg"));

		articleRepository.save(new Article("7", "GoPro", 150, 1, camera, "gopro-7.jpg"));
		articleRepository.save(new Article("10", "GoPro", 200, 1, camera, "gopro-10.jpg"));
		articleRepository.save(new Article("HT", "Panasonic", 1500, 1, tv, "panasonic.jpg"));
		articleRepository.save(new Article("L43", "Philips", 450, 1, tv, "philips.jpg"));

		userRepository
				.save(new User(null, "Mathieu", securityConfig.encodePassword("fms2022"), "ADMIN", true, null));
		userRepository
				.save(new User(null, "Mathieu", securityConfig.encodePassword("fms2022"), "USER", true, null));
		userRepository
				.save(new User(null, "Tristan", securityConfig.encodePassword("fms2022"), "USER", true, null));
		userRepository
				.save(new User(null, "Martial", securityConfig.encodePassword("fms2022"), "USER", true, null));
		userRepository
				.save(new User(null, "Eric", securityConfig.encodePassword("fms2022"), "USER", true, null));

		roleRepository.save(new Role(null, "USER"));
		roleRepository.save(new Role(null, "ADMIN"));
	}
}