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
@Table(name="tab_estado_civil", schema="concursos")
public class EstadoCivil {
	

	@Id
	@SequenceGenerator(name="seqEstadoCivil", sequenceName="tab_estado_civil_cod_estado_civil_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqEstadoCivil")
	@Column(name="cod_estado_civil")
	private Integer id;
	
	@Column(name="estado_civil")
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
