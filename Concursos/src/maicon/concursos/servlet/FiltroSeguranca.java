/**
 * 
 */
package maicon.concursos.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Maicon
 *
 */
public class FiltroSeguranca implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		String url = null;
		
		if (request instanceof HttpServletRequest)
		      url = ((HttpServletRequest)request).getServletPath();		      
		
		/*if (url.contains("excel")){		
			String contextPath = ((HttpServletRequest)request).getContextPath();
			((HttpServletResponse)response).sendRedirect(contextPath + "/excel/encerramento.jsp");
			return;
		}*/
		//((HttpServletResponse)response).sendRedirect("jsp/encerramento.jsp");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
