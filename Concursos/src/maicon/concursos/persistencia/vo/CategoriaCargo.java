/**
 * 
 */
package maicon.concursos.persistencia.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Maicon
 *
 */
@Entity
@Table(name="categoria_cargo", schema="concursos")
public class CategoriaCargo {

	@Id
	@SequenceGenerator(name="seqCategoriaCargo", sequenceName="categoria_cargo_cod_categoria_cargo_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqCategoriaCargo")
	@Column(name="cod_categoria_cargo")
	private Integer id;
	
	@Column(name="categoria_cargo")
	private String descricao;
		
	@ManyToOne (targetEntity = GrupoCargo.class, fetch = FetchType.LAZY)
	@JoinColumn (name = "cod_grupo_cargo", nullable=false)
	private GrupoCargo grupoCargo;
	
	
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

	public GrupoCargo getGrupoCargo() {
		return grupoCargo;
	}

	public void setGrupoCargo(GrupoCargo grupoCargo) {
		this.grupoCargo = grupoCargo;
	}
	
	
}
