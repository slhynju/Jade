package org.jade;

/**
 * Root exception of Jade framework.
 * 
 * @author slhynju
 */
public class JadeException extends RuntimeException {

	private static final long serialVersionUID = -8737890225967405787L;

	public JadeException(String message) {
		super(message);
	}

	public JadeException(CharSequence message) {
		super(message.toString());
	}

	public JadeException(String message, Throwable cause) {
		super(message, cause);
	}

	public JadeException(CharSequence message, Throwable cause) {
		super(message.toString(), cause);
	}

}
