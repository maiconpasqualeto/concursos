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
@Table(name="cargo", schema="excel")
public class ExCargo {
	
	@Id
	@SequenceGenerator(name="seqCargo", sequenceName="cargo_cod_cargo_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqCargo")
	@Column(name="cod_cargo")
	private Integer id;
	
	@Column(name="cargo")
	private String descricao;
	
	@Column(name="valor")
	private Float valor;
	
	@Column(name="nivel")
	private String nivel;
	
	@Column(name="cod_municipio_ibge")
	private String codMunicipioIbge;
	
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

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the codMunicipioIbge
	 */
	public String getCodMunicipioIbge() {
		return codMunicipioIbge;
	}

	/**
	 * @param codMunicipioIbge the codMunicipioIbge to set
	 */
	public void setCodMunicipioIbge(String codMunicipioIbge) {
		this.codMunicipioIbge = codMunicipioIbge;
	}
		
}
