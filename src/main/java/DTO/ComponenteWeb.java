package DTO;

public class ComponenteWeb {

	
	//tipo del componente
	private String tipo;
	//enlace que pueda tener
	private String contenido;
	//http o https
	private String seguridad;
	
	public ComponenteWeb(String tipo, String contenido, String seguridad){
		this.tipo = tipo;
		this.contenido = contenido;
		this.seguridad = seguridad;
		
		
	}
	
	
	public String getSeguridad() {
		return seguridad;
	}
	public void setSeguridad(String seguridad) {
		this.seguridad = seguridad;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
}
