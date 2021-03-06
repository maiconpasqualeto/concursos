/**
 * 
 */
package maicon.excel.persistencia.vo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Maicon
 *
 */
@Entity
@Table(name="candidato", schema="excel")
public class ExCandidato {

	@Id
	@SequenceGenerator(name="seqCandidato", sequenceName="candidato_cod_candidato_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqCandidato")
	@Column(name="cod_candidato")
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cpf")
	private String cpf;
	
	@Column(name="numero_inscricao")
	private String numeroInscricao;
	
	@Column(name="endereco")
	private String endereco;
	
	@Column(name="end_numero")
	private String numeroEndereco;
	
	@Column(name="bairro")
	private String bairro;
	
	@Column(name="complemento")
	private String complemento;
	
	@Column(name="cidade")
	private String cidade;
	
	@Column(name="uf")
	private String uf;
	
	@Column(name="cep")
	private String cep;
	
	@Column(name="telefone_ddd")
	private String telefoneDDD;
	
	@Column(name="telefone")
	private String telefone;
	
	@Column(name="data_nascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column(name="local_nascimento")
	private String localNascimento;
	
	@Column(name="uf_nascimento")
	private String ufNascimento;
	
	@Column(name="possui_deficiencia")
	private Boolean possuiDeficiencia;
	
	@Column(name="deficiencia")
	private String deficiencia;
	
	@Column(name="ident_tipo")
	private String identidadeTipo;
	
	@Column(name="ident_numero")
	private String identidadeNumero;
	
	@Column(name="ident_orgao_expedidor")
	private String identidadeOrgaoExpedidor;
	
	@Column(name="ident_uf")
	private String identidadeUf;
	
	@Column(name="email")
	private String email;
	
	@Column(name="senha")
	private String senha;
	
	@Column(name="sexo")
	private String sexo;
	
	@ManyToOne (targetEntity = ExConcurso.class)
	@JoinColumn (name = "cod_concurso")
	private ExConcurso concurso;
	
	@ManyToOne (targetEntity = ExCargo.class)
	@JoinColumn (name = "cod_cargo")
	private ExCargo cargo;
		
	@ManyToOne (targetEntity = ExEstadoCivil.class)
	@JoinColumn (name = "cod_estado_civil")
	private ExEstadoCivil estadoCivil;
	
	@Column(name="cod_municipio_ibge")
	private String codMunicipioIbge;
	
	@Column(name="cod_uf_ibge")
	private String codUfIbge;
	
	@Column(name="inscricao_2008")
	private String inscricao2008;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumeroInscricao() {
		return numeroInscricao;
	}

	public void setNumeroInscricao(String numeroInscricao) {
		this.numeroInscricao = numeroInscricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefoneDDD() {
		return telefoneDDD;
	}

	public void setTelefoneDDD(String telefoneDDD) {
		this.telefoneDDD = telefoneDDD;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getLocalNascimento() {
		return localNascimento;
	}

	public void setLocalNascimento(String localNascimento) {
		this.localNascimento = localNascimento;
	}

	public String getUfNascimento() {
		return ufNascimento;
	}

	public void setUfNascimento(String ufNascimento) {
		this.ufNascimento = ufNascimento;
	}

	public Boolean getPossuiDeficiencia() {
		return possuiDeficiencia;
	}

	public void setPossuiDeficiencia(Boolean possuiDeficiencia) {
		this.possuiDeficiencia = possuiDeficiencia;
	}
	
	public String getPossuiDeficienciaStr() {
		if (possuiDeficiencia)
			return "Sim";
		return "N�o";
	}

	public void setPossuiDeficienciaStr(String possuiDeficienciaStr) {
		if (possuiDeficienciaStr != null && !possuiDeficienciaStr.equals("")){
			if (possuiDeficienciaStr.equals("Sim"))
				this.possuiDeficiencia = true;
			else 
				this.possuiDeficiencia = false;
		}		
	}

	public String getDeficiencia() {
		return deficiencia;
	}

	public void setDeficiencia(String deficiencia) {
		this.deficiencia = deficiencia;
	}

	public String getIdentidadeTipo() {
		return identidadeTipo;
	}

	public void setIdentidadeTipo(String identidadeTipo) {
		this.identidadeTipo = identidadeTipo;
	}

	public String getIdentidadeNumero() {
		return identidadeNumero;
	}

	public void setIdentidadeNumero(String identidadeNumero) {
		this.identidadeNumero = identidadeNumero;
	}

	public ExConcurso getConcurso() {
		return concurso;
	}

	public void setConcurso(ExConcurso concurso) {
		this.concurso = concurso;
	}

	public ExCargo getCargo() {
		return cargo;
	}

	public void setCargo(ExCargo cargo) {
		this.cargo = cargo;
	}

	public ExEstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(ExEstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	/**
	 * @return the identidadeOrgaoExpedidor
	 */
	public String getIdentidadeOrgaoExpedidor() {
		return identidadeOrgaoExpedidor;
	}

	/**
	 * @param identidadeOrgaoExpedidor the identidadeOrgaoExpedidor to set
	 */
	public void setIdentidadeOrgaoExpedidor(String identidadeOrgaoExpedidor) {
		this.identidadeOrgaoExpedidor = identidadeOrgaoExpedidor;
	}
	
	private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	private transient String dataNascimentoStr;
	private transient String identidadeExpedicaoStr;
	
	
	/**
	 * @return the dataNascimentoStr
	 */
	public String getDataNascimentoStr() {
		Date data = getDataNascimento();		
		if (data != null){
			dataNascimentoStr = df.format(data);
		}
		return dataNascimentoStr;
	}

	/**
	 * @param dataNascimentoStr the dataNascimentoStr to set
	 */
	public void setDataNascimentoStr(String dataNascimentoStr) {
		if (dataNascimentoStr != null && (!dataNascimentoStr.equals(""))){
			try {
				setDataNascimento(df.parse(dataNascimentoStr));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the identidadeUf
	 */
	public String getIdentidadeUf() {
		return identidadeUf;
	}

	/**
	 * @param identidadeUf the identidadeUf to set
	 */
	public void setIdentidadeUf(String identidadeUf) {
		this.identidadeUf = identidadeUf;
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

	/**
	 * @return the codUfIbge
	 */
	public String getCodUfIbge() {
		return codUfIbge;
	}

	/**
	 * @param codUfIbge the codUfIbge to set
	 */
	public void setCodUfIbge(String codUfIbge) {
		this.codUfIbge = codUfIbge;
	}

	/**
	 * @return the identidadeExpedicaoStr
	 */
	public String getIdentidadeExpedicaoStr() {
		return identidadeExpedicaoStr;
	}

	/**
	 * @param identidadeExpedicaoStr the identidadeExpedicaoStr to set
	 */
	public void setIdentidadeExpedicaoStr(String identidadeExpedicaoStr) {
		this.identidadeExpedicaoStr = identidadeExpedicaoStr;
	}

	public String getInscricao2008() {
		return inscricao2008;
	}

	public void setInscricao2008(String inscricao2008) {
		this.inscricao2008 = inscricao2008;
	}	
	
	
}
