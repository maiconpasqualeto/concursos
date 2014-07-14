/**
 * 
 */
package maicon.excel.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maicon.concursos.ferramentas.Utilitarios;
import maicon.excel.facade.ExcelFacade;
import maicon.ferramentas.facade.FacadeException;

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
public class ExProcessaEntradaAction extends DispatchAction {

	
	public ActionForward Confirma(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		
		// Valida entrada de dados
		if (!valida(request, f)) {			
			return mapping.findForward("erro");
		}
		String cpf = f.getString("cpf");
		
		request.setAttribute("nome", f.getString("nome"));
		request.setAttribute("cpf", cpf);
		
		if (ExcelFacade.verificaSeCandidato2008JaTemNovaInscricao(cpf))			
			return mapping.findForward("impressao2008");
		
		if (ExcelFacade.verificaCpfJaInscrito(cpf))
			return mapping.findForward("impressaoBoleto");
				
		if (ExcelFacade.verificaSeCandidatoJaInscrito2008(cpf))
			return mapping.findForward("confirma");
		
		return mapping.findForward("nova");
	}
	
	private boolean valida(HttpServletRequest request, DynaActionForm f) throws FacadeException{
		
		boolean retorno = true;
		
		String cpf = f.getString("cpf");
		
		ActionMessages msgs = new ActionMessages();
		
		try {
			if (!Utilitarios.validaCpf(cpf)) // valida cpf válido
				msgs.add("msg", new ActionMessage("excel.entrada.cpf.invalido"));
			
		} catch (NumberFormatException e){
			msgs.add("msg", new ActionMessage("excel.entrada.cpf.invalido.mascara"));
		}
		 
		if (msgs.size() > 0){
			saveMessages(request, msgs);
			retorno = false;
		}
		
		return retorno;
	}

}
