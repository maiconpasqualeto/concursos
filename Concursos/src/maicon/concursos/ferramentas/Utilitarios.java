package maicon.concursos.ferramentas;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import sun.text.normalizer.NormalizerBase;

/**
 * 
 * @author maicon
 */
public class Utilitarios {
	
	public static String geraHashMd5(String senha){
		String retorno = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(senha.getBytes());
			BigInteger bigInteger = new BigInteger(md.digest());			
			retorno = bigInteger.toString(32);			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return retorno; 
	}
	
	/**
	 * Completa string com zeros a esquerda
	 * 
	 * @@param string
	 * @@param tamanho
	 * @@return
	 */
	public static String completaComZeros(String string, int tamanho){
		StringBuffer buffer = new StringBuffer(string);
		if (string.length() > tamanho) 
			buffer.setLength(tamanho);
		for (int i=0; i<(tamanho - string.length()); i++){
			buffer.insert(0, '0');
		}
		return buffer.toString();
	}
	
	/**
	 * Completa string com espa�os a direita
	 * 
	 * @@param string
	 * @@param tamanho
	 * @@return
	 */
	public static String completaComEspacos(String string, int tamanho){		
		StringBuffer buffer = new StringBuffer(string);
		if (string.length() > tamanho) 
			buffer.setLength(tamanho);
		for (int i=0; i<(tamanho - string.length()); i++){
			buffer.append(' ');
		}
		return buffer.toString();
	}
	
