/**
 * 
 */
package maicon.excel.persistencia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import maicon.concursos.ferramentas.dao.DAOException;
import maicon.concursos.ferramentas.dao.GenericDAO;
import maicon.excel.persistencia.vo.ExCandidato2008;

/**
 * @author Maicon
 *
 */
public class ExCandidato2008DAO extends GenericDAO<ExCandidato2008> {

	private static Logger logger = Logger.getLogger(ExCandidato2008DAO.class);
	
	@SuppressWarnings("unchecked")
	public ExCandidato2008 buscarCandidato2008PorCpf(String cpf, EntityManager em) throws DAOException{
		Query query = null;
		ExCandidato2008 candidato = null;
		
		try{
			query = em.createQuery("from ExCandidato2008 as c where c.cpf = :cpf");
			query.setParameter("cpf", cpf);
			query.setMaxResults(1);
			List<ExCandidato2008> lista = query.getResultList();
			
			if (lista != null && lista.size() > 0)
				candidato = lista.get(0);
			
		} catch(Exception e){
			throw new DAOException("Erro ao buscar ExCandidato por cpf ", e, logger);
		}
			
		return candidato;
	}
	
	@SuppressWarnings("unchecked")
	public ExCandidato2008 buscarCandidato2008PorCpfENovaInscricao(String cpf, EntityManager em) throws DAOException{
		Query query = null;
		ExCandidato2008 candidato = null;
		
		try{
			query = em.createQuery("from ExCandidato2008 as c where c.cpf = :cpf and c.novaInscricao = 'N'");
			query.setParameter("cpf", cpf);
			query.setMaxResults(1);
			List<ExCandidato2008> lista = query.getResultList();
			
			if (lista != null && lista.size() > 0)
				candidato = lista.get(0);
			
		} catch(Exception e){
			throw new DAOException("Erro ao buscar ExCandidato por cpf ", e, logger);
		}
			
		return candidato;
	}
}
