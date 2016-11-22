<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="nested" uri="/WEB-INF/struts-nested.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>

<%@ page language="java" contentType="text/html; charset=iso-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
	<jsp:include page="include.jsp" flush="true" ></jsp:include>
	<script language="javascript">
		function CallPrint(strid) {			
			var inscricao = document.getElementById('inscricao').value;
			var WinPrint = window.open(strid + inscricao,'Impressão',' "style:height=900,width=800,top=110,left=50,scrollbars=1" ');
		}
		function popup(nome,w,h,scroll){
			var inscricao = $("#inscricao").val();
			var codConcurso = $("#codConcurso").val();
			pagina = 'efetuaInscricao.do?acao=ImprimirBoleto&numeroInscricao=' + inscricao + '&codConcurso=' + codConcurso;
			LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
			TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
			settings = 'height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable'
			var win = window.open(pagina,nome,settings);
		}
	</script>
</head>
<body>
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td></td>
			<td width="650px">
				<html:form styleClass="AppForm" action="/efetuaInscricao" style="margin: 4px;">
				<jsp:include page="infoConcurso.jsp" flush="true" ></jsp:include>
				<bean:define id="codConcurso">
					<bean:write name="codConcurso"/>
			    </bean:define>
				<html:hidden styleId="codConcurso" property="codConcurso" value="<%=codConcurso%>" />
			
				<table width="100%" border="1px" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td colspan="4">
							<table width="100%" border="1px" cellpadding="0" cellspacing="0" border="0">
								<jsp:include page="headerPrefeitura.jsp" flush="true" ></jsp:include>
							</table>
						</td>
					</tr>
					<tr align="center"><td colspan="4"><B><bean:write name="nomeConcurso"/></B></td></tr>
					<TR>
						<TD>
							<label>Inscri��o</label> 
							<B><bean:write name="inscricaoForm" property="numeroInscricao" /></B>
							<html:hidden property="numeroInscricao" styleId="inscricao"/>
						</TD>
						<TD colspan="3">
							<label>Cargo</label> 
							<bean:write name="inscricaoForm" property="descricaoCargo" />
						</TD>
					</TR>
					<TR>
						<TD  colspan="2">
							<label><bean:message key="label.lotacao"/></label> 
							<bean:write name="inscricaoForm" property="descricaoLotacao" />
						</TD>
						<TD  colspan="2">
							<label><bean:message key="label.funcao"/></label> 
							<bean:write name="inscricaoForm" property="descricaoFuncao" />
						</TD>
					</TR>
					<TR>
						<TD  colspan="3">
							<label>Nome</label> 
							<bean:write name="inscricaoForm" property="nome" />
						</TD>
						<TD>
							<label>Sexo</label> 
							<logic:equal name="inscricaoForm" property="sexo" value="M">Masculino</logic:equal>
							<logic:equal name="inscricaoForm" property="sexo" value="F">Feminino</logic:equal>							
						</TD>
					</TR>
					<TR>
						<TD  colspan="3">
							<label>Endere�o</label> 
							<bean:write name="inscricaoForm" property="endereco" />
						</TD>
						<TD>
							<label>N�mero</label> 
							<bean:write name="inscricaoForm" property="numero" />
						</TD>
					</TR>
					<TR>
						<TD  colspan="2">
							<label>Bairro</label> 
							<bean:write name="inscricaoForm" property="bairro" />
						</TD>
						<TD>
							<label>E-mail</label> 
							<bean:write name="inscricaoForm" property="cadastroemail" />
						</TD>
						<TD>
							<label>Complemento</label> 
							<bean:write name="inscricaoForm" property="complemento" />
						</TD>
					</TR>
					<TR>
						<TD>
							<label>Cidade</label> 
							<bean:write name="inscricaoForm" property="cidade" />
						</TD>
						<TD>
							<label>UF</label> 
							<bean:write name="inscricaoForm" property="descricaoUf" />
						</TD>
						<TD>
							<label>CEP</label> 
							<bean:write name="inscricaoForm" property="cep" />
						</TD>
						<TD>
							<label>Telefone</label> 
							<bean:write name="inscricaoForm" property="telefone" />
						</TD>
					</TR>
					<TR>
						<TD  colspan="4">
							<label>Nome do pai</label> 
							<bean:write name="inscricaoForm" property="nomePai" />
						</TD>
					</TR>
					<TR>
						<TD  colspan="4">
							<label>Nome da m�e</label> 
							<bean:write name="inscricaoForm" property="nomeMae" />
						</TD>
					</TR>
					<TR>
						<TD>
							<label>data Nascimento</label> 
							<bean:write name="inscricaoForm" property="dataNascimentoStr" />
						</TD>
						<TD>
							<label>Local</label> 
							<bean:write name="inscricaoForm" property="localNascimento" />
						</TD>
						<TD>
							<label>UF Nascimento</label> 
							<bean:write name="inscricaoForm" property="descricaoUfNascimento" />
						</TD>
						<TD>
							<label>Estado Civil</label> 
							<bean:write name="inscricaoForm" property="descricaoEstadoCivil" />
						</TD>
					</TR>
					<TR>
						<TD colspan="2">
							<label>Possui Defici�ncia f�sica</label>
							<logic:equal name="inscricaoForm" property="possuiDeficiencia" value="true">Sim</logic:equal>
							<logic:equal name="inscricaoForm" property="possuiDeficiencia" value="false">N�o</logic:equal>
						</TD>
						<TD colspan="2">
							<label>Defici�ncia</label> 
							<bean:write name="inscricaoForm" property="deficiencia" />
						</TD>
					</TR>
					<TR>
						<TD>
							<label>CPF</label> 
							<bean:write name="inscricaoForm" property="cpf" />
							<html:hidden styleId="cpf" property="cpf" />
						</TD>
						<TD>
							<label>Tipo Documento</label> 
							<bean:write name="inscricaoForm" property="identidadeTipo" />
						</TD>
						<TD>
							<label>N�mero Documento</label>
							<bean:write name="inscricaoForm" property="identidadeNumero" /> 
						</TD>
						<TD>
							<label>�rg�o Expedidor</label>
							<bean:write name="inscricaoForm" property="identidadeOrgaoExpedidor" /> 
						</TD>
					</TR>
				</table>
				</html:form>
				<table>
					<TR>
						<td>
							<form action="https://www16.bancodobrasil.com.br/site/mpag/" method="post" name="pagamento">
								<input type="hidden" name="idConv" value="<bean:write name="convenio"/>">
								<input type="hidden" name="refTran" value="<bean:write name="refTrans"/>">
								<input type="hidden" name="valor" value="<bean:write name="valor"/>">
								<input type="hidden" name="qtdPontos" value="">
								<input type="hidden" name="dtVenc" value="<bean:write name="dataVenc"/>">
								<input type="hidden" name="tpPagamento" value="2"> 
								<input type="hidden" name="cpfCnpj" value="<bean:write name="inscricaoForm" property="cpf" />"> 
								<input type="hidden" name="indicadorPessoa" value="1"> 
								<input type="hidden" name="valorDesconto" value=""> 
								<input type="hidden" name="dataLimiteDesconto" value=""> 
								<input type="hidden" name="tpDuplicata" value="DS">
								<input type="hidden" name="urlRetorno" value="http://concurso.sixinf.com.br/opcao.do?acao=Confirma&codConcurso=<bean:write name="codConcurso"/>">
								<input type="hidden" name="urlInforma" value="">
								<input type="hidden" name="nome" value="<bean:write name="inscricaoForm" property="nome" />"> 
								<input type="hidden" name="endereco" value="<bean:write name="inscricaoForm" property="endereco" />">
								<input type="hidden" name="cidade" value="<bean:write name="inscricaoForm" property="cidade" />">
								<input type="hidden" name="uf" value="<bean:write name="inscricaoForm" property="uf" />"> 
								<input type="hidden" name="cep" value="<bean:write name="inscricaoForm" property="cep" />"> 
								<input type="hidden" name="msgLoja" value="Concurso: <bean:write name="prefeitura"/>">
								
								<input type="submit" value="Imprimir Boleto" />
								<button onclick="window.location='opcao.do?acao=Confirma&codConcurso=<bean:write name="codConcurso"/>'" type="reset">Voltar</button>
								
							</form>
						
							<%-- <input id="btnimprimir" runat="server" name="btnimprimir" 
									onclick="CallPrint('efetuaInscricao.do?acao=Imprimir&nomeConcurso=<bean:write name="nomeConcurso"/>&prefeitura=<bean:write name="prefeitura" />&codConcurso=<bean:write name="codConcurso"/>&inscricao=');" type="button" value="Imprimir" /> --%>
							<%-- <input type="button" value="Imprimir Boleto" name="imprimirboleto"  
								onclick="popup('Imprimir','750','600','no')" />	 --%>
							
						</td>
					</TR>
				</table>
			</td>
			<td></td>
		</tr>
	</table>
	
		
	
</body>
</html:html>