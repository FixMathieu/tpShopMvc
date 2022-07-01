package fr.fms.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.dao.CommandeRepository;
import fr.fms.dao.CustomerRepository;
import fr.fms.dao.DetailsRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.Commande;
import fr.fms.entities.Customer;
import fr.fms.entities.Details;
import fr.fms.entities.Role;
import fr.fms.entities.User;
import fr.fms.security.SecurityConfig;

@Controller
public class ArticleController {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	IBusinessImpl businessImpl;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	DetailsRepository detailsRepository;
	@Autowired
	CommandeRepository commandeRepository;
	@Autowired
	private SecurityConfig securityConfig;

	@GetMapping("/index")
	public String index(Model model) {
		nameAuth(model);
//		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();	// verifie utilisateur Connecte
//		 String currentUserCo = auth.getName();											// recupere son nom
//		 model.addAttribute("auth",currentUserCo);
		return "index";
	}

	@GetMapping("/cart")
	public String cart(Model model) {
		nameAuth(model);
		List<Article> articles = articleRepository.findAll();
		List<Article> articlesInCart = articles.stream()
				.filter(article -> businessImpl.getCart().get(article.getId()) != null).collect(Collectors.toList());

		model.addAttribute("quantities", businessImpl.getCart());
		model.addAttribute("listArticle", articlesInCart);
		System.out.println(businessImpl.getTotalAmount());
		model.addAttribute("price",businessImpl.getTotalAmount());
		return "cart";
	}

	@GetMapping("/addCart")
	public String addCart(Long id) {

		businessImpl.addToCart(id);

		return "redirect:/articles";
	}

	@GetMapping("/removeCart")
	public String removeCart(Long id) {

		businessImpl.removeFromCart(id);

		return "redirect:/cart";
	}

	@GetMapping("/articles")
	public String articles(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "keyword", defaultValue = "") String kw,
			@RequestParam(name = "category", defaultValue = "-1") long catId,
			@RequestParam(name = "isAdmin", defaultValue = "true") String isAdmin) {
		Page<Article> articles;

		if (catId != -1) {
			articles = articleRepository.findByDescriptionContainsAndCategoryId(kw, catId, PageRequest.of(page, 5));
		} else {
			articles = articleRepository.findByDescriptionContains(kw, PageRequest.of(page, 5));
		}
//		Page<Article> articlesCat=articleRepository.findByCategoryId(catId,PageRequest.of(page, 5));
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("keyword", kw);

//		 UserDetails userDetails = (UserDetails) auth.getPrincipal();					// recupere ses droits/roles
		nameAuth(model);
		model.addAttribute("listCategory", categories);
		model.addAttribute("category", catId);
		model.addAttribute("listArticle", articles.getContent());
		model.addAttribute("pages", new int[articles.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("isAdmin", isAdmin);
		// verifie si un utilisateur est connecté ( "${auth!=null}" ) ou pas (
		// "${auth==null}" )
		return "articles";
	}

	@GetMapping("/delete")
	public String delete(Long id, int page, String keyword) {
		articleRepository.deleteById(id);
		return "redirect:/articles?page=" + page + "&keyword=" + keyword;
	}

	@PostMapping("/save")
	public String save(@Valid Article article, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "edit";
		articleRepository.save(article);
		return "redirect:/articles";
	}

	@GetMapping("/article")
	public String article(Model model) {
		nameAuth(model);
		List<Article> articles = articleRepository.findAll();
		model.addAttribute("listArticle", articles);
		List<Category> category = categoryRepository.findAll();
		model.addAttribute("listCategory", category);
		model.addAttribute("article", new Article());
		return "article";
	}

	@GetMapping("/edit")
	public String edit(Long id, Model model) {
		nameAuth(model);
		List<Article> articles = articleRepository.findAll();
		model.addAttribute("listArticle", articles);
		List<Category> category = categoryRepository.findAll();
		model.addAttribute("listCategory", category);
		Article article = articleRepository.findById(id).get();
		model.addAttribute("article", article);
		return "edit";
	}

	public void nameAuth(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // verifie utilisateur Connecte
		String currentUserCo = auth.getName(); // recupere son nom
		model.addAttribute("auth", currentUserCo);
	}

	@GetMapping("/commande")
	public String commande(Model model) {
		nameAuth(model);
		model.addAttribute("customer", new Customer());
		return "commande";

	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}

	@GetMapping("/403")
	public String refused() {
		return "403";
	}

	@PostMapping("/submitCustomer")
	public String submitCustomer(@Valid Customer customer, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "commande";
		customerRepository.save(customer);
		Commande commande = businessImpl.placeCommande(customer);

		return "redirect:/sales?commandeId=" + commande.getId();
	}

	@GetMapping("/login")
	public String login(Model model, @RequestParam(name = "error", defaultValue = "false") boolean loginError) {
		if (loginError) {

		}
		nameAuth(model);
		return "login";
	}

	@GetMapping("/loginAuth")
	public String loginAuth(Model model) {
		nameAuth(model);
		return "redirect:/index";
	}

	@GetMapping("/sales")
	public String test(Model model, @RequestParam(name = "commandeId") long commandeId) {
		nameAuth(model);
		Commande commande = commandeRepository.findById(commandeId).get();
		List<Details> details = detailsRepository.findByCommandeId(commandeId);
		model.addAttribute("commande", commande);
		model.addAttribute("details", details);
		return "sales";
	}

	@GetMapping("/myOrder")
	public String myOrder(Model model) {
		nameAuth(model);
		long userId = businessImpl.userCurrent().getId();
		List<Commande> commandes = commandeRepository.findByUserId(userId);
		model.addAttribute("listCommande", commandes);
//				List<Commande>commandes = commandeRepository.findByCustomerId(customerId);
//				List<Details> details = detailsRepository.findByCommandeId(commandeId);
//				model.addAttribute("listCommande",commandes);
//				model.addAttribute("listDetails",details);
		return "myOrder";
	}

//	@RequestMapping(value = "/")
//	public String index() {
//		return "index";
//	}
	
	@RequestMapping(value = "/")
	public @ResponseBody String home() {
		return "Hello";
	}

	@RequestMapping("/greating")
	public @ResponseBody String greating() {
		return businessImpl.great();
	}

	@GetMapping("/backup")
	public String backup() {

		generateValues();
		return "redirect:/articles";
	}

	public void generateValues() {
		businessImpl.articleRepository.deleteAll();
		businessImpl.categoryRepository.deleteAll();
		businessImpl.userRepository.deleteAll();
		Category smartphone = businessImpl.categoryRepository.save(new Category("Smartphone"));
		Category pc = businessImpl.categoryRepository.save(new Category("Ordinateur"));
		Category tablet = businessImpl.categoryRepository.save(new Category("Tablette"));
		Category printer = businessImpl.categoryRepository.save(new Category("Imprimante"));
		Category camera = businessImpl.categoryRepository.save(new Category("Camera"));
		Category tv = businessImpl.categoryRepository.save(new Category("TV"));

		businessImpl.articleRepository.save(new Article("S8", "Samsung", 250, 1, smartphone, "samsung.jpg"));
		businessImpl.articleRepository.save(new Article("S9", "Samsung", 300, 1, smartphone, "samsung.jpg"));
		businessImpl.articleRepository.save(new Article("iPhone 10", "Apple", 500, 1, smartphone, "iphone.jpg"));
		businessImpl.articleRepository.save(new Article("MI11", "Xiaomi", 100, 1, smartphone, "xiaomi.jpg"));
		businessImpl.articleRepository.save(new Article("9 Pro", "OnePlus", 200, 1, smartphone, "oneplus.jpg"));
		businessImpl.articleRepository.save(new Article("Pixel 5", "Google", 350, 1, smartphone, "googleSp.jpg"));
		businessImpl.articleRepository.save(new Article("F3", "Poco", 150, 1, smartphone, "poco.jpg"));

		businessImpl.articleRepository.save(new Article("810", "Dell", 550, 1, pc, "dellpc.jpg"));
		businessImpl.articleRepository.save(new Article("F756", "Asus", 600, 1, pc, "asuspc.jpg"));
		businessImpl.articleRepository.save(new Article("E80", "Asus", 700, 1, pc, "asuspc.jpg"));
		businessImpl.articleRepository.save(new Article("Pro", "MacBook", 1000, 1, pc, "macbook.jpg"));
		businessImpl.articleRepository.save(new Article("Air", "MacBook", 1200, 1, pc, "macbook.jpg"));

		businessImpl.articleRepository.save(new Article("XL 5", "IPad", 300, 1, tablet, "ipad.jpg"));
		businessImpl.articleRepository.save(new Article("XL 7", "IPad", 500, 1, tablet, "ipad.jpg"));

		businessImpl.articleRepository.save(new Article("MG30", "Canon", 50, 1, printer, "canon-mg30.jpg"));
		businessImpl.articleRepository.save(new Article("MG50", "Canon", 60, 1, printer, "canon-mg50.jpg"));
		businessImpl.articleRepository.save(new Article("OfficeJet 6950", "HP", 50, 1, printer, "hp-6950.jpg"));
		businessImpl.articleRepository.save(new Article("WF 2830", "Epson", 100, 1, printer, "wf-2830.jpg"));

		businessImpl.articleRepository.save(new Article("7", "GoPro", 150, 1, camera, "gopro-7.jpg"));
		businessImpl.articleRepository.save(new Article("10", "GoPro", 200, 1, camera, "gopro-10.jpg"));

		businessImpl.articleRepository.save(new Article("HT", "Panasonic", 1500, 1, tv, "panasonic.jpg"));
		businessImpl.articleRepository.save(new Article("L43", "Philips", 450, 1, tv, "philips.jpg"));

		businessImpl.userRepository
				.save(new User(null, "Mathieu", securityConfig.encodePassword("fms2022"), "ADMIN", true, null));
		businessImpl.userRepository
				.save(new User(null, "Mathieu", securityConfig.encodePassword("fms2022"), "USER", true, null));
		businessImpl.userRepository
				.save(new User(null, "Tristan", securityConfig.encodePassword("fms2022"), "USER", true, null));
		businessImpl.userRepository
				.save(new User(null, "Martial", securityConfig.encodePassword("fms2022"), "USER", true, null));
		businessImpl.userRepository
				.save(new User(null, "Eric", securityConfig.encodePassword("fms2022"), "USER", true, null));

		businessImpl.roleRepository.save(new Role(null, "USER"));
		businessImpl.roleRepository.save(new Role(null, "ADMIN"));
	}
}
