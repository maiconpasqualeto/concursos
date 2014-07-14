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

/**
 * @author Maicon
 *
 */
@Entity
@Table(name="grupo_cargo", schema="concursos")
public class GrupoCargo {

	@Id
	@SequenceGenerator(name="seqGrupoCargo", sequenceName="grupo_cargo_cod_grupo_cargo_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqGrupoCargo")
	@Column(name="cod_grupo_cargo")
	private Integer id;

	@Column(name="grupo_cargo")
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
	
}