	/**
	 * M�todo que utiliza introspec��o para ler par�metros de arquvos XML
	 * @param o
	 * @return
	 */
	public String introspect(Object o){
		StringBuilder str = new StringBuilder();
		try {
			BeanInfo bi = Introspector.getBeanInfo(o.getClass());
			PropertyDescriptor[] pds = bi.getPropertyDescriptors();		
			for (int i=0; i<pds.length; i++){
				if ((!pds[i].getName().equals("class")) && 
						(!pds[i].getName().equals("annotations")) &&
						(!pds[i].getName().equals("annotation"))){
					Object retorno = pds[i].getReadMethod().invoke(o, new Object[0]);
					if (retorno != null){
						String tipo = retorno.getClass().getSimpleName();
						System.out.println(pds[i].getName());
						if (tipo.equals("String") || 
							tipo.equals("BigInteger") ||
							tipo.equals("BigDecimal") ||
							tipo.equals("XMLGregorianCalendarImpl") ||							
							tipo.equals("JAXBElement") ||
							tipo.equals("Boolean") ||
							tipo.startsWith("St")){
							str.append(retorno.toString());
						} else {
							str.append(introspect(retorno));							
						}
					}
				}
			}
			
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return str.toString();
	} 
	
	
	/**
	 * Cálculo do CNPJ
	 * ex 91.155.259/0001-67
	 *    (5*9)+(4*1)+(3*1)+(2*5)+(9*5)+(8*2)+(7*5)+(6*9)+(5*0)+(4*0)+(3*0)+(2*1)=(214 * 10) mod 11 = 6
	 * 	  (6*9)+(5*1)+(4*1)+(3*5)+(2*5)+(9*2)+(8*5)+(7*9)+(6*0)+(5*0)+(4*0)+(3*1)+(2*6)=(224 *10 ) mod 11 = 7
	 * 
	 * @ejb.interface-method view-type = "both"
	 * @param cnpj
	 * @return
	 */
	public static boolean validaCnpj (String cnpj) {		
		if (cnpj.length() != 14)
			return false;
		
		/* primeiro dígito */
		int j=2, soma=0, dig;
		for (int i=11; i>=0; i--) {
			soma += Integer.parseInt(cnpj.substring(i,i+1)) * j++;
			if (j>9) j=2;
		}
		dig = (soma * 10) % 11;
		if (dig == 10) dig = 0;
		if (dig != Integer.parseInt(cnpj.substring(12, 13)))
			return false;
		
		/* segundo dígito */
		j=2; soma=0;
		for (int i=12; i>=0; i--) {
			soma += Integer.parseInt(cnpj.substring(i, i+1)) * j++;
			if (j>9) j=2;
		}
		dig = (soma * 10) % 11;
		if (dig == 10) dig = 0;
		if (dig != Integer.parseInt(cnpj.substring(13, 14)))
			return false;
		
		return true;
}
	
	/**
	 * Cálculo do CPF
	 * ex 244.436.621-20
	 *    (10*2)+(9*4)+(8*4)+(7*4)+(6*3)+(5*6)+(4*6)+(3*2)+(2*1)=(295 * 10) mod 11 = 2
	 * 	  (10*4)+(9*4)+(8*4)+(7*3)+(6*6)+(5*6)+(4*2)+(3*1)+(2*2)=(265 * 10) mod 11 = 10 = 0
	 * 
	 * @ejb.interface-method view-type = "both"
	 * @param cnpj
	 * @return
	 */
	public static boolean validaCpf (String cpf) {
		if (cpf.length()<11)
			return false;
		if ("00000000000".equals(cpf))
			return false;
		
		/* primeiro dígito */
		int j=2, soma=0, dig;
		for (int i=8; i>=0; i--) {
			soma += Integer.parseInt(cpf.substring(i, i+1)) * j++;
		}
		dig = (soma * 10) % 11;
		if (dig == 10) dig = 0;
		if (dig != Integer.parseInt(cpf.substring(9, 10)))
			return false;
		
		/* segundo dígito */
		j=2; soma=0;
		for (int i=9; i>0; i--) {
			soma += Integer.parseInt(cpf.substring(i, i+1)) * j++;
		}
		dig = (soma * 10) % 11;
		if (dig == 10) dig = 0;
		if (dig != Integer.parseInt(cpf.substring(10, 11)))
			return false;
		
		return true;
	}
	
	/**
	 * metodo para testar se os campos da tela foram alterados
	 * @param texto1
	 * @param texto2
	 * @return
	 */
	public static boolean testaAlteracao(String[] texto1, String[] texto2){		
		boolean b = false;
		b = Arrays.equals(texto1,texto2);		
		return b;
	}
	
	
	/**
	 * m�todo que devolve devolve um int referente ao calculo
	 * das duas datas, indicando se a data final � maior ou menor
	 * que a data inicial.
	 * @param dataAtual
	 * @param dataComparar
	 * @return
	 */
	public static int getDiferencaDeDias(Date dataInicial, Date dataFinal) {
		//setando os quebrados da data inicial
		Calendar dtInicial = Calendar.getInstance();
		dtInicial.setTime(dataInicial);
		dtInicial.set(Calendar.AM_PM, Calendar.AM);
		dtInicial.set(Calendar.HOUR, 0);
		dtInicial.set(Calendar.MINUTE, 0);
		dtInicial.set(Calendar.SECOND, 0);
		dtInicial.set(Calendar.MILLISECOND, 0);
				
		//setando os quebrados da data final
		Calendar dtFinal = Calendar.getInstance();
		dtFinal.setTime(dataFinal);
		dtFinal.set(Calendar.AM_PM, Calendar.AM);
		dtFinal.set(Calendar.HOUR, 0);
		dtFinal.set(Calendar.MINUTE, 0);
		dtFinal.set(Calendar.SECOND, 0);
		dtFinal.set(Calendar.MILLISECOND, 0);
						
		long diferenca = (dtFinal.getTimeInMillis() - dtInicial.getTimeInMillis());
		return (int)(diferenca / (24 * 60 * 60 * 1000));
	 }
	
	/**
	 * metodo para comparar a data passada com a a data atual e
	 *  devolver um inteiro que diz se a data � maior, menor ou igual a atual
	 */
	public static int comparaDatas(Date dataComparacao){
		
		int resultado = 0;
		
		//setando o horario da data atual
		Calendar dtAtual = Calendar.getInstance();
		dtAtual.set(Calendar.AM_PM, Calendar.AM);
		dtAtual.set(Calendar.HOUR, 0);
		dtAtual.set(Calendar.MINUTE, 0);
		dtAtual.set(Calendar.SECOND, 0);
		dtAtual.set(Calendar.MILLISECOND, 0);

		//setando o horario da data final
		Calendar dataComparar = Calendar.getInstance();
		dataComparar.setTime(dataComparacao);
		dataComparar.set(Calendar.AM_PM, Calendar.AM);
		dataComparar.set(Calendar.HOUR, 0);
		dataComparar.set(Calendar.MINUTE, 0);
		dataComparar.set(Calendar.SECOND, 0);
		dataComparar.set(Calendar.MILLISECOND, 0);
		
		resultado = dtAtual.compareTo(dataComparar);
		
		return resultado;
	}
	
	/**
	 * metodo que a data1 com a data2!
	 * se a data1 for igual a data2 o metodo devolve o int 0
	 * caso a data1 for maior q a data2 devolve o int 1
	 * caso a data1 for menor q a data2 devolve o int -1
	 */
	public static int comparaDuasDatas(Date data1, Date data2){
		
		int resultado = 0;
		
		//setando a data1
		Calendar dt1 = Calendar.getInstance();
		dt1.setTime(data1);
		dt1.set(Calendar.AM_PM, Calendar.AM);
		dt1.set(Calendar.HOUR, 0);
		dt1.set(Calendar.MINUTE, 0);
		dt1.set(Calendar.SECOND, 0);
		dt1.set(Calendar.MILLISECOND, 0);

		//setando a data2
		Calendar dt2 = Calendar.getInstance();
		dt2.setTime(data2);		
		dt2.set(Calendar.AM_PM, Calendar.AM);
		dt2.set(Calendar.HOUR, 0);
		dt2.set(Calendar.MINUTE, 0);
		dt2.set(Calendar.SECOND, 0);
		dt2.set(Calendar.MILLISECOND, 0);
		
		resultado = dt1.compareTo(dt2);
		
		return resultado;
	}
	
	/**
	 * m�todo para gera��o de digito verificador para o cart�o de acesso
	 * @param empresa
	 * @param matricula
	 * @return
	 */
	public static int geraDigitoCartao(String empresa, String matricula){
        int sum=0;
        int j=2;
        String numero = empresa + matricula;
		for (int i=numero.length() - 1; i>=0; i--) {        	
			sum += (Integer.parseInt(numero.substring(i, i+1))) * j++;
	        if (j==10) 
	        	j=2;
	    }        
	    sum = (sum % 10);
		return sum;
	}
	
	
	/**
	 * Gera senha inicial aleatória para usuário.
	 * @return
	 */
	public static String gerarSenha() {
		StringBuffer senha = new StringBuffer("");
		String letras = "abcdefghijklmnopqrstuvwxyz0123456789";
		for (int i=0; i<6; i++) {
			senha.append(letras.charAt((int) (Math.random() * 35)));
		}
		return senha.toString();
	}
	
	public static Float getFloatDeString(String numero){
		if(numero == null || numero.trim().equals("")){
			return new Float(0f);
		}
		NumberFormat nf = NumberFormat.getInstance();
		Float novoFloat = null;
		try {
			novoFloat = nf.parse(numero).floatValue();
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
		
		return novoFloat;
	}
	
	public static String getStringDeFloat(Float numero, int casasDecimais){
		if (numero == null)
			return null;
		NumberFormat nf = new DecimalFormat("0.00");
		String novoNumero = nf.format(round(numero, casasDecimais));
		return novoNumero;
	}
	
	/**
	 * Recebe um objeto date e retorna um String com formato dd/MM/yyyy
	 * @param date
	 * @return
	 */
	public static String getDataDeDate(Date date){
		if(date == null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String novaData = sdf.format(date);
		return novaData;
	}
	
	public static Date getDateDeString(String data){
		if(data == null || data.trim().equals("")){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date novaData = new Date();
		try {
			novaData = sdf.parse(data);
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
		
		return novaData;
	}
	
	/**
	 * Arredonda valor de um float para a quantidade de casas decimais passadas no par�metro 
	 * @param number
	 * @param digits
	 * @return
	 */
	public static float round(float numero, int casasDecimais){
		long fator = (long) Math.pow(10, casasDecimais);		
		numero *= fator;						
		return (float) Math.round(numero) / fator;
	}

	
	/**
	 * Remove os zeros a esquerda de uma string
	 * 
	 * @param string
	 * @return
	 */
	public static String removeZerosAEsquerda(String string){
		StringBuffer buffer = new StringBuffer(string);
		int i=0;
		for (; i<buffer.length(); i++){
			if (buffer.charAt(i) != '0')
				break;
		}
		return buffer.substring(i);
	}
	
	/**
	 * Remove v�rgula de uma string
	 * 
	 * @param valor
	 * @return
	 */
	public static String removeVirgula(String valor){
		valor = valor.replaceAll("[.]", "");
		valor = valor.replaceAll("[,]", "");
		return valor;
	}
	
	/**
	 * Remove ponto de uma string
	 * 
	 * @param valor
	 * @return
	 */
	public static String removePonto(String valor){
		StringBuffer buffer = new StringBuffer(valor);
		buffer.deleteCharAt(valor.indexOf('.'));
		return buffer.toString();
	}
	
	/**
	 * Adiciona ponto de uma string
	 * 
	 * @param valor
	 * @return
	 */
	public static String adicionaPonto(String valor){
		StringBuffer buffer = new StringBuffer(valor);
		buffer.insert((buffer.length() - 2), '.');
		return buffer.toString();
	}
	
	/**
	 * Adiciona decimais Float
	 * 
	 * @param valor
	 * @return
	 */
	public static Float adicionaDecimais(Float valor, int decimais){
		int multiplo = (int) Math.pow(10, decimais);
		return valor / multiplo;
	}
	
	public static String removeAcentuacao(String string){		
		return NormalizerBase.decompose(string, false, 0).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");		
	}
	
	/**
	 * Fun��o para calcular o digito verificador de um n�mero no m�dulo 10.
	 * 
	 * @param numero - N�mero que a ser usado para calcular o digito verificador
	 * @param pesoInicial - Menor valor que o peso pode assumir
	 * @param pesoFinal - Maior valor que o peso pode assumir
	 * @param quantidadeDigitos - Quantidade de d�gitos que comp�e o digito verificador
	 * @return DAC - D�gito de Auto-Confer�ncia calculado
	 * @author maicon
	 */
	public static String calculaDigitoVerificadorModulo10(String numero,
			Integer pesoInicial, Integer pesoFinal, Integer quantidadeDigitos) { // fun��o;
		String DAC; // valor de retorno;
		long soma = 0L, num = 0L, digito = 0L; // vari�veis de controle interno;
		int multiplicador = pesoFinal; // peso multiplicador;
		for (int i = numero.length() - 1; i >= 0; --i) {	// para numero da
															// direita pra esquerda fa�a;
			num = (long) Character.getNumericValue(numero.charAt(i));	// pega
																		// o digito;
			num *= multiplicador;	// multiplica pelo peso;
			multiplicador = (multiplicador > pesoInicial) ? multiplicador - 1
					: pesoFinal;	// atualiza para o novo peso;
			num = (num / 1000000000)	// caso o resultado tenha mais de uma casa decimal
					+ ((num % 1000000000) / 100000000)	// somar os valores absolutos do 
					+ ((num % 100000000) / 10000000)	// n�mero;
					+ ((num % 10000000) / 1000000)
					+ ((num % 1000000) / 100000)
					+ ((num % 100000) / 10000)
					+ ((num % 10000) / 1000)
					+ ((num % 1000) / 100) + ((num % 100) / 10) + (num % 10);
			soma += num;	// soma os valores dos produtos calculados;
		} // fim do para;
		digito = (10 - (soma % 10)) % 10;	// calcula o digito de acordo com a
											// fun��o especificado;
		DAC = new String(String.valueOf(digito));	// coloca na String
													// o digito calculado;
		if (quantidadeDigitos > 1) {	// se a quantidade de digito(s)
										// verificador(es) > 1 ent�o;
			return DAC.concat(calculaDigitoVerificadorModulo10(
						numero.concat(DAC), pesoInicial, pesoFinal,
						quantidadeDigitos - 1));	// chama a fun��o
													// recursivamente para
													// calcular os digitos
													// restantes de acordo
													// com as especifica��o;
		} else { // sen�o;
			return DAC; // � apenas um digito e retorna o DAC;
		} // fim do se;
	} // fim da fun��o;
	
	/**
	 * Fun��o para calcular o digito verificador de um n�mero no m�dulo 11.
	 * 
	 * @param numero - N�mero que a ser usado para calcular o digito verificador
	 * @param pesoInicial - Menor valor que o peso pode assumir
	 * @param pesoFinal - Maior valor que o peso pode assumir
	 * @param quantidadeDigitos - Quantidade de d�gitos que comp�e o digito verificador
	 * @return DAC - D�gito de Auto-Confer�ncia calculado
	 * 
	 * @author maicon
	 */
	public static String calculaDigitoVerificadorModulo11(String numero,
			Integer pesoInicial, Integer pesoFinal, Integer quantidadeDigitos) { // fun��o
		String DAC; // valor de retorno;
		long soma = 0L, num = 0L, digito = 0L; // vari�veis de controle interno;
		int multiplicador = pesoInicial; // peso multiplicador;
		for (int i = numero.length() - 1; i >= 0; --i) {	// para numero da
															// direita pra esquerda fa�a;
			num = (long) Character.getNumericValue(numero.charAt(i));	// pega
																		// o digito;
			num *= multiplicador;	// multiplica pelo peso;
			multiplicador = (multiplicador < pesoFinal) ? multiplicador + 1
					: pesoInicial;	// atualiza para o novo peso;
			soma += num;	// soma os valores dos produtos calculados;
		}
		digito = ( 11 - ( ( ( soma % 11 ) == 1) ? 0 : ( soma % 11 ) ) ) % 11; // calcula
																// o digito de acordo com a
																// fun��o especificado;
		DAC = new String(String.valueOf(digito));	// coloca na String
													// o digito calculado;
		if (quantidadeDigitos > 1) {	// se a quantidade de digito(s)
										// verificador(es) > 1 ent�o;
			return DAC.concat(calculaDigitoVerificadorModulo11(
					numero.concat(DAC), pesoInicial, pesoFinal,
					quantidadeDigitos - 1));	// chama a fun��o
												// recursivamente para
												// calcular os digitos
												// restantes de acordo
												// com as especifica��o;
		} else { // sen�o;
			return DAC; // � apenas um digito e retorna o DAC;
		} // fim do se;
	} // fim da fun��o;
	
	public static boolean validaEmail(String email){
		   
	   // Set the email pattern string
	   Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

	   // Match the given string with the pattern
	   Matcher m = p.matcher(email);

	   // check whether match is found
	   boolean matchFound = m.matches();

	   StringTokenizer st = new StringTokenizer(email, ".");
	   String lastToken = null;
	   while (st.hasMoreTokens()) {
	      lastToken = st.nextToken();
	   }

	   if (matchFound && lastToken.length() >= 2
	      && email.length() - 1 != lastToken.length()) {

	      // validate the country code
	      return true;
	   }
	   else return false;
	   
	}

}
