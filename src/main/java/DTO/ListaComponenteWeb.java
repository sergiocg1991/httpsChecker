package DTO;

import java.util.ArrayList;
import java.util.List;


public class ListaComponenteWeb {
	
	private List<ComponenteWeb> lista;
	private int img;
	private int https;
	private int http;
	private int script;
	



	public ListaComponenteWeb(){
		img=0;
		http=0;
		https=0;
		script=0;
		lista = new ArrayList<ComponenteWeb> ();
	}
	
	
	
	public boolean addImg(String contenido,String seguridad){
		
		ComponenteWeb aux = new ComponenteWeb("img",contenido,seguridad);
		
		lista.add(aux);
		
		img++;
		
		return true;
		
	}
	public boolean addScript(String contenido,String seguridad){
		
		ComponenteWeb aux = new ComponenteWeb("script",contenido,seguridad);
		
		lista.add(aux);
		
		script++;
		
		return true;
	}	
	
	public boolean addHttp(String contenido,String seguridad){
		
		ComponenteWeb aux = new ComponenteWeb("http",contenido,seguridad);
		
		lista.add(aux);
		
		http++;
		
		return true;
		
	}
	
	public boolean addHttps(String contenido,String seguridad){
		
		ComponenteWeb aux = new ComponenteWeb("https",contenido,seguridad);
		
		lista.add(aux);
		
		https++;
		
		return true;
		
	}
	
	
	public int getHttp() {
		return http;
	}


	public void setHttps(int href) {
		this.https = https;
	}
	
	public int getHttps() {
		return https;
	}


	public void setHttp(int href) {
		this.http = http;
	}
	
	
	public List<ComponenteWeb> getLista() {
		return lista;
	}


	public void setLista(List<ComponenteWeb> lista) {
		this.lista = lista;
	}


	public int getImg() {
		return img;
	}


	public void setImg(int img) {
		this.img = img;
	}

	public int getScript() {
		return script;
	}



	public void setScript(int script) {
		this.script = script;
	}




}
