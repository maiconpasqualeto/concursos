<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>

<bean:define id="nomeConcurso">
	<bean:write name="nomeConcurso"/>
         </bean:define>
<html:hidden property="nomeConcurso" value="<%=nomeConcurso%>" /> 
<bean:define id="prefeitura">
	<bean:write name="prefeitura"/>
         </bean:define>
<html:hidden property="prefeitura" value="<%=prefeitura%>" /> 