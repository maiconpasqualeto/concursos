/**
 * 
 */
package maicon.concursos.action.ajax;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maicon.concursos.persistencia.vo.Lotacao;
import maicon.ferramentas.facade.InscricaoService;

import org.ajaxtags.servlets.BaseAjaxServlet;
import org.ajaxtags.xml.AjaxXmlBuilder;

/**
 * @author Maicon
 *
 */
public class AjaxListaLotacoes extends BaseAjaxServlet {

	@Override
	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		String idCargo = request.getParameter("idCargo");
		
		List<Lotacao> lotacoes = new ArrayList<Lotacao>();
		Lotacao lotacao = new Lotacao();		
		lotacao.setDescricao("");
		lotacoes.add(lotacao);
						 
		lotacoes.addAll(InscricaoService.recuperaLotacoesPorCargo(Integer.valueOf(idCargo)));
		
		return new AjaxXmlBuilder().addItems(lotacoes, "descricao", "idAux").toString();
		
	}
	
}
