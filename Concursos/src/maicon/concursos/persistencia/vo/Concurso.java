/**
 * 
 */
package maicon.concursos.persistencia.vo;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Maicon
 *
 */
@Entity
@Table(name="concurso", schema="concursos")
public class Concurso {
	
	@Id
	@SequenceGenerator(name="seqConcurso", sequenceName="concurso_cod_concurso_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqConcurso")
	@Column(name="cod_concurso")
	private Integer id;
	
	@Column(name="concurso")
	private String descricao;
	
	@Column(name="prefeitura")
	private String prefeitura;
	
	@Column(name="situacao")
	private String situacao;
	
	@Column(name="tem_senha")
	private String temSenha;
	
	@Column(name="senha")
	private String senha;
	
	@Column(name="pagamento_nome")
	private String pagamentoNome;
	
	@Column(name="pagamento_banco")
	private String pagamentoBanco;
	
	@Column(name="pagamento_agencia")
	private String pagamentoAgencia;
	
	@Column(name="pagamento_conta")
	private String pagamentoConta;
	
	@Column(name="ultimo_num_inscricao")
	private Integer ultimoNumeroInscricao;
	
	@Column(name="cedente")
	private String cedente;
	
	@Column(name="cnpj")
	private String cnpj;
	
	@Column(name="numero_convenio")
	private String numeroConvenio;
	
	@Column(name="data_vencimento")
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	@Column(name="carteira")
	private String carteira;
	
	@ManyToMany(targetEntity=Candidato.class, mappedBy="concurso", fetch=FetchType.LAZY)
	private List<Candidato> candidatos;	
	
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

	public String getPrefeitura() {
		return prefeitura;
	}

	public void setPrefeitura(String prefeitura) {
		this.prefeitura = prefeitura;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTemSenha() {
		return temSenha;
	}

	public void setTemSenha(String temSenha) {
		this.temSenha = temSenha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPagamentoNome() {
		return pagamentoNome;
	}

	public void setPagamentoNome(String pagamentoNome) {
		this.pagamentoNome = pagamentoNome;
	}

	public String getPagamentoBanco() {
		return pagamentoBanco;
	}

	public void setPagamentoBanco(String pagamentoBanco) {
		this.pagamentoBanco = pagamentoBanco;
	}

	public String getPagamentoAgencia() {
		return pagamentoAgencia;
	}

	public void setPagamentoAgencia(String pagamentoAgencia) {
		this.pagamentoAgencia = pagamentoAgencia;
	}

	public String getPagamentoConta() {
		return pagamentoConta;
	}

	public void setPagamentoConta(String pagamentoConta) {
		this.pagamentoConta = pagamentoConta;
	}

	public Integer getUltimoNumeroInscricao() {
		return ultimoNumeroInscricao;
	}

	public void setUltimoNumeroInscricao(Integer ultimoNumeroInscricao) {
		this.ultimoNumeroInscricao = ultimoNumeroInscricao;
	}

	public String getCedente() {
		return cedente;
	}

	public void setCedente(String cedente) {
		this.cedente = cedente;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}

	public String getNumeroConvenio() {
		return numeroConvenio;
	}

	public void setNumeroConvenio(String numeroConvenio) {
		this.numeroConvenio = numeroConvenio;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}
	
}
