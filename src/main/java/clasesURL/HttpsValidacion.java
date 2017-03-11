package clasesURL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import DTO.MiUrl;

@Component
public class HttpsValidacion {

			
			String inputLine = "";
			String inputText = "";
			final int FINAL_CADENA_HTTP=5;
			final int INICIO_CADENA_HTTP=1;
			
			
			public List<MiUrl> obtenerUrls(String urlPrincipal){
				
				ArrayList<MiUrl> urlsPagWeb = new ArrayList<MiUrl>();
				InputStreamReader isPaginas = null;
				BufferedReader bfPaginas = null;
				URLConnection conexion = null;
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
					conexion = url.openConnection();
					conexion.connect();

					// Lectura
					isPaginas = new InputStreamReader(url.openStream());
					bfPaginas = new BufferedReader(isPaginas);
					
					// Introducimos las lineas leidas en un "texto"
					while ((inputLine = bfPaginas.readLine()) != null) {
						inputText = inputText + inputLine;
					}

	
					
					inicioLink = inputText.indexOf(etiquetaEnlace);
					
					while(inicioLink != -1){
						
						substringaux = inputText.substring(inicioLink,inicioLink+200);
						if(substringaux.contains("\"")){
							substringaux = substringaux.split("\"")[1];
						}else{
							if(substringaux.contains("'")){
								substringaux = substringaux.split("'")[1];
						}
						}
							if(esHTTP(substringaux)){
								urlaux = new MiUrl(substringaux);
								urlsPagWeb.add(urlaux);
							}

						inicioLink = inputText.indexOf(etiquetaEnlace,inicioLink+1);
					}
	
					
				} catch (MalformedURLException e) {
					Logger.getGlobal().log(Level.SEVERE, "URL introducida no es correcta" + e.getMessage());
				} catch (IOException e) {
					Logger.getGlobal().log(Level.SEVERE, "URL introducida no es correcta 2" + e.getMessage());
				}
				inputText="";
				return (urlsPagWeb);
				
			}
			
			public boolean esHTTP(String link){
				if(link.contains("http")){
					return true;
				}
				return false;
			}
	
}