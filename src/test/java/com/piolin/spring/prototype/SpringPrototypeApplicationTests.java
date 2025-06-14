package com.piolin.spring.prototype;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SpringPrototypeApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TestRestTemplate template;

	@Test
	void getHello() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("SpingBoot Application is Up & Running ... Version: 1.0.0-RELEASE")));
	}

	@Test
	void getHello2() throws Exception{
		ResponseEntity<String> response = template.getForEntity("/", String.class);
		assertThat(response.getBody()).isEqualTo("SpingBoot Application is Up & Running ... Version: 1.0.0-RELEASE");
	}

}
