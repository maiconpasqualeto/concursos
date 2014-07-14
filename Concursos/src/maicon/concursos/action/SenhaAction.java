/**
 * 
 */
package maicon.concursos.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maicon.concursos.persistencia.vo.Concurso;
import maicon.ferramentas.facade.AppFacade;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 * @author maicon
 *
 */
public class SenhaAction extends DispatchAction {
	
	public ActionForward Entrar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		String senha = (String) f.get("senha");
		Integer codConcurso = (Integer) f.get("codConcurso");
		Concurso c = AppFacade.buscar(codConcurso, Concurso.class);
		
		request.setAttribute("codConcurso", c.getId());
		request.setAttribute("nomeConcurso", c.getDescricao());
		request.setAttribute("prefeitura", c.getPrefeitura());
		
		if(senha.equals(c.getSenha()))
			return mapping.findForward("sucesso");
		
		ActionMessages msgs = new ActionMessages();
		
		msgs.add("msg", new ActionMessage("senha.invalida"));
		
		saveMessages(request, msgs);
				
		return mapping.getInputForward();
	}
	
	public ActionForward Voltar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
								
		return mapping.findForward("voltar");
	}

}
