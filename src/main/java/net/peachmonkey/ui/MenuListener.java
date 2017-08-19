package net.peachmonkey.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.stereotype.Component;

@Component
public class MenuListener implements ActionListener {

	public enum Action {
		EXIT {

			@Override
			public boolean doYourThing() {
				System.exit(0);
				return true;
			}

			@Override
			public String toString() {
				return "Exit";
			}
		},
		ABOUT {

			@Override
			public boolean doYourThing() {
				Dialog.displayAbout();
				return true;
			}

			@Override
			public String toString() {
				return "About";
			}
		},
		YO_DAWG {
			// TODO: Open the web site

			@Override
			public boolean doYourThing() {
				Dialog.displayEggs();
				return true;
			}

			@Override
			public String toString() {
				return "Yo Dawg!";
			}
		};

		public abstract boolean doYourThing();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Action action : Action.values()) {
			if (action.name().equals(e.getActionCommand())) {
				action.doYourThing();
				return;
			}
		}
	}
}