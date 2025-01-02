package es.iesjandula.damfilms_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DamfilsController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/documentales")
	public String listaDocumentales() 
	{
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/documentales/detalle")
	public String detallesDocumental() 
	{
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/documentales/descripcion")
	public String descripcionDocumental() 
	{
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/peliculas")
	public String listaPeliculas() 
	{
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/peliculas/detalle")
	public String detallesPelicul() 
	{
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/peliculas/descripcion")
	public String descripcionPelicula() 
	{
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/peliculas-visualizadas")
	public String registrarPelicualVisualizada() 
	{
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/peliculas-visualizadas")
	public String listaPelicualasVisualizadas() 
	{
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/documentales-visualizadas")
	public String registrarDocumentalVisualizada() 
	{
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/documentales-visualizadas")
	public String listaDocumentalesVisualizadas() 
	{
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/series-visualizadas")
	public String registrarSerieVisualizada() 
	{
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/series-visualizadas")
	public String listaSeriesVisualizadas() 
	{
		return null;
		
	}

}
