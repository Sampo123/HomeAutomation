package homeautomation.servlets;

import java.io.BufferedWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import homeautomation.Home;
import homeautomation.api.AddLed;

/**
 * Servlet implementation class LedService
 */
@WebServlet("/LedService")
public class LedService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Gson gson;
	private static final String CT = "application/json";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LedService() {
        super();
        this.gson = new Gson();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.gson.toJson(Home.getInstance().getLedService().getRegisteredLeds(), response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
	    HttpServletResponse response) throws ServletException, IOException {
		
	  String path = request.getPathInfo();
	  String[] pathParams = path.split("/");
	  
	  switch (pathParams[0]) {
    case "addLed": {
      AddLed led = this.gson.fromJson(request.getReader(), AddLed.class);
      try {
        Home.getInstance().getLedService().registerLed(led.name, led.pinNumber);
        response.setContentType(CT);
        this.gson.toJson(Home.getInstance().getLedService().getRegisteredLeds(), response.getWriter());
      }
      catch (Exception  e) {
        this.passError(response, e);
      }
      
      break;
    }
    case "blink":{
      AddLed led = this.gson.fromJson(request.getReader(), AddLed.class);
      try {
        Home.getInstance().getLedService().registerLed(led.name, led.pinNumber);
        response.setContentType(CT);
        this.gson.toJson(Home.getInstance().getLedService().getRegisteredLeds(), response.getWriter());
      }
      catch (Exception e) {
        this.passError(response, e);
      }
      break;
    }
    case "toggle": {
      AddLed led = this.gson.fromJson(request.getReader(), AddLed.class);
      try {
        Home.getInstance().getLedService().registerLed(led.name, led.pinNumber);
        response.setContentType(CT);
        this.gson.toJson(Home.getInstance().getLedService().getRegisteredLeds(), response.getWriter());
      }
      catch (Exception e) {
        this.passError(response, e);
      }
      break;
    }

    default:
      break;
    }
	  
	  
	}
	
	
	private void passError(HttpServletResponse resp, final Exception e) throws IOException {
	  resp.setStatus(500);
	  resp.setContentType(CT);
	  StringBuilder sb = new StringBuilder();
	  for (StackTraceElement ste : e.getStackTrace()) {
	    sb.append(ste.toString()+ "<br />");
	    
	  }
	  final String st = sb.toString();
	  
	    try (BufferedWriter bw = new BufferedWriter(resp.getWriter())){
	      bw.write(this.gson.toJson(new Object(){
	        String message = e.getMessage();
	        String stackTrace = st;
	      }));
	    } catch (IOException ioe) {
	      
	    }
	    finally {
	      if (!resp.isCommitted())
	      {
	        resp.setStatus(500);
	        resp.getWriter().close();
	      }
	    }
	}

}
