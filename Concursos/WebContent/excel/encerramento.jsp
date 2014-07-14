<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="nested" uri="/WEB-INF/struts-nested.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>

<%@ page language="java" contentType="text/html; charset=iso-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
	<jsp:include page="includeexcel.jsp" flush="true" ></jsp:include>
	<html:javascript formName="excelEntradaForm" />
</head>
<body>	
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="200px"></td>
			<td width="400px">
				<table width="100%" border="1px" cellpadding="0" cellspacing="0">
					<tr>					
						<td align="center"><img src="<%=request.getContextPath()%>/images/brasao.gif"/></td>
						<td align="center">
							<table>
								<tr align="center"><td><B>PREFEITURA MUNICIPAL DE PONTA PORÃ</B></td></tr>
								<tr align="center"><td>Estado de Mato Grosso do Sul</td></tr>
								<tr align="center"><td>Comissão Municipal de Concurso Público</td></tr>						
							</table>
						</td>
					</tr>					
					<tr>
						<td align="center" colspan="2"><B>Concurso 01/2009</B></td>
					</tr>
					<tr>
						<td valign="top" colspan="2" align="justify" style="text-indent: 30px;">
							A Comissão Municipal de Concurso Público da Prefeitura de Ponta Porã - MS, TORNA PÚBLICO, que as inscrições para o Concurso 01/2009 se encontram encerradas.<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Os candidatos que já emitiram o BOLETO poderão efetuar o pagamento até o dia 15.06.2009, devendo entregar os respectivos documentos que efetivem a inscrição pessoalmente até as 17:00 horas do dia 15.06.2009 no Departamento de Recursos Humanos da Prefeitura Municipal de Ponta Porã, sito à Rua Guia Lopes, 663 -  Centro, Ponta Porã - MS ou encaminhá-los via Sedex para o mesmo endereço.<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;As fichas de inscrições não entregues no Departamento Pessoal da Prefeitura, postadas ou com pagamento posterior ao dia 15.06.2009, serão indeferidas.
						</td>
					</tr>					
				</table>
			</td>
			<td width="200px"></td>
		</tr>
	</table>
</body>
</html:html>