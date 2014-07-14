<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="nested" uri="/WEB-INF/struts-nested.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>

<%@ page language="java" contentType="text/html; charset=iso-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
	<jsp:include page="includeexcel.jsp" flush="true" ></jsp:include>
	<html:javascript formName="excelInscricaoForm" />	
	<script type="text/javascript">
	function mudaCampoNecessidade(){
		if ($("#possuiDeficiencia").val() != "Sim"){
			$("#deficiencia").val("");
			$("#deficiencia").attr("readonly", "true");
		} else {
			$("#deficiencia").removeAttr("readonly");
		}
	}
	
	function limparCampos(){
		$("#cargo").val("");
		$("#sexo").val("");
		$("#cep").val("");
		$("#endereco").val("");
		$("#cidade").val("");
		$("#bairro").val("");
		$("#uf").val("");
		$("#codMunicipioIbge").val("");
		$("#bairro").attr("readonly", "true");
		$("#endereco").attr("readonly", "true");
		$("#numero").val("");
		$("#complemento").val("");
		$("#ddd").val("");
		$("#telefone").val("");
		$("#dataNascimentoStr").val("");
		$("#localNascimento").val("");
		$("#identidadeNumero").val("");
		$("#orgaoExpedidor").val("");
		$("#ufNascimento").val("");
		$("#codEstadoCivil").val("");
		$("#identidadeTipo").val("");
		$("#possuiDeficiencia").val("");
		$("#deficiencia").val("");
		$("#identidadeUf").val("");
	}
	</script>
