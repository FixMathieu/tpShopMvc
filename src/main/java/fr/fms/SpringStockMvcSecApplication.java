package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;


import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;

@SpringBootApplication
public class SpringStockMvcSecApplication implements CommandLineRunner  {
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringStockMvcSecApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//	articleRepository.save(new Article(null,"Samsung"," S8",250,10,1));
//		articleRepository.findAll().forEach(a -> System.out.println(a));
	}
	@RequestMapping(value = "/")
	public String index() {
	return "index";
	}
}
