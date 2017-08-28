package DTO;

import java.util.ArrayList;
import java.util.List;


public class ListaComponenteWeb {
	
	private List<ComponenteWeb> lista;
	private int img;
	private int href;
	private int script;
	
	public ListaComponenteWeb(){
		img=0;
		href=0;
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
		
		img++;
		
		return true;
	}	
	
	public boolean addHref(String contenido,String seguridad){
		
		ComponenteWeb aux = new ComponenteWeb("href",contenido,seguridad);
		
		lista.add(aux);
		
		href++;
		
		return true;
		
	}
	
	
	public int getHref() {
		return href;
	}


	public void setHref(int href) {
		this.href = href;
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





}
