package fr.fms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Customer;

@SpringBootTest
class TpShopMvcAppTests {
	
	@Autowired
	IBusinessImpl job;

	private static Instant startedAt;
	
	@BeforeEach
	public void beforeEachTest() {
		System.out.println("avant chaque test");
	}
	
	@AfterEach
	public void afterEachTest() {
		System.out.println("après chaque test");
	}

	@BeforeAll
	public static void initStartingTime() {
		System.out.println("Appel avant tous les tests");
		startedAt = Instant.now();
	}
	
	@AfterAll
	public static void showTestDuration() {
		System.out.println("Appel après tout les tests");
		final Instant endedAt = Instant.now();
		final long duration = Duration.between(startedAt, endedAt).toMillis();
		System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
	}
	
	@ParameterizedTest(name= "{0} x 0 doit être égal à 0")
	@ValueSource(ints = {1, 2, 42, 1011, 5089})
	public void multiply_shouldReturnZero(int arg) {
		assertEquals(0,arg*0);
	}
	
	@Timeout(1)
	@Test
	public void orderShouldComputeLess1Second() {
		Customer customer = new Customer((long) 1,"Ondres","tlaclau@mail.com","Tristan","Laclau",(long)0613201407);
		job.placeCommande(customer);
	}
	
	
	@Test
	void contextLoads() {
		assertFalse(1==2);
	}
	
	@Test
	void testTotalAmountCart() {
		//Arrange
		job.addToCart((long)1);
		job.addToCart((long)2);
		
		//Act
		double amount = job.getTotalAmount();
		
		//Assert
		assertEquals(amount,550);
	}
}
