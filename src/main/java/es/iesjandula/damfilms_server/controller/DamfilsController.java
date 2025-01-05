package es.iesjandula.damfilms_server.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.iesjandula.damfilms_server.dtos.DocumentalDescripcion;
import es.iesjandula.damfilms_server.dtos.DocumentalDetalle;
import es.iesjandula.damfilms_server.dtos.PeliculaDescripcion;
import es.iesjandula.damfilms_server.dtos.PeliculaDetallada;
import es.iesjandula.damfilms_server.entities.Documental;
import es.iesjandula.damfilms_server.entities.DocumentalVisualizado;
import es.iesjandula.damfilms_server.entities.Pelicula;
import es.iesjandula.damfilms_server.entities.PeliculaVisualizada;
import es.iesjandula.damfilms_server.entities.SerieVisualizada;
import es.iesjandula.damfilms_server.repositories.IDocumentalRepository;
import es.iesjandula.damfilms_server.repositories.IDocumentalVisualizadoRepository;
import es.iesjandula.damfilms_server.repositories.IPeliculaRepository;
import es.iesjandula.damfilms_server.repositories.IPeliculaVisualizadaRepository;
import es.iesjandula.damfilms_server.repositories.ISerieVisualizadaRepository;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class DamfilsController {
	
	@Autowired
	private IDocumentalRepository documentalRepository;
	
	@Autowired
	private IPeliculaRepository peliculaRepository;
	
	@Autowired 
	private IDocumentalVisualizadoRepository documentalVisualizadoRepository;
	
	@Autowired
	private IPeliculaVisualizadaRepository peliculaVisualizadaRepository;
	
	@Autowired
	private ISerieVisualizadaRepository serieVisualizadaRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/documentales")
	public ResponseEntity<?> listaDocumentales() 
	{
		
		try 
		{
			
			List<Documental> listaDocumentales;
			
			if(this.documentalRepository.findAll().isEmpty()) 
			{
				String mensajeError = "No se ha encontrado ningún documental";
				log.error(mensajeError);
				throw new DamfilmsServerException(1, mensajeError);
				
			}
			
			listaDocumentales = this.documentalRepository.findAll();
			
			return ResponseEntity.ok(listaDocumentales);
			
		}
		catch (DamfilmsServerException damfilmsServerException) 
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		}
		catch (Exception exception) 
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);
			
			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/documentales/detalle")
	public ResponseEntity<?> detallesDocumental(@RequestParam(name = "titulo", required = true) String titulo,
									 			@RequestParam(name = "fechaEstreno", required = true) Date fechaEstreno) 
	{
		
		try 
		{
			
			if(this.documentalRepository.encontrarDocumentalDetallado(titulo, fechaEstreno)==null) 
			{
				String mensajeError = "No se ha encontrado ningún documental con ese titulo y fecha de estreno";
				log.error(mensajeError);
				throw new DamfilmsServerException(2, mensajeError);
				
			}
			
			DocumentalDetalle documentalDetalle = new DocumentalDetalle();
			documentalDetalle = this.documentalRepository.encontrarDocumentalDetallado(titulo, fechaEstreno);
			
			return ResponseEntity.ok(documentalDetalle);
			
		}
		catch (DamfilmsServerException damfilmsServerException) 
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		}
		catch (Exception exception) 
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);
			
			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/documentales/descripcion")
	public ResponseEntity<?> descripcionDocumental(@RequestParam(name = "titulo", required = true) String titulo,
			 									   @RequestParam(name = "fechaEstreno", required = true) Date fechaEstreno)  
	{
		try 
		{
			
			if(this.documentalRepository.encontrarDocumentalDescripcion(titulo, fechaEstreno)==null) 
			{
				String mensajeError = "No se ha encontrado ningún documental con ese titulo y fecha de estreno";
				log.error(mensajeError);
				throw new DamfilmsServerException(3, mensajeError);
				
			}
			
			DocumentalDescripcion documentalDescripcion = new DocumentalDescripcion();
			documentalDescripcion = this.documentalRepository.encontrarDocumentalDescripcion(titulo, fechaEstreno);
			
			return ResponseEntity.ok(documentalDescripcion);
			
		}
		catch (DamfilmsServerException damfilmsServerException) 
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		}
		catch (Exception exception) 
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);
			
			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/peliculas")
	public ResponseEntity<?> listaPeliculas() 
	{
		try 
		{
			
			List<Pelicula> listaDocumentales;
			
			if(this.peliculaRepository.findAll().isEmpty()) 
			{
				String mensajeError = "No se ha encontrado ninguna película";
				log.error(mensajeError);
				throw new DamfilmsServerException(4, mensajeError);
				
			}
			
			listaDocumentales = this.peliculaRepository.findAll();
			
			return ResponseEntity.ok(listaDocumentales);
			
		}
		catch (DamfilmsServerException damfilmsServerException) 
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		}
		catch (Exception exception) 
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);
			
			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/peliculas/detalle")
	public ResponseEntity<?> detallesPelicula(@RequestParam(name = "titulo", required = true) String titulo,
											  @RequestParam(name = "fechaEstreno", required = true) Date fechaEstreno)  
	{
		try 
		{
			
			if(this.peliculaRepository.encontrarPeliculaDetallado(titulo, fechaEstreno)==null) 
			{
				String mensajeError = "No se ha encontrado ninguna película con ese titulo y fecha de estreno";
				log.error(mensajeError);
				throw new DamfilmsServerException(5, mensajeError);
				
			}
			
			PeliculaDetallada peliculaDetallada = new PeliculaDetallada();
			peliculaDetallada = this.peliculaRepository.encontrarPeliculaDetallado(titulo, fechaEstreno);
			
			return ResponseEntity.ok(peliculaDetallada);
			
		}
		catch (DamfilmsServerException damfilmsServerException) 
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		}
		catch (Exception exception) 
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);
			
			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/peliculas/descripcion")
	public ResponseEntity<?> descripcionPelicula(@RequestParam(name = "titulo", required = true) String titulo,
			 						  			 @RequestParam(name = "fechaEstreno", required = true) Date fechaEstreno) 
	{
		try 
		{
			
			if(this.peliculaRepository.encontrarPeliculaDescripcion(titulo, fechaEstreno)==null) 
			{
				String mensajeError = "No se ha encontrado ninguna película con ese titulo y fecha de estreno";
				log.error(mensajeError);
				throw new DamfilmsServerException(6, mensajeError);
				
			}
			
			PeliculaDescripcion peliculaDescripcion = new PeliculaDescripcion();
			peliculaDescripcion = this.peliculaRepository.encontrarPeliculaDescripcion(titulo, fechaEstreno);
			
			return ResponseEntity.ok(peliculaDescripcion);
			
		}
		catch (DamfilmsServerException damfilmsServerException) 
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		}
		catch (Exception exception) 
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);
			
			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/peliculas-visualizadas")
	public ResponseEntity<?> listaPelicualasVisualizadas() 
	{
		try 
		{
			
			List<PeliculaVisualizada> listaDocumentales;
			
			if(this.peliculaVisualizadaRepository.findAll().isEmpty()) 
			{
				String mensajeError = "No se ha encontrado ninguna película visualizada";
				log.error(mensajeError);
				throw new DamfilmsServerException(4, mensajeError);
				
			}
			
			listaDocumentales = this.peliculaVisualizadaRepository.findAll();
			
			return ResponseEntity.ok(listaDocumentales);
			
		}
		catch (DamfilmsServerException damfilmsServerException) 
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		}
		catch (Exception exception) 
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);
			
			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/documentales-visualizadas")
	public ResponseEntity<?> listaDocumentalesVisualizadas() 
	{
		try 
		{
			
			List<DocumentalVisualizado> listaDocumentales;
			
			if(this.documentalVisualizadoRepository.findAll().isEmpty()) 
			{
				String mensajeError = "No se ha encontrado ningún documental visualizado";
				log.error(mensajeError);
				throw new DamfilmsServerException(4, mensajeError);
				
			}
			
			listaDocumentales = this.documentalVisualizadoRepository.findAll();
			
			return ResponseEntity.ok(listaDocumentales);
			
		}
		catch (DamfilmsServerException damfilmsServerException) 
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		}
		catch (Exception exception) 
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);
			
			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/series-visualizadas")
	public ResponseEntity<?> listaSeriesVisualizadas() 
	{
		try 
		{
			
			List<SerieVisualizada> listaDocumentales;
			
			if(this.serieVisualizadaRepository.findAll().isEmpty()) 
			{
				String mensajeError = "No se ha encontrado ninguna serie visualizada";
				log.error(mensajeError);
				throw new DamfilmsServerException(4, mensajeError);
				
			}
			
			listaDocumentales = this.serieVisualizadaRepository.findAll();
			
			return ResponseEntity.ok(listaDocumentales);
			
		}
		catch (DamfilmsServerException damfilmsServerException) 
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		}
		catch (Exception exception) 
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);
			
			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
		
	}

}
