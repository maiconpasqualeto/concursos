/**
 * 
 */
package maicon.ferramentas.facade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import maicon.concursos.ferramentas.dao.CandidatoDAO;
import maicon.concursos.ferramentas.dao.ConcursoDAO;
import maicon.concursos.ferramentas.dao.DAOException;
import maicon.concursos.persistencia.vo.Candidato;
import maicon.concursos.persistencia.vo.Cargo;
import maicon.concursos.persistencia.vo.Concurso;

import org.apache.log4j.Logger;

/**
 * @author maicon.pasqualeto
 *
 */
public class AppFacade extends FacadeBean {
	
	private static Logger logger = Logger.getLogger(AppFacade.class); 
	
	public static Candidato buscaCandidatoPorCpf(String cpf) throws FacadeException{
		EntityManager em = BasicService.getEntityManager();
		CandidatoDAO candidatoDAO = new CandidatoDAO();
		try {
			return candidatoDAO.buscarCandidatoPorCpf(cpf, em);
		} catch (DAOException e) {
			throw new FacadeException(e.getCause(), logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
	}
	
	public static Candidato buscarCandidatoPorInscricao(String numeroInscricao, Integer idConcurso) throws FacadeException{
		EntityManager em = BasicService.getEntityManager();
		CandidatoDAO candidatoDAO = new CandidatoDAO();
		try {
			return candidatoDAO.buscarCandidatoPorInscricao(numeroInscricao, idConcurso, em);
		} catch (DAOException e) {
			throw new FacadeException(e.getCause(), logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
	}
	
	public static List<Concurso> buscarConcursosAtivos() throws FacadeException{
		List<Concurso> concursos = null;
		
		EntityManager em = BasicService.getEntityManager();
		ConcursoDAO dao = new ConcursoDAO();
		try {
			concursos = dao.buscarConcursosAtivos(em);
		} catch (DAOException e) {
			throw new FacadeException(e.getCause(), logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		
		return concursos;
	}
	
	public static List<Cargo> buscarCargosPorConcurso(Integer codConcurso) throws FacadeException {
		List<Cargo> cargos = null;
		
		EntityManager em = BasicService.getEntityManager();
		CargoDAO dao = new CargoDAO();
		try {
			cargos = dao.buscarCargosPorConcurso(em, codConcurso);
		} catch (DAOException e) {
			throw new FacadeException(e.getCause(), logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		
		return cargos;
	}
	
}
