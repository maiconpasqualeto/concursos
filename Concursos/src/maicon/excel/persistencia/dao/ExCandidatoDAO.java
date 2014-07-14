/**
 * 
 */
package maicon.excel.persistencia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import maicon.concursos.ferramentas.dao.DAOException;
import maicon.concursos.ferramentas.dao.GenericDAO;
import maicon.excel.persistencia.vo.ExCandidato;

import org.apache.log4j.Logger;

/**
 * @author Maicon
 *
 */
public class ExCandidatoDAO extends GenericDAO<ExCandidato> {

	private static Logger logger = Logger.getLogger(ExCandidatoDAO.class);
	
	@SuppressWarnings("unchecked")
	public ExCandidato buscarCandidatoPorCpf(String cpf, EntityManager em) throws DAOException{
		Query query = null;
		ExCandidato candidato = null;
		
		try{
			query = em.createQuery("from ExCandidato as c where c.cpf = :cpf");
			query.setParameter("cpf", cpf);
			query.setMaxResults(1);
			List<ExCandidato> lista = query.getResultList();
			
			if (lista != null && lista.size() > 0)
				candidato = lista.get(0);
			
		} catch(Exception e){
			throw new DAOException("Erro ao buscar ExCandidato por cpf ", e, logger);
		}
			
		return candidato;
	}
	
	@SuppressWarnings("unchecked")
	public ExCandidato buscarCandidatoPorInscricao(String numeroInscricao, EntityManager em) throws DAOException{
		Query query = null;
		ExCandidato candidato = null;
		
		try{
			query = em.createQuery("from ExCandidato as c where c.numeroInscricao = :numeroInscricao");
			query.setParameter("numeroInscricao", numeroInscricao);
			query.setMaxResults(1);
			List<ExCandidato> lista = query.getResultList();
			
			if (lista != null && lista.size() > 0)
				candidato = lista.get(0);
			
		} catch(Exception e){
			throw new DAOException("Erro ao buscar objeto por inscricao ", e, logger);
		}
			
		return candidato;
	}
}
