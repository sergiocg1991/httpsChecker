package HelloWorldController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import DTO.ComponenteWeb;
import DTO.ListaComponenteWeb;
import DTO.MiUrl;
import clasesURL.HttpsValidacion;





@Controller
public class HelloWorldController {
	
	@Autowired
	private HttpsValidacion validacion;
	
	@RequestMapping("/hello")
	public ModelAndView welcomeMessage(
			@RequestParam(value = "name", required = false) String name) {
		

		name = (name.split(","))[0];
		
	
		ListaComponenteWeb misurls = validacion.obtenerUrls(name);
		
//		int numeroImg = misurls.get;
//		int numeroHref = 
		
		List<String> nombreurl = new ArrayList<String>();
		
		for(ComponenteWeb urla: misurls.getLista()){
			nombreurl.add(urla.getTipo() + "     -       " + urla.getContenido());
			}
		
		ModelAndView view = new ModelAndView("hello");
		view.addObject("name", name);
		view.addObject("enlaces", nombreurl);
		view.addObject("numImg", misurls.getImg());
		view.addObject("numHttp", misurls.getHttp());
		view.addObject("numHttps", misurls.getHttps());
		view.addObject("numScript", misurls.getScript());

		return view;
	}
	
	@RequestMapping("/detalles")
	public ModelAndView detallesMessage() {
		
		
		ModelAndView view = new ModelAndView("detalles");
		view.addObject("name", "hola");
		view.addObject("urls", "nombre");

		return view;
	}
}