/**
 * 
 */
package maicon.ferramentas.facade;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import maicon.concursos.ferramentas.dao.DAOException;
import maicon.concursos.ferramentas.dao.GenericDAO;

import org.apache.log4j.Logger;


/**
 * @author maicon
 *
 */
public class FacadeBean {
	
	static Logger logger = Logger.getLogger(FacadeBean.class);
	
	//GenericDAO<T> dao = new GenericDAO<T>();
	
	public FacadeBean() {		
	}

	/* (non-Javadoc)
	 * @see br.com.byter.utilitarios.persistence.FacadeEjbInterface#alterar(java.lang.Object)
	 */
	public static <T> void alterar(T objeto) throws FacadeException {
		EntityManager em = BasicService.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			new GenericDAO<T>().salvar(objeto, em);
			tr.commit();
		} catch (DAOException e) {
			tr.rollback();
			throw new FacadeException("Erro ao alterar objeto", e, logger); 
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
	}

	/* (non-Javadoc)
	 * @see br.com.byter.utilitarios.persistence.FacadeEjbInterface#buscaTotalRegistros()
	 */
	public int buscaTotalRegistros() throws FacadeException {
		// TODO Buscar total de registros
		return 0;
	}

	/* (non-Javadoc)
	 * @see br.com.byter.utilitarios.persistence.FacadeEjbInterface#buscar(java.io.Serializable)
	 */
	public static <T> T buscar(Serializable pk, Class<T> classe) throws FacadeException {
		EntityManager em = BasicService.getEntityManager();
		T o;
		try {
			o = new GenericDAO<T>().buscar(pk, classe, em);
		} catch (DAOException e) {
			throw new FacadeException("Erro ao buscar objeto", e, logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		return o;
	}

	/* (non-Javadoc)
	 * @see br.com.byter.utilitarios.persistence.FacadeEjbInterface#buscarPorPosicao(java.lang.Integer)
	 */
	public static <T> T buscarPorPosicao(Integer posicao) throws FacadeException {
		// TODO buscar registros por posicao
		return null;
	}

	/* (non-Javadoc)
	 * @see br.com.byter.utilitarios.persistence.FacadeEjbInterface#buscarTodos()
	 */
	public static <T> Collection<T> buscarTodos(Class<T> classe) throws FacadeException {
		EntityManager em = BasicService.getEntityManager();
		Collection<T> objetos = null;
		try {
			objetos = new GenericDAO<T>().buscarTodos(classe, em);			
		} catch (DAOException e) {
			throw new FacadeException("Erro ao buscar objeto", e, logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		return objetos;
	}

	/* (non-Javadoc)
	 * @see br.com.byter.utilitarios.persistence.FacadeEjbInterface#buscarTodosOrdenados(java.lang.String)
	 */
	public static <T> Collection<T> buscarTodosOrdenados(Class<T> classe, String propriedade)
			throws FacadeException {
		EntityManager em = BasicService.getEntityManager();
		Collection<T> objetos = null;
		try {
			objetos = new GenericDAO<T>().buscarTodosOrdenados(classe, propriedade, em);			
		} catch (DAOException e) {
			throw new FacadeException("Erro ao buscar objeto", e, logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		return objetos;
	}

	/* (non-Javadoc)
	 * @see br.com.byter.utilitarios.persistence.FacadeEjbInterface#buscarTodosPaginado(int, int, java.lang.String, java.lang.Object)
	 */
	public static <T> Collection<T> buscarTodosPaginado(int inicio, int quantidade,
			String propriedade, Object expressao) throws FacadeException {
		// TODO buscar todos paginado
		return null;
	}

	/* (non-Javadoc)
	 * @see br.com.byter.utilitarios.persistence.FacadeEjbInterface#excluir(java.lang.Object)
	 */	
	public static <T> void excluir(Serializable id, Class<T> classe) throws FacadeException {
		EntityManager em = BasicService.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			T o = new GenericDAO<T>().buscar(id, classe, em);
			if (o == null)
				throw new FacadeException("Objeto não econtrado - id: " + id, logger);
			new GenericDAO<T>().excluir(o, em);
			tr.commit();
		} catch (DAOException e) {
			BasicService.getEntityManager().getTransaction().rollback();
			throw new FacadeException("Erro ao excluir objeto", e, logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
	}

	/* (non-Javadoc)
	 * @see br.com.byter.utilitarios.persistence.FacadeEjbInterface#incluir(java.lang.Object)
	 */
	public static <T> T incluir(T objeto) throws FacadeException {
		EntityManager em = BasicService.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			new GenericDAO<T>().salvar(objeto, em);			
			tr.commit();
		} catch (DAOException e) {
			tr.rollback();
			throw new FacadeException("Erro ao incluir objeto", e, logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		return objeto;
	}

}
