package ${package};

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringApplication {

	public static void main(String[] args) {
		//noinspection resource
		new SpringApplicationBuilder()
			.sources(SpringApplication.class)
			.run(args);
	}
}
