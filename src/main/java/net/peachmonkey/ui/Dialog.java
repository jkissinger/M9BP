package net.peachmonkey.ui;

import javax.swing.JOptionPane;

public class Dialog {

	public static void displayEggs() {
		JOptionPane.showMessageDialog(null, "Eggs are not supposed to be green.", "Yo Dawg Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void displayAbout() {
		JOptionPane.showMessageDialog(null, "This isn't really about anything at all...");
	}

	public static boolean displayConfirm(String text) {
		return JOptionPane.showConfirmDialog(null, text, "Are you sure?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	}

	public static String getOptions(String text, String... choices) {
		return (String) JOptionPane.showInputDialog(null, text, "Make a Choice", JOptionPane.PLAIN_MESSAGE, null, choices, null);
	}
}