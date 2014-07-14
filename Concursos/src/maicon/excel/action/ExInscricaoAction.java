/**
 * 
 */
package maicon.excel.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maicon.concursos.ferramentas.Utilitarios;
import maicon.excel.facade.ExcelFacade;
import maicon.excel.persistencia.vo.BoletoBancario;
import maicon.excel.persistencia.vo.ExCandidato;
import maicon.excel.persistencia.vo.ExCandidato2008;
import maicon.excel.persistencia.vo.ExCargo;
import maicon.excel.persistencia.vo.ExEstadoCivil;
import maicon.excel.persistencia.vo.ExUf;
import maicon.ferramentas.facade.FacadeBean;
import maicon.ferramentas.facade.FacadeException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 * @author Maicon
 *
 */
public class ExInscricaoAction extends DispatchAction {
	
	@Override
	protected ActionForward dispatchMethod(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, String methodName)
			throws Exception {
		
		String novoNomeMetodo = methodName;
		if (methodName.equals("Nova Inscricao"))
			novoNomeMetodo = "NovaInscricao";
		else 
			if (methodName.equals("Imprimir Boleto"))
				novoNomeMetodo = "ImprimirBoleto";
		
		return super.dispatchMethod(mapping, form, request, response, novoNomeMetodo);
	}
	
	public ActionForward abreConfirmacao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		ExCandidato2008 candidato = ExcelFacade.buscaCandidato2008PorCpf(f.getString("cpf"));		
		
		preencheFormCandidato(f, candidato, null);
		
