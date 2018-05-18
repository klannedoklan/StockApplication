package exceptions;

public class StockServiceException extends Exception {

	private static final long serialVersionUID = 878999443323L;

	public StockServiceException() {
	}

	public StockServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StockServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public StockServiceException(String message) {
		super(message);
	}

	public StockServiceException(Throwable cause) {
		super(cause);
	}

}
