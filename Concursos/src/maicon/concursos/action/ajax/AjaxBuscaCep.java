/**
 * 
 */
package maicon.concursos.action.ajax;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author maicon.pasqualeto
 *
 */
public class AjaxBuscaCep extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String cep = req.getParameter("cep");
		// Objeto URL
		//URL url = new URL("http://www.buscarcep.com.br?cep=" + cep + "&formato=xml&chave=1aLqB2mpqHoH/Xa/m8jkf0Wm/S99vK0");
		URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?formato=xml&cep=" + cep);
		
		// Objeto HttpURLConnection
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// M�todo
		connection.setRequestProperty("Request-Method", "GET");

		// V�ariavel do resultado
		connection.setDoInput(true);
		connection.setDoOutput(false);

		// Faz a conex�o
		connection.connect();
		
		// seta o conte�do
		resp.setContentType("text/xml");
		
		// Abre a conex�o
		//BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));		
		InputStream is = connection.getInputStream();
		byte[] buf = new byte[is.available()];
		is.read(buf);
		resp.getOutputStream().write(buf);
		resp.getOutputStream().flush();
		is.close();
		
		/*resp.setContentType("text/xml");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream()));
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		for (String linha = br.readLine(); linha != null; linha = br.readLine())  {
		    bw.write(linha);
		}  
		bw.flush();
		bw.close();*/		
	}
	
}
