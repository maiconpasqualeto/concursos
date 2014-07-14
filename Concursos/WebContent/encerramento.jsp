<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="nested" uri="/WEB-INF/struts-nested.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>

<%@ page language="java" contentType="text/html; charset=iso-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
	<jsp:include page="jsp/include.jsp" flush="true" ></jsp:include>
</head>
<body>	
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="200px"></td>
			<td width="400px">
				<table width="100%" border="1px" cellpadding="0" cellspacing="0">
					<tr>					
						<jsp:include page="jsp/headerPrefeitura.jsp" flush="true" ></jsp:include>
					</tr>					
					<tr>
						<td align="center" colspan="2"><B><bean:write name="nomeConcurso"/></B></td>
					</tr>
					<tr>
						<td valign="top" colspan="2" align="center">
							A Comissão Municipal de Concurso Público da <bean:write name="prefeitura"/>, TORNA PÚBLICO, que as inscrições para <bean:write name="nomeConcurso"/> se encontram encerradas.
						</td>									
					</tr>					
				</table>
			</td>
			<td width="200px"></td>
		</tr>
	</table>
</body>
</html:html>