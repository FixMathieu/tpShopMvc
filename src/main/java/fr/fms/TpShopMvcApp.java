package fr.fms;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.Commande;
import fr.fms.entities.Details;
import fr.fms.entities.Role;
import fr.fms.entities.User;
import fr.fms.security.SecurityConfig;

@SpringBootApplication
public class TpShopMvcApp implements CommandLineRunner  {
	
	@Autowired
	private IBusinessImpl job;
	
	@Autowired
	private SecurityConfig securityConfig;

	public static void main(String[] args) {
		SpringApplication.run(TpShopMvcApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//	articleRepository.save(new Article(null,"Samsung"," S8",250,10,1));
//	articleRepository.findAll().forEach(a -> System.out.println(a));
		
		generateValues();
	}
	
	public void generateValues() {
		Category smartphone = job.categoryRepository.save(new Category("Smartphone"));
		Category pc = job.categoryRepository.save(new Category("Ordinateur"));
		Category tablet = job.categoryRepository.save(new Category("Tablette"));
	Category printer = job.categoryRepository.save(new Category("Imprimante"));
		Category camera = job.categoryRepository.save(new Category("Camera"));
		Category tv = job.categoryRepository.save(new Category("TV"));
		
		job.articleRepository.save(new Article("S8","Samsung",250,1,smartphone, "samsung.jpg"));
		job.articleRepository.save(new Article("S9","Samsung", 300,1,smartphone, "samsung.jpg"));
		job.articleRepository.save(new Article("iPhone 10","Apple",500,1,smartphone, "iphone.jpg"));
		job.articleRepository.save(new Article("MI11","Xiaomi",100,1,smartphone, "xiaomi.jpg"));
		job.articleRepository.save(new Article("9 Pro","OnePlus",200,1,smartphone, "oneplus.jpg"));
		job.articleRepository.save(new Article("Pixel 5","Google",350,1,smartphone, "googleSp.jpg"));
		job.articleRepository.save(new Article("F3","Poco",150,1,smartphone, "poco.jpg"));
		
		job.articleRepository.save(new Article("810","Dell",550,1,pc, "dellpc.jpg"));
		job.articleRepository.save(new Article("F756","Asus",600,1,pc, "asuspc.jpg"));
		job.articleRepository.save(new Article("E80","Asus",700,1,pc, "asuspc.jpg"));
		job.articleRepository.save(new Article("Pro","MacBook",1000,1,pc, "macbook.jpg"));
		job.articleRepository.save(new Article("Air","MacBook",1200,1,pc, "macbook.jpg"));
		
		job.articleRepository.save(new Article("XL 5","IPad",300,1,tablet, "ipad.jpg"));
		job.articleRepository.save(new Article("XL 7","IPad",500,1,tablet, "ipad.jpg"));
		
		
		job.articleRepository.save(new Article("MG30","Canon",50,1,printer, "canon-mg30.jpg"));
		job.articleRepository.save(new Article("MG50","Canon",60,1,printer, "canon-mg50.jpg"));
		job.articleRepository.save(new Article("OfficeJet 6950","HP",50,1,printer, "hp-6950.jpg"));
		job.articleRepository.save(new Article("WF 2830","Epson",100,1,printer, "wf-2830.jpg"));
		
		job.articleRepository.save(new Article("7","GoPro",150,1,camera, "gopro-7.jpg"));
		job.articleRepository.save(new Article("10","GoPro",200,1,camera, "gopro-10.jpg"));
		
		job.articleRepository.save(new Article("HT","Panasonic",1500,1,tv, "panasonic.jpg"));
		job.articleRepository.save(new Article("L43","Philips",450,1,tv, "philips.jpg"));

		
		
		
		/*	
		job.articleRepository.save(new Article("XL 5","IPad",300,1,tablet, "testimage1.png"));
		job.articleRepository.save(new Article("XL 7","IPad",500,1,tablet, "testimage1.png"));
		
		
		job.articleRepository.save(new Article("MG30","Canon",50,1,printer, "testimage1.png"));
		job.articleRepository.save(new Article("MG50","Canon",60,1,printer, "testimage1.png"));
		job.articleRepository.save(new Article("800","HP",50,1,printer, "testimage1.png"));
		job.articleRepository.save(new Article("3T","Epson",100,1,printer, "testimage1.png"));
		
		job.articleRepository.save(new Article("7","GoPro",150,1,camera, "testimage1.png"));
		job.articleRepository.save(new Article("10","GoPro",200,1,camera, "testimage1.png"));
		
		job.articleRepository.save(new Article("HT","Panasonic",1500,1,tv, "testimage1.png"));
		job.articleRepository.save(new Article("L43","Philips",450,1,tv, "testimage1.png"));	
		*/
		
		
		job.userRepository.save(new User(null,"Mathieu",securityConfig.encodePassword("fms2022"),"ADMIN",true));
		job.userRepository.save(new User(null,"Mathieu",securityConfig.encodePassword("fms2022"),"USER",true));
		job.userRepository.save(new User(null,"Tristan",securityConfig.encodePassword("fms2022"),"USER",true));
		job.userRepository.save(new User(null,"Martial",securityConfig.encodePassword("fms2022"),"USER",true));
		job.userRepository.save(new User(null,"Eric",securityConfig.encodePassword("fms2022"),"USER",true));
		
		job.roleRepository.save(new Role(null,"USER"));
		job.roleRepository.save(new Role(null,"ADMIN"));
		

	}
	
	@RequestMapping(value = "/")
	public String index() {
	return "index";
	}
	
}
