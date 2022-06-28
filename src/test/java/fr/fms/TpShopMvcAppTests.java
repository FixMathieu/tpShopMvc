package fr.fms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.fms.business.IBusinessImpl;

@SpringBootTest
class TpShopMvcAppTests {
	
	@Autowired
	IBusinessImpl job;

	@Test
	void contextLoads() {
		assertFalse(1==2);
	}
	
	@Test
	void testTotalAmountCart() {
		job.addToCart((long)1);
		job.addToCart((long)2);
		
		
		assertEquals(job.getTotalAmount(),550);
	}

}
