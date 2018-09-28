package DemoService.odata.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataHttpHandler;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.edmx.EdmxReference;
import org.apache.olingo.server.api.processor.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoServlet extends HttpServlet {

	  private static final long serialVersionUID = 1L;
	  private static final Logger LOG = LoggerFactory.getLogger(DemoServlet.class);
	  
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	        // create odata handler and configure it with CsdlEdmProvider and Processor
	        OData odata = OData.newInstance();
	        ServiceMetadata edm = odata.createServiceMetadata(new DemoCsdlEdmProvider(), new ArrayList<EdmxReference>());
	        ODataHttpHandler handler = odata.createHandler(edm);
	       // handler.register(new Processor());

	        // let the handler do the work
	        handler.process(req, resp);
	      } catch (RuntimeException e) {
	        LOG.error("Server Error occurred in ExampleServlet", e);
	        throw new ServletException(e);
	      }
	    }
}
