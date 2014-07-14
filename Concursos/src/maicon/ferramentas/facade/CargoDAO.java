/**
 * 
 */
package maicon.ferramentas.facade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import maicon.concursos.ferramentas.dao.DAOException;
import maicon.concursos.ferramentas.dao.GenericDAO;
import maicon.concursos.persistencia.vo.Cargo;

/**
 * @author maicon
 *
 */
public class CargoDAO extends GenericDAO<Cargo> {
	
	private static Logger logger = Logger.getLogger(CargoDAO.class);

	@SuppressWarnings("unchecked")
	public List<Cargo> buscarCargosPorConcurso(EntityManager em, Integer codConcurso) throws DAOException {
		Query query = null;
		List<Cargo> cargos = new ArrayList<Cargo>();
		
		try{
			query = em.createQuery("from Cargo as c where c.concurso.id = :id ");
			query.setParameter("id", codConcurso);
			cargos = query.getResultList();
			
		} catch(Exception e){
			throw new DAOException("Erro ao buscar lista de cargos por concurso ", e, logger);
		}
		
		return cargos;
	}

}
