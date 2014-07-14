<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="nested" uri="/WEB-INF/struts-nested.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>

<%@ page language="java" contentType="text/html; charset=iso-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
	<jsp:include page="include.jsp" flush="true" ></jsp:include>
	<script language="javascript">
		self.print();		
	</script>
</head>
<body>
	<html:form styleClass="impressao" action="/efetuaInscricao">
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td></td>
			<td width="650px">
				<table width="100%" border="1px" cellpadding="0" cellspacing="0" class="tabela" >
					<tr>
						<td align="center" colspan="4">
							<table cellpadding="0" cellspacing="0" border="0" class="titulo" >
								<tr align="center" ><td><B><bean:write name="prefeitura"/></B></td></tr>
								<tr align="center"><td>Estado de Mato Grosso do Sul</td></tr>
								<tr align="center"><td>Comissão Municipal de Concurso Público</td></tr>						
							</table>
						</td>
					</tr>
					<tr align="center"><td colspan="4"><B><bean:write name="nomeConcurso"/></B></td></tr>
					<TR>
						<TD>
							<label>Inscrição</label> 
							<B><bean:write name="inscricaoForm" property="numeroInscricao" /></B>
						</TD>
						<TD colspan="3">
							<label>Cargo</label> 
							<bean:write name="inscricaoForm" property="descricaoCargo" />
						</TD>
					</TR>
					<TR>
						<TD  colspan="2">
							<label><bean:message key="label.lotacao"/></label> 
							<bean:write name="inscricaoForm" property="descricaoLotacao" />
						</TD>
						<TD  colspan="2">
							<label><bean:message key="label.funcao"/></label> 
							<bean:write name="inscricaoForm" property="descricaoFuncao" />
						</TD>
					</TR>
					<TR>
						<TD  colspan="3">
							<label>Nome</label> 
							<bean:write name="inscricaoForm" property="nome" />
						</TD>
						<TD>
							<label>Sexo</label> 
							<logic:equal name="inscricaoForm" property="sexo" value="M">Masculino</logic:equal>
							<logic:equal name="inscricaoForm" property="sexo" value="F">Feminino</logic:equal>							
						</TD>
					</TR>
					<TR>
						<TD  colspan="3">
							<label>Endereço</label> 
							<bean:write name="inscricaoForm" property="endereco" />
						</TD>
						<TD>
							<label>Número</label> 
							<bean:write name="inscricaoForm" property="numero" />
						</TD>
					</TR>
					<TR>
						<TD  colspan="2">
							<label>Bairro</label> 
							<bean:write name="inscricaoForm" property="bairro" />
						</TD>
						<TD>
							<label>E-mail</label> 
							<bean:write name="inscricaoForm" property="cadastroemail" />
						</TD>
						<TD>
							<label>Complemento</label> 
							<bean:write name="inscricaoForm" property="complemento" />
						</TD>
					</TR>
					<TR>
						<TD>
							<label>Cidade</label> 
							<bean:write name="inscricaoForm" property="cidade" />
						</TD>
						<TD>
							<label>UF</label> 
							<bean:write name="inscricaoForm" property="descricaoUf" />
						</TD>
						<TD>
							<label>CEP</label> 
							<bean:write name="inscricaoForm" property="cep" />
						</TD>
						<TD>
							<label>Telefone</label> 
							<bean:write name="inscricaoForm" property="telefone" />
						</TD>
					</TR>
					<TR>
						<TD  colspan="4">
							<label>Nome do pai</label> 
							<bean:write name="inscricaoForm" property="nomePai" />
						</TD>
					</TR>
					<TR>
						<TD  colspan="4">
							<label>Nome da mãe</label> 
							<bean:write name="inscricaoForm" property="nomeMae" />
						</TD>
					</TR>
					<TR>
						<TD>
							<label>data Nascimento</label> 
							<bean:write name="inscricaoForm" property="dataNascimentoStr" />
						</TD>
						<TD>
							<label>Local</label> 
							<bean:write name="inscricaoForm" property="localNascimento" />
						</TD>
						<TD>
							<label>UF Nascimento</label> 
							<bean:write name="inscricaoForm" property="descricaoUfNascimento" />
						</TD>
						<TD>
							<label>Estado Civil</label> 
							<bean:write name="inscricaoForm" property="descricaoEstadoCivil" />
						</TD>
					</TR>
					<TR>
						<TD colspan="4">
							<B>Possui alguma deficiência física?&nbsp</B>
							<logic:equal name="inscricaoForm" property="possuiDeficiencia" value="true">Sim</logic:equal>
							<logic:equal name="inscricaoForm" property="possuiDeficiencia" value="false">Não</logic:equal>
						</TD>												
					</TR>
					<logic:equal name="inscricaoForm" property="possuiDeficiencia" value="true">
						<TR>
							<TD colspan="4">
								<label>Qual?</label> 
								<bean:write name="inscricaoForm" property="deficiencia" />
							</TD>
						</TR>
					</logic:equal>
					<TR>
						<TD colspan="4">(Em caso afirmativo, anexar Laudo Médico, atestando a espécie e o grau ou nível da deficiência, com expressa referência ao código correspondente da Classificação Internacional de Doença - CID bem como a provável causa da deficiência).</TD>
					</TR>
					<TR>
						<TD>
							<label>CPF</label> 
							<bean:write name="inscricaoForm" property="cpf" />
							<html:hidden property="cpf"/>
						</TD>
						<TD>
							<label>Tipo Documento</label> 
							<bean:write name="inscricaoForm" property="identidadeTipo" />
						</TD>
						<TD>
							<label>N. Documento</label>
							<bean:write name="inscricaoForm" property="identidadeNumero" /> 
						</TD>
						<TD>
							<label>Órgão Expedidor</label>
							<bean:write name="inscricaoForm" property="identidadeOrgaoExpedidor" /> 
						</TD>
					</TR>
					<TR>
						<TD colspan="4">
							<table cellpadding="0" cellspacing="0" border="0" class="texto" >				
								<TR>
									<TD>1) O Requerente declara possuir todos os requisitos exigidos para provimento do cargo</TD>
								</TR>
								<TR style="border: 0px;">
									<TD>2) Declara conhecer e aceitar todas as condições estabelecidas no edital.</TD>
								</TR>
								<TR style="border: 0px;">
									<TD>Pagamento: Depósito bancário identificado em nome de 
										<bean:write name="inscricaoForm" property="pagamentoNome"/>, no banco 
										<bean:write name="inscricaoForm" property="pagamentoBanco"/>, agencia
										<bean:write name="inscricaoForm" property="pagamentoAgencia"/>, na conta
										<bean:write name="inscricaoForm" property="pagamentoConta"/>.
								    </TD>
								</TR>
								<TR>
									<TD align="center">Local e Data: ___________________________, ____ de ________________ de <bean:write name="anoConcurso"/>.</TD>
								</TR>
								<TR>
									<TD align="center" valign="bottom">________________________________</TD>
								</TR>
								<TR>
									<TD align="center" valign="top">Assinatura do Candidato</TD>
								</TR>
							</table>
						</TD>
					</TR>
					<tr>
						<td align="center" colspan="4">
							<table cellpadding="0" cellspacing="0" border="0" class="titulo" >
								<tr align="center" ><td><B><bean:write name="prefeitura"/></B></td></tr>
								<tr align="center"><td>Estado de Mato Grosso do Sul</td></tr>
								<tr align="center"><td>Comissão Municipal de Concurso Público</td></tr>						
							</table>
						</td>
					</tr>
					<tr align="center"><td colspan="4"><B><bean:write name="nomeConcurso"/></B></td></tr>
					<TR>
						<TD>
							Inscrição nº: <B><bean:write name="inscricaoForm" property="numeroInscricao" /></B>
						</TD>
						<TD colspan="3">
							Cargo: <bean:write name="inscricaoForm" property="descricaoCargo" />
						</TD>
					</TR>
					<TR>
						<TD colspan="2">
							<bean:message key="label.lotacao"/>: <bean:write name="inscricaoForm" property="descricaoLotacao" />
						</TD>
						<TD colspan="2">
							<bean:message key="label.funcao"/>: <bean:write name="inscricaoForm" property="descricaoFuncao" />
						</TD>
					</TR>
					<TR>
						<TD colspan="4">
							Nome: <bean:write name="inscricaoForm" property="nome" />
						</TD>
					</TR>
				</table>				
			</td>
			<td></td>
		</tr>
	</table>
	</html:form>
</body>
</html:html>
