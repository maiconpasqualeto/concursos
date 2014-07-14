<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="nested" uri="/WEB-INF/struts-nested.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>

<%@ page language="java" contentType="text/html; charset=iso-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
	<jsp:include page="includeexcel.jsp" flush="true" ></jsp:include>
	<script language="javascript">
		self.print();		
	</script>
</head>
<body>
	<html:form styleClass="AppForm" action="/excelinscricao">
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td></td>
			<td width="650px">
				<table width="100%" border="1px" cellpadding="0" cellspacing="0" class="tabela" >
					<tr>
						<td align="center"><html:img src="images/brasao.gif"/></td>
						<td align="center" colspan="3">
							<table cellpadding="0" cellspacing="0" border="0" class="titulo" >
								<tr align="center"><td><B>PREFEITURA MUNICIPAL DE PONTA POR�</B></td></tr>
								<tr align="center"><td>Estado de Mato Grosso do Sul</td></tr>
								<tr align="center"><td>Comiss�o Municipal de Concurso P�blico</td></tr>						
							</table>
						</td>
					</tr>
					<tr align="center"><td colspan="4"><B>Concurso P�blico 01/2009 - FICHA DE INSCRI��O</B></td></tr>
					<TR>
						<TD>
							<label>Inscri��o</label> 
							<B><bean:write name="excelInscricaoForm" property="numeroInscricao" /></B>
						</TD>
						<TD colspan="3">
							<label>Cargo</label> 
							<bean:write name="excelInscricaoForm" property="descricaoCargo" />
						</TD>
					</TR>
					<TR>
						<TD  colspan="3">
							<label>Nome</label> 
							<bean:write name="excelInscricaoForm" property="nome" />
						</TD>
						<TD>
							<label>Sexo</label> 
							<logic:equal name="excelInscricaoForm" property="sexo" value="M">Masculino</logic:equal>
							<logic:equal name="excelInscricaoForm" property="sexo" value="F">Feminino</logic:equal>							
						</TD>
					</TR>
					<TR>
						<TD  colspan="3">
							<label>Endere�o</label> 
							<bean:write name="excelInscricaoForm" property="endereco" />
						</TD>
						<TD>
							<label>N�mero</label> 
							<bean:write name="excelInscricaoForm" property="numero" />
						</TD>
					</TR>
					<TR>
						<TD  colspan="3">
							<label>Bairro</label> 
							<bean:write name="excelInscricaoForm" property="bairro" />
						</TD>
						<TD>
							<label>Complemento</label> 
							<bean:write name="excelInscricaoForm" property="complemento" />
						</TD>
					</TR>
					<TR>
						<TD>
							<label>Cidade</label> 
							<bean:write name="excelInscricaoForm" property="cidade" />
						</TD>
						<TD>
							<label>UF</label> 
							<bean:write name="excelInscricaoForm" property="descricaoUf" />
						</TD>
						<TD>
							<label>CEP</label> 
							<bean:write name="excelInscricaoForm" property="cep" />
						</TD>
						<TD>
							<label>Telefone</label> 
							<bean:write name="excelInscricaoForm" property="telefone" />
						</TD>
					</TR>
					<TR>
						<TD>
							<label>data Nascimento</label> 
							<bean:write name="excelInscricaoForm" property="dataNascimentoStr" />
						</TD>
						<TD>
							<label>Local</label> 
							<bean:write name="excelInscricaoForm" property="localNascimento" />
						</TD>
						<TD>
							<label>UF Nascimento</label> 
							<bean:write name="excelInscricaoForm" property="descricaoUfNascimento" />
						</TD>
						<TD>
							<label>Estado Civil</label> 
							<bean:write name="excelInscricaoForm" property="descricaoEstadoCivil" />
						</TD>
					</TR>
					<TR>
						<TD colspan="4">
							<B>Portador de necessidades especiais?&nbsp</B>
							<bean:write name="excelInscricaoForm" property="possuiDeficiencia" />
						</TD>												
					</TR>
					<logic:equal name="excelInscricaoForm" property="possuiDeficiencia" value="Sim">
						<TR>
							<TD colspan="4">
								<label>Qual?</label> 
								<bean:write name="excelInscricaoForm" property="deficiencia" />
							</TD>
						</TR>
					</logic:equal>
					<TR>
						<TD colspan="4">(Em caso afirmativo, anexar Laudo M�dico, atestando a esp�cie e o grau ou n�vel da defici�ncia, com expressa refer�ncia ao c�digo correspondente da Classifica��o Internacional de Doen�a - CID bem como a prov�vel causa da defici�ncia).</TD>
					</TR>
					<TR>
						<TD>
							<label>CPF</label> 
							<bean:write name="excelInscricaoForm" property="cpf" />
							<html:hidden property="cpf"/>
						</TD>
						<TD>
							<label>Tipo Documento</label> 
							<bean:write name="excelInscricaoForm" property="identidadeTipo" />
						</TD>
						<TD>
							<label>N. Documento</label>
							<bean:write name="excelInscricaoForm" property="identidadeNumero" /> 
						</TD>
						<TD>
							<label>�rg�o Expedidor</label>
							<bean:write name="excelInscricaoForm" property="identidadeOrgaoExpedidor" /> 
						</TD>
					</TR>
					<TR>
						<TD colspan="4">
							<table cellpadding="0" cellspacing="0" border="0" class="texto" >				
								<TR>
									<TD>1) O Requerente declara possuir todos os requisitos exigidos para provimento do cargo</TD>
								</TR>
								<TR style="border: 0px;">
									<TD>2) Declara conhecer e aceitar todas as condi��es estabelecidas no edital de concurso n� 01/2009 e no regulamento geral.</TD>
								</TR>
								<TR>
									<TD align="center">Local e Data: ___________________________, ____ de ________________ de 2009.</TD>
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
								<tr align="center" ><td><B>PREFEITURA MUNICIPAL DE PONTA POR�</B></td></tr>
								<tr align="center"><td>Estado de Mato Grosso do Sul</td></tr>
								<tr align="center"><td>Comiss�o Municipal de Concurso P�blico</td></tr>						
							</table>
						</td>
					</tr>
					<tr align="center"><td colspan="4"><B>Concurso P�blico 01/2009 - comprovante de inscri��o</B></td></tr>
					<TR>
						<TD>
							Inscri��o n�: <B><bean:write name="excelInscricaoForm" property="numeroInscricao" /></B>
						</TD>
						<TD colspan="3">
							Cargo: <bean:write name="excelInscricaoForm" property="descricaoCargo" />
						</TD>
					</TR>
					<TR>
						<TD colspan="4">
							Nome: <bean:write name="excelInscricaoForm" property="nome" />
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