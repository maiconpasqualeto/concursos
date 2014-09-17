/**
 * 
 */
package maicon.concursos.ferramentas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import maicon.concursos.persistencia.vo.Candidato;
import maicon.concursos.persistencia.vo.Concurso;
import maicon.concursos.persistencia.vo.Lotacao;

import org.apache.log4j.Logger;

/**
 * @author maicon
 *
 */
public class ConcursoDAO extends GenericDAO<Concurso> {
	
	private static Logger logger = Logger.getLogger(ConcursoDAO.class);

	@SuppressWarnings("unchecked")
	public List<Concurso> buscarConcursosAtivos(EntityManager em) throws DAOException {
		Query query = null;
		List<Concurso> concursos = new ArrayList<Concurso>();
		
		try{
			query = em.createQuery("from Concurso as c where c.situacao != 'CANCELADO'");
			concursos = query.getResultList();
			
		} catch(Exception e){
			throw new DAOException("Erro ao buscar lista de concursos ", e, logger);
		}
		
		return concursos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Lotacao> buscarLotacaoPorCargo(EntityManager em, Integer codCargo) throws DAOException {
		Query query = null;
		List<Lotacao> lotacoes = new ArrayList<Lotacao>();
		
		try{
			query = em.createQuery("select distinct l from Lotacao l inner join l.cargos c where c.id = :id");
			query.setParameter("id", codCargo);
			lotacoes = query.getResultList();
			
		} catch(Exception e){
			throw new DAOException("Erro ao buscar lista de lotacoes por concurso ", e, logger);
		}
		
		return lotacoes;
	}
	
	
}
