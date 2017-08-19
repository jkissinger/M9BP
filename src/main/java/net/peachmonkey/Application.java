package net.peachmonkey;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Application {

	public static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = new SpringApplicationBuilder(Application.class).headless(false).run(args);
	}
}