package es.iesjandula.damfilms_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.iesjandula.damfilms_server.repositories.IDocumentalRepository;
import es.iesjandula.damfilms_server.repositories.IPeliculaRepository;
import es.iesjandula.damfilms_server.repositories.ISerieRepository;

@Controller
public class SpringSecurityController {
	
	@Autowired
	private IPeliculaRepository iPeliculaRepository ;
	
	@Autowired
	private ISerieRepository iSerieRepository;
	
	@Autowired
	private IDocumentalRepository iDocumentalRepository;
	

    /* Ruta por defecto, redirige a index.html */
    @RequestMapping("/")
    public String inicioDefault() {
        return "inicio.html";
    }

    /* Ruta para la página de inicio */
    @RequestMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    }

    /* Ruta para la página principal (home) */
    @RequestMapping("/home")
    public String home() {
        return "home.html";
    }

    /* Ruta para el catálogo de series */
    @RequestMapping("/series")
    public String series(Model model) {
    	model.addAttribute("ultimasLllegadas", iSerieRepository.findTop10ByOrderByFechaEstrenoDesc()) ;
    	model.addAttribute("mejorClasificadas", iSerieRepository.findTop10ByOrderByClasificacionDesc()) ;
    	model.addAttribute("todasLasSeries", iSerieRepository.findAll()) ;
        return "series.html";
    }

    /* Ruta para el catálogo de películas */
    @RequestMapping("/peliculas")
    public String peliculas(Model model) {
    	model.addAttribute("ultimasLlegadas", iPeliculaRepository.findTop10ByOrderByFechaEstrenoDesc()) ;
    	model.addAttribute("mejorClasificadas", iPeliculaRepository.findTop10ByOrderByClasificacionDesc()) ;
    	model.addAttribute("todasLasPeliculas", iPeliculaRepository.findAll()) ;
        return "peliculas.html";
    }

    /* Ruta para el catálogo de documentales */
    @RequestMapping("/documentales")
    public String documentales(Model model) {
    	model.addAttribute("ultimasLllegadas", iDocumentalRepository.findTop10ByOrderByFechaEstrenoDesc()) ;
    	model.addAttribute("mejorClasificadas", iDocumentalRepository.findTop10ByOrderByClasificacionDesc()) ;
    	model.addAttribute("todosLosDocumentales", iDocumentalRepository.findAll()) ;
        return "documentales.html";
    }
    @RequestMapping("/modo")
    public String modo() {
        return "modo.html";
    }
    @RequestMapping("/usuarios")
    public String usuarios() {
        return "usuarios.html";
    }
    
    /* Ruta para la página de registro */
    @RequestMapping("/signin")
    public String signIn() {
        return "signin.html";
    }

    /* Ruta para la página de inicio de sesión */
    @RequestMapping("/login")
    public String logIn() {
        return "login.html";
    }

    /* Ruta para la página de configuración principal */
    @RequestMapping("/configuracion")
    public String configuracion() {
        return "configuracion.html";
    }

    /* Ruta para la página de acuerdo de licencia de usuario final (EULA) */
    @RequestMapping("/eula")
    public String eula() {
        return "eula.html";
    }

    /* Ruta para la página de error de inicio de sesión */
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }
}