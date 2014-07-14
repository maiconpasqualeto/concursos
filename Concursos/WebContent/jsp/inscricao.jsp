<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="nested" uri="/WEB-INF/struts-nested.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>

<%@ page language="java" contentType="text/html; charset=iso-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
	<jsp:include page="include.jsp" flush="true" ></jsp:include>
	<html:javascript formName="inscricaoForm" />
	<script type="text/javascript">
		function buscarCep(){
			var cep = $("#cep").val();
			if ( (cep != '') && (cep.length == 8) ) {
				initProgress("#carregandoCep");
				$.get(
					"buscaCep.ajax?cep=" + $("#cep").val(),	
					{},
					function(xml){
						$(xml).find("webservicecep").each(
							function(){
								resultado = $(this).find("resultado").text();
								if (resultado == '1' || resultado == '2') {
									if ($(this).find("tipo_logradouro").text() != "")
										$("#endereco").val($(this).find("tipo_logradouro").text() + " " + $(this).find("logradouro").text());
									else 
										$("#endereco").val($(this).find("logradouro").text());
									$("#cidade").val($(this).find("cidade").text());
									$("#bairro").val($(this).find("bairro").text());
									$("#uf").val($(this).find("uf").text());
									//$("#codMunicipioIbge").val($(this).find("ibge_municipio").text());
									$("#bairro").removeAttr("readonly");
									$("#endereco").removeAttr("readonly");									
								} else {
									$("#endereco").val("");
									$("#cidade").val("");
									$("#bairro").val("");
									$("#uf").val("");
									//$("#codMunicipioIbge").val("");
									$("#bairro").attr("readonly", "true");
									$("#endereco").attr("readonly", "true");
									alert('CEP incorreto!');
								}
							}
						);
						resetProgress("#carregandoCep");
					},
					"xml"
				);
			} else {
				$("#endereco").val("");
								$("#cidade").val("");
								$("#bairro").val("");
								$("#uf").val("");
								//$("#codMunicipioIbge").val("");
								$("#bairro").attr("readonly", "true");
								$("#endereco").attr("readonly", "true");
			}
		}
		
		function mudaCargo(){
			$("#carregandoCargo").show();
			$("#lotacao").empty();
			if ($("#cargo").val() != '') {
				$.get(
					"listaLotacoes.ajax?idCargo=" + $("#cargo").val(),	
					{},
					function(xml){
						$(xml).find("item").each(
							function(){								
								$("#lotacao").append("<option value='"+ $(this).find("value").text() +"'>"+ $(this).find("name").text() +"</option>");
							}
						);
					},
					"xml"
				);
			}
			$("#carregandoCargo").fadeOut("slow");
		}
		function mudaLotacao(){
			$("#carregandoCargo").show();
			$("#funcao").empty();
			if ($("#lotacao").val() != '') {
				$.get(
					"listaFuncoes.ajax?idCargo=" + $("#cargo").val() + "&idLotacao=" + $("#lotacao").val(),	
					{},
					function(xml){
						$(xml).find("item").each(
							function(){								
								$("#funcao").append("<option value='"+ $(this).find("value").text() +"'>"+ $(this).find("name").text() +"</option>");
							}
						);
					},
					"xml"
				);
			}
			$("#carregandoCargo").fadeOut("slow");
		}
		function limpaCampoDeficiencia(){
			$("#deficiencia").val("");						
		}
	</script>
