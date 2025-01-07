package es.iesjandula.damfilms_server.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.iesjandula.damfilms_server.dtos.DocumentalDescripcion;
import es.iesjandula.damfilms_server.dtos.DocumentalDetalle;
import es.iesjandula.damfilms_server.dtos.PeliculaDescripcion;
import es.iesjandula.damfilms_server.dtos.PeliculaDetallada;
import es.iesjandula.damfilms_server.dtos.SerieDescripcion;
import es.iesjandula.damfilms_server.dtos.SerieDetalle;
import es.iesjandula.damfilms_server.entities.Documental;
import es.iesjandula.damfilms_server.entities.DocumentalVisualizado;
import es.iesjandula.damfilms_server.entities.Episodio;
import es.iesjandula.damfilms_server.entities.Pelicula;
import es.iesjandula.damfilms_server.entities.PeliculaVisualizada;
import es.iesjandula.damfilms_server.entities.Serie;
import es.iesjandula.damfilms_server.entities.SerieVisualizada;
import es.iesjandula.damfilms_server.entities.Temporada;
import es.iesjandula.damfilms_server.repositories.IDocumentalRepository;
import es.iesjandula.damfilms_server.repositories.IDocumentalVisualizadoRepository;
import es.iesjandula.damfilms_server.repositories.IEpisodioRepository;
import es.iesjandula.damfilms_server.repositories.IPeliculaRepository;
import es.iesjandula.damfilms_server.repositories.IPeliculaVisualizadaRepository;
import es.iesjandula.damfilms_server.repositories.ISerieRepository;
import es.iesjandula.damfilms_server.repositories.ISerieVisualizadaRepository;
import es.iesjandula.damfilms_server.repositories.ITemporadaRepository;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class DamfilsController {
	
	@Autowired
	private IDocumentalRepository iDocumentalRepository;
	
    @Autowired
    private IEpisodioRepository iEpisodioRepository;
    
    @Autowired
    private ITemporadaRepository iTemporadaRepository;
    
    @Autowired
    private ISerieRepository iSerieRepository;
	
	@Autowired
	private IPeliculaRepository iPeliculaRepository;
	
	@Autowired 
	private IDocumentalVisualizadoRepository iDocumentalVisualizadoRepository;
	
	@Autowired
	private IPeliculaVisualizadaRepository iPeliculaVisualizadaRepository;
	
	@Autowired
	private ISerieVisualizadaRepository iSerieVisualizadaRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/documentales")
	public ResponseEntity<?> listaDocumentales() 
	{
		
		try 
		{
			
			List<Documental> listaDocumentales;
			
			if(this.iDocumentalRepository.findAll().isEmpty()) 
			{
				String mensajeError = "No se ha encontrado ningún documental";
				log.error(mensajeError);
				throw new DamfilmsServerException(1, mensajeError);
				
			}
			
			listaDocumentales = this.iDocumentalRepository.findAll();
			
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
	public ResponseEntity<?> detalleDocumental(@RequestParam(name = "titulo", required = true) String titulo,
									 			@RequestParam(name = "fechaEstreno", required = true) Date fechaEstreno) 
	{
		
		try 
		{
			
			if(this.iDocumentalRepository.encontrarDocumentalDetallado(titulo, fechaEstreno)==null) 
			{
				String mensajeError = "No se ha encontrado ningún documental con ese titulo y fecha de estreno";
				log.error(mensajeError);
				throw new DamfilmsServerException(2, mensajeError);
				
			}
			
			DocumentalDetalle documentalDetalle = new DocumentalDetalle();
			documentalDetalle = this.iDocumentalRepository.encontrarDocumentalDetallado(titulo, fechaEstreno);
			
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
			
			if(this.iDocumentalRepository.encontrarDocumentalDescripcion(titulo, fechaEstreno)==null) 
			{
				String mensajeError = "No se ha encontrado ningún documental con ese titulo y fecha de estreno";
				log.error(mensajeError);
				throw new DamfilmsServerException(3, mensajeError);
				
			}
			
			DocumentalDescripcion documentalDescripcion = new DocumentalDescripcion();
			documentalDescripcion = this.iDocumentalRepository.encontrarDocumentalDescripcion(titulo, fechaEstreno);
			
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
			
			List<Pelicula> listaPeliculas;
			
			if(this.iPeliculaRepository.findAll().isEmpty()) 
			{
				String mensajeError = "No se ha encontrado ninguna película";
				log.error(mensajeError);
				throw new DamfilmsServerException(4, mensajeError);
				
			}
			
			listaPeliculas = this.iPeliculaRepository.findAll();
			
			return ResponseEntity.ok(listaPeliculas);
			
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
	public ResponseEntity<?> detallePelicula(@RequestParam(name = "titulo", required = true) String titulo,
											  @RequestParam(name = "fechaEstreno", required = true) Date fechaEstreno)  
	{
		try 
		{
			
			if(this.iPeliculaRepository.encontrarPeliculaDetallada(titulo, fechaEstreno)==null) 
			{
				String mensajeError = "No se ha encontrado ninguna película con ese titulo y fecha de estreno";
				log.error(mensajeError);
				throw new DamfilmsServerException(5, mensajeError);
				
			}
			
			PeliculaDetallada peliculaDetallada = new PeliculaDetallada();
			peliculaDetallada = this.iPeliculaRepository.encontrarPeliculaDetallada(titulo, fechaEstreno);
			
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
			
			if(this.iPeliculaRepository.encontrarPeliculaDescripcion(titulo, fechaEstreno)==null) 
			{
				String mensajeError = "No se ha encontrado ninguna película con ese titulo y fecha de estreno";
				log.error(mensajeError);
				throw new DamfilmsServerException(6, mensajeError);
				
			}
			
			PeliculaDescripcion peliculaDescripcion = new PeliculaDescripcion();
			peliculaDescripcion = this.iPeliculaRepository.encontrarPeliculaDescripcion(titulo, fechaEstreno);
			
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/peliculas/visualizadas")
	public ResponseEntity<?> listaPeliculasVisualizadas() 
	{
		try 
		{
			
			List<PeliculaVisualizada> listaPeliculasVisualizadas;
			
			if(this.iPeliculaVisualizadaRepository.findAll().isEmpty()) 
			{
				String mensajeError = "No se ha encontrado ninguna película visualizada";
				log.error(mensajeError);
				throw new DamfilmsServerException(4, mensajeError);
				
			}
			
			listaPeliculasVisualizadas = this.iPeliculaVisualizadaRepository.findAll();
			
			return ResponseEntity.ok(listaPeliculasVisualizadas);
			
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/documentales/visualizados")
	public ResponseEntity<?> listaDocumentalesVisualizados() 
	{
		try 
		{
			
			List<DocumentalVisualizado> listaDocumentalesVisualizados;
			
			if(this.iDocumentalVisualizadoRepository.findAll().isEmpty()) 
			{
				String mensajeError = "No se ha encontrado ningún documental visualizado";
				log.error(mensajeError);
				throw new DamfilmsServerException(4, mensajeError);
				
			}
			
			listaDocumentalesVisualizados = this.iDocumentalVisualizadoRepository.findAll();
			
			return ResponseEntity.ok(listaDocumentalesVisualizados);
			
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
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/series/visualizadas")
	public ResponseEntity<?> listaSeriesVisualizadas() 
	{
		try 
		{
			
			List<SerieVisualizada> listaSeriesVisualizadas;
			
			if(this.iSerieVisualizadaRepository.findAll().isEmpty()) 
			{
				String mensajeError = "No se ha encontrado ninguna serie visualizada";
				log.error(mensajeError);
				throw new DamfilmsServerException(4, mensajeError);
				
			}
			
			listaSeriesVisualizadas = this.iSerieVisualizadaRepository.findAll();
			
			return ResponseEntity.ok(listaSeriesVisualizadas);
			
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
	/** episodio */
	  @RequestMapping(method = RequestMethod.GET, value = "/serie/{serieId}/episodio/{episodioId}")
	    public ResponseEntity<?> getEpisodioById(@RequestParam(name = "serieId") Long serieId, 
	    									@RequestParam(name = "episodioId") Long episodioId) {
	        try {
	            Episodio episodio = iEpisodioRepository.findByIdAndSerieId(episodioId, serieId);
	            if (episodio == null) {
	                throw new DamfilmsServerException(1, "Episodio no encontrado para la serie especificada");
	            }
	            return ResponseEntity.ok(episodio);
	        } catch (DamfilmsServerException e) {
	            return ResponseEntity.status(404).body(e.getBodyExceptionMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error interno del servidor");
	        }
	    }
	  
	  
	    @RequestMapping(method = RequestMethod.GET, value = "/temporadas/{temporadaId}/episodios")
	    public ResponseEntity<?> getEpisodiosByTemporada(@RequestParam(name = "temporadaId") Long temporadaId) {
	        try {
	            List<Episodio> episodios = iEpisodioRepository.findByTemporadaId(temporadaId);
	            if (episodios.isEmpty()) {
	                throw new DamfilmsServerException(2, "No se encontraron episodios para la temporada especificada");
	            }
	            return ResponseEntity.ok(episodios);
	        } catch (DamfilmsServerException e) {
	            return ResponseEntity.status(404).body(e.getBodyExceptionMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error interno del servidor");
	        }
	    }
	    
	    /** Temporada */
	    
	    @RequestMapping(method = RequestMethod.GET, value = "/temporadas/{temporadaId}")
	    public ResponseEntity<?> getTemporadaById(@RequestParam(name = "temporadaId") Long temporadaId) {
	        try {
	            Temporada temporada = iTemporadaRepository.findById(temporadaId).orElse(null);
	            if (temporada == null) {
	                throw new DamfilmsServerException(3, "Temporada no encontrada");
	            }
	            return ResponseEntity.ok(temporada);
	        } catch (DamfilmsServerException e) {
	            return ResponseEntity.status(404).body(e.getBodyExceptionMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error interno del servidor");
	        }
	    }

	    @RequestMapping(method = RequestMethod.GET, value = "/series/{serieId}/temporadas")
	    public ResponseEntity<?> getTemporadasBySerie(@RequestParam(name = "serieId") Long serieId) {
	        try {
	            List<Temporada> temporadas = iTemporadaRepository.findBySerieId(serieId);
	            if (temporadas.isEmpty()) {
	                throw new DamfilmsServerException(4, "No se encontraron temporadas para la serie especificada");
	            }
	            return ResponseEntity.ok(temporadas);
	        } catch (DamfilmsServerException e) {
	            return ResponseEntity.status(404).body(e.getBodyExceptionMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error interno del servidor");
	        }
	    }
	    
	    @RequestMapping(method = RequestMethod.GET, value = "/series/{serieId}")
	    public ResponseEntity<?> getSerieById(@RequestParam(name = "serieId") Long serieId) {
	        try {
	            Serie serie = iSerieRepository.findById(serieId).orElse(null);
	            if (serie == null) {
	                throw new DamfilmsServerException(5, "Serie no encontrada");
	            }
	            return ResponseEntity.ok(serie);
	        } catch (DamfilmsServerException e) {
	            return ResponseEntity.status(404).body(e.getBodyExceptionMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error interno del servidor");
	        }
	    }

	    @RequestMapping(method = RequestMethod.GET, value = "/series/{genero}")
	    public ResponseEntity<?> getSeriesByGenero(@RequestParam(name = "Genero") String genero) {
	        try {
	            List<Serie> series = iSerieRepository.findByGenero(genero);
	            if (series.isEmpty()) {
	                throw new DamfilmsServerException(6, "No se encontraron series para el género especificado");
	            }
	            return ResponseEntity.ok(series);
	        } catch (DamfilmsServerException e) {
	            return ResponseEntity.status(404).body(e.getBodyExceptionMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error interno del servidor");
	        }
	    }
	    @RequestMapping(method = RequestMethod.GET, value = "/series/detalle")
	    public ResponseEntity<?> getDetalleSerie(@RequestParam(name = "titulo", required = true) String titulo,
	                                             @RequestParam(name = "fechaEstreno", required = true) Date fechaEstreno) 
	    {
	        try 
	        {
	            // Verificar si la serie existe
	            if (this.iSerieRepository.encontrarSerieDetallada(titulo, fechaEstreno) == null) 
	            {
	                String mensajeError = "No se ha encontrado ninguna serie con ese título y fecha de estreno";
	                log.error(mensajeError);
	                throw new DamfilmsServerException(5, mensajeError);
	            }

	            // Crear un DTO para la serie detallada
	            SerieDetalle serieDetalle = new SerieDetalle();
	            serieDetalle = this.iSerieRepository.encontrarSerieDetallada(titulo, fechaEstreno);
				
				
	            // Retornar la respuesta con la serie detallada
	            return ResponseEntity.ok(serieDetalle);
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
	    

@RequestMapping(method = RequestMethod.GET, value = "/series/descripcion")
public ResponseEntity<?> getDescripcionSerie(@RequestParam(name = "titulo", required = true) String titulo,
                                              @RequestParam(name = "fechaEstreno", required = true) Date fechaEstreno) 
{
    try 
    {
        // Verificar si la serie existe
        if (this.iSerieRepository.encontrarSerieDescripcion(titulo, fechaEstreno) == null) 
        {
            String mensajeError = "No se ha encontrado ninguna serie con ese título y fecha de estreno";
            log.error(mensajeError);
            throw new DamfilmsServerException(6, mensajeError);
        }

        // Crear un DTO para la descripción de la serie
        SerieDescripcion serieDescripcion = new SerieDescripcion();
        serieDescripcion = this.iSerieRepository.encontrarSerieDescripcion(titulo, fechaEstreno);

        // Retornar la respuesta con la descripción de la serie
        return ResponseEntity.ok(serieDescripcion);
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


