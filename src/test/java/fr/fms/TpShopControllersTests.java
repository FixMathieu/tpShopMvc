package fr.fms;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes=TpShopMvcApp.class)
//@WebMvcTest(ArticleController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TpShopControllersTests {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void test_get_welcome() throws Exception{
		
		this.mvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) content().string(containsString("Hello")));
	}
}
