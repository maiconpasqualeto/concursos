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
			
			Calendar dataVencimento = new GregorianCalendar(2014, Calendar.AUGUST, 15); 
			Calendar dataBaseFebraban = new GregorianCalendar(1997, Calendar.OCTOBER, 07);
			
			// fator vencimento é diferença em dias entre a data base da febraban até o dia de vencimento do boleto.
			Long fatorVendimento = (dataVencimento.getTimeInMillis() - dataBaseFebraban.getTimeInMillis()) / 1000 / 3600 / 24;
			
			ExCandidatoDAO candidatoDAO = new ExCandidatoDAO();
			ExCandidato candidato = candidatoDAO.buscarCandidatoPorCpf(cpf, em);
			BoletoBancario boleto = new BoletoBancario();
			boleto.setCargo(candidato.getCargo().getDescricao());
			
			String numeroConvenio = "2655932"; 
			
			String nossoNumero = 
					numeroConvenio + Utilitarios.completaComZeros(candidato.getNumeroInscricao(), 10);
			
			String codigoBarras = 
					montaCodigoBarrasBancoBrasil(
							candidato.getCargo().getValor(), nossoNumero, fatorVendimento.toString());
			
			boleto.setCodigoDeBarra(codigoBarras);
			boleto.setCpfSacado(candidato.getCpf());
			boleto.setDataEmissao(new Date());
			boleto.setDataVencimento(dataVencimento.getTime());
			boleto.setLinhaDigitavel(montaLinhaDigitavelBancoBrasil(codigoBarras));
			boleto.setNossoNumero(nossoNumero);			
			boleto.setNumeroDocumento(Utilitarios.completaComZeros(candidato.getNumeroInscricao(), 6));
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
	
	/**
	 * Monta c�digo de barras
	 * 
	 * @param valor
	 * @param numeroInscricao
	 * @param cpf
	 * @return
	 */
	public static String montaCodigoBarrasBancoBrasil(
			Float valor, String nossoNumero, String fatorVencimento){
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		
		StringBuilder codigoBarra = new StringBuilder();		
		codigoBarra.append("001");
		codigoBarra.append("9");
		codigoBarra.append(fatorVencimento);
		String strValor = Utilitarios.getStringDeFloat(valor, 2);
		codigoBarra.append(Utilitarios.completaComZeros(Utilitarios.removeVirgula(strValor), 10));
		codigoBarra.append("000000");
		codigoBarra.append(nossoNumero);
		codigoBarra.append("18");
		
		// calcula e insere o d�gito verificador na posi��o 4
		String digitoVerificador = Utilitarios.calculaDigitoVerificadorModulo11(codigoBarra.toString(), 2, 9, 1);
		if (digitoVerificador.equals("0") ||
				digitoVerificador.equals("10"))
			digitoVerificador = "1";
		codigoBarra.insert(4, digitoVerificador);
		
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
	
	public static String montaLinhaDigitavelBancoBrasil(String codigoDeBarras){
		StringBuilder linhaDigitavel = new StringBuilder();
		
		StringBuilder bloco1 = new StringBuilder();
		bloco1.append(codigoDeBarras.substring(0, 4));
		bloco1.append(codigoDeBarras.substring(19, 24));
			
		StringBuilder bloco2 = new StringBuilder();
		bloco2.append(codigoDeBarras.substring(24, 34));

		StringBuilder bloco3 = new StringBuilder();
		bloco3.append(codigoDeBarras.substring(34, 44));
		
		// Bloco 4 é somente o DV do código de barras
		String bloco4 = codigoDeBarras.substring(4, 5); 
		
		StringBuilder bloco5 = new StringBuilder();
		bloco5.append(codigoDeBarras.substring(5, 9));
		bloco5.append(codigoDeBarras.substring(9, 19));
		
		String digitoBloco1 = Utilitarios.calculaDigitoVerificadorModulo10(bloco1.toString(), 1, 2, 1);
		String digitoBloco2 = Utilitarios.calculaDigitoVerificadorModulo10(bloco2.toString(), 1, 2, 1);
		String digitoBloco3 = Utilitarios.calculaDigitoVerificadorModulo10(bloco3.toString(), 1, 2, 1);
		
		
		linhaDigitavel.append(bloco1);
		linhaDigitavel.append(digitoBloco1);
		linhaDigitavel.append(bloco2);
		linhaDigitavel.append(digitoBloco2);
		linhaDigitavel.append(bloco3);
		linhaDigitavel.append(digitoBloco3);
		linhaDigitavel.append(bloco4);
		linhaDigitavel.append(bloco5);
		
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
