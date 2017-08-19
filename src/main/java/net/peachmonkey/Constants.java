package net.peachmonkey;

public class Constants {

	public enum TaskStatus {
		INVALID, PENDING, DUE, OVERDUE, COMPLETED;
	}

	public static final String FILENAME_SEPARATOR = "-";
	public static final String AUDIO_FILE_EXTENSION = ".mp3";

	public static final class Sounds {

		public static final String TASKS_DIR = "tasks";
		public static final String GAME_DIR = "game";
		public static final String SYSTEM_DIR = "system";
		public static final String COMPLETION = "completed";
		public static final String ONLINE = "online";
		public static final String MISSING = "missing";
		public static final String ERROR = "error";

	}
}
