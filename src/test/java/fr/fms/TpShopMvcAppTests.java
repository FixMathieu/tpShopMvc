package fr.fms;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.dao.CommandeRepository;
import fr.fms.dao.UserRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.Commande;
import fr.fms.entities.Customer;
import fr.fms.entities.User;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes=TpShopMvcApp.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TpShopMvcAppTests {
	
//	@Autowired(required=true)
//	IBusinessImpl businessImpl;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommandeRepository commandeRepository;

//	private static Instant startedAt;
	
//	@BeforeEach
//	public void beforeEachTest() {
//		System.out.println("avant chaque test");
//	}
//	
//	@AfterEach
//	public void afterEachTest() {
//		System.out.println("après chaque test");
//	}
//
//	@BeforeAll
//	public static void initStartingTime() {
//		System.out.println("Appel avant tous les tests");
//		startedAt = Instant.now();
//	}
//	
//	@AfterAll
//	public static void showTestDuration() {
//		System.out.println("Appel après tout les tests");
//		final Instant endedAt = Instant.now();
//		final long duration = Duration.between(startedAt, endedAt).toMillis();
//		System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
//	}
//	
//	@ParameterizedTest(name= "{0} x 0 doit être égal à 0")
//	@ValueSource(ints = {1, 2, 42, 1011, 5089})
//	public void multiply_shouldReturnZero(int arg) {
//		assertEquals(0,arg*0);
//	}
	
//	@Timeout(1)
//	@Test
//	public void orderShouldComputeLess1Second() {
//		Customer customer = new Customer((long) 1,"Ondres","tlaclau@mail.com","Tristan","Laclau",(long)0613201407);
//		businessImpl.saveCustomer(customer);
//		businessImpl.placeCommande(customer);
//	}
	
//	
//	@Test
//	void contextLoads() {
//		assertFalse(1==2);
//	}
	
//	@Test
//	void testTotalAmountCart() {
//		//Arrange
//		businessImpl.addToCart((long)1);
//		businessImpl.addToCart((long)2);
//		
//		//Act
//		double amount = businessImpl.getTotalAmount();
//		
//		//Assert
//		assertEquals(amount,550);
//	}
	
	@Test
    void test_add_article() {
        //GIVEN
        Category anonymous = categoryRepository.save(new Category(null,"anonymous",null));          
        articleRepository.save(new Article(null,"incognito","incognito 007" , 375 , 1 , anonymous,null));      

        //WHEN
        Article article = articleRepository.findByBrandContains("incognito").get(0);

        //THEN
        assertEquals("incognito 007", article.getDescription());
    }
	
	@Test
	void should_find_one_article() {
		Iterable<Article> articles = articleRepository.findAll();
		assertThat(articles).isNotEmpty();
	}
	
	@Test
	void test_add_category() {
        Category category = categoryRepository.save(new Category(null,"category",null));           

        assertEquals("category",category.getName());
	}
	
	@Test
	void should_find_one_category() {
		Iterable<Category> categories = categoryRepository.findAll();
		assertThat(categories).isNotEmpty();
	}
	
	@Test
	void test_add_user() {
		User user = userRepository.save(new User(null,"Walton","pwd","USER",false, null));
		
		assertEquals("Walton",user.getUsername());
	}
	
	@Test
	void should_find_one_user() {
		Iterable<User> users = userRepository.findAll();
		assertThat(users).isNotEmpty();
	}
	
	@Test
	void test_commande() {
		Commande commande = commandeRepository.save(new Commande(null,null, 0,new Customer(null,"Baker Street","detective@mail.com","Sherlock","Holmes",0),null,new User(null,"Walton","pwd","USER",false, null)));
		
	}
}
