/**
 * 
 */
package maicon.excel.persistencia.vo;

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
@Table(name="concurso", schema="excel")
public class ExConcurso {
	
	@Id
	@SequenceGenerator(name="seqConcurso", sequenceName="concurso_cod_concurso_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqConcurso")
	@Column(name="cod_concurso")
	private Integer id;
	
	@Column(name="concurso")
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
