/**
 * 
 */
package maicon.concursos.ferramentas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import maicon.concursos.persistencia.vo.Candidato;

/**
 * @author maicon.pasqualeto
 *
 */
public class CandidatoDAO extends GenericDAO<Candidato> {

	private static Logger logger = Logger.getLogger(CandidatoDAO.class);
	
	@SuppressWarnings("unchecked")
	public Candidato buscarCandidatoPorCpf(String cpf, EntityManager em) throws DAOException{
		Query query = null;
		Candidato candidato = null;
		
		try{
			query = em.createQuery("from Candidato as c where c.cpf = :cpf");
			query.setParameter("cpf", cpf);
			query.setMaxResults(1);
			List<Candidato> lista = query.getResultList();
			
			if (lista != null && lista.size() > 0)
				candidato = lista.get(0);
			
		} catch(Exception e){
			throw new DAOException("Erro ao buscar objeto por cpf ", e, logger);
		}
			
		return candidato;
	}
	
	@SuppressWarnings("unchecked")
	public Candidato buscarCandidatoPorInscricao(String numeroInscricao, Integer idConcurso, EntityManager em) throws DAOException{
		Query query = null;
		Candidato candidato = null;
		
		try{
			query = em.createQuery("from Candidato as c where c.numeroInscricao = :numeroInscricao and c.concurso.id = :idConcurso");
			query.setParameter("numeroInscricao", numeroInscricao);
			query.setParameter("idConcurso", idConcurso);
			query.setMaxResults(1);
			List<Candidato> lista = query.getResultList();
			
			if (lista != null && lista.size() > 0)
				candidato = lista.get(0);
			
		} catch(Exception e){
			throw new DAOException("Erro ao buscar objeto por inscricao ", e, logger);
		}
			
		return candidato;
	}
	
}
