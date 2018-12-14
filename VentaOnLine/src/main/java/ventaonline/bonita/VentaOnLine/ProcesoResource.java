package ventaonline.bonita.VentaOnLine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.List;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProcesoResource {
	
	static String xBonitaToken;
	static String jSession;

	public static String loginService() {

		  HttpURLConnection connection = null;
		  CookieManager cookieManager = new CookieManager();
		  CookieHandler.setDefault(cookieManager);
		  try {
			  System.out.println("Entro en el executePost");
			
		    //Create connection
		    URL url = new URL("http://localhost:8080/bonita/loginservice?username=walter.bates&password=bpm&redirect=false");
			 
			connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", 
		        "application/x-www-form-urlencoded");
		    System.out.println(connection.getRequestProperties());;
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);
		    //Send request
		    DataOutputStream wr = new DataOutputStream (
		        connection.getOutputStream());
		    wr.writeBytes("");
		    wr.close();
		    
		    int status = connection.getResponseCode();

		    if (status == HttpURLConnection.HTTP_OK) {
		              //String primerHeader= connection.getHeaderField("Access-Control-Allow-Origin");
		              List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
		              for (HttpCookie cookie : cookies) {
		                  System.out.println(cookie.getDomain());
		                  System.out.println(cookie);
		                  System.out.println("ESTE ES EL NOMBRE DE LA COOKIE " + cookie.getName() + " VALOR " + cookie.getValue());
		                  if (cookie.getName().equals("X-Bonita-API-Token")) {
		                	  xBonitaToken =  cookie.getValue();
		                	  
		                  }
		                  if (cookie.getName().equals("JSESSIONID")) {
		                	  jSession =  cookie.getValue();
		                	  
		                  }
		              }

		        }
		    System.out.println("Paso la ejecucion del get request");
		    //Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		    String line;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');

		    }
		    rd.close();
		    return "Hola";
		  } catch (Exception e) {
		    e.printStackTrace();
		    return null;
		  } finally {
		    if (connection != null) {
		      connection.disconnect();
		    }
		  }
		  
		}
	
	
	@CrossOrigin
	@RequestMapping(value = "/iniciarProceso/{idProceso}/{cupon}/{empleado}/{producto}",produces = "application/json")
	public static JSONObject iniciarproceso(@PathVariable("idProceso") String idProceso,
			@PathVariable("cupon") String cupon,
					@PathVariable("empleado") String empleado,
							@PathVariable("producto") String producto) {
		 
		 // loginService();
		
		//Hago el login
		
		System.out.print("Esto me viene como Url" + idProceso);
		
		 HttpURLConnection connection1 = null;
		  CookieManager cookieManager = new CookieManager();
		  CookieHandler.setDefault(cookieManager);
		 
		  HttpURLConnection connection = null;
		  try {
			  System.out.println("Entro en el executePost");
			
			  
			  //Login
			    URL url1 = new URL("http://localhost:8080/bonita/loginservice?username=walter.bates&password=bpm&redirect=false");
				 
				connection1 = (HttpURLConnection) url1.openConnection();
			    connection1.setRequestMethod("POST");
			    connection1.setRequestProperty("Content-Type", 
			        "application/x-www-form-urlencoded");
			    System.out.println(connection1.getRequestProperties());;
			    connection1.setUseCaches(false);
			    connection1.setDoOutput(true);
			    //Send request
			    DataOutputStream wr1 = new DataOutputStream (
			        connection1.getOutputStream());
			    wr1.writeBytes("");
			    wr1.close();
			    //try {
					  System.out.println("Entro en el executePost");
					
				    //Create connection

				    
				    int status1 = connection1.getResponseCode();

			    if (status1 == HttpURLConnection.HTTP_OK) {
				              //String primerHeader= connection.getHeaderField("Access-Control-Allow-Origin");
				              List<HttpCookie> cookies1 = cookieManager.getCookieStore().getCookies();
				              for (HttpCookie cookie1 : cookies1) {
				            	  System.out.println("Imprimir cookie " + cookie1);
				                  if (cookie1.getName().equals("X-Bonita-API-Token")) {
				                	  xBonitaToken =  cookie1.getValue();
				                	  
				                  }
				                  if (cookie1.getName().equals("JSESSIONID")) {
				                	  jSession =  cookie1.getValue();
				                	  
				                  }
				              }

				        }
			  //Login
			  
		    //Create connection
		    URL url = new URL("http://localhost:8080/bonita/API/bpm/process/"+idProceso+"/instantiation");
		    //URL url = new URL("http://localhost:8080/bonita/API/system/session/unusedId"); 
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    //String userCredentials = "X-Bonita-API-Token:"+xBonitaToken;
		    //String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
		    //connection.setRequestProperty ("Authorization", basicAuth);
	        connection.setRequestProperty("Content-Type", 
		        "application/json");
		    connection.setRequestProperty("bonita.tenant","1");
		    //connection.setRequestProperty("X-Bonita-API-Token",xBonitaToken);
		    //connection.setRequestProperty("JSESSIONID",jSession);
		    connection.addRequestProperty("X-Bonita-API-Token",xBonitaToken);
		    connection.addRequestProperty("JSESSIONID",jSession);
		    //connection.setRequestProperty("Cookie", "X-Bonita-API-Token="+xBonitaToken+";"+"JSESSIONID="+jSession+";"+"bonita.tenant="+"1");	  
		    System.out.println("Estas son las properties " +connection.getRequestProperties());
		    
		    connection.setUseCaches(false);
		    connection.setDoInput(true);
		    connection.setDoOutput(true); 
		    DatosCompra dc = new DatosCompra("2","2","2");
		    System.out.println("Estos parametros viene del front end ");
		    System.out.println("cupon " + cupon);
		    System.out.println("empleado " + empleado);
		    System.out.println("producto " + producto);
		    JSONObject jsonObj1 = new JSONObject("{\"datosCompraInput\":{\"idCupon\":\""+cupon+"\",\"idEmpleado\":\""+empleado+"\",\"idProducto\":\""+producto+"\"}}");
		    System.out.println("ESto es mi contrato" + jsonObj1);
		    
		    byte[] outputInBytes = jsonObj1.toString().getBytes("UTF-8");
		    OutputStream os = connection.getOutputStream();

		    os.write(outputInBytes);
		    os.close();
		    
		    int status = connection.getResponseCode();
            
/*		    if (status == HttpURLConnection.HTTP_OK) {
		              //String primerHeader= connection.getHeaderField("Access-Control-Allow-Origin");
		              List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
		              for (HttpCookie cookie : cookies) {
		                  System.out.println(cookie.getDomain());
		                  System.out.println(cookie);
		                  System.out.println("ESTE ES EL NOMBRE DE LA COOKIE " + cookie.getName() + " VALOR " + cookie.getValue());
		              }

		        }*/
		    System.out.println("Este es el status" + status);
		    System.out.println("Paso la ejecucion del get request");
		    //Get Response  
		    InputStream is = connection.getInputStream();
		    System.out.println("Paso la ejecucion el InputStream");
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    System.out.println("Paso la ejecucion el InputStreamReader");
		    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		    System.out.println("Paso la ejecucion el StringBuilder");
		    String line;
		    JSONObject jsonObj = null;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		      System.out.println(line);

		       jsonObj = new JSONObject(line);
		      System.out.println(jsonObj);
		    }
		    rd.close();
		    return jsonObj;
		  }
		  catch (Exception e) {
		    e.printStackTrace();
		    return null;
		  } finally {
		    if (connection != null) {
		      connection.disconnect();
		    }
		  }
		  
	} //Cierra el metodo
	

	
}//Cierra la clase