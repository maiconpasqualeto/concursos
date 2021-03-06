/**
 * 
 */
package maicon.concursos.persistencia.vo;

import java.math.BigDecimal;
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
@Table(name="boleto_concurso", schema="concursos")
public class BoletoConcurso {


	@Id
	@SequenceGenerator(name="seqBoleto", sequenceName="boleto_concurso_cod_boleto_concurso_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqBoleto")
	@Column(name="cod_boleto_concurso")
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
	private BigDecimal valor;
	
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
	
	@Column(name="nome_concurso")
	private String nomeConcurso;
	
	@Column(name="cedente")
	private String cedente;
	
	@Column(name="agencia_conta")
	private String agenciaConta;
	
	@Column(name="cnpj")
	private String cnpj;
	
	@Column(name="funcao_candidato")
	private String funcaoCandidato;
	
	@Column(name="lotacao_candidato")
	private String lotacaoCandidato;
	
	@Column(name="carteira")
	private String carteira;
	
	@Column(name="sacado_endereco")
	private String sacadoEndereco;
	
	@Column(name="sacado_cidade")
	private String sacadoCidade;
	
	@Column(name="sacado_uf")
	private String sacadoUF;
	
	@Column(name="sacado_cep")
	private String sacadoCEP;
	
		
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
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

	public String getNomeConcurso() {
		return nomeConcurso;
	}

	public void setNomeConcurso(String nomeConcurso) {
		this.nomeConcurso = nomeConcurso;
	}

	public String getCedente() {
		return cedente;
	}

	public void setCedente(String cedente) {
		this.cedente = cedente;
	}

	public String getAgenciaConta() {
		return agenciaConta;
	}

	public void setAgenciaConta(String agenciaConta) {
		this.agenciaConta = agenciaConta;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getFuncaoCandidato() {
		return funcaoCandidato;
	}

	public void setFuncaoCandidato(String funcaoCandidato) {
		this.funcaoCandidato = funcaoCandidato;
	}

	public String getLotacaoCandidato() {
		return lotacaoCandidato;
	}

	public void setLotacaoCandidato(String lotacaoCandidato) {
		this.lotacaoCandidato = lotacaoCandidato;
	}

	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

	public String getSacadoEndereco() {
		return sacadoEndereco;
	}

	public void setSacadoEndereco(String sacadoEndereco) {
		this.sacadoEndereco = sacadoEndereco;
	}

	public String getSacadoCidade() {
		return sacadoCidade;
	}

	public void setSacadoCidade(String sacadoCidade) {
		this.sacadoCidade = sacadoCidade;
	}

	public String getSacadoUF() {
		return sacadoUF;
	}

	public void setSacadoUF(String sacadoUF) {
		this.sacadoUF = sacadoUF;
	}

	public String getSacadoCEP() {
		return sacadoCEP;
	}

	public void setSacadoCEP(String sacadoCEP) {
		this.sacadoCEP = sacadoCEP;
	}
	
}
