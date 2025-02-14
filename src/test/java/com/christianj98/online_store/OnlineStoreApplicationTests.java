package com.christianj98.online_store;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("integration")
@EnableKafka
@EmbeddedKafka(partitions = 1,
		brokerProperties = {
				"listeners=PLAINTEXT://localhost:9092",
				"port=9092"
		})
class OnlineStoreApplicationTests {

	@Test
	void contextLoads() {
	}

}
