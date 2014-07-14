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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Maicon
 *
 */
@Entity
@Table(name="boleto_bancario", schema="excel")
public class BoletoBancario {

	@Id
	@SequenceGenerator(name="seqBoletoBancario", sequenceName="tab_boleto_bancario_cod_boleto_bancario_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqBoletoBancario")
	@Column(name="cod_boleto_bancario")
	private Integer id;
	
	@Column(name="numero_inscricao")
	private String numeroInscricao;
	
	@Column(name="numero_documento")
	private String numeroDocumento;
	
	@Column(name="data_emissao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEmissao;
	
	@Column(name="codigo_barra")
	private String codigoDeBarra;
	
	@Column(name="nosso_numero")
	private String nossoNumero;
	
	@Column(name="valor")
	private Float valor;
	
	@Column(name="data_vencimento")
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	@Column(name="linha_digitavel")
	private String linhaDigitavel;
	
	@Column(name="sacado")
	private String sacado;

	@Column(name="cargo")
	private String cargo;
	
	@Column(name="cpf_sacado")
	private String cpfSacado;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroInscricao() {
		return numeroInscricao;
	}

	public void setNumeroInscricao(String numeroInscricao) {
		this.numeroInscricao = numeroInscricao;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getCodigoDeBarra() {
		return codigoDeBarra;
	}

	public void setCodigoDeBarra(String codigoDeBarra) {
		this.codigoDeBarra = codigoDeBarra;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getLinhaDigitavel() {
		return linhaDigitavel;
	}

	public void setLinhaDigitavel(String linhaDigitavel) {
		this.linhaDigitavel = linhaDigitavel;
	}

	public String getSacado() {
		return sacado;
	}

	public void setSacado(String sacado) {
		this.sacado = sacado;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCpfSacado() {
		return cpfSacado;
	}

	public void setCpfSacado(String cpfSacado) {
		this.cpfSacado = cpfSacado;
	}
	
	
}
