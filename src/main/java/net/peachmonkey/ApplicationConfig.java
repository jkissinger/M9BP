package net.peachmonkey;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import net.peachmonkey.properties.ApplicationProperties;

@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class ApplicationConfig {

	private static final Logger LOGGER = LogManager.getLogger();
	@Autowired
	private ApplicationContext context;
	@Autowired
	private ApplicationProperties props;

	@Bean
	public ImageIcon imageIcon() throws IOException {
		File file = new File(props.getTrayIconImage());
		URL imageUrl = null;
		if (file.exists()) {
			imageUrl = file.toURI().toURL();
		} else {
			imageUrl = context.getResource(props.getTrayIconImage()).getURL();
		}
		LOGGER.info("Icon URL {}", imageUrl);
		return new ImageIcon(imageUrl, props.getApplicationName());
	}
}