/**
 * 
 */
package maicon.concursos.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maicon.concursos.ferramentas.Utilitarios;
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
public class CadastroAction extends DispatchAction {

	public ActionForward Confirma(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		// tempor�rio
		/*if ( !f.getString("login").equals("cida") || 
				!f.getString("senha").equals("#123456#.") ){
			response.sendRedirect("encerramento.jsp");
		}*/
		
		request.setAttribute("codConcurso", f.getString("codConcurso"));
		request.setAttribute("nomeConcurso", f.getString("nomeConcurso"));
		request.setAttribute("prefeitura", f.getString("prefeitura"));
		
		// Valida entrada de dados
		if (!valida(request, f)) {			
			return mapping.getInputForward();
		}
		
		request.setAttribute("nome", f.getString("nome"));
		request.setAttribute("cpf", f.getString("cpf"));
		//request.setAttribute("email", f.getString("email"));
		
		return mapping.findForward("sucesso");
	}
	
	private boolean valida(HttpServletRequest request, DynaActionForm f) throws FacadeException{
		
		boolean retorno = true;
		
		String cpf = f.getString("cpf");
		
		ActionMessages msgs = new ActionMessages();
		
		// valia cpf v�lido
		if (!Utilitarios.validaCpf(cpf))
			msgs.add("msg", new ActionMessage("cadastro.cpf.invalido"));
		 
		
		if (msgs.size() > 0){
			saveMessages(request, msgs);
			retorno = false;
		}
		
		return retorno;
	}
	
}
