package com.estore.api.estoreapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:application-context.xml"})
class EstoreApiApplicationTests {
	@Test
	void contextLoads() {
	}
}
