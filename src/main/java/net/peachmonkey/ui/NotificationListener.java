package net.peachmonkey.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener implements ActionListener {

	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void actionPerformed(ActionEvent e) {
		LOGGER.info("ActionEvent: command={}, source={}, param={}", e.getActionCommand(), e.getSource(), e.paramString());
	}

}