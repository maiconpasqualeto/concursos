/**
 * 
 */
package maicon.concursos.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maicon.concursos.ferramentas.Utilitarios;
import maicon.concursos.persistencia.vo.BoletoConcurso;
import maicon.concursos.persistencia.vo.Candidato;
import maicon.concursos.persistencia.vo.Cargo;
import maicon.concursos.persistencia.vo.Concurso;
import maicon.concursos.persistencia.vo.EstadoCivil;
import maicon.concursos.persistencia.vo.Funcao;
import maicon.concursos.persistencia.vo.Lotacao;
import maicon.concursos.persistencia.vo.Uf;
import maicon.ferramentas.facade.AppFacade;
import maicon.ferramentas.facade.FacadeBean;
import maicon.ferramentas.facade.FacadeException;
import maicon.ferramentas.facade.InscricaoService;

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
public class InscricaoAction extends DispatchAction {

	
	public ActionForward inscrever(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		return mapping.findForward("sucesso");
	}
	
	public ActionForward abreFicha(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		//request.setAttribute("mail", f.getString("mail"));
		
		f.set("nome", request.getAttribute("nome"));
		f.set("cpf", request.getAttribute("cpf"));
		//f.set("mail", request.getAttribute("mail"));
		
		Integer codConcurso = Integer.valueOf(f.getString("codConcurso"));
		
		preencheDadosPagina(request, f, codConcurso);
		
		return mapping.findForward("abrirFicha");
	}
	
	public ActionForward Confirmar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		//Integer id = (Integer) f.get("codCandidato");
		
		Integer codConcurso = Integer.valueOf(f.getString("codConcurso"));
		
		// Valida entrada de dados
		if (!valida(request, f)) {
			preencheDadosPagina(request, f, codConcurso);
			Concurso c = AppFacade.buscar(codConcurso, Concurso.class);
			
			request.setAttribute("codConcurso", c.getId());
			request.setAttribute("nomeConcurso", c.getDescricao());
			request.setAttribute("prefeitura", c.getPrefeitura());
			
			return mapping.getInputForward();
		}
		
		Candidato candidato = new Candidato();
		candidato.setNome(f.getString("nome"));
		candidato.setCpf(f.getString("cpf"));
		candidato.setEmail(f.getString("cadastroemail"));
		candidato.setSenha((String) request.getSession().getAttribute("senha"));
		candidato.setEndereco(f.getString("endereco"));
		candidato.setNumeroEndereco(f.getString("numero"));
		candidato.setBairro(f.getString("bairro"));
		candidato.setComplemento(f.getString("complemento"));
		candidato.setCidade(f.getString("cidade"));
		candidato.setUf(f.getString("uf"));
		candidato.setCep(f.getString("cep"));
		candidato.setTelefone("(" + f.getString("ddd") + ")" + f.getString("telefone"));
		candidato.setNomePai(f.getString("nomePai"));
		candidato.setNomeMae(f.getString("nomeMae"));
		candidato.setSexo(f.getString("sexo"));
		candidato.setDataNascimentoStr(f.getString("dataNascimentoStr"));
		candidato.setLocalNascimento(f.getString("localNascimento"));		
		candidato.setUfNascimento(f.getString("ufNascimento"));
		candidato.setPossuiDeficiencia((Boolean) f.get("possuiDeficiencia"));
		candidato.setDeficiencia(f.getString("deficiencia"));
		candidato.setIdentidadeTipo(f.getString("identidadeTipo"));
		candidato.setIdentidadeNumero(f.getString("identidadeNumero"));
		candidato.setIdentidadeOrgaoExpedidor(f.getString("identidadeOrgaoExpedidor"));
		
		Integer idEstadoCivil = Integer.valueOf(f.getString("codEstadoCivil"));
		Integer idCargo = Integer.valueOf(f.getString("codCargo"));
		Integer idLotacao = Integer.valueOf(f.getString("codLotacao"));
		Integer idFuncao = Integer.valueOf(f.getString("codFuncao"));
				
		EstadoCivil estadoCivil = FacadeBean.buscar(idEstadoCivil, EstadoCivil.class);
		f.set("descricaoEstadoCivil", estadoCivil.getDescricao());
		Cargo cargo = FacadeBean.buscar(idCargo, Cargo.class);
		f.set("descricaoCargo", cargo.getDescricao());
		Lotacao lotacao = FacadeBean.buscar(idLotacao, Lotacao.class);
		f.set("descricaoLotacao", lotacao.getDescricao());
		Uf uf = FacadeBean.buscar(candidato.getUf(), Uf.class);
		f.set("descricaoUf", uf.getEstado());
		Uf ufNascimento = FacadeBean.buscar(candidato.getUfNascimento(), Uf.class);
		f.set("descricaoUfNascimento", ufNascimento.getEstado());
		Funcao funcao = FacadeBean.buscar(idFuncao, Funcao.class);
		f.set("descricaoFuncao", funcao.getDescricao());
		
