package org.jewel;

/**
 * Root exception of Jewel framework.
 * 
 * @author slhynju
 */
public class JewelException extends RuntimeException {

	private static final long serialVersionUID = -8737890225967405787L;

	public JewelException(String message) {
		super(message);
	}

	public JewelException(CharSequence message) {
		super(message.toString());
	}

	public JewelException(String message, Throwable cause) {
		super(message, cause);
	}

	public JewelException(CharSequence message, Throwable cause) {
		super(message.toString(), cause);
	}

}
