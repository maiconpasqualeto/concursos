	<!-- META Tags added by Add-Meta-Tags WordPress plugin. Get it at: http://www.g-loaded.eu/ -->
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<meta name="rating" content="Concurso" />
	<meta name="author" content="Maicon Pasqualeto" />
	<meta name="language" content="pt-br" />
	<meta name="DC.title" content="" />
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/forms.css"/>
		
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/validacao.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript">		
		function initProgress(componente) {		  
		  $(componente).show();
		}
		function resetProgress(componente) {
		  $(componente).fadeOut("slow");
		}
		function CallPrint(strid) {
			var inscricao = $("#inscricao").val();
			var WinPrint = window.open(strid + inscricao,'Impressão',' "style:height=900,width=800,top=110,left=50,scrollbars=1" ');
		}
		function buscarCep(){
			var cep = $("#cep").val();
			if ( (cep != '') && (cep.length == 8) ) {
				initProgress("#carregandoCep");
				$.get(
					"buscaCep.ajax?cep=" + $("#cep").val(),	
					{},
					function(xml){
						$(xml).find("webservicecep").each(
							function(){
								resultado = $(this).find("resultado").text();
								if (resultado == '1') {
									if ($(this).find("tipo_logradouro").text() != "")
										$("#endereco").val($(this).find("tipo_logradouro").text() + " " + $(this).find("logradouro").text());
									else 
										$("#endereco").val($(this).find("logradouro").text());
									$("#cidade").val($(this).find("cidade").text());
									$("#bairro").val($(this).find("bairro").text());
									$("#uf").val($(this).find("uf").text());
									$("#codMunicipioIbge").val($(this).find("ibge_municipio").text());
									$("#bairro").removeAttr("readonly");
									$("#endereco").removeAttr("readonly");									
								} else {
									$("#endereco").val("");
									$("#cidade").val("");
									$("#bairro").val("");
									$("#uf").val("");
									$("#codMunicipioIbge").val("");
									$("#bairro").attr("readonly", "true");
									$("#endereco").attr("readonly", "true");
									alert('CEP incorreto!');
								}
							}
						);
						resetProgress("#carregandoCep");
					},
					"xml"
				);
			} else {
				$("#endereco").val("");
								$("#cidade").val("");
								$("#bairro").val("");
								$("#uf").val("");
								$("#codMunicipioIbge").val("");
								$("#bairro").attr("readonly", "true");
								$("#endereco").attr("readonly", "true");
			}
		}
	</script>