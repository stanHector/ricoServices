package service.ricotunes.giftcards.exception;

import org.springframework.http.HttpStatus;

public class GiftException extends RuntimeException {

	private static final long serialVersionUID = -6593330219878485669L;

	private final HttpStatus status;
	private final String message;

	public GiftException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public GiftException(HttpStatus status, String message, Throwable exception) {
		super(exception);
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