</head>
<body>

	<html:form action="/efetuaInscricao" focus="nome" styleClass="AppForm" onsubmit="return validateInscricaoForm(this);">
	<jsp:include page="infoConcurso.jsp" flush="true" ></jsp:include>
	<bean:define id="codConcurso">
		<bean:write name="codConcurso"/>
    </bean:define>
	<html:hidden property="codConcurso" value="<%=codConcurso%>" />
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td></td>
			<td width="200px">
				<table width="100%" border="1px" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td colspan="4">
							<table width="100%" border="1px" cellpadding="0" cellspacing="0" border="0">
								<jsp:include page="headerPrefeitura.jsp" flush="true" ></jsp:include>
							</table>
						</td>
					</tr>
					<tr align="center"><td colspan="4"><B><bean:write name="nomeConcurso"/></B></td></tr>					
					
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
							<label>Inscrição</label> 
							<html:text property="numeroInscricao" readonly="true"></html:text>					
						</TD>
						<TD colspan="3">
							<label>Cargo</label> 
							<html:select property="codCargo" styleId="cargo" onchange="mudaCargo();" >
								<html:option value="" />
								<html:options collection="cargos" 
									property="id" labelProperty="descricao"/>
							</html:select>
	                        <span id="carregandoCargo" style="display:none;">
                            	<img alt="Indicator" src="images/indicator.gif" />Carregando...</span>
						</TD>
					</TR>
					<TR>
						<TD  colspan="2">
							<label><bean:message key="label.lotacao"/></label> 
							<html:select property="codLotacao" styleId="lotacao" onchange="mudaLotacao();" >
								<html:option value="" />
								<html:options collection="lotacoes" 
									property="id" labelProperty="descricao"/>
							</html:select>				
						</TD>
						<TD  colspan="2">
							<label><bean:message key="label.funcao"/></label> 
							<html:select property="codFuncao" styleId="funcao">
								<html:option value="" />
								<html:options collection="funcoes" 
									property="id" labelProperty="descricao"/>
							</html:select>
						</TD>
					</TR>
					<TR>
						<TD  colspan="3">
							<label>Nome</label> 
							<html:text property="nome" size="80" readonly="true"></html:text>
						</TD>
						<TD>
							<label>Sexo</label> 
							<html:select property="sexo" >
								<html:option value="" />
								<html:option value="M">Masculino</html:option>
								<html:option value="F">Feminino</html:option>
							</html:select>
						</TD>
					</TR>
					<TR>
						<TD colspan="2">
							<label>CEP</label> 
							<html:text styleId="cep" property="cep" maxlength="8" onblur="buscarCep();"></html:text>
							<span id="carregandoCep" style="display:none;">
                            	<img alt="Indicator" src="images/indicator.gif" />Carregando endereço...</span>
						</TD>
						<TD colspan="2">
							<label>E-mail</label> 
							<html:text styleId="cadastroemail" property="cadastroemail" size="55" ></html:text>
						</TD>
					</TR>
					<TR>
						<TD  colspan="3">
							<label>Endereço</label> 
							<html:text styleId="endereco" property="endereco" size="70" maxlength="250" readonly="true"></html:text>
						</TD>
						<TD>
							<label>Número</label> 
							<html:text property="numero" size="15" maxlength="15"></html:text>
						</TD>
					</TR>
					<TR>
						<TD  colspan="3">
							<label>Bairro</label> 
							<html:text styleId="bairro" property="bairro" size="70" maxlength="50" readonly="true"></html:text>
						</TD>
						<TD>
							<label>Complemento</label> 
							<html:text property="complemento" maxlength="50"></html:text>
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
							<html:text property="ddd" size="2" maxlength="2"></html:text>
							<html:text property="telefone" size="10" maxlength="8"></html:text>
						</TD>
					</TR>
					<TR>
						<TD  colspan="4">
							<label>Nome do pai</label> 
							<html:text property="nomePai" size="100" maxlength="100"></html:text>
						</TD>
					</TR>
					<TR>
						<TD  colspan="4">
							<label>Nome da mãe</label> 
							<html:text property="nomeMae" size="100" maxlength="100"></html:text>
						</TD>
					</TR>
					<TR>
						<TD>
							<label>data Nascimento</label> 
							<html:text property="dataNascimentoStr" maxlength="10" onkeydown="FormataCampo(this, event, '##/##/####');"></html:text>
						</TD>
						<TD>
							<label>Local</label> 
							<html:text property="localNascimento" maxlength="100"></html:text>
						</TD>
						<TD>
							<label>UF Nascimento</label> 
							<html:select property="ufNascimento">
								<html:option value="" />
								<html:options collection="ufs" 
									property="uf" labelProperty="estado"/>
							</html:select>
						</TD>
						<TD>
							<label>Estado Civil</label> 
							<html:select property="codEstadoCivil">
								<html:option value="" />
								<html:options collection="estadosCivis" 
									property="id" labelProperty="descricao"/>
							</html:select>
						</TD>
					</TR>
					<TR>
						<TD colspan="1">
							<label>Possui Deficiência física</label>
							<html:radio property="possuiDeficiencia" value="false" onclick="limpaCampoDeficiencia();">Não</html:radio>
							<html:radio property="possuiDeficiencia" value="true">Sim</html:radio>
						</TD>
						<TD colspan="3">
							<label>Deficiência</label> 
							<html:text property="deficiencia" styleId="deficiencia" maxlength="150" size="90"></html:text>
						</TD>
					</TR>
					<TR>
						<TD>
							<label>CPF</label> 
							<html:text property="cpf" readonly="true"></html:text>
						</TD>
						<TD>
							<label>Tipo Documento</label> 
							<html:select property="identidadeTipo">
								<html:option value=""></html:option>
								<html:option value="Identidade Civil"></html:option>
								<html:option value="Identidade Militar"></html:option>
								<html:option value="Identidade Profissional"></html:option>
								<html:option value="Modelo 19"></html:option>
							</html:select>
						</TD>
						<TD>
							<label>Número Documento</label>
							<html:text property="identidadeNumero" maxlength="15"></html:text> 
						</TD>
						<TD>
							<label>Órgão Expedidor</label>
							<html:text property="identidadeOrgaoExpedidor" maxlength="20"></html:text> 
						</TD>
					</TR>
				</table>
				<table>
					<TR>
						<td>
							<html:submit property="acao" value="Confirmar"></html:submit>
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
