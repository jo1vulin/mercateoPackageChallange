package com.mercateo.packager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class PackagerApplicationTests {

	@MockBean
	private CommandLineRunner commandLineRunner;

	@Test
	void verifyContextLoads() {
	}

}