		return mapping.findForward("confirmacao");
	}
	
	public ActionForward abreNova(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		preencheDadosPagina(request, f);
		
		return mapping.findForward("nova");
	}
	
	public ActionForward Confirma(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		ExCandidato2008 candidato2008 = ExcelFacade.buscaCandidato2008PorCpf(f.getString("cpf"));
		
		if (!validaCandidato2008(request, f)){
			return abreConfirmacao(mapping, form, request, response);
		}
		
		ExCandidato candidatoNovo = new ExCandidato();
		
		BeanUtils.copyProperties(candidatoNovo, candidato2008);		
		candidatoNovo.setId(null);
		candidatoNovo.setNumeroInscricao(null);
		candidatoNovo.setInscricao2008("S");
		
		candidato2008.setNovaInscricao("S");		
		
		try {
			
			ExcelFacade.salvarInscricao(candidatoNovo, candidato2008);		
					
		} catch (FacadeException e){
			retornaErro(request, f, candidatoNovo);
			return abreConfirmacao(mapping, form, request, response);
		}
		
		preencheFormCandidato(f, candidatoNovo);
		
		return mapping.findForward("confirmada");
	}
	
	public ActionForward NovaInscricao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		preencheDadosPagina(request, f);
		
		return mapping.findForward("nova");
	}
	
	public ActionForward Salvar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		Integer idCargo = Integer.valueOf(f.getString("codCargo"));
		ExCargo cargo = FacadeBean.buscar(idCargo, ExCargo.class);
		f.set("descricaoCargo", cargo.getDescricao());
		
		// Valida entrada de dados
		if (!valida(request, f, cargo)) {
			preencheDadosPagina(request, f);
			return mapping.getInputForward();
		}
		
		ExCandidato candidato = new ExCandidato();
		candidato.setNome(f.getString("nome"));
		candidato.setCpf(f.getString("cpf"));
		candidato.setEmail(f.getString("email"));
		candidato.setSenha((String) request.getSession().getAttribute("senha"));
		candidato.setEndereco(f.getString("endereco"));
		candidato.setNumeroEndereco(f.getString("numero"));
		candidato.setBairro(f.getString("bairro"));
		String complemento = f.getString("complemento");
		if (complemento != null && !complemento.equals(""))
			candidato.setComplemento(complemento);
		candidato.setCidade(f.getString("cidade"));
		candidato.setUf(f.getString("uf"));
		candidato.setCep(f.getString("cep"));
		candidato.setTelefone(f.getString("telefone"));
		candidato.setTelefoneDDD(f.getString("ddd"));
		candidato.setSexo(f.getString("sexo"));
		candidato.setDataNascimentoStr(f.getString("dataNascimentoStr"));
		candidato.setLocalNascimento(f.getString("localNascimento"));		
		candidato.setUfNascimento(f.getString("ufNascimento"));		
		candidato.setPossuiDeficienciaStr(f.getString("possuiDeficiencia"));		
		String deficiencia = f.getString("deficiencia");
		if (deficiencia != null && !deficiencia.equals("")){
			candidato.setDeficiencia(deficiencia);
		}
		candidato.setIdentidadeTipo(f.getString("identidadeTipo"));
		candidato.setIdentidadeNumero(f.getString("identidadeNumero"));
		candidato.setIdentidadeOrgaoExpedidor(f.getString("identidadeOrgaoExpedidor"));
		candidato.setIdentidadeUf(f.getString("identidadeUf"));
		candidato.setCodMunicipioIbge(f.getString("codMunicipioIbge"));
		
		Integer idEstadoCivil = Integer.valueOf(f.getString("codEstadoCivil"));
				
		ExEstadoCivil estadoCivil = FacadeBean.buscar(idEstadoCivil, ExEstadoCivil.class);
		f.set("descricaoEstadoCivil", estadoCivil.getDescricao());
				
		ExUf uf = FacadeBean.buscar(candidato.getUf(), ExUf.class);
		f.set("descricaoUf", uf.getEstado());
		ExUf ufNascimento = FacadeBean.buscar(candidato.getUfNascimento(), ExUf.class);
		f.set("descricaoUfNascimento", ufNascimento.getEstado());
					
		candidato.setEstadoCivil(estadoCivil);
		candidato.setCargo(cargo);
		try {
			
			Integer inscricao = ExcelFacade.salvarInscricao(candidato, null);
			
			f.set("numeroInscricao", Utilitarios.completaComZeros(inscricao.toString(), 6));
			
		} catch (FacadeException e) {			
			retornaErro(request, f, candidato);
			return mapping.getInputForward();
		}
		
		return mapping.findForward("sucesso");
	}
	
	public ActionForward Imprimir(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		String numeroInscricao = request.getParameter("inscricao");
		
		numeroInscricao = Utilitarios.removeZerosAEsquerda(numeroInscricao);
		
		ExCandidato candidato = ExcelFacade.buscarExCandidatoPorInscricao(numeroInscricao);
		
		preencheFormCandidato(f, candidato);
		
		return mapping.findForward("impressao");
	}
	
	public ActionForward ImprimirConfirmacao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		String numeroInscricao = request.getParameter("inscricao");
		
		numeroInscricao = Utilitarios.removeZerosAEsquerda(numeroInscricao);
		
		ExCandidato candidato = ExcelFacade.buscarExCandidatoPorInscricao(numeroInscricao);
		
		preencheFormCandidato(f, candidato);
		
		return mapping.findForward("impressaoConfirmacao");
	}	

	public ActionForward ImprimirBoleto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		Integer idBoleto = ExcelFacade.geraBoleto(f.getString("cpf"));
		BoletoBancario b = ExcelFacade.buscar(idBoleto, BoletoBancario.class);
		
		ActionForward fo = new ActionForward();
		fo.setName("imprimir");
		fo.setPath("/run?ReportName=boleto.rptdesign&codigoBoleto=" + idBoleto + "&codigoBarra=" + b.getCodigoDeBarra());
		fo.setRedirect(true);
		
		return fo;
	}	
	
	public ActionForward inscricao2008JaEfetuada(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		ExCandidato2008 candidato2008 = ExcelFacade.buscaCandidato2008PorCpf(f.getString("cpf"));
		ExCandidato candidato = ExcelFacade.buscaCandidatoPorCpf(f.getString("cpf"));
		
		preencheFormCandidato(f, candidato2008, candidato.getNumeroInscricao());
		
		return mapping.findForward("confirmada");
	}	
	
	public ActionForward inscricaoJaEfetuada(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		ExCandidato candidato = ExcelFacade.buscaCandidatoPorCpf(f.getString("cpf"));
		
		preencheFormCandidato(f, candidato);
		
		return mapping.findForward("sucesso");
	}	
	
	public ActionForward Voltar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return mapping.findForward("voltar");
	}	
	
	private void preencheDadosPagina(HttpServletRequest request, DynaActionForm f) throws FacadeException {
		List<ExUf> ufs = (List<ExUf>) FacadeBean.buscarTodos(ExUf.class);
		
		List<ExCargo> cargos = (List<ExCargo>) FacadeBean.buscarTodosOrdenados(ExCargo.class, "descricao");
		
		List<ExEstadoCivil> estadosCivis = (List<ExEstadoCivil>) FacadeBean.buscarTodos(ExEstadoCivil.class);
		
		request.setAttribute("cargos", cargos);
		request.setAttribute("ufs", ufs);
		request.setAttribute("estadosCivis", estadosCivis);
	}
	
	private boolean valida(HttpServletRequest request, DynaActionForm f, ExCargo cargo) throws FacadeException{
		
		boolean retorno = true;
				
		ActionMessages msgs = new ActionMessages();
		
		// valida cpf
		if (!Utilitarios.validaCpf(f.getString("cpf"))){
			msgs.add("msg", new ActionMessage("cadastro.cpf.invalido"));
		} else if (f.getString("possuiDeficiencia").equals("Sim")) {
			String deficiencia = f.getString("deficiencia");
			if ("".equals(deficiencia)){
				msgs.add("msg", new ActionMessage("excel.cadastro.necessidade.especial.vazio"));
			}
		}
		
		// Valida data de nascimento
		/*Calendar c70 = new GregorianCalendar(2009, Calendar.JUNE, 1);
		c70.add(Calendar.YEAR, -70);
		Calendar c16 = new GregorianCalendar(2009, Calendar.JUNE, 1);
		c16.add(Calendar.YEAR, -16);
		
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date dataNascimento = df.parse(f.getString("dataNascimentoStr"));
			if ( (Utilitarios.comparaDuasDatas(dataNascimento, c70.getTime()) < 0) ||
				(Utilitarios.comparaDuasDatas(dataNascimento, c16.getTime()) > 0) ){
				msgs.add("msg", new ActionMessage("excel.cadastro.dataNascimento.fora.limite"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
		// valida se j� existe cpf cadastrado.
		ExCandidato c = ExcelFacade.buscaCandidatoPorCpf(f.getString("cpf"));
		if (c != null){
			msgs.add("msg", new ActionMessage("excel.cadastro.cpf.ja.cadastrado"));
		}
		
		// Valida Munic�pio onde candidato mora		
		if (cargo.getCodMunicipioIbge() != null && 
				(!cargo.getCodMunicipioIbge().equals(""))){
			
			if (!cargo.getCodMunicipioIbge().equals(f.getString("codMunicipioIbge"))){
				msgs.add("msg", new ActionMessage("excel.cadastro.municipio.diferente"));
			}
		}
		
		if (msgs.size() > 0){
			saveMessages(request, msgs);
			retorno = false;
		}
		
		return retorno;
	}
	
	private boolean validaCandidato2008(HttpServletRequest request, DynaActionForm f) throws FacadeException{
		
		boolean retorno = true;
				
		ActionMessages msgs = new ActionMessages();
						
		// valida se j� existe cpf cadastrado.
		ExCandidato c = ExcelFacade.buscaCandidatoPorCpf(f.getString("cpf"));
		if (c != null){
			msgs.add("msg", new ActionMessage("excel.cadastro.cpf.ja.cadastrado"));
		}
		
		if (msgs.size() > 0){
			saveMessages(request, msgs);
			retorno = false;
		}
		
		return retorno;
	}
	
	private void retornaErro(HttpServletRequest request, DynaActionForm f, ExCandidato candidato) throws FacadeException{
		log.error("CPF: " + candidato.getCpf() + " - Candidato: " + candidato.getNome());
		preencheDadosPagina(request, f);
		ActionMessages msgs = new ActionMessages();
		msgs.add("msg", new ActionMessage("excel.erro.exception"));
		saveMessages(request, msgs);
	}
		
	private void preencheFormCandidato(DynaActionForm f, ExCandidato candidato){
		if (candidato.getNumeroInscricao() != null)
			f.set("numeroInscricao", Utilitarios.completaComZeros(candidato.getNumeroInscricao(), 6));
		if (f.getString("cpf").equals(""))
			f.set("cpf", candidato.getCpf());
		f.set("nome", candidato.getNome());
		f.set("endereco", candidato.getEndereco());
		f.set("numero", candidato.getNumeroEndereco());
		f.set("bairro", candidato.getBairro());
		f.set("complemento", candidato.getComplemento());
		f.set("cidade", candidato.getCidade());
		f.set("uf", candidato.getUf());
		f.set("cep", candidato.getCep());
		f.set("telefone", candidato.getTelefone());
		f.set("dataNascimentoStr", candidato.getDataNascimentoStr());
		f.set("localNascimento", candidato.getLocalNascimento());		
		f.set("ufNascimento", candidato.getUfNascimento());
		f.set("possuiDeficiencia", candidato.getPossuiDeficienciaStr());
		f.set("deficiencia", candidato.getDeficiencia());
		f.set("identidadeTipo", candidato.getIdentidadeTipo());
		f.set("identidadeNumero", candidato.getIdentidadeNumero());
		f.set("identidadeOrgaoExpedidor", candidato.getIdentidadeOrgaoExpedidor());
		f.set("sexo", candidato.getSexo());
		f.set("descricaoEstadoCivil", candidato.getEstadoCivil().getDescricao());		
		f.set("descricaoCargo", candidato.getCargo().getDescricao());		
		f.set("descricaoUf", candidato.getUf());
		f.set("descricaoUfNascimento", candidato.getUfNascimento());
	}
	
	private void preencheFormCandidato(DynaActionForm f, ExCandidato2008 candidato, String novoNumeroInscricao){
		if (novoNumeroInscricao != null)
			f.set("numeroInscricao", Utilitarios.completaComZeros(novoNumeroInscricao, 6));
		if (f.getString("cpf").equals(""))
			f.set("cpf", candidato.getCpf());
		f.set("nome", candidato.getNome());			
		f.set("endereco", candidato.getEndereco());
		f.set("numero", candidato.getNumeroEndereco());
		f.set("bairro", candidato.getBairro());
		f.set("complemento", candidato.getComplemento());
		f.set("cidade", candidato.getCidade());
		f.set("uf", candidato.getUf());
		f.set("cep", candidato.getCep());
		f.set("telefone", candidato.getTelefone());
		f.set("dataNascimentoStr", candidato.getDataNascimentoStr());
		f.set("localNascimento", candidato.getLocalNascimento());		
		f.set("ufNascimento", candidato.getUfNascimento());
		f.set("possuiDeficiencia", candidato.getPossuiDeficienciaStr());
		f.set("deficiencia", candidato.getDeficiencia());
		f.set("identidadeTipo", candidato.getIdentidadeTipo());
		f.set("identidadeNumero", candidato.getIdentidadeNumero());
		f.set("identidadeOrgaoExpedidor", candidato.getIdentidadeOrgaoExpedidor());
		//f.set("identidadeUf", candidato.getIdentidadeUf());
		f.set("sexo", candidato.getSexo());
		f.set("descricaoEstadoCivil", candidato.getEstadoCivil().getDescricao());		
		f.set("descricaoCargo", candidato.getCargo().getDescricao());		
		f.set("descricaoUf", candidato.getUf());
		f.set("descricaoUfNascimento", candidato.getUfNascimento());
	}
}
