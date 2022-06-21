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

		job.articleRepository.save(new Article(null,"S8","Samsung",250,0,smartphone, "testimage1.png"));
		job.articleRepository.save(new Article(null,"S9","Samsung", 300,0,smartphone, "testimage1.png"));
		job.articleRepository.save(new Article(null,"iPhone 10","Apple",500,0,smartphone, "testimage1.png"));		
		job.articleRepository.save(new Article(null,"MI11","Xiaomi",100,0,smartphone, "testimage1.png"));
		job.articleRepository.save(new Article(null,"9 Pro","OnePlus",200,0,smartphone, "testimage1.png"));
		job.articleRepository.save(new Article(null,"Pixel 5","Google",350,0,smartphone, "testimage1.png"));
		job.articleRepository.save(new Article(null,"F3","Poco",150,0,smartphone, "testimage1.png"));

		job.articleRepository.save(new Article(null,"810","Dell",550,0,pc, "testimage1.png"));
		job.articleRepository.save(new Article(null,"F756","Asus",600,0,pc, "testimage1.png"));
		job.articleRepository.save(new Article(null,"E80","Asus",700,0,pc, "testimage1.png"));
		job.articleRepository.save(new Article(null,"Pro","MacBook",1000,0,pc, "testimage1.png"));
		job.articleRepository.save(new Article(null,"Air","MacBook",1200,0,pc, "testimage1.png"));

		job.articleRepository.save(new Article(null,"XL 5","IPad",300,0,tablet, "testimage1.png"));
		job.articleRepository.save(new Article(null,"XL 7","IPad",500,0,tablet, "testimage1.png"));


		job.articleRepository.save(new Article(null,"MG30","Canon",50,0,printer, "testimage1.png"));
		job.articleRepository.save(new Article(null,"MG50","Canon",60,0,printer, "testimage1.png"));
		job.articleRepository.save(new Article(null,"800","HP",50,0,printer, "testimage1.png"));
		job.articleRepository.save(new Article(null,"3T","Epson",100,0,printer, "testimage1.png"));

		job.articleRepository.save(new Article(null,"7","GoPro",150,0,camera, "testimage1.png"));
		job.articleRepository.save(new Article(null,"10","GoPro",200,0,camera, "testimage1.png"));

		job.articleRepository.save(new Article(null,"HT","Panasonic",1500,0,tv, "testimage1.png"));
		job.articleRepository.save(new Article(null,"L43","Philips",450,0,tv, "testimage1.png"));	
	}

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	//
}