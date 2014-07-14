package maicon.birt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.HTMLRenderContext;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;


public class WebReport extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3656202991475539637L;
	/**
	 * Constructor of the object.
	 */
	private IReportEngine birtReportEngine = null;
	protected static Logger logger = Logger.getLogger( "org.eclipse.birt" );
	
	public WebReport() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
		BirtEngine.destroyBirtEngine();
	}


	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//get report name and launch the engine
		resp.setContentType("text/html");
		//resp.setContentType( "application/pdf" ); 
		//resp.setHeader ("Content-Disposition","inline; filename=test.pdf");		
		String reportName = req.getParameter("ReportName");
		String codigoBoleto = req.getParameter("codigoBoleto");
		ServletContext sc = req.getSession().getServletContext();
		this.birtReportEngine = BirtEngine.getBirtEngine(sc);
		
		//setup image directory
		HTMLRenderContext renderContext = new HTMLRenderContext();
		renderContext.setBaseImageURL(req.getContextPath()+"/images");
		renderContext.setImageDirectory(sc.getRealPath("/images"));
		
		logger.log( Level.FINE, "image directory " + sc.getRealPath("/images"));		
		System.out.println("stdout image directory " + sc.getRealPath("/images"));
		
		HashMap<String, HTMLRenderContext> contextMap = new HashMap<String, HTMLRenderContext>();
		contextMap.put( EngineConstants.APPCONTEXT_HTML_RENDER_CONTEXT, renderContext );
		
		IReportRunnable design;
		try
		{
			//Open report design
			design = birtReportEngine.openReportDesign( sc.getRealPath("/Reports")+"/"+reportName );
			//create task to run and render report
			IRunAndRenderTask task = birtReportEngine.createRunAndRenderTask( design );	
			task.setLocale(new Locale("pt", "BR"));
			task.setAppContext( contextMap );
			
			Iterator it = req.getParameterMap().keySet().iterator(); 
			while(it.hasNext()) { 
				String key = (String) it.next(); 
				if (!key.equals("ReportName")) {
					String[] val = (String[]) req.getParameterMap().get(key);
					task.setParameterValue(key, val[0]);
				}
			}
			
			//set output options
			/*HTMLRenderOption options = new HTMLRenderOption();
			options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_HTML);
			//options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_PDF);
			options.setOutputStream(resp.getOutputStream());
			task.setRenderOption(options);*/
			
			
			//PDF render options
			PDFRenderOption options = new PDFRenderOption();
			options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_PDF);
			resp.setHeader(
			"Content-Disposition", "inline; filename=\"boleto_" + codigoBoleto + ".pdf\"" );
			resp.setContentType( "application/pdf" );
			options.setOutputStream(resp.getOutputStream());
			task.setRenderOption(options);
			
			//run report
			task.run();
			task.close();
		}catch (Exception e){
			
			e.printStackTrace();
			throw new ServletException( e );
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		BirtEngine.initBirtConfig();
		
	}

}

