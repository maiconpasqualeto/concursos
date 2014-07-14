/**
 * 
 */
package maicon.concursos.persistencia.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Maicon
 *
 */
@Entity
@Table(name="cargo", schema="concursos")
public class Cargo {
	
	@Id
	@SequenceGenerator(name="seqCargo", sequenceName="cargo_cod_cargo_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqCargo")
	@Column(name="cod_cargo")
	private Integer id;
	
	@Column(name="cargo")
	private String descricao;
	
	
	@ManyToOne (targetEntity = CategoriaCargo.class, fetch = FetchType.LAZY)
	@JoinColumn (name = "cod_categoria_cargo", nullable=false)
	private CategoriaCargo categoriaCargo;
	
	@ManyToMany(targetEntity=Lotacao.class, fetch=FetchType.LAZY)
	@JoinTable(
			name="cargo_lotacao",  
			schema="concursos",
			joinColumns={@JoinColumn(name="cod_cargo")},
			inverseJoinColumns={@JoinColumn(name="cod_lotacao")})
	private List<Lotacao> lotacoes;
	
	@ManyToOne (targetEntity = Concurso.class, fetch = FetchType.LAZY)
	@JoinColumn (name = "cod_concurso")
	private Concurso concurso;

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

	public CategoriaCargo getCategoriaCargo() {
		return categoriaCargo;
	}

	public void setCategoriaCargo(CategoriaCargo categoriaCargo) {
		this.categoriaCargo = categoriaCargo;
	}

	public List<Lotacao> getLotacoes() {
		return lotacoes;
	}

	public void setLotacoes(List<Lotacao> lotacoes) {
		this.lotacoes = lotacoes;
	}

	public Concurso getConcurso() {
		return concurso;
	}

	public void setConcurso(Concurso concurso) {
		this.concurso = concurso;
	}
	
	
}
