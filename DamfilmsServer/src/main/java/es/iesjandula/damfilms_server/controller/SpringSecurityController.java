package es.iesjandula.damfilms_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringSecurityController {

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
    public String catalogSeries() {
        return "catalog-series.html";
    }

    /* Ruta para el catálogo de películas */
    @RequestMapping("/catalog-movies.html")
    public String catalogMovies() {
        return "catalog-movies.html";
    }

    /* Ruta para el catálogo de documentales */
    @RequestMapping("/catalog-documentals.html")
    public String catalogDocumentals() {
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