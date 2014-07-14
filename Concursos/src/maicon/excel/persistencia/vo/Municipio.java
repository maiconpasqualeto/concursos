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
@Table(name="municipio", schema="excel")
public class Municipio {
	
	@Id
	@Column(name="cod_municipio_ibge")
	private Long codigoMunicipioIbge;
		
	@Column(name="municipio")
	private String municipio;

	public Long getCodigoMunicipioIbge() {
		return codigoMunicipioIbge;
	}

	public void setCodigoMunicipioIbge(Long codigoMunicipioIbge) {
		this.codigoMunicipioIbge = codigoMunicipioIbge;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
		
}
