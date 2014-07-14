package maicon.ferramentas.facade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BasicService {

	protected static EntityManagerFactory emf;
	//protected static EntityManager em;
	protected static EntityTransaction emt;

	static {
		emf = Persistence.createEntityManagerFactory("ConcursosUnit");
		//em = emf.createEntityManager();
		//emt = em.getTransaction();
	}
	
	public static EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
	
	/*public static EntityTransaction getEntityTransaction(){
		if (emt == null)
			emt = em.getTransaction();
		return emt;
	}
	*/
	
	public static void close(){
		if (emf != null && emf.isOpen())
			emf.close();
	}
	
	
	public EntityManager getEM(){
		return emf.createEntityManager();
	}
}
