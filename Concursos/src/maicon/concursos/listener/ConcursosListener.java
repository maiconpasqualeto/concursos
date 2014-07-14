/**
 * 
 */
package maicon.concursos.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import maicon.ferramentas.facade.BasicService;

/**
 * @author Maicon
 *
 */
public class ConcursosListener implements ServletContextListener {
	
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent evt) {
		BasicService.close();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent evt) {
		BasicService.getEntityManager();
		
	}

}
