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
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="200px"></td>
			<td width="400px">
				<table width="100%" border="1px" cellpadding="0" cellspacing="0">
					<tr>
						<jsp:include page="headerArea.jsp" flush="true" ></jsp:include>
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
							<html:form action="/opcao" styleClass="AppForm" >
								<table>
									<tr>
										<td><B>Selecione sua opção:</B></td>
									</tr>
									<tr>
										<td>
											<logic:iterate property="concursos" id="concurso" name="opcaoForm">
									            <bean:define id="concursoValue">
							                           <bean:write name="concurso" property="descricao"/> - 
							                           <bean:write name="concurso" property="prefeitura"/>
									            </bean:define>
									            <bean:define id="id">
							                           <bean:write name="concurso" property="id"/>
									            </bean:define>
									            <html:radio property="codConcurso" 
									                       value="<%=id%>" 
									                       styleId="<%=id%>"/>
									            <bean:write name="concursoValue"/><br/>
									        </logic:iterate><br/>
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