<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="nested" uri="/WEB-INF/struts-nested.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>

<%@ page language="java" contentType="text/html; charset=iso-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
	<jsp:include page="include.jsp" flush="true" ></jsp:include>
	<html:javascript formName="cadastroForm" />
	</head>
<body>	
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="200px"></td>
			<td width="400px">
				<table width="100%" border="1px" cellpadding="0" cellspacing="0">
					<tr>
						<jsp:include page="headerPrefeitura.jsp" flush="true" ></jsp:include>
					</tr>
					<logic:messagesPresent message="true">
						<tr>
							<td colspan="2">
								<html:messages id="mensagem" message="true" name="ms">
									<span style="color: red;"><bean:write name="mensagem"/></span>
								</html:messages>
							</td>
						</tr>
					</logic:messagesPresent>
					<tr>
						<td id="colunaEsquerda" valign="top" colspan="2" align="center">
							<html:form action="/cadastro" styleClass="AppForm" focus="nome" onsubmit="return validateCadastroForm(this);" >
								<jsp:include page="infoConcurso.jsp" flush="true" ></jsp:include>
								<bean:define id="codConcurso">
									<bean:write name="codConcurso"/>
						        </bean:define>
								<html:hidden property="codConcurso" value="<%=codConcurso%>" />
								<table>
									<tr>
										<td><B>Informe o NOME e CPF para fazer a inscrição.</B></td>
									</tr>
									<tr>
										<td>
											<label>Nome</label>
											<html:text property="nome" maxlength="250" size="70"></html:text>
										</td>
									</tr>
									<tr>
										<td>
											<label>Cpf</label>
											<html:text property="cpf" maxlength="11"></html:text>
										</td>
									</tr>
									<tr>
										<td>
											<html:submit value="Confirma" property="acao" ></html:submit>
										</td>
									</tr>
								</table>
							</html:form>
						</td>									
					</tr>
				</table>
			</td>
			<td width="200px"></td>
		</tr>
	</table>
</body>
</html:html>