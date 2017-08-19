package net.peachmonkey.exception;

public class RequiredSoundMissingException extends RuntimeException {

	private static final long serialVersionUID = 5720719701200941046L;

	public RequiredSoundMissingException() {
		super();
	}

	public RequiredSoundMissingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RequiredSoundMissingException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequiredSoundMissingException(String message) {
		super(message);
	}

	public RequiredSoundMissingException(Throwable cause) {
		super(cause);
	}

}