		candidato.setEstadoCivil(estadoCivil);
		candidato.setCargo(cargo);
		candidato.setLotacao(lotacao);
		candidato.setFuncao(funcao);

		Concurso c = AppFacade.buscar(codConcurso, Concurso.class);
		candidato.setConcurso(c);
		
		Integer inscricao = InscricaoService.salvarInscricao(candidato, c);
		
		f.set("numeroInscricao", Utilitarios.completaComZeros(inscricao.toString(), 6));
		
		request.setAttribute("codConcurso", c.getId());
		request.setAttribute("nomeConcurso", f.getString("nomeConcurso"));
		request.setAttribute("prefeitura", f.getString("prefeitura"));
		
		return mapping.findForward("sucesso");
	}
	
	
	private boolean valida(HttpServletRequest request, DynaActionForm f) throws FacadeException{
		
		boolean retorno = true;
				
		ActionMessages msgs = new ActionMessages();
		
		// valida cpf
		if (!Utilitarios.validaCpf(f.getString("cpf"))){
			msgs.add("msg", new ActionMessage("cadastro.cpf.invalido"));
		} else if ((Boolean) f.get("possuiDeficiencia")) {
			String deficiencia = f.getString("deficiencia");
			if ("".equals(deficiencia)){
				msgs.add("msg", new ActionMessage("cadastro.deficiencia.vazio"));
			}
		} else if (!"".equals(f.getString("cadastroemail")) &&
				!Utilitarios.validaEmail(f.getString("cadastroemail"))){
			msgs.add("msg", new ActionMessage("inscricao.email.invalido"));
		}
		
		// Valida data de nascimento
		Calendar cIdadeMaxima = GregorianCalendar.getInstance();
		cIdadeMaxima.add(Calendar.YEAR, -70);
		cIdadeMaxima.set(Calendar.AM_PM, Calendar.AM);
		cIdadeMaxima.set(Calendar.HOUR, 0);
		cIdadeMaxima.set(Calendar.MINUTE, 0);
		cIdadeMaxima.set(Calendar.SECOND, 0);
		cIdadeMaxima.set(Calendar.MILLISECOND, 0);
		
		Calendar cIdadeMinima = GregorianCalendar.getInstance();
		cIdadeMinima.add(Calendar.YEAR, -15);
		cIdadeMinima.set(Calendar.AM_PM, Calendar.AM);
		cIdadeMinima.set(Calendar.HOUR, 0);
		cIdadeMinima.set(Calendar.MINUTE, 0);
		cIdadeMinima.set(Calendar.SECOND, 0);
		cIdadeMinima.set(Calendar.MILLISECOND, 0);
		
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date dataNascimento = df.parse(f.getString("dataNascimentoStr"));
			if ( (Utilitarios.comparaDuasDatas(dataNascimento, cIdadeMaxima.getTime()) < 0) ||
				(Utilitarios.comparaDuasDatas(dataNascimento, cIdadeMinima.getTime()) > 0) ){
				msgs.add("msg", new ActionMessage("cadastro.dataNascimento.fora.limite"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (msgs.size() > 0){
			saveMessages(request, msgs);
			retorno = false;
		}
		
		return retorno;
	}
	
	
	public ActionForward Cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		String cpf = f.getString("cpf");
		
		Candidato candidato = AppFacade.buscaCandidatoPorCpf(cpf);
		
		FacadeBean.excluir(candidato.getId(), Candidato.class);
		
		return mapping.findForward("inicio");
	}
	
	public ActionForward Imprimir(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		String strCodConcurso = request.getParameter("codConcurso");
		Integer codConcurso = Integer.valueOf(strCodConcurso);
		
		Concurso c = AppFacade.buscar(codConcurso, Concurso.class);
		
		String numeroInscricao = request.getParameter("inscricao");
		
		Candidato candidato = AppFacade.buscarCandidatoPorInscricao(Integer.valueOf(numeroInscricao), c.getId());
		
		f.set("numeroInscricao", Utilitarios.completaComZeros(candidato.getNumeroInscricao().toString(), 6));
		f.set("nome", candidato.getNome());
		f.set("cpf", candidato.getCpf());				
		f.set("endereco", candidato.getEndereco());
		f.set("numero", candidato.getNumeroEndereco());
		f.set("bairro", candidato.getBairro());
		f.set("cadastroemail", candidato.getEmail());
		f.set("complemento", candidato.getComplemento());
		f.set("cidade", candidato.getCidade());
		f.set("uf", candidato.getUf());
		f.set("cep", candidato.getCep());
		f.set("telefone", candidato.getTelefone());
		f.set("nomePai", candidato.getNomePai());
		f.set("nomeMae", candidato.getNomeMae());
		f.set("dataNascimentoStr", candidato.getDataNascimentoStr());
		f.set("localNascimento", candidato.getLocalNascimento());		
		f.set("ufNascimento", candidato.getUfNascimento());
		f.set("possuiDeficiencia", candidato.getPossuiDeficiencia());
		f.set("deficiencia", candidato.getDeficiencia());
		f.set("identidadeTipo", candidato.getIdentidadeTipo());
		f.set("identidadeNumero", candidato.getIdentidadeNumero());
		f.set("identidadeOrgaoExpedidor", candidato.getIdentidadeOrgaoExpedidor());
		f.set("sexo", candidato.getSexo());
		f.set("descricaoEstadoCivil", candidato.getEstadoCivil().getDescricao());		
		f.set("descricaoCargo", candidato.getCargo().getDescricao());
		f.set("descricaoLotacao", candidato.getLotacao().getDescricao());
		f.set("descricaoFuncao", candidato.getFuncao().getDescricao());
		f.set("descricaoUf", candidato.getUf());
		f.set("descricaoUfNascimento", candidato.getUfNascimento());
		
		f.set("pagamentoNome", c.getPagamentoNome());
		f.set("pagamentoBanco", c.getPagamentoBanco());
		f.set("pagamentoAgencia", c.getPagamentoAgencia());
		f.set("pagamentoConta", c.getPagamentoConta());
		
		/*Uf uf = FacadeBean.buscar(candidato.getUf(), Uf.class);
		f.set("descricaoUf", uf.getEstado());
		Uf ufNascimento = FacadeBean.buscar(candidato.getUfNascimento(), Uf.class);
		f.set("descricaoUfNascimento", ufNascimento.getEstado());*/
		
		request.setAttribute("nomeConcurso", request.getParameter("nomeConcurso"));
		request.setAttribute("prefeitura", request.getParameter("prefeitura"));
		
		request.setAttribute("anoConcurso", new SimpleDateFormat("yyyy").format(new Date()));
		
		return mapping.findForward("impressao");
	}

	public ActionForward Voltar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String codConcurso = request.getParameter("codConcurso");
		String numeroInscricao = request.getParameter("numeroInscricao");
		Candidato c = AppFacade.buscarCandidatoPorInscricao(
				Integer.valueOf(numeroInscricao), Integer.valueOf(codConcurso));		
		
		ActionForward forward = mapping.findForward("voltarNovo");		
		return new ActionForward(forward.getPath() + "&codConcurso=" + c.getConcurso().getId());
	}
	
	private void preencheDadosPagina(HttpServletRequest request, DynaActionForm f, Integer codConcurso) throws FacadeException {
		List<Uf> ufs = (List<Uf>) FacadeBean.buscarTodos(Uf.class);
		
		List<Cargo> cargos = AppFacade.buscarCargosPorConcurso(codConcurso);
		
		List<EstadoCivil> estadosCivis = (List<EstadoCivil>) FacadeBean.buscarTodos(EstadoCivil.class);
		
		List<Lotacao> lotacoes = new ArrayList<Lotacao>();
		
		List<Funcao> funcoes = new ArrayList<Funcao>();
		
		if (!"".equals(f.getString("codCargo"))){
			Integer idCargo = Integer.valueOf(f.getString("codCargo"));
			lotacoes = InscricaoService.recuperaLotacoesPorCargo(idCargo);
		}
		
		if (!"".equals(f.getString("codLotacao"))){
			Integer idCargo = Integer.valueOf(f.getString("codCargo"));
			Integer idLotacao = Integer.valueOf(f.getString("codLotacao"));
			funcoes = InscricaoService.recuperaFuncoesPorCargoELotacao(idCargo, idLotacao);
		}
		
		request.setAttribute("cargos", cargos);
		request.setAttribute("ufs", ufs);
		request.setAttribute("estadosCivis", estadosCivis);
		request.setAttribute("lotacoes", lotacoes);
		request.setAttribute("funcoes", funcoes);
	}
	
	public ActionForward ImprimirBoleto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		Integer codConcurso = Integer.valueOf(f.getString("codConcurso"));
		String inscricao = f.getString("numeroInscricao");
		
		BoletoConcurso b = AppFacade.geraBoleto(Integer.valueOf(inscricao), codConcurso);		
		
		ActionForward fo = new ActionForward();
		fo.setName("imprimir");
		fo.setPath("/run?ReportName=boleto.rptdesign&codigoBoleto=" + b.getId());
		fo.setRedirect(true);
		
		return fo;
	}
}
