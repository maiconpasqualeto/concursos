<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="nested" uri="/WEB-INF/struts-nested.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>

<%@ page language="java" contentType="text/html; charset=iso-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
	<jsp:include page="includeexcel.jsp" flush="true" ></jsp:include>
</head>
<body>
	<html:form styleClass="AppForm" action="/excelinscricao">
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td></td>
			<td width="650px">
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
					<tr align="center"><td colspan="4"><B>Concurso Público 01/2009 - CONFIRMAÇÃO DA INSCRIÇÃO</B></td></tr>
					<tr>
						<td colspan="4"><b>
							<span style="color: red;">							
								Confirmada sua inscrição para o concurso 01/2009 com base no Decreto Municipal nº 5.420 de 26 de março de 2009.<br>
								Confira se o seu nome constará no edital de homologação das inscrições para o Concurso 01/2009, que será publicado após o término das inscrições.
								Fique atento às publicações e prazo de recurso.
							</span> </b>
						</td>
					</tr>
					<TR>						
						<TD colspan="3">
							<label>Cargo</label> 
							<bean:write name="excelInscricaoForm" property="descricaoCargo" />
						</TD>
						<TD>
							<b><label>Inscrição</label></b>
							<b><bean:write name="excelInscricaoForm" property="numeroInscricao" /></b>
							<html:hidden property="numeroInscricao" styleId="inscricao"/>
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
							<label>Endereço</label> 
							<bean:write name="excelInscricaoForm" property="endereco" />
						</TD>
						<TD>
							<label>Número</label> 
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
						<TD colspan="2">
							<label>É portador de necessidades especiais</label>
							<bean:write name="excelInscricaoForm" property="possuiDeficiencia" />
						</TD>
						<TD colspan="2">
							<label>Necessidade Especial</label> 
							<bean:write name="excelInscricaoForm" property="deficiencia" />
						</TD>
					</TR>
					<TR>
						<TD>
							<label>CPF</label> 
							<bean:write name="excelInscricaoForm" property="cpf" />
							<html:hidden styleId="cpf" property="cpf" />
						</TD>
						<TD>
							<label>Tipo Documento</label> 
							<bean:write name="excelInscricaoForm" property="identidadeTipo" />
						</TD>
						<TD>
							<label>Número Documento</label>
							<bean:write name="excelInscricaoForm" property="identidadeNumero" /> 
						</TD>
						<TD>
							<label>Órgão Expedidor</label>
							<bean:write name="excelInscricaoForm" property="identidadeOrgaoExpedidor" /> 
							<bean:write name="excelInscricaoForm" property="identidadeUf" />
						</TD>
					</TR>
				</table>
				<table>
					<TR>
						<td>
							<input type="button" value="Imprimir" name="btnimprimir"
								onclick="CallPrint('excelinscricao.do?acao=ImprimirConfirmacao&inscricao=');" />
							<html:cancel value="Voltar" property="acao"></html:cancel>											
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