</head>
<body>

	<html:form action="/excelinscricao" focus="nome" styleClass="AppForm" onsubmit="return validateExcelInscricaoForm(this);">
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td></td>
			<td width="200px">
				<table width="100%" border="1px" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td align="center"><html:img src="images/brasao.gif"/></td>
						<td align="center" colspan="3">
							<table>
								<tr align="center"><td><B>PREFEITURA MUNICIPAL DE PONTA PORÃ</B></td></tr>
								<tr align="center"><td>Estado de Mato Grosso do Sul</td></tr>
								<tr align="center"><td>Comissão Municipal de Concurso Público</td></tr>						
							</table>
						</td>
					</tr>
					<tr align="center"><td colspan="4"><B>Concurso Público 01/2009 - FICHA DE INSCRIÇÃO</B></td></tr>					
					<html:hidden property="email"/>
					<logic:messagesPresent message="true">
						<tr>
							<td colspan="4">								
								<html:messages id="mensagem" message="true" name="ms">
									<span style="color: red;"><bean:write name="mensagem"/></span>
								</html:messages>
							</td>
						</tr>
					</logic:messagesPresent>
					<TR>
						<TD>
							<label>CPF</label> 
							<html:text property="cpf" readonly="true"></html:text>
						</TD>
						<TD colspan="3">
							<label>Cargo</label> 
							<html:select property="codCargo" styleId="cargo" >
								<html:option value="" />
								<html:options collection="cargos" 
									property="id" labelProperty="descricao"/>
							</html:select>
	                        <span id="carregandoCargo" style="display:none;">
                            	<img alt="Indicator" src="images/indicator.gif" />Carregando...</span>
						</TD>
					</TR>					
					<TR>
						<TD  colspan="3">
							<label>Nome</label> 
							<html:text property="nome" size="80" readonly="true"></html:text>
						</TD>
						<TD>
							<label>Sexo</label> 
							<html:select property="sexo" styleId="sexo">
								<html:option value="" />
								<html:option value="M">Masculino</html:option>
								<html:option value="F">Feminino</html:option>
							</html:select>
						</TD>
					</TR>
					<TR>
						<TD colspan="4">
							<label>CEP</label> 
							<html:text styleId="cep" property="cep" maxlength="8" onblur="buscarCep();"></html:text>
							<span id="carregandoCep" style="display:none;">
                            	<img alt="Indicator" src="images/indicator.gif" />Carregando endereço...</span>
                            <html:hidden styleId="codMunicipioIbge" property="codMunicipioIbge" />
						</TD>
					</TR>
					<TR>
						<TD  colspan="3">
							<label>Endereço</label> 
							<html:text styleId="endereco" property="endereco" size="70" readonly="true" maxlength="250"></html:text>
						</TD>
						<TD>
							<label>Número</label> 
							<html:text property="numero" size="15" styleId="numero" maxlength="15"></html:text>
						</TD>
					</TR>
					<TR>
						<TD  colspan="3">
							<label>Bairro</label> 
							<html:text styleId="bairro" property="bairro" size="70" readonly="true" maxlength="100"></html:text>
						</TD>
						<TD>
							<label>Complemento</label> 
							<html:text property="complemento" styleId="complemento" maxlength="50"></html:text>
						</TD>
					</TR>
					<TR>
						<TD colspan="2">
							<label>Cidade</label> 
							<html:text styleId="cidade" property="cidade" size="50" readonly="true"></html:text>
						</TD>
						<TD>
							<label>UF</label> 
							<html:text styleId="uf" property="uf" readonly="true"></html:text>							
						</TD>						
						<TD>
							<label>Telefone</label>
							<html:text property="ddd" size="2" maxlength="2" styleId="ddd"></html:text>
							<html:text property="telefone" size="10" maxlength="8" styleId="telefone" ></html:text>
						</TD>
					</TR>
					<TR>
						<TD>
							<label>data Nascimento</label> 
							<html:text property="dataNascimentoStr" styleId="dataNascimentoStr" 
								maxlength="10" onkeydown="FormataCampo(this, event, '##/##/####');"></html:text>
						</TD>
						<TD>
							<label>Local</label> 
							<html:text property="localNascimento" styleId="localNascimento" maxlength="100"></html:text>
						</TD>
						<TD>
							<label>UF Nascimento</label> 
							<html:select property="ufNascimento" styleId="ufNascimento">
								<html:option value="" />
								<html:options collection="ufs" 
									property="uf" labelProperty="estado"/>
							</html:select>
						</TD>
						<TD>
							<label>Estado Civil</label> 
							<html:select property="codEstadoCivil" styleId="codEstadoCivil">
								<html:option value="" />
								<html:options collection="estadosCivis" 
									property="id" labelProperty="descricao"/>
							</html:select>
						</TD>
					</TR>
					<TR>
						<TD colspan="1">
							<label>Portador de necesidades especiais</label>
							<html:select property="possuiDeficiencia" styleId="possuiDeficiencia" onchange="mudaCampoNecessidade();">
								<html:option value="" />
								<html:option value="Sim" />
								<html:option value="Não" />
							</html:select>
						</TD>
						<TD colspan="3">
							<label>Necessidade Especial</label> 
							<html:text property="deficiencia" styleId="deficiencia" maxlength="150" size="90" readonly="true"></html:text>
						</TD>
					</TR>
					<TR>						
						<TD>
							<label>Tipo Documento</label> 
							<html:select property="identidadeTipo" styleId="identidadeTipo">
								<html:option value=""></html:option>
								<html:option value="Identidade Civil"></html:option>
								<html:option value="Identidade Militar"></html:option>
								<html:option value="Identidade Profissional"></html:option>
								<html:option value="Modelo 19"></html:option>
							</html:select>
						</TD>
						<TD>
							<label>Número Documento</label>
							<html:text property="identidadeNumero" styleId="identidadeNumero" maxlength="15"></html:text> 
						</TD>
						<TD>
							<label>Órgão Expedidor</label>
							<html:text property="identidadeOrgaoExpedidor" styleId="orgaoExpedidor" maxlength="20"></html:text> 
						</TD>
						<TD>
							<label>UF Órgão Expedidor</label>
							<html:select property="identidadeUf" styleId="identidadeUf">
								<html:option value="" />
								<html:options collection="ufs" 
									property="uf" labelProperty="uf"/>
							</html:select>
						</TD>
					</TR>
				</table>
				<table>
					<TR>
						<td>
							<html:submit property="acao" value="Salvar" onclick="return confirm('Você confirma os dados para sua inscrição no Concurso 01/2009?');"></html:submit>
							<html:cancel property="acao" value="Voltar"></html:cancel>
							<input id="btnLimpar" name="btnLimpar" onclick="limparCampos();" type="button" value="Limpar" />
						</td>
					</TR>
				</table>
			</td>
			<td></td>
		</tr>
	</table>
	</html:form>
</body>
</html:html>