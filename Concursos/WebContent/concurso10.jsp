<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
    
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<jsp:forward page="opcao.do?acao=Confirma">
		<jsp:param name="codConcurso" value="10" />  
	</jsp:forward>
</body>
</html>








