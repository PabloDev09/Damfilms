package es.iesjandula.damfilms_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.iesjandula.damfilms_server.config.MyUserDetailsService;
import es.iesjandula.damfilms_server.dtos.UserRegistrationDto;
import es.iesjandula.damfilms_server.repositories.IDocumentalRepository;
import es.iesjandula.damfilms_server.repositories.IPeliculaRepository;
import es.iesjandula.damfilms_server.repositories.ISerieRepository;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;

@Controller
public class ThymeleafController 
{
	@Autowired
    private MyUserDetailsService myUserDetailsService ;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder ;
	
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
    
    @RequestMapping("/signin.html")
    public String register(Model model)
    {
    	// Asociamos "user" como modelo que almacenará los datos del formulario de "register.html"
        model.addAttribute("user", new UserRegistrationDto()) ;
        
        return "signin.html";
    }
    
    @RequestMapping(method=RequestMethod.POST, value= "/signin")
    public String processRegistration(UserRegistrationDto userDto, Model model)
    {
        // Comprobamos si el usuario ya existe
    	if (this.myUserDetailsService.existsByUsername(userDto.getUsername()))
        {
    		// Si existe, añadimos al modelo "registerError" a "true" para indicar que hay un error
            model.addAttribute("registerError", true) ;
            
            // Además, añadimos al modelo "registerErrorMessage" para enviar el texto "El usuario ya existe"
            model.addAttribute("registerErrorMessage", "El usuario ya existe") ;
            
            return "signin.html" ;
        }

    	// En caso de que no exista el usuario, seguimos el flujo normal de creación de usuario
    	
        // Ciframos la contraseña
        String encodedPassword = this.passwordEncoder.encode(userDto.getPassword());

        try
        {
        	// Guardamos el nuevo usuario en la base de datos
			this.myUserDetailsService.saveUser(userDto, encodedPassword) ;
		}
        catch (DamfilmsServerException damfilmsServerException)
        {
    		// Si existe, añadimos al modelo "registerError" a "true" para indicar que hay un error
            model.addAttribute("registerError", true) ;
            
            // Además, añadimos al modelo "registerErrorMessage" para enviar el texto del error
            model.addAttribute("registerErrorMessage", damfilmsServerException.getMessage()) ;
            
            return "signin.html" ;
		}

        return "login.html" ;
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
    	model.addAttribute("ultimasLlegadas", iDocumentalRepository.findTop10ByOrderByFechaEstrenoDesc()) ;
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
    
    @RequestMapping("/forbidden.html")
    public String forbidden()
    {
        return "forbidden.html";
    }
    
    @RequestMapping("/not-found.html")
    public String notFound()
    {
        return "not-found.html";
    }
}