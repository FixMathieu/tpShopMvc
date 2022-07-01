package fr.fms;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import fr.fms.business.IBusinessImpl;
import fr.fms.web.ArticleController;

@WebMvcTest(ArticleController.class)
public class TpShopArticleTests {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private IBusinessImpl businessImpl;
	
	@Test
	public void test_get_welcome() throws Exception{
		when(businessImpl.great()).thenReturn("Hello, Mock");
		
		this.mvc.perform(get("/greating"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content()
					.string(containsString("Hello, Mock")));
	}
}
