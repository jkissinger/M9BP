package net.peachmonkey.ui;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import javax.annotation.PostConstruct;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.peachmonkey.properties.ApplicationProperties;
import net.peachmonkey.ui.MenuListener.Action;

@Component
public class UiTrayIcon {

	private static final Logger LOGGER = LogManager.getLogger();
	@Autowired
	private ApplicationProperties props;
	@Autowired
	private ImageIcon imageIcon;
	@Autowired
	private MenuListener menuListener;
	@Autowired
	private NotificationListener notificationListener;

	private PopupMenu popup;
	private SystemTray tray = SystemTray.getSystemTray();
	private java.awt.TrayIcon trayIcon;

	@PostConstruct
	public void init() throws AWTException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		LOGGER.info("Initializing UiTrayIcon");
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		trayIcon = new java.awt.TrayIcon(imageIcon.getImage(), props.getApplicationName());
		createPopupMenu();
		createMenuItems();
		trayIcon.addActionListener(notificationListener);
		tray.add(trayIcon);
		LOGGER.info("Initialized UiTrayIcon");
	}

	private void createPopupMenu() {
		popup = new PopupMenu();
		popup.addActionListener(menuListener);
		trayIcon.setPopupMenu(popup);
	}

	private void createMenuItems() {
		for (Action action : Action.values()) {
			addMenuItem(action.toString(), action.name());
		}
	}

	public void showMessage(String caption, String text, MessageType messageType) {
		if (trayIcon == null) {
			LOGGER.trace("UiTrayIcon not instantiated");
		} else {
			trayIcon.displayMessage(caption, text, messageType);
		}
	}

	public boolean hasMenuItem(String label) {
		return getMenuItemIndex(label) >= 0;
	}

	public int getMenuItemIndex(String label) {
		for (int i = 0; i < popup.getItemCount(); i++) {
			MenuItem item = popup.getItem(i);
			if (item.getLabel().equals(label)) {
				return i;
			}
		}
		return -1;
	}

	public void addMenuItem(String label, String action) {
		MenuItem item = new MenuItem();
		item.setLabel(label);
		item.setActionCommand(action);
		popup.add(item);
	}

	public void removeMenuItem(String label) {
		int index = getMenuItemIndex(label);
		if (index >= 0) {
			popup.remove(index);
		}
	}
}