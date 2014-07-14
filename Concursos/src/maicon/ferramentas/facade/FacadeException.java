/**
 * 
 */
package maicon.ferramentas.facade;



import maicon.concursos.ferramentas.log.LoggerException;

import org.apache.log4j.Logger;

/**
 * @author maicon
 *
 */
@SuppressWarnings("serial")
public class FacadeException extends LoggerException {

	/**
	 * 
	 */
	public FacadeException(Logger logger) {
		super(logger);		
	}

	/**
	 * @param message
	 */
	public FacadeException(String message, Logger logger) {
		super(message, logger);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FacadeException(String message, Throwable cause, Logger logger) {
		super(message, cause, logger);
	}

	/**
	 * @param cause
	 */
	public FacadeException(Throwable cause, Logger logger) {
		super(cause, logger);
	}

}
