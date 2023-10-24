package control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class) //스프링용 단위테스트
@ContextConfiguration(classes = {config.MyApplicationContext.class, config.MyMVCContext.class})
@WebAppConfiguration
class CustomerControllerTest {
	@Autowired
	WebApplicationContext ctx;
	
	MockMvc mockMvc; //모의객체
	
	//@Autowired
	//CustomerController controller;
	@Test
	void test() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		assertNotNull(mockMvc);
	}
	
	@Test
	void testIddupchk() throws Exception{
		String id = "B";
		//controller.iddupchk(id);
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		String url = "/iddupchk?id="+id;
		MockHttpServletRequestBuilder msrb;
		msrb = MockMvcRequestBuilders.get(url); //요청
		ResultActions actions = mockMvc.perform(msrb); //응답
		actions.andExpect(MockMvcResultMatchers.status().isOk());//응답상태코드가 200으로 예상
	}

}
