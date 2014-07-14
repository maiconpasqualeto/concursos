/**
 * 
 */
package maicon.concursos.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maicon.concursos.persistencia.vo.GrupoCargo;
import maicon.ferramentas.facade.BasicService;

/**
 * @author Maicon
 *
 */
public class TesteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("ConcursosUnit");
		
		EntityManager em = emf.createEntityManager();
		
		em.find(GrupoCargo.class, 1);
		em.close();
		
		super.doGet(req, resp);
	}
	
		
}
