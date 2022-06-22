package fr.fms.business;

import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.dao.UserRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.User;


@Service
public class IBusinessImpl implements IBusiness{
	
	@Autowired
	public ArticleRepository articleRepository;
	
	@Autowired
	public CategoryRepository categoryRepository;
	
	@Autowired
	public UserRepository userRepository;
	
	@Override
	@PostConstruct
	public List<Article> getAllArticles() {
		return articleRepository.findAll();
	}
	
	@Override
	public void createArticle(String brand,String description, double price,int quantity,Category category, String image) {
		articleRepository.save(new Article(null,brand,description,price,quantity,category, image));
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
	

}