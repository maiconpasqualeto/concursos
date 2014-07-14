/**
 * 
 */
package maicon.ferramentas.facade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import maicon.concursos.ferramentas.dao.GenericDAO;
import maicon.concursos.persistencia.vo.Funcao;

/**
 * @author maicon
 *
 */
public class FuncaoDAO extends GenericDAO<Funcao> {
	
	private static Logger logger = Logger.getLogger(CargoDAO.class);

	
	public static List<Funcao> recuperaFuncoesPorCargoELotacao(Integer idCargo, Integer idLotacao) throws FacadeException{
		EntityManager em = BasicService.getEntityManager();
		List<Funcao> funcoes = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select cod_funcao, funcao from concursos.funcao ");
			sql.append("where cod_funcao in ");
			sql.append("( select cod_funcao from concursos.cargo_lotacao ");
			sql.append("where cod_cargo = 3 and cod_lotacao = 3 ) ");
			
			Query q = em.createNativeQuery(sql.toString());
			q.setParameter("cod_cargo", idCargo);
			q.setParameter("cod_lotacao", idLotacao);
			
			@SuppressWarnings("unchecked")
			List<Object[]> retorno = q.getResultList();
			if (retorno.size() > 0)
				funcoes = new ArrayList<Funcao>();
			
			for (Object[] o : retorno) {
				Funcao f = new Funcao();
				f.setId(Integer.valueOf(o[0].toString()));
				f.setDescricao(o[2].toString());
				funcoes.add(f);
			}
			
		} catch (Exception e) {			
			throw new FacadeException("Erro", e, logger);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
		return funcoes;
	}
	
	

}
