<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="nested" uri="/WEB-INF/struts-nested.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>


<%@ page language="java" contentType="text/html; charset=iso-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
	<jsp:include page="include.jsp" flush="true" ></jsp:include>
	<html:javascript formName="senhaForm" />
</head>
<body>
<html:form action="/senha" focus="usuario" styleClass="AppForm" onsubmit="return validateSenhaForm(this);">
<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td></td>
			<td width="700px">
				<table width="100%" border="1px" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td colspan="4">
							<table width="100%" border="1px" cellpadding="0" cellspacing="0" border="0">
								<jsp:include page="headerPrefeitura.jsp" flush="true" ></jsp:include>
							</table>
						</td>
					</tr>
					<tr align="center"><td colspan="4"><B><bean:write name="nomeConcurso"/></B></td></tr>					
					
					<TR align="center"><TD><B>LOGIN</B></TD></TR>					
						<logic:messagesPresent message="true">
							<tr align="center">
								<td>
									<html:messages id="mensagem" message="true" name="ms">
										<span style="color: red;"><bean:write name="mensagem"/></span>
									</html:messages>
								</td>
							</tr>
						</logic:messagesPresent>
							<bean:define id="codConcurso">
								<bean:write name="codConcurso"/>
						    </bean:define>
						<html:hidden property="codConcurso" value="<%=codConcurso%>" />
						<TR align="center">
							<TD>			
								<label>Senha:</label> 
								<html:password property="senha"></html:password>				
							</TD>		
						</TR>
						<TR align="center">
							<TD>
								<html:submit property="acao">Entrar</html:submit>
								<html:cancel property="acao" value="Voltar"></html:cancel>	
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