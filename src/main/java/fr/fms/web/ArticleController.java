package fr.fms.web;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@Controller
public class ArticleController {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	IBusinessImpl job;

	@GetMapping("/index")
	public String index(Model model) {
Authentication auth = SecurityContextHolder.getContext().getAuthentication();	// verifie utilisateur Connecte
String currentUserCo = auth.getName();											// recupere son nom
UserDetails userDetails = (UserDetails) auth.getPrincipal();					// recupere ses droits/roles
model.addAttribute("currentUserCo", currentUserCo);

		return "index";
	}

	@GetMapping("/cart")
	public String cart(Model model) {
		List<Article> articles = articleRepository.findAll();
		List<Article> articlesInCart = articles.stream().filter(article -> job.getCart().get(article.getId()) != null)
				.collect(Collectors.toList());

		model.addAttribute("quantities", job.getCart());

		model.addAttribute("listArticle", articlesInCart);
		return "cart";
	}
	// @GetMapping("/cart") public String cart(Model model,@RequestParam(name="page",defaultValue="0") int page) { Page<Article> articles = articleRepository.findByQuantityGreaterThan(0, PageRequest.of(page, 5)); model.addAttribute("listArticle",articles.getContent()); return "cart"; }
	

	@GetMapping("/addCart")
	public String addCart(Long id) {

		job.addToCart(id);

		return "redirect:/articles";
	}

	@GetMapping("/removeCart")
	public String removeCart(Long id) {

		job.removeFromCart(id);

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
//		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();	// verifie utilisateur Connecte
//		 String currentUserCo = auth.getName();											// recupere son nom
//		 UserDetails userDetails = (UserDetails) auth.getPrincipal();					// recupere ses droits/roles
		model.addAttribute("listCategory", categories);
		model.addAttribute("category", catId);
		model.addAttribute("listArticle", articles.getContent());
		model.addAttribute("pages", new int[articles.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("isAdmin", isAdmin);
//		model.addAttribute("auth",auth);								// verifie si un utilisateur est connecté ( "${auth!=null}" ) ou pas ( "${auth==null}" )
		return "articles";
	}


	@GetMapping("/delete")
	public String delete(Long id, int page, String keyword) {
		articleRepository.deleteById(id);
		return "redirect:/articles?page="+page+"&keyword="+keyword;
	}

	//	@PostMapping("/update")
	//	public String update(@Valid  Article article, BindingResult bindingResult){
	//		if(bindingResult.hasErrors()) return "article";
	//		articleRepository.save(article);
	//		return"redirect:/articles";
	//	}
	
	@PostMapping("/save")
	public String save(@Valid Article article, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "edit";
		articleRepository.save(article);
		return "redirect:/articles";
	}

	@GetMapping("/article")
	public String article(Model model) {
		List<Article> articles = articleRepository.findAll();
		model.addAttribute("listArticle", articles);
		List<Category> category = categoryRepository.findAll();
		model.addAttribute("listCategory", category);
		model.addAttribute("article", new Article());
		return "article";
	}

	@GetMapping("/edit")
	public String edit(Long id, Model model) {
		List<Article> articles = articleRepository.findAll();
		model.addAttribute("listArticle", articles);
		List<Category> category = categoryRepository.findAll();
		model.addAttribute("listCategory", category);
		Article article = articleRepository.findById(id).get();
		model.addAttribute("article", article);
		return "edit";
	}


}
