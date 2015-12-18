/**
 * 
 */
package maicon.concursos.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maicon.concursos.ferramentas.Utilitarios;
import maicon.concursos.persistencia.vo.Candidato;
import maicon.concursos.persistencia.vo.Uf;
import maicon.ferramentas.facade.AppFacade;
import maicon.ferramentas.facade.FacadeBean;
import maicon.ferramentas.facade.FacadeException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 * @author maicon.pasqualeto
 *
 */
public class JaCadastradoAction extends DispatchAction {

	public ActionForward Entrar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		// Valida entrada de dados
		if (!valida(request, f))
			return mapping.getInputForward();
		
		String cpf = f.getString("cpf");
		String senha = f.getString("senha");
		
		Candidato candidato = AppFacade.buscaCandidatoPorCpf(cpf);
		
		ActionMessages msgs = new ActionMessages();
		
		if (candidato == null) {
			msgs.add("msg", new ActionMessage("cadastro.nao.encontrado"));
			saveMessages(request, msgs);
			return mapping.getInputForward();
		} else {
			String senhaDigitada = Utilitarios.geraHashMd5(senha);
			if (!candidato.getSenha().equals(senhaDigitada)){
				msgs.add("msg", new ActionMessage("cadastro.senha.invalida"));
				saveMessages(request, msgs);
				return mapping.getInputForward();
			}	
		}
		
		f.set("numeroInscricao", Utilitarios.completaComZeros(candidato.getNumeroInscricao().toString(), 6));
		f.set("nome", candidato.getNome());
		f.set("cpf", candidato.getCpf());				
		f.set("endereco", candidato.getEndereco());
		f.set("numero", candidato.getNumeroEndereco());
		f.set("bairro", candidato.getBairro());
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
		
		Uf uf = FacadeBean.buscar(candidato.getUf(), Uf.class);
		f.set("descricaoUf", uf.getEstado());
		Uf ufNascimento = FacadeBean.buscar(candidato.getUfNascimento(), Uf.class);
		f.set("descricaoUfNascimento", ufNascimento.getEstado());
		
		request.setAttribute("nome", candidato.getNome());
		request.setAttribute("cpf", candidato.getCpf());
		request.setAttribute("codCandidato", candidato.getId());
		
		return mapping.findForward("sucesso");
	}
	
	private boolean valida(HttpServletRequest request, DynaActionForm f) throws FacadeException{
		
		boolean retorno = true;
		
		String cpf = f.getString("cpf");
		
		ActionMessages msgs = new ActionMessages();
		
		// valida se cpf � v�lido
		if (!Utilitarios.validaCpf(cpf)) {
			msgs.add("msg", new ActionMessage("cadastro.cpf.invalido"));
		} 
		
		if (msgs.size() > 0){
			saveMessages(request, msgs);
			retorno = false;
		}
		
		return retorno;
	}
}
