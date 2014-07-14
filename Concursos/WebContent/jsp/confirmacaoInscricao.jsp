<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="nested" uri="/WEB-INF/struts-nested.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>

<%@ page language="java" contentType="text/html; charset=iso-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
	<jsp:include page="include.jsp" flush="true" ></jsp:include>
	
</head>
<body>
	<html:form action="/efetuaInscricao" focus="nome" styleClass="AppForm">
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td></td>
			<td width="200px">
				Sua inscrição foi confirmada
			</td>
			<td></td>
		</tr>
	</table>
	</html:form>
</body>
</html:html>