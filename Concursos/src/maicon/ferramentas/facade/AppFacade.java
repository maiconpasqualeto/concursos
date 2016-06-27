/**
 * 
 */
package maicon.ferramentas.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import maicon.concursos.ferramentas.Utilitarios;
import maicon.concursos.ferramentas.dao.CandidatoDAO;
import maicon.concursos.ferramentas.dao.ConcursoDAO;
import maicon.concursos.ferramentas.dao.DAOException;
import maicon.concursos.ferramentas.dao.GenericDAO;
import maicon.concursos.persistencia.vo.BoletoConcurso;
import maicon.concursos.persistencia.vo.Candidato;
import maicon.concursos.persistencia.vo.Cargo;
import maicon.concursos.persistencia.vo.Concurso;
import maicon.excel.facade.ExcelFacade;

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
	
	public static Candidato buscarCandidatoPorInscricao(Integer numeroInscricao, Integer idConcurso) throws FacadeException{
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
	
	public static BoletoConcurso geraBoleto(Integer inscricao, Integer codConcurso) throws FacadeException{
		EntityManager em = BasicService.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		
		BoletoConcurso boleto = null;
		try {
			tr.begin();
			
			CandidatoDAO dao = new CandidatoDAO();
			Candidato candidato = dao.buscarCandidatoPorInscricao(inscricao, codConcurso, em);
			Concurso concurso = candidato.getConcurso();
			
			// dados específicos do boleto
			Date dataVencimento = concurso.getDataVencimento();
			String numeroConvenio = concurso.getNumeroConvenio();
			String cnpj = concurso.getCnpj();
			String cedente = concurso.getCedente();
			String agenciaConta = concurso.getPagamentoAgencia() + " / " + concurso.getPagamentoConta();
			
			
			//Sempre fixo
			Calendar dataBaseFebraban = new GregorianCalendar(1997, Calendar.OCTOBER, 07);
			
			// fator vencimento é diferença em dias entre a data base da febraban até o dia de vencimento do boleto.
			Long fatorVendimento = 
					(dataVencimento.getTime() - dataBaseFebraban.getTimeInMillis()) / 1000 / 3600 / 24;
			
			boleto = new BoletoConcurso();
			boleto.setCargo(candidato.getCargo().getDescricao());
			boleto.setNomeConcurso(candidato.getConcurso().getDescricao() + " - " + 
							candidato.getConcurso().getPrefeitura());
			
			String nossoNumero = 
					numeroConvenio + Utilitarios.completaComZeros(candidato.getNumeroInscricao().toString(), 10);
			
			String codigoBarras = 
					ExcelFacade.montaCodigoBarrasBancoBrasil(
							candidato.getFuncao().getValor().floatValue(), 
							nossoNumero, 
							fatorVendimento.toString(),
							concurso.getCarteira());
			
			boleto.setCodigoDeBarra(codigoBarras);
			boleto.setCpfSacado(candidato.getCpf());
			boleto.setDataEmissao(new Date());
			boleto.setDataVencimento(dataVencimento);
			boleto.setLinhaDigitavel(
					ExcelFacade.montaLinhaDigitavelBancoBrasil(codigoBarras));
			boleto.setNossoNumero(nossoNumero);			
			boleto.setNumeroDocumento(Utilitarios.completaComZeros(candidato.getNumeroInscricao().toString(), 6));
			boleto.setNumeroInscricao(Utilitarios.completaComZeros(candidato.getNumeroInscricao().toString(), 6));
			boleto.setSacado(candidato.getNome());
			boleto.setValor(candidato.getFuncao().getValor());
			boleto.setCedente(cedente);
			boleto.setCnpj(cnpj);
			boleto.setAgenciaConta(agenciaConta);
			boleto.setFuncaoCandidato(candidato.getFuncao().getDescricao());
			boleto.setLotacaoCandidato(candidato.getLotacao().getDescricao());
			boleto.setCarteira(concurso.getCarteira());
			
			new GenericDAO<BoletoConcurso>().salvar(boleto, em);
			
			tr.commit();
						
		} catch (DAOException e) {
			tr.rollback();
			throw new FacadeException(e.getCause(), logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		
		return boleto;
	}
	
}
