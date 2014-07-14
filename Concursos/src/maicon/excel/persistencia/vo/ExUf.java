/**
 * 
 */
package maicon.excel.persistencia.vo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Maicon
 *
 */
@Entity
@Table(name="tab_uf", schema="concursos")
public class ExUf {
	
	@Id
	@Column(name="uf")
	private String uf;
	
	@Column(name="estado")
	private String estado;

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
