package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class TpShopMvcApp implements CommandLineRunner  {
	
	@Autowired
	private IBusinessImpl job;

	public static void main(String[] args) {
		SpringApplication.run(TpShopMvcApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//	articleRepository.save(new Article(null,"Samsung"," S8",250,10,1));
//		articleRepository.findAll().forEach(a -> System.out.println(a));
		
		generateValues();
	}
	
	public void generateValues() {
		Category smartphone = job.categoryRepository.save(new Category("Smartphone"));
		Category pc = job.categoryRepository.save(new Category("Ordinateur"));
		Category tablet = job.categoryRepository.save(new Category("Tablette"));
		Category printer = job.categoryRepository.save(new Category("Imprimante"));
		Category camera = job.categoryRepository.save(new Category("Camera"));
		Category tv = job.categoryRepository.save(new Category("TV"));
		
		job.articleRepository.save(new Article(null,"S8","Samsung",250,1,smartphone, "samsung.jpg"));
		job.articleRepository.save(new Article(null,"S9","Samsung", 300,1,smartphone, "samsung.jpg"));
		job.articleRepository.save(new Article(null,"iPhone 10","Apple",500,1,smartphone, "iphone.jpg"));		
		job.articleRepository.save(new Article(null,"MI11","Xiaomi",100,1,smartphone, "xiaomi.jpg"));
		job.articleRepository.save(new Article(null,"9 Pro","OnePlus",200,1,smartphone, "oneplus.jpg"));
		job.articleRepository.save(new Article(null,"Pixel 5","Google",350,1,smartphone, "googleSp.jpg"));
		job.articleRepository.save(new Article(null,"F3","Poco",150,1,smartphone, "poco.jpg"));
		
		job.articleRepository.save(new Article(null,"810","Dell",550,1,pc, "dellpc.jpg"));
		job.articleRepository.save(new Article(null,"F756","Asus",600,1,pc, "asuspc.jpg"));
		job.articleRepository.save(new Article(null,"E80","Asus",700,1,pc, "asuspc.jpg"));
		job.articleRepository.save(new Article(null,"Pro","MacBook",1000,1,pc, "macbook.jpg"));
		job.articleRepository.save(new Article(null,"Air","MacBook",1200,1,pc, "macbook.jpg"));
		
		job.articleRepository.save(new Article(null,"XL 5","IPad",300,1,tablet, "testimage1.png"));
		job.articleRepository.save(new Article(null,"XL 7","IPad",500,1,tablet, "testimage1.png"));
		
		
		job.articleRepository.save(new Article(null,"MG30","Canon",50,1,printer, "testimage1.png"));
		job.articleRepository.save(new Article(null,"MG50","Canon",60,1,printer, "testimage1.png"));
		job.articleRepository.save(new Article(null,"800","HP",50,1,printer, "testimage1.png"));
		job.articleRepository.save(new Article(null,"3T","Epson",100,1,printer, "testimage1.png"));
		
		job.articleRepository.save(new Article(null,"7","GoPro",150,1,camera, "testimage1.png"));
		job.articleRepository.save(new Article(null,"10","GoPro",200,1,camera, "testimage1.png"));
		
		job.articleRepository.save(new Article(null,"HT","Panasonic",1500,1,tv, "testimage1.png"));
		job.articleRepository.save(new Article(null,"L43","Philips",450,1,tv, "testimage1.png"));	
	}
	
	@RequestMapping(value = "/")
	public String index() {
	return "index";
	}
	//
}
