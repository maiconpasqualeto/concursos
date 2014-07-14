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
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Maicon
 *
 */
@Entity
@Table(name="lotacao", schema="concursos")
public class Lotacao {


	@Id
	@SequenceGenerator(name="seqLotacao", sequenceName="lotacao_cod_lotacao_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqLotacao")
	@Column(name="cod_lotacao")
	private Integer id;
	
	@Column(name="lotacao")
	private String descricao;
	
	@ManyToMany(targetEntity=Cargo.class, mappedBy="lotacoes", fetch=FetchType.LAZY)
	private List<Cargo> cargos;	
	
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

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
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
