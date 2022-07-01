package fr.fms;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes=TpShopMvcApp.class)

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(ArticleController.class)
class TpShopControllersTests {

	@Autowired
	private MockMvc mvc;
	
	@Test
	void contextLoads() {
		assertFalse(1==2);
	}
	
	@Test
	public void test_get_welcome() throws Exception{
		
		this.mvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) content().string(containsString("Hello")));
	}
}
