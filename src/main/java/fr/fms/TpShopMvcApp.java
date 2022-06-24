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
//		Category smartphone = job.categoryRepository.save(new Category("Smartphone"));
//		Category pc = job.categoryRepository.save(new Category("Ordinateur"));
//		Category tablet = job.categoryRepository.save(new Category("Tablette"));
//		Category printer = job.categoryRepository.save(new Category("Imprimante"));
//		Category camera = job.categoryRepository.save(new Category("Camera"));
//		Category tv = job.categoryRepository.save(new Category("TV"));
		
//		job.articleRepository.save(new Article("S8","Samsung",250,1,smartphone, "samsung.jpg"));
//		job.articleRepository.save(new Article("S9","Samsung", 300,1,smartphone, "samsung.jpg"));
//		job.articleRepository.save(new Article("iPhone 10","Apple",500,1,smartphone, "iphone.jpg"));
//		job.articleRepository.save(new Article("MI11","Xiaomi",100,1,smartphone, "xiaomi.jpg"));
//		job.articleRepository.save(new Article("9 Pro","OnePlus",200,1,smartphone, "oneplus.jpg"));
//		job.articleRepository.save(new Article("Pixel 5","Google",350,1,smartphone, "googleSp.jpg"));
//		job.articleRepository.save(new Article("F3","Poco",150,1,smartphone, "poco.jpg"));
//		
//		job.articleRepository.save(new Article("810","Dell",550,1,pc, "dellpc.jpg"));
//		job.articleRepository.save(new Article("F756","Asus",600,1,pc, "asuspc.jpg"));
//		job.articleRepository.save(new Article("E80","Asus",700,1,pc, "asuspc.jpg"));
//		job.articleRepository.save(new Article("Pro","MacBook",1000,1,pc, "macbook.jpg"));
//		job.articleRepository.save(new Article("Air","MacBook",1200,1,pc, "macbook.jpg"));
//		
//		job.articleRepository.save(new Article("XL 5","IPad",300,1,tablet, "testimage1.png"));
//		job.articleRepository.save(new Article("XL 7","IPad",500,1,tablet, "testimage1.png"));
//		
//		
//		job.articleRepository.save(new Article("MG30","Canon",50,1,printer, "testimage1.png"));
//		job.articleRepository.save(new Article("MG50","Canon",60,1,printer, "testimage1.png"));
//		job.articleRepository.save(new Article("800","HP",50,1,printer, "testimage1.png"));
//		job.articleRepository.save(new Article("3T","Epson",100,1,printer, "testimage1.png"));
//		
//		job.articleRepository.save(new Article("7","GoPro",150,1,camera, "testimage1.png"));
//		job.articleRepository.save(new Article("10","GoPro",200,1,camera, "testimage1.png"));
//		
//		job.articleRepository.save(new Article("HT","Panasonic",1500,1,tv, "testimage1.png"));
//		job.articleRepository.save(new Article("L43","Philips",450,1,tv, "testimage1.png"));	
		
		
//		job.userRepository.save(new User(null,"Mathieu","Fix","Dax", "mathieu.fix@fms.com", 118218, "mf","ADMIN"));
//		job.userRepository.save(new User(null,"Tristan","Laclau","Bayonne", "tristan.laclau@fms.com", 118008, "tl", "USER"));
//		job.userRepository.save(new User(null,"Martial","Derand","St Paul", "martial.derand@fms.com", 118860, "md", "USER"));
//		job.userRepository.save(new User(null,"Eric","Mauler","St Geour", "eric.mauler@fms.com", 118109, "em", "USER"));
//		
//		job.userRepository.save(new User("Mathieu","fms2022","ADMIN",true));
//		job.userRepository.save(new User("Tristan","fms2022","USER",true));
//		job.userRepository.save(new User("Martial","fms2022","USER",true));
//		job.userRepository.save(new User("Eric","fms2022","USER",true));
//		
//		job.roleRepository.save(new Role("USER"));
//		job.roleRepository.save(new Role("ADMIN"));
		

	}
	
	@RequestMapping(value = "/")
	public String index() {
	return "index";
	}
	//
}
