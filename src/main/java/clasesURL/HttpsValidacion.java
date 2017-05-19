package clasesURL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.security.cert.Certificate;

import org.springframework.stereotype.Component;

import DTO.MiUrl;

@Component
public class HttpsValidacion {

			
			String inputLine = "";
			String inputText = "";
			final int FINAL_CADENA_HTTP=5;
			final int INICIO_CADENA_HTTP=1;
			
			//prueba local1
			public List<MiUrl> obtenerUrls(String urlPrincipal){
				
				ArrayList<MiUrl> urlsPagWeb = new ArrayList<MiUrl>();
				InputStreamReader isPaginas = null;
				BufferedReader bfPaginas = null;
				HttpURLConnection conexion = null;
				URL url;
				String[] links = new String[30];
				int numeroLink = 0;
				String etiquetaEnlace = "href";
				boolean finEnlace = false;
				String substringaux = "";
				String enlace = "";
				int inicioLink = 0;
				int longitud = 0;
				MiUrl urlaux = null;
				
				try {
					// Se abre la conexión
					url = new URL(urlPrincipal);
					conexion =(HttpURLConnection) url.openConnection();
					conexion.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Safari/537.36");
					conexion.connect();

					// Lectura
					isPaginas = new InputStreamReader(url.openStream());
					bfPaginas = new BufferedReader(isPaginas);
					
					// Introducimos el HTML de la pagina en un string 
					while ((inputLine = bfPaginas.readLine()) != null) {
						inputText = inputText + inputLine;
					}

	
					//Buscamos los href que señalan el inicio de enlaces en HTML
					inicioLink = inputText.indexOf(etiquetaEnlace);
					
					while(inicioLink != -1){
						
						//los enlaces tras el href se encuentran entre '' o "", que localizamos en cadenas de 200 caracteres posteriores al href
						substringaux = inputText.substring(inicioLink,inicioLink+200);
						if(substringaux.contains("\"")){
							substringaux = substringaux.split("\"")[1];
						}else{
							if(substringaux.contains("'")){
								substringaux = substringaux.split("'")[1];
						}
						}
						//solo guardaremos los enlaces HTTP o HTTPS
							if(esHTTP(substringaux)){
								urlaux = new MiUrl(substringaux);
								urlsPagWeb.add(urlaux);
							}
							
						//buscamos el siguente href
						inicioLink = inputText.indexOf(etiquetaEnlace,inicioLink+1);
					}
	
					
				} catch (MalformedURLException e) {
					Logger.getGlobal().log(Level.SEVERE, e.getMessage());
				} catch (IOException e) {
					Logger.getGlobal().log(Level.SEVERE, e.getMessage());
				}
				//borramos inputText para evitar que se sobeescriba el analisis con futuras busquedas
				inputText="";
				return (urlsPagWeb);
				
			}
			
			public boolean esHTTP(String link){
				if(link.contains("http")){
					return true;
				}
				return false;
			}
			public boolean esHTTPS(String link){
				if(link.contains("https")){
					return true;
				}
				return false;
			}
			
			public Certificate[] getCerts(MiUrl enlace){
				Certificate[] certs=null;
				String dir = enlace.getUrlcompleta();
				URL urlaux;
				try {
					urlaux = new URL(dir);
				
				HttpsURLConnection con = (HttpsURLConnection) urlaux.openConnection();
				con.connect();
				certs = con.getServerCertificates();
				} catch (MalformedURLException e) {
					Logger.getGlobal().log(Level.SEVERE, "URL introducida no es correcta" + e.getMessage());
				} catch (IOException e) {
					Logger.getGlobal().log(Level.SEVERE, "URL introducida no es correcta 2" + e.getMessage());
				}
				return certs;
			}
}