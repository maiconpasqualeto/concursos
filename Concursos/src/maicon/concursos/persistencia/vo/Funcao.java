/**
 * 
 */
package maicon.concursos.persistencia.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Maicon
 *
 */
@Entity
@Table(name="funcao", schema="concursos")
public class Funcao {
	
	@Id
	@SequenceGenerator(name="seqFuncao", sequenceName="funcao_cod_funcao_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqFuncao")
	@Column(name="cod_funcao")
	private Integer id;
	
	@Column(name="funcao")
	private String descricao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Transient
	private String idAux;

	public String getIdAux() {
		idAux = "";
		if (getId() != null)
			idAux = getId().toString();
		return idAux;
	}

	public void setIdAux(String idAux) {
		if (!"".equals(idAux)){
			try {
				setId(Integer.parseInt(idAux));
			} catch (NumberFormatException e) {}
		}
		this.idAux = idAux;
	}
	

}
