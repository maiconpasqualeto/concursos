/**
 * 
 */
package maicon.excel.persistencia.vo;

import java.util.Date;

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
@Table(name="tab_inscricao", schema="excel")
public class ExTabInscricao {

	@Id
	@SequenceGenerator(name="seqTabIncricao", sequenceName="tab_inscricao_cod_tab_inscricao_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqTabIncricao")
	@Column(name="cod_inscricao")
	private Integer id;
	
	@Column(name="data_inscricao")
	private Date dataIncricao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataIncricao() {
		return dataIncricao;
	}

	public void setDataIncricao(Date dataIncricao) {
		this.dataIncricao = dataIncricao;
	}
}
