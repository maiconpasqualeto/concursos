/**
 * 
 */
package maicon.concursos.ferramentas.dao;

import maicon.concursos.ferramentas.log.LoggerException;

import org.apache.log4j.Logger;

/**
 * @author maicon
 *
 */
public class DAOException extends LoggerException {

	/**
	 * 
	 */
	public DAOException(Logger logger) {
		super(logger);		
	}

	/**
	 * @param message
	 */
	public DAOException(String message, Logger logger) {
		super(message, logger);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DAOException(String message, Throwable cause, Logger logger) {
		super(message, cause, logger);		
	}

	/**
	 * @param cause
	 */
	public DAOException(Throwable cause, Logger logger) {
		super(cause, logger);		
	}

}
