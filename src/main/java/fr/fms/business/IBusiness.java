package fr.fms.business;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.User;

public interface IBusiness {

	/**
	 * Returns all articles of the database
	 * @return every article, in List<Article>
	 */
	public List<Article> getAllArticles();
	
	/**
	 * Insert an article in the database
	 * @param description
	 * @param brand
	 * @param price
	 * @param quantity
	 * @param category
	 * @param image
	 */
	public void createArticle(String description, String brand, double price,int quantity,Category category, String image);
	
	/**
	 * Delete the article corresponding to the id in parameter
	 * @param id : the id of the article to delete
	 */
	public void deleteArticleById(Long id);
	
	/**
	 * Update an article in the database
	 * @param id
	 * @param description
	 * @param brand
	 * @param price
	 * @param quantity
	 * @param catName : name of the category
	 * @param image
	 */
	public void updateArticle(Long id, String description, String brand, double price, int quantity, String catName, String image);
	
	/**
	 * Returns a page of articles for ease of use in display functions
	 * @param pageable
	 * @return Page<Article> a page of articles
	 * @throws Exception
	 */
	Page<Article> getArticlesPages(Pageable pageable) throws Exception;
	
	/**
	 * Returns all users in the database
	 * @return a List<User> containing all users
	 */
	public List<User> getAllUsers();
	
	/**
	 * Returns all categories in the database
	 * @return a List<Category> containing all categories
	 */
	public List<Category> getAllCategories();
	
	/**
	 * Insert a category in the database
	 * @param name : the name of the category
	 */
	public void createCategory(String name);
	
	/**
	 * Removes a category from the database, based on its id
	 * @param id : the id of the selected category
	 */
	public void deleteCategoryById(Long id);
	
	/**
	 * Updates a category in the database
	 * @param id : the id of the selected category
	 * @param name : the new name of the category
	 */
	public void updateCategory(Long id, String name);

}