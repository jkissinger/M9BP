package net.peachmonkey.audio;

public class AudioException extends RuntimeException {

	private static final long serialVersionUID = -1222389821692702111L;

	public AudioException() {
		super();
	}

	public AudioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AudioException(String message, Throwable cause) {
		super(message, cause);
	}

	public AudioException(String message) {
		super(message);
	}

	public AudioException(Throwable cause) {
		super(cause);
	}

}
