package md.utm.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MongoDbSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication
				.run(MongoDbSecurityDemoApplication.class, args)
				.close();
	}
}
