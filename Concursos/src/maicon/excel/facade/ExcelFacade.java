package maicon.excel.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import maicon.concursos.ferramentas.Utilitarios;
import maicon.concursos.ferramentas.dao.DAOException;
import maicon.concursos.ferramentas.dao.GenericDAO;
import maicon.excel.persistencia.dao.ExCandidato2008DAO;
import maicon.excel.persistencia.dao.ExCandidatoDAO;
import maicon.excel.persistencia.vo.BoletoBancario;
import maicon.excel.persistencia.vo.ExCandidato;
import maicon.excel.persistencia.vo.ExCandidato2008;
import maicon.excel.persistencia.vo.ExTabInscricao;
import maicon.ferramentas.facade.BasicService;
import maicon.ferramentas.facade.FacadeBean;
import maicon.ferramentas.facade.FacadeException;

import org.apache.log4j.Logger;

public class ExcelFacade extends FacadeBean {
	
	private static Logger logger = Logger.getLogger(ExcelFacade.class); 

	public static ExCandidato buscaCandidatoPorCpf(String cpf) throws FacadeException{
		EntityManager em = BasicService.getEntityManager();
		ExCandidatoDAO candidatoDAO = new ExCandidatoDAO();
		try {
			return candidatoDAO.buscarCandidatoPorCpf(cpf, em);
		} catch (DAOException e) {
			throw new FacadeException(e.getCause(), logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
	}
	
	public static ExCandidato2008 buscaCandidato2008PorCpf(String cpf) throws FacadeException{
		EntityManager em = BasicService.getEntityManager();
		ExCandidato2008DAO dao = new ExCandidato2008DAO();
		try {
			return dao.buscarCandidato2008PorCpf(cpf, em);
		} catch (DAOException e) {
			throw new FacadeException(e.getCause(), logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
	}
	
	public static Boolean verificaSeCandidatoJaInscrito2008(String cpf) throws FacadeException {
		EntityManager em = BasicService.getEntityManager();
		ExCandidato2008DAO dao2008 = new ExCandidato2008DAO();		
		Boolean retorno = Boolean.FALSE;
		try {
			ExCandidato2008 candidato2008 = dao2008.buscarCandidato2008PorCpfENovaInscricao(cpf, em);
			if (candidato2008 != null)
				retorno = Boolean.TRUE;
		} catch (DAOException e) {
			throw new FacadeException(e.getCause(), logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		return retorno;
	}
	
	public static Integer salvarInscricao(ExCandidato candidato, ExCandidato2008 candidato2008) throws FacadeException{
		ExTabInscricao inscricao = new ExTabInscricao();
		inscricao.setDataIncricao(new Date());
		
		EntityManager em = BasicService.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		
		try {
			tr.begin();			
			
			new GenericDAO<ExTabInscricao>().salvar(inscricao, em);
			
			candidato.setNumeroInscricao(inscricao.getId().toString());
			
			if (candidato2008 != null)
				new GenericDAO<ExCandidato2008>().merge(candidato2008, em);
			
			new GenericDAO<ExCandidato>().salvar(candidato, em);
			
			tr.commit();
		} catch (DAOException e) {
			tr.rollback();
			throw new FacadeException("Erro", e, logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		
		return inscricao.getId();
	}
	
	public static Integer geraBoleto(String cpf) throws FacadeException{
		EntityManager em = BasicService.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		
		Integer idBoleto = null;
		try {
			tr.begin();
			
			//Calendar dataVencimento = new GregorianCalendar(2009, Calendar.JUNE, 10); 
			Calendar dataVencimento = GregorianCalendar.getInstance();
			
			ExCandidatoDAO candidatoDAO = new ExCandidatoDAO();
			ExCandidato candidato = candidatoDAO.buscarCandidatoPorCpf(cpf, em);
			BoletoBancario boleto = new BoletoBancario();
			boleto.setCargo(candidato.getCargo().getDescricao());
			String codigoBarras = 
				montaCodigoBarras(
						candidato.getCargo().getValor(), 
						Utilitarios.completaComZeros(candidato.getNumeroInscricao(), 6), "13102873");
						//Utilitarios.completaComZeros(candidato.getNumeroInscricao(), 6), "03434792");
						
			boleto.setCodigoDeBarra(codigoBarras);
			boleto.setCpfSacado(candidato.getCpf());
			boleto.setDataEmissao(new Date());
			boleto.setDataVencimento(dataVencimento.getTime());
			boleto.setLinhaDigitavel(montaLinhaDigitavel(codigoBarras));
			boleto.setNossoNumero("900000000001646031");
			boleto.setNumeroDocumento("164602");
			boleto.setNumeroInscricao(Utilitarios.completaComZeros(candidato.getNumeroInscricao(), 6));
			boleto.setSacado(candidato.getNome());
			boleto.setValor(candidato.getCargo().getValor());
			
			new GenericDAO<BoletoBancario>().salvar(boleto, em);
			
			tr.commit();
			
			idBoleto = boleto.getId();
			
		} catch (DAOException e) {
			tr.rollback();
			throw new FacadeException(e.getCause(), logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		
		return idBoleto;
	}
	
	public static ExCandidato buscarExCandidatoPorInscricao(String numeroInscricao) throws FacadeException{
		EntityManager em = BasicService.getEntityManager();
		ExCandidatoDAO candidatoDAO = new ExCandidatoDAO();
		try {
			return candidatoDAO.buscarCandidatoPorInscricao(numeroInscricao, em);
		} catch (DAOException e) {
			throw new FacadeException(e.getCause(), logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
	}
	
	/**
	 * Monta c�digo de barras
	 * 
	 * @param valor
	 * @param numeroInscricao
	 * @param cpf
	 * @return
	 */
	public static String montaCodigoBarras(
			Float valor, String numeroInscricao, String cpf){
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		
		StringBuilder codigoBarra = new StringBuilder();		
		codigoBarra.append("8");
		codigoBarra.append("6");
		codigoBarra.append("6");
		String strValor = Utilitarios.getStringDeFloat(valor, 2);
		codigoBarra.append(Utilitarios.completaComZeros(Utilitarios.removeVirgula(strValor), 11));
		codigoBarra.append(cpf);
		codigoBarra.append(Utilitarios.completaComZeros(numeroInscricao, 21));
		
		// calcula e insere o d�gito verificador na posi��o 4
		String digitoVerificador = Utilitarios.calculaDigitoVerificadorModulo10(codigoBarra.toString(), 1, 2, 1);
		codigoBarra.insert(3, digitoVerificador);
		
		return codigoBarra.toString();
	}
		
	public static String montaLinhaDigitavel(String codigoDeBarras){
		StringBuilder linhaDigitavel = new StringBuilder();
		
		String bloco1 = codigoDeBarras.substring(0, 11);
		String bloco2 = codigoDeBarras.substring(11, 22);
		String bloco3 = codigoDeBarras.substring(22, 33);
		String bloco4 = codigoDeBarras.substring(33, 44);
		
		String digitoBloco1 = Utilitarios.calculaDigitoVerificadorModulo10(bloco1, 1, 2, 1);
		String digitoBloco2 = Utilitarios.calculaDigitoVerificadorModulo10(bloco2, 1, 2, 1);
		String digitoBloco3 = Utilitarios.calculaDigitoVerificadorModulo10(bloco3, 1, 2, 1);
		String digitoBloco4 = Utilitarios.calculaDigitoVerificadorModulo10(bloco4, 1, 2, 1);
		
		linhaDigitavel.append(bloco1);
		linhaDigitavel.append(digitoBloco1);
		linhaDigitavel.append(bloco2);
		linhaDigitavel.append(digitoBloco2);
		linhaDigitavel.append(bloco3);
		linhaDigitavel.append(digitoBloco3);
		linhaDigitavel.append(bloco4);
		linhaDigitavel.append(digitoBloco4);
		
		return linhaDigitavel.toString();
	}
	
	public static Boolean verificaCpfJaInscrito(String cpf) throws FacadeException{
		EntityManager em = BasicService.getEntityManager();
		ExCandidatoDAO dao = new ExCandidatoDAO();
		Boolean retorno = Boolean.FALSE;
		try {
			ExCandidato candidato = dao.buscarCandidatoPorCpf(cpf, em);
			if (candidato != null)				
				retorno = Boolean.TRUE;
		} catch (DAOException e) {
			throw new FacadeException(e.getCause(), logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		return retorno;		
	}
	
	public static Boolean verificaSeCandidato2008JaTemNovaInscricao(String cpf) throws FacadeException {
		EntityManager em = BasicService.getEntityManager();
		ExCandidato2008DAO dao2008 = new ExCandidato2008DAO();		
		Boolean retorno = Boolean.FALSE;
		try {
			ExCandidato2008 candidato2008 = dao2008.buscarCandidato2008PorCpf(cpf, em);
			if (candidato2008 != null){
				if ("S".equals(candidato2008.getNovaInscricao()))
					retorno = Boolean.TRUE;
			}
		} catch (DAOException e) {
			throw new FacadeException(e.getCause(), logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		return retorno;
	}
}
