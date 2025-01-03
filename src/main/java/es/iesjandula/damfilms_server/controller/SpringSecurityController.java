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
    public String indexDefault() {
        return "index.html";
    }

    /* Ruta para la página de inicio */
    @RequestMapping("/index.html")
    public String index() {
        return "index.html";
    }

    /* Ruta para la página principal (home) */
    @RequestMapping("/home.html")
    public String home() {
        return "home.html";
    }

    /* Ruta para el catálogo de series */
    @RequestMapping("/catalog-series.html")
    public String catalogSeries(Model model) {
    	model.addAttribute("ultimasLllegadas", iSerieRepository.findTop10ByOrderByFechaEstrenoDesc()) ;
    	model.addAttribute("mejorClasificadas", iSerieRepository.findTop10ByOrderByClasificacionDesc()) ;
    	model.addAttribute("todasLasSeries", iSerieRepository.findAll()) ;
        return "catalog-series.html";
    }

    /* Ruta para el catálogo de películas */
    @RequestMapping("/catalog-movies.html")
    public String catalogMovies(Model model) {
    	model.addAttribute("ultimasLllegadas", iPeliculaRepository.findTop10ByOrderByFechaEstrenoDesc()) ;
    	model.addAttribute("mejorClasificadas", iPeliculaRepository.findTop10ByOrderByClasificacionDesc()) ;
    	model.addAttribute("todasLasPeliculas", iPeliculaRepository.findAll()) ;
        return "catalog-movies.html";
    }

    /* Ruta para el catálogo de documentales */
    @RequestMapping("/catalog-documentals.html")
    public String catalogDocumentals(Model model) {
    	model.addAttribute("ultimasLllegadas", iDocumentalRepository.findTop10ByOrderByFechaEstrenoDesc()) ;
    	model.addAttribute("mejorClasificadas", iDocumentalRepository.findTop10ByOrderByClasificacionDesc()) ;
    	model.addAttribute("todosLosDocumentales", iDocumentalRepository.findAll()) ;
        return "catalog-documentals.html";
    }

    /* Ruta para la página de registro */
    @RequestMapping("/sign_in.html")
    public String signIn() {
        return "sign_in.html";
    }

    /* Ruta para la página de inicio de sesión */
    @RequestMapping("/Log_in.html")
    public String logIn() {
        return "Log_in.html";
    }

    /* Ruta para la página de configuración principal */
    @RequestMapping("/config-principal.html")
    public String configPrincipal() {
        return "config-principal.html";
    }

    /* Ruta para la página de acuerdo de licencia de usuario final (EULA) */
    @RequestMapping("/eula.html")
    public String eula() {
        return "eula.html";
    }

    /* Ruta para la página de error de inicio de sesión */
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "Log_in.html";
    }
}