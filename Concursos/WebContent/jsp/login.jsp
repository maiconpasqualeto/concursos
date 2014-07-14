<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="nested" uri="/WEB-INF/struts-nested.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>


<%@ page language="java" contentType="text/html; charset=iso-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
	<jsp:include page="include.jsp" flush="true" ></jsp:include>
	<html:javascript formName="loginForm" />	
</head>
<body>
<html:form action="/logar" focus="usuario" styleClass="AppForm" onsubmit="return validateLogin(this);">
<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" bordercolor="#666666">	
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
	<TR align="center">
		<TD>
			<label>Usuário:</label> 
			<html:text property="usuario"></html:text>					
		</TD>		
	</TR>
	<TR align="center">
		<TD>
			<label>Senha:</label> 
			<html:password property="senha"></html:password>				
		</TD>		
	</TR>
	<TR align="center">
		<TD>
			<html:submit property="acao">Logar</html:submit>				
		</TD>		
	</TR>
</table>
</html:form>
</body>
</html:html>