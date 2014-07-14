/**
 * 
 */
package maicon.concursos.action.ajax;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maicon.concursos.persistencia.vo.Funcao;
import maicon.concursos.persistencia.vo.Lotacao;
import maicon.ferramentas.facade.InscricaoService;

import org.ajaxtags.servlets.BaseAjaxServlet;
import org.ajaxtags.xml.AjaxXmlBuilder;

/**
 * @author maicon
 *
 */
public class AjaxListaFuncoes extends BaseAjaxServlet {

	@Override
	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		String idCargo = request.getParameter("idCargo");
		String idLotacao = request.getParameter("idLotacao");
		
		List<Funcao> funcoes = new ArrayList<Funcao>();
		Funcao funcao = new Funcao();		
		funcao.setDescricao("");
		funcoes.add(funcao);
						 
		funcoes.addAll(InscricaoService.recuperaFuncoesPorCargoELotacao(Integer.valueOf(idCargo), Integer.valueOf(idLotacao)));
		
		return new AjaxXmlBuilder().addItems(funcoes, "descricao", "idAux").toString();
		
	}

}
