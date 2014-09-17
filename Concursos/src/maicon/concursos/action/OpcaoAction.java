/**
 * 
 */
package maicon.concursos.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maicon.concursos.persistencia.vo.Concurso;
import maicon.ferramentas.facade.AppFacade;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 * @author maicon
 *
 */
public class OpcaoAction extends DispatchAction {
	
	public ActionForward entrada(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Concurso> concursos = AppFacade.buscarConcursosAtivos();
		
		DynaActionForm f = (DynaActionForm) form;
		f.set("concursos", concursos);
		if (!concursos.isEmpty())
			f.set("codConcurso", concursos.get(0).getId());
		
		return mapping.findForward("opcao");
	}
	
	public ActionForward Confirma(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm f = (DynaActionForm) form;
		Integer codConcurso = (Integer) f.get("codConcurso");
		Concurso c = AppFacade.buscar(codConcurso, Concurso.class);
		
		request.setAttribute("codConcurso", c.getId());
		request.setAttribute("nomeConcurso", c.getDescricao());
		request.setAttribute("prefeitura", c.getPrefeitura());
		
		if("S".equals(c.getTemSenha()))
			return mapping.findForward("temsenha");
		else 
			if("FECHADO".equals(c.getSituacao()))
				return mapping.findForward("fechado");
		else 
			if("AGUARDANDO".equals(c.getSituacao()))
				return mapping.findForward("aguardando");
		else 
			if("ABERTO".equals(c.getSituacao()))
				return mapping.findForward("entrada");
		
		// se não for nenhuma das alternativas está fechado		
		return mapping.findForward("fechado");
	}

}
