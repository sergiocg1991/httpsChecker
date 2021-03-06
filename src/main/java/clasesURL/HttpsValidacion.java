package clasesURL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;

import DTO.ComponenteWeb;
import DTO.ListaComponenteWeb;
import DTO.MiUrl;
import oracle.jrockit.jfr.tools.ConCatRepository;

@Component
public class HttpsValidacion {


			String inputLine = "";
			String inputText = "";
			final int FINAL_CADENA_HTTP=5;
			final int INICIO_CADENA_HTTP=1;
			int TAMANIO_INICIO_ETIQUETA =200;
			boolean flag = false;
			Certificate[] pene;
			HttpsURLConnection con;


			public ListaComponenteWeb obtenerUrls(String urlPrincipal){

				
				InputStreamReader isPaginas = null;
				BufferedReader bfPaginas = null;
				HttpURLConnection conexion = null;
				URL url;
				String etiquetaEnlace = "<";
				String substringaux = "";
				String[] substringaux2;
				String substringaux3 = "";
				int inicioLink = 0;
				String separadores = "[\\ \\>]";
				ListaComponenteWeb lista = new ListaComponenteWeb();
				
				try {
					// Se abre la conexi�n
					url = new URL(urlPrincipal);
					conexion =(HttpURLConnection) url.openConnection();
					conexion.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Safari/537.36");
					conexion.connect();

					// Lectura
					isPaginas = new InputStreamReader(url.openStream());
					bfPaginas = new BufferedReader(isPaginas);
					
					// Introducimos el c�digo HTML de la pagina en un string 
					while ((inputLine = bfPaginas.readLine()) != null) {
						inputText = inputText + inputLine;
						
					}

	
					//Buscamos los < que abren las etiquetas en html
					inicioLink = inputText.indexOf(etiquetaEnlace);
					
					
					while(inicioLink != -1){
						

						if((inicioLink+TAMANIO_INICIO_ETIQUETA)>inputText.length()){
							substringaux = inputText.substring(inicioLink,inputText.length());
						}else{
						
//						substringaux = inputText.substring(inicioLink,inicioLink+TAMANIO_INICIO_ETIQUETA);
						
							
						substringaux = inputText.substring(inicioLink,inputText.length());
						substringaux2 = substringaux.split("/>");
						substringaux3 = substringaux2[0];
						
						//Para evitar fallos al buscar
						substringaux3 = substringaux3.toLowerCase();
						}
							
						
						if ("img".compareTo((substringaux3.split(separadores)[0]).substring(1)) == 0) {

							/*  Descomentar para mostrar toda la imagen mediante html
							String contenido = substringaux3;
							*/
							if(substringaux3.contains("src")){
								String auxs = (substringaux3.split("src")[1]).substring(1);
								String contenido = "";
								if(auxs.contains("\"")){
									contenido = auxs.split("\"")[1];
								}
								
								if(auxs.contains("\'")){
									contenido = auxs.split("\'")[1];
								}
							
								lista.addImg(contenido, "a");
							}
						}
						if ("a".compareTo((substringaux3.split(separadores)[0]).substring(1)) == 0) {
							if(substringaux3.contains("href")){
								String auxs = (substringaux3.split("href")[1]).substring(1);
								String contenido = "";
								
								if(auxs.contains(">")){
									contenido = auxs.split(">")[0];
									if(esHTTPS(contenido)){
										contenido=contenido.split("\"")[1];
										lista.addHttps(contenido, "HTTPS");
										MiUrl enlace = new MiUrl();
										enlace.setUrlcompleta(contenido);
										pene=this.getCerts(enlace);
									}else{
										if(esHTTP(contenido)){
											lista.addHttp(contenido, "HTTP");
										}
									}
								}
								
							}
						}
						if ("script".compareTo((substringaux3.split(separadores)[0]).substring(1)) == 0) {
							if(substringaux3.contains("src=")){
								String auxs = (substringaux3.split("src=")[1]).substring(1);
								String contenido = "";
								
								if(auxs.contains("\"")){
									contenido = auxs.split("\"")[1];
								}
								
								if(auxs.contains("\'")){
									contenido = auxs.split("\'")[1];
								}
								
								lista.addScript(contenido, "a");
							}
						}
							
						//buscamos el siguente enlace
						inicioLink = inputText.indexOf(etiquetaEnlace,inicioLink+1);
					}
	
					
				} catch (MalformedURLException e) {
					Logger.getGlobal().log(Level.SEVERE, e.getMessage());
				} catch (IOException e) {
					Logger.getGlobal().log(Level.SEVERE, e.getMessage());
				}
				//borramos inputText para evitar que se sobeescriba el analisis con futuras busquedas
				inputText="";
				return lista;
				
			}
			
			public boolean esHTTP(String link){
				if(link.startsWith("\"http")){
					return true;
				}
				return false;
			}
			public boolean esHTTPS(String link){
				if(link.startsWith("\"https")){
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
					
				
				con = (HttpsURLConnection) urlaux.openConnection();
				
				con.connect();
				certs = con.getServerCertificates();
				con.disconnect();
				
				} catch (MalformedURLException e) {
					Logger.getGlobal().log(Level.SEVERE, "URL introducida no es correcta" + e.getMessage());
				} catch (IOException e) {
					Logger.getGlobal().log(Level.SEVERE, "URL introducida no es correcta 2" + e.getMessage());
				}
			
				return certs;
			}
}