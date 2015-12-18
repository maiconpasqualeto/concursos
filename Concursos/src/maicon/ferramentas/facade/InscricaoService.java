/**
 * 
 */
package maicon.ferramentas.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import maicon.concursos.ferramentas.dao.ConcursoDAO;
import maicon.concursos.ferramentas.dao.DAOException;
import maicon.concursos.persistencia.vo.Candidato;
import maicon.concursos.persistencia.vo.Concurso;
import maicon.concursos.persistencia.vo.Funcao;
import maicon.concursos.persistencia.vo.Lotacao;
import maicon.concursos.persistencia.vo.TabIncricao;

import org.apache.log4j.Logger;

/**
 * @author Maicon
 *
 */
public class InscricaoService  {
	
	private static Logger logger = Logger.getLogger(InscricaoService.class);
	
	public static synchronized Integer salvarInscricao(Candidato candidato, Concurso concurso) throws FacadeException{
		EntityManager em = BasicService.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		
		Integer numeroInscricao = null;
		
		try {			
			tr.begin();
			
			numeroInscricao = 
					(Integer) em.createQuery(
							"select ultimoNumeroInscricao from Concurso c where c.id = :codConcurso")
					.setParameter("codConcurso", concurso.getId())
					.getSingleResult();
			
			if (numeroInscricao == null)
				numeroInscricao = 1;
			else 
				numeroInscricao++;
			
			concurso.setUltimoNumeroInscricao(numeroInscricao);
						
			TabIncricao inscricao = new TabIncricao();
			inscricao.setDataIncricao(new Date());
			inscricao.setConcurso(concurso);						
			inscricao.setNumeroInscricao(numeroInscricao);
			
			em.persist(inscricao);
			
			em.merge(concurso);
			
			candidato.setNumeroInscricao(numeroInscricao);
			
			em.persist(candidato);
			
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw new FacadeException("Erro", e, logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		
		return numeroInscricao;
	}
	
	
	public static List<Lotacao> recuperaLotacoesPorCargo(Integer idCargo) throws FacadeException{
		EntityManager em = BasicService.getEntityManager();
		List<Lotacao> lotacoes = null;
		try {
			
			lotacoes = new ConcursoDAO().buscarLotacaoPorCargo(em, idCargo);
			
		} catch (DAOException e) {			
			throw new FacadeException("Erro", e, logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		
		return lotacoes;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Funcao> recuperaFuncoesPorCargoELotacao(Integer idCargo, Integer idLotacao) throws FacadeException{
		EntityManager em = BasicService.getEntityManager();
		List<Funcao> funcoes = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select cod_funcao, funcao from concursos.funcao ");
			sql.append("where cod_funcao in ");
			sql.append("( select cod_funcao from concursos.cargo_lotacao ");
			sql.append("where cod_cargo = :cod_cargo and cod_lotacao = :cod_lotacao ) ");
			
			Query q = em.createNativeQuery(sql.toString());
			q.setParameter("cod_cargo", idCargo);
			q.setParameter("cod_lotacao", idLotacao);
						
			List<Object[]> retorno = q.getResultList();
			if (retorno.size() > 0)
				funcoes = new ArrayList<Funcao>();
			
			for (Object[] o : retorno) {
				Funcao f = new Funcao();
				f.setId(Integer.valueOf(o[0].toString()));
				f.setDescricao(o[1].toString());
				funcoes.add(f);
			}
			
		} catch (Exception e) {			
			throw new FacadeException("Erro", e, logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		
		return funcoes;
	}

}
