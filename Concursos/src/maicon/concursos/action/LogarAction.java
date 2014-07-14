package maicon.concursos.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

public class LogarAction extends DispatchAction {

	public ActionForward Logar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		// temporário
		if ( !f.getString("usuario").equals("cida") || 
				!f.getString("senha").equals("#123456#.") ){
			
			ActionMessages msgs = new ActionMessages();
			
			msgs.add("msg", new ActionMessage("login.invalido"));
			
			saveMessages(request, msgs);
			
			return mapping.findForward("voltar");
		}
		
		return mapping.findForward("sucesso");
	}

}
