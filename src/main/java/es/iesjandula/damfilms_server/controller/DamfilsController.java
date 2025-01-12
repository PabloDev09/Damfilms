package es.iesjandula.damfilms_server.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.iesjandula.damfilms_server.dtos.DocumentalDescripcion;
import es.iesjandula.damfilms_server.dtos.DocumentalDetalle;
import es.iesjandula.damfilms_server.dtos.PeliculaDescripcion;
import es.iesjandula.damfilms_server.dtos.PeliculaDetallada;
import es.iesjandula.damfilms_server.dtos.SerieDescripcion;
import es.iesjandula.damfilms_server.dtos.SerieDetalle;
import es.iesjandula.damfilms_server.entities.Configuracion;
import es.iesjandula.damfilms_server.entities.Documental;
import es.iesjandula.damfilms_server.entities.DocumentalVisualizado;
import es.iesjandula.damfilms_server.entities.Episodio;
import es.iesjandula.damfilms_server.entities.Modo;
import es.iesjandula.damfilms_server.entities.Pelicula;
import es.iesjandula.damfilms_server.entities.PeliculaVisualizada;
import es.iesjandula.damfilms_server.entities.Serie;
import es.iesjandula.damfilms_server.entities.SerieVisualizada;
import es.iesjandula.damfilms_server.entities.Suscripcion;
import es.iesjandula.damfilms_server.entities.Temporada;
import es.iesjandula.damfilms_server.entities.Usuario;
import es.iesjandula.damfilms_server.entities.ids.SuscripcionId;
import es.iesjandula.damfilms_server.entities.ids.TemporadaId;
import es.iesjandula.damfilms_server.repositories.IConfiguracionRepository;
import es.iesjandula.damfilms_server.repositories.IDocumentalRepository;
import es.iesjandula.damfilms_server.repositories.IDocumentalVisualizadoRepository;
import es.iesjandula.damfilms_server.repositories.IEpisodioRepository;
import es.iesjandula.damfilms_server.repositories.IModoRepository;
import es.iesjandula.damfilms_server.repositories.IPeliculaRepository;
import es.iesjandula.damfilms_server.repositories.IPeliculaVisualizadaRepository;
import es.iesjandula.damfilms_server.repositories.IRoleRepository;
import es.iesjandula.damfilms_server.repositories.ISerieRepository;
import es.iesjandula.damfilms_server.repositories.ISerieVisualizadaRepository;
import es.iesjandula.damfilms_server.repositories.ISuscripcionRepository;
import es.iesjandula.damfilms_server.repositories.ITemporadaRepository;
import es.iesjandula.damfilms_server.repositories.IUsuarioRepository;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class DamfilsController
{

	@Autowired
	private IDocumentalRepository iDocumentalRepository;

	@Autowired
	private ITemporadaRepository iTemporadaRepository;

	@Autowired
	private ISerieRepository iSerieRepository;

	@Autowired
	private IEpisodioRepository iEpisodioRepository;

	@Autowired
	private IPeliculaRepository iPeliculaRepository;

	@Autowired
	private IDocumentalVisualizadoRepository iDocumentalVisualizadoRepository;

	@Autowired
	private IPeliculaVisualizadaRepository iPeliculaVisualizadaRepository;

	@Autowired
	private ISerieVisualizadaRepository iSerieVisualizadaRepository;

	@Autowired
	private IUsuarioRepository iUsuarioRepository;

	@Autowired
	private IModoRepository iModoRepository;

	@Autowired
	private IConfiguracionRepository iConfiguracionRepository;

	@Autowired
	private ISuscripcionRepository iSuscripcionRepository;

	@Autowired
	private IRoleRepository iRoleRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/documentales_list")
	public ResponseEntity<?> obtenerDocumentales()
	{

		try
		{

			List<Documental> listaDocumentales;

			if (this.iDocumentalRepository.findAll().isEmpty())
			{
				String mensajeError = "No se ha encontrado ningún documental";
				log.error(mensajeError);
				throw new DamfilmsServerException(101, mensajeError);

			}

			listaDocumentales = this.iDocumentalRepository.findAll();

			return ResponseEntity.ok(listaDocumentales);

		} catch (DamfilmsServerException damfilmsServerException)
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		} catch (Exception exception)
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/documentales/detalle")
	public ResponseEntity<?> obtenerDetalleDocumental(@RequestParam(name = "titulo", required = true) String titulo,
			@RequestParam(name = "fechaEstreno", required = true) Date fechaEstreno)
	{

		try
		{

			if (this.iDocumentalRepository.encontrarDocumentalDetallado(titulo, fechaEstreno) == null)
			{
				String mensajeError = "No se ha encontrado ningún documental con ese titulo y fecha de estreno";
				log.error(mensajeError);
				throw new DamfilmsServerException(102, mensajeError);

			}

			DocumentalDetalle documentalDetalle = new DocumentalDetalle();
			documentalDetalle = this.iDocumentalRepository.encontrarDocumentalDetallado(titulo, fechaEstreno);

			return ResponseEntity.ok(documentalDetalle);

		} catch (DamfilmsServerException damfilmsServerException)
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		} catch (Exception exception)
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/documentales/descripcion")
	public ResponseEntity<?> obtenerDescripcionDocumental(@RequestParam(name = "titulo", required = true) String titulo,
			@RequestParam(name = "fechaEstreno", required = true) Date fechaEstreno)
	{
		try
		{

			if (this.iDocumentalRepository.encontrarDocumentalDescripcion(titulo, fechaEstreno) == null)
			{
				String mensajeError = "No se ha encontrado ningún documental con ese titulo y fecha de estreno";
				log.error(mensajeError);
				throw new DamfilmsServerException(103, mensajeError);

			}

			DocumentalDescripcion documentalDescripcion = new DocumentalDescripcion();
			documentalDescripcion = this.iDocumentalRepository.encontrarDocumentalDescripcion(titulo, fechaEstreno);

			return ResponseEntity.ok(documentalDescripcion);

		} catch (DamfilmsServerException damfilmsServerException)
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		} catch (Exception exception)
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/peliculas_list")
	public ResponseEntity<?> obtenerPeliculas()
	{
		try
		{

			List<Pelicula> listaPeliculas;

			if (this.iPeliculaRepository.findAll().isEmpty())
			{
				String mensajeError = "No se ha encontrado ninguna película";
				log.error(mensajeError);
				throw new DamfilmsServerException(104, mensajeError);

			}

			listaPeliculas = this.iPeliculaRepository.findAll();

			return ResponseEntity.ok(listaPeliculas);

		} catch (DamfilmsServerException damfilmsServerException)
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		} catch (Exception exception)
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/peliculas/detalle")
	public ResponseEntity<?> obtenerDetallePelicula(@RequestParam(name = "titulo", required = true) String titulo,
			@RequestParam(name = "fechaEstreno", required = true) Date fechaEstreno)
	{
		try
		{

			if (this.iPeliculaRepository.encontrarPeliculaDetallada(titulo, fechaEstreno) == null)
			{
				String mensajeError = "No se ha encontrado ninguna película con ese titulo y fecha de estreno";
				log.error(mensajeError);
				throw new DamfilmsServerException(105, mensajeError);

			}

			PeliculaDetallada peliculaDetallada = new PeliculaDetallada();
			peliculaDetallada = this.iPeliculaRepository.encontrarPeliculaDetallada(titulo, fechaEstreno);

			return ResponseEntity.ok(peliculaDetallada);

		} catch (DamfilmsServerException damfilmsServerException)
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		} catch (Exception exception)
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/peliculas/descripcion")
	public ResponseEntity<?> obtenerDescripcionPelicula(@RequestParam(name = "titulo", required = true) String titulo,
			@RequestParam(name = "fechaEstreno", required = true) Date fechaEstreno)
	{
		try
		{

			if (this.iPeliculaRepository.encontrarPeliculaDescripcion(titulo, fechaEstreno) == null)
			{
				String mensajeError = "No se ha encontrado ninguna película con ese titulo y fecha de estreno";
				log.error(mensajeError);
				throw new DamfilmsServerException(106, mensajeError);

			}

			PeliculaDescripcion peliculaDescripcion = new PeliculaDescripcion();
			peliculaDescripcion = this.iPeliculaRepository.encontrarPeliculaDescripcion(titulo, fechaEstreno);

			return ResponseEntity.ok(peliculaDescripcion);

		} catch (DamfilmsServerException damfilmsServerException)
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		} catch (Exception exception)
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/peliculas/visualizadas")
	public ResponseEntity<?> obtenerPeliculasVisualizadasPorUsuario(
			@RequestParam(name = "usuario", required = true) String usuario)
	{
		try
		{

			if (this.iPeliculaVisualizadaRepository.encontrarUsuario(usuario) == null)
			{
				String mensajeError = "No se ha encontrado ningun usuario con ese nombre";
				log.error(mensajeError);
				throw new DamfilmsServerException(107, mensajeError);
			}

			List<PeliculaVisualizada> listaPeliculasVisualizadasPorUsuario;

			if (this.iPeliculaVisualizadaRepository.encontrarPeliculasVisualizadasPorUsuario(usuario).isEmpty())
			{
				String mensajeError = "No se ha encontrado ninguna película visualizada por ese usuario";
				log.error(mensajeError);
				throw new DamfilmsServerException(108, mensajeError);

			}

			listaPeliculasVisualizadasPorUsuario = this.iPeliculaVisualizadaRepository
					.encontrarPeliculasVisualizadasPorUsuario(usuario);

			return ResponseEntity.ok(listaPeliculasVisualizadasPorUsuario);

		} catch (DamfilmsServerException damfilmsServerException)
		{
			if (damfilmsServerException.getCode() == 107)
			{
				return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
			} else
			{
				return ResponseEntity.status(405).body(damfilmsServerException.getBodyExceptionMessage());
			}

		} catch (Exception exception)
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/documentales/visualizados")
	public ResponseEntity<?> obtenerDocumentalesVisualizadosPorUsuario(
			@RequestParam(name = "usuario", required = true) String usuario)
	{
		try
		{

			if (this.iDocumentalVisualizadoRepository.encontrarUsuario(usuario) == null)
			{
				String mensajeError = "No se ha encontrado ninguna usuario con ese nombre";
				log.error(mensajeError);
				throw new DamfilmsServerException(109, mensajeError);
			}

			List<DocumentalVisualizado> listaDocumentalesVisualizados;

			if (this.iDocumentalVisualizadoRepository.findAll().isEmpty())
			{
				String mensajeError = "No se ha encontrado ningún documental visualizado";
				log.error(mensajeError);
				throw new DamfilmsServerException(110, mensajeError);

			}

			listaDocumentalesVisualizados = this.iDocumentalVisualizadoRepository.findAll();

			return ResponseEntity.ok(listaDocumentalesVisualizados);

		} catch (DamfilmsServerException damfilmsServerException)
		{

			if (damfilmsServerException.getCode() == 109)
			{
				return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
			} else
			{
				return ResponseEntity.status(405).body(damfilmsServerException.getBodyExceptionMessage());
			}

		} catch (Exception exception)
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/series/visualizadas")
	public ResponseEntity<?> obtenerSeriesVisualizadasPorUsuario(
			@RequestParam(name = "usuario", required = true) String usuario)
	{
		try
		{

			if (this.iSerieVisualizadaRepository.encontrarUsuario(usuario) == null)
			{
				String mensajeError = "No se ha encontrado ninguna usuario con ese nombre";
				log.error(mensajeError);
				throw new DamfilmsServerException(111, mensajeError);
			}

			List<SerieVisualizada> listaSeriesVisualizadas;

			if (this.iSerieVisualizadaRepository.findAll().isEmpty())
			{
				String mensajeError = "No se ha encontrado ninguna serie visualizada";
				log.error(mensajeError);
				throw new DamfilmsServerException(112, mensajeError);

			}

			listaSeriesVisualizadas = this.iSerieVisualizadaRepository.findAll();

			return ResponseEntity.ok(listaSeriesVisualizadas);

		} catch (DamfilmsServerException damfilmsServerException)
		{
			if (damfilmsServerException.getCode() == 111)
			{
				return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
			} else
			{
				return ResponseEntity.status(405).body(damfilmsServerException.getBodyExceptionMessage());
			}
		} catch (Exception exception)
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}

	}

	// ==================== Temporada ====================

	@RequestMapping(method = RequestMethod.GET, value = "/temporadas")
	public ResponseEntity<?> obtenerTemporadaPorId(@RequestParam(name = "temporadaId") TemporadaId temporadaId)
	{
		try
		{
			Temporada temporada = iTemporadaRepository.findById(temporadaId).orElse(null);
			if (temporada == null)
			{
				String mensajeError = "Temporada no encontrada";
				log.error(mensajeError);
				throw new DamfilmsServerException(4, mensajeError);

			}
			return ResponseEntity.ok(temporada);
		} catch (DamfilmsServerException e)
		{
			return ResponseEntity.status(404).body(e.getBodyExceptionMessage());
		} catch (Exception exception)
		{

			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/series/{serieId}/temporadas")
	public ResponseEntity<?> obtenerTemporadasPorSerieId(@RequestParam(name = "serieId") Long serieId)
	{
		try
		{
			List<Temporada> temporadas = iTemporadaRepository.findBySerieId(serieId);
			if (temporadas.isEmpty())
			{
				String mensajeError = "No se encontraron temporadas para la serie especificada";
				log.error(mensajeError);
				throw new DamfilmsServerException(4, mensajeError);
			}
			return ResponseEntity.ok(temporadas);
		} catch (DamfilmsServerException e)
		{
			return ResponseEntity.status(404).body(e.getBodyExceptionMessage());
		} catch (Exception exception)
		{

			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/series/{id}")
	public ResponseEntity<?> obtenerSeriePorId(@RequestParam(name = "id") Long id)
	{
		try
		{
			Serie serie = iSerieRepository.findById(id).orElse(null);
			if (serie == null)
			{
				String mensajeError = "Serie no encontrada";
				log.error(mensajeError);
				throw new DamfilmsServerException(4, mensajeError);
			}
			return ResponseEntity.ok(serie);
		} catch (DamfilmsServerException e)
		{
			return ResponseEntity.status(404).body(e.getBodyExceptionMessage());
		} catch (Exception exception)
		{

			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/series/{genero}")
	public ResponseEntity<?> obtenerSeriePorGenero(@RequestParam(name = "genero") String genero)
	{
		try
		{
			List<Serie> series = iSerieRepository.findByGenero(genero);

			if (series.isEmpty())
			{
				String mensajeError = "No se encontraron series para el género especificado";
				log.error(mensajeError);
				throw new DamfilmsServerException(5, mensajeError);
			}
			return ResponseEntity.ok(series);
		} catch (DamfilmsServerException error)
		{
			return ResponseEntity.status(404).body(error.getBodyExceptionMessage());
		} catch (Exception exception)
		{

			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/series/detalle")
	public ResponseEntity<?> obtenerDetalleSerie(@RequestParam(name = "nombre", required = true) String nombre)
	{
		try
		{
			// Verificar si la serie existe
			if (this.iSerieRepository.encontrarSerieDetallada(nombre) == null)
			{
				String mensajeError = "No se ha encontrado ninguna serie con ese título y fecha de estreno";
				log.error(mensajeError);
				throw new DamfilmsServerException(5, mensajeError);
			}

			// Crear un DTO para la serie detallada
			SerieDetalle serieDetalle = new SerieDetalle();
			serieDetalle = this.iSerieRepository.encontrarSerieDetallada(nombre);

			// Retornar la respuesta con la serie detallada
			return ResponseEntity.ok(serieDetalle);
		} catch (DamfilmsServerException damfilmsServerException)
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		} catch (Exception exception)
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/series/descripcion")
	public ResponseEntity<?> obtenerDescripcionSerie(@RequestParam(name = "nombre", required = true) String nombre)
	{
		try
		{
			// Verificar si la serie existe
			if (this.iSerieRepository.encontrarSerieDescripcion(nombre) == null)
			{
				String mensajeError = "No se ha encontrado ninguna serie con ese título y fecha de estreno";
				log.error(mensajeError);
				throw new DamfilmsServerException(6, mensajeError);
			}

			// Crear un DTO para la descripción de la serie
			SerieDescripcion serieDescripcion = new SerieDescripcion();
			serieDescripcion = this.iSerieRepository.encontrarSerieDescripcion(nombre);

			// Retornar la respuesta con la descripción de la serie
			return ResponseEntity.ok(serieDescripcion);
		} catch (DamfilmsServerException damfilmsServerException)
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		} catch (Exception exception)
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
	}

	// ==================== Episodio ====================

	@RequestMapping(method = RequestMethod.GET, value = "/episodios/detalle")
	public ResponseEntity<?> obtenerDetalleEpisodio(
			@RequestParam(name = "episodioNumero", required = true) Integer episodioNumero,
			@RequestParam(name = "temporadaNumero", required = true) Integer temporadaNumero,
			@RequestParam(name = "serieId", required = true) String serieNombre)
	{
		try
		{
			// Buscar episodio
			Episodio episodio = this.iEpisodioRepository.findByIdAndSerieNombre(episodioNumero, serieNombre);
			if (episodio == null)
			{
				String mensajeError = "No se encontró ningún episodio con el número especificado en la serie "
						+ serieNombre;
				log.error(mensajeError);
				throw new DamfilmsServerException(5, mensajeError);
			}

			// Retornar el episodio detallado
			return ResponseEntity.ok(episodio);

		} catch (DamfilmsServerException damfilmsServerException)
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		} catch (Exception exception)
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError, exception);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/episodios/temporada")
	public ResponseEntity<?> getEpisodiosPorTemporada(
			@RequestParam(name = "temporadaNumero", required = true) Integer temporadaNumero,
			@RequestParam(name = "serieNombre", required = true) String serieNombre)
	{
		try
		{
			// Buscar episodios
			List<Episodio> episodios = iEpisodioRepository.findByTemporadaId(temporadaNumero, serieNombre);
			if (episodios == null || episodios.isEmpty())
			{
				String mensajeError = "No se encontraron episodios para la temporada " + temporadaNumero
						+ " de la serie " + serieNombre;
				log.error(mensajeError);
				throw new DamfilmsServerException(5, mensajeError);
			}

			// Retornar la lista de episodios
			return ResponseEntity.ok(episodios);
		} catch (DamfilmsServerException damfilmsServerException)
		{
			return ResponseEntity.status(404).body(damfilmsServerException.getBodyExceptionMessage());
		} catch (Exception exception)
		{
			String mensajeError = "Error interno del servidor";
			DamfilmsServerException damfilmsServerException = new DamfilmsServerException(100, mensajeError, exception);

			log.error(mensajeError, exception);
			return ResponseEntity.status(500).body(damfilmsServerException.getBodyExceptionMessage());
		}
	}
	// ==================== Modo ====================

	// Endpoint para cambiar el modo de una configuración
	@RequestMapping(method = RequestMethod.PUT, value = "/modo")
	public ResponseEntity<?> cambiarModo(@RequestParam String usuario, @RequestParam String nombreModo)
	{
		try
		{

			// Verifica si el usuario existe
			Usuario usuarioExistente = iUsuarioRepository.findByNombre(usuario);
			if (usuarioExistente == null)
			{
				throw new DamfilmsServerException(404, "Usuario no encontrado");
			}

			// Obtiene la configuración asociada al usuario
			Configuracion configuracionActual = usuarioExistente.getConfiguracion();
			if (configuracionActual == null)
			{
				throw new DamfilmsServerException(404, "Configuración no encontrada para el usuario");
			}

			// Verifica si el modo existe
			Optional<Modo> modoExistente = iModoRepository.findById(nombreModo);
			if (!modoExistente.isPresent())
			{
				throw new DamfilmsServerException(404, "Modo no encontrado");
			}

			// Cambia el modo en la configuración
			configuracionActual.setModo(modoExistente.get());
			iConfiguracionRepository.save(configuracionActual);

			log.info("Modo cambiado exitosamente en la configuración del usuario: {}", usuario);
			return ResponseEntity.ok().build();

		} catch (DamfilmsServerException ex)
		{
			// Manejo de errores
			log.error("Error al cambiar el modo en la configuración: {}", ex.getMessage());
			DamfilmsServerException customException = new DamfilmsServerException(500,
					"Error al cambiar el modo en la configuración", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(customException.getBodyExceptionMessage());
		}
	}

	// Endpoint para obtener el modo actual de una configuración asociada a un
	// usuario
	@RequestMapping(method = RequestMethod.GET, value = "/modo")
	public ResponseEntity<?> verModo(@RequestParam String usuario)
	{
		try
		{
			// Verifica si el usuario existe
			Usuario usuarioOpt = iUsuarioRepository.findByNombre(usuario);
			if (usuarioOpt == null)
			{
				throw new DamfilmsServerException(404, "Usuario no encontrado");
			}

			// Obtiene la configuración asociada al usuario
			Configuracion configuracionActual = usuarioOpt.getConfiguracion();
			if (configuracionActual == null)
			{
				throw new DamfilmsServerException(404, "Configuración no encontrada para el usuario");
			}

			// Obtiene el modo actual de la configuración
			Modo modoActual = configuracionActual.getModo();
			log.info("Modo actual en la configuración del usuario {}: {}", usuario, modoActual.getNombre());
			return ResponseEntity.ok(modoActual);
		} catch (Exception ex)
		{
			// Manejo de errores
			log.error("Error al obtener el modo en la configuración: {}", ex.getMessage());
			DamfilmsServerException customException = new DamfilmsServerException(500,
					"Error al obtener el modo en la configuración", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(customException.getBodyExceptionMessage());
		}
	}

	// ==================== Configuración ====================
	@RequestMapping(method = RequestMethod.PUT, value = "/configuracion")
	public ResponseEntity<?> cambiarConfiguracion(@RequestParam String usuario,
			@RequestBody Configuracion configuracionNueva)
	{
		try
		{
			Usuario usuarioOpt = iUsuarioRepository.findByNombre(usuario);
			if (usuarioOpt == null)
			{
				throw new DamfilmsServerException(404, "Usuario no encontrado");
			}

			Optional<Configuracion> configuracionIdiomaOpt = iConfiguracionRepository
					.findByIdioma(configuracionNueva.getIdioma());
			if (!configuracionIdiomaOpt.isPresent())
			{
				throw new DamfilmsServerException(404, "Idioma no encontrado");
			}

			Usuario usuarioEnt = usuarioOpt;
			usuarioEnt.setConfiguracion(configuracionNueva);
			iConfiguracionRepository.save(configuracionNueva);

			log.info("Configuración cambiada exitosamente para usuario: {}", usuario);
			return ResponseEntity.ok().build();
		} catch (DamfilmsServerException ex)
		{
			log.error("Error al cambiar configuración: {}", ex.getMessage());
			DamfilmsServerException customException = new DamfilmsServerException(500, "Error al cambiar configuración",
					ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(customException.getBodyExceptionMessage());
		}
	}

	
	    @RequestMapping(method = RequestMethod.GET, value = "/configuracion")
    public ResponseEntity<?> verConfiguracion(@RequestParam String usuario) {
        try {
        	Usuario usuarioOpt = iUsuarioRepository.findByNombre(usuario);
            if (usuarioOpt == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }

            Configuracion configuracionActual = usuarioOpt.getConfiguracion();
            log.info("Configuración actual para usuario {}: {}", usuario, configuracionActual);
            return ResponseEntity.ok(configuracionActual);
        } catch (Exception exception) {
            log.error("Error inesperado al obtener configuración: {}", exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al obtener la configuración");
        }
    }

	// ==================== Usuario ====================
	@RequestMapping(method = RequestMethod.POST, value = "/usuarios")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario nuevoUsuario) 
    {
        try 
        {
            // Comprobar si ya existe un usuario con el mismo nombre
            Usuario usuarioExistente = iUsuarioRepository.findByNombre(nuevoUsuario.getNombre());
            if (usuarioExistente!=null) {
                // Si el usuario existe, devolver un error
                log.error("Ya existe un usuario con el nombre: {}", nuevoUsuario.getNombre());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario con ese nombre.");
            }
            
            // Comprobar si ya existe un usuario con el mismo email
            Optional<Usuario> usuarioEmailExistente = iUsuarioRepository.findByCorreo(nuevoUsuario.getCorreo());
            if (usuarioEmailExistente.isPresent()) {
                // Si el usuario existe, devolver un error
                log.error("Ya existe un usuario con el correo: {}", nuevoUsuario.getCorreo());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario con ese correo.");
            }


            // Si no existe, guardar el nuevo usuario
            iUsuarioRepository.save(nuevoUsuario);
            log.info("Usuario creado exitosamente: {}", nuevoUsuario.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente.");
        } 
        catch (Exception ex) 
        {
            log.error("Error al crear usuario: {}", ex.getMessage());
            DamfilmsServerException customException = new DamfilmsServerException(500, "Error al crear usuario", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customException.getBodyExceptionMessage());
        }
    }

	@RequestMapping(method = RequestMethod.DELETE, value = "/usuarios")
	public ResponseEntity<?> eliminarUsuario(@RequestParam String nombre)
	{
		try
		{
			Long id = iUsuarioRepository.findByNombre(nombre).getId();
			if (!iUsuarioRepository.existsById(id))
			{
				throw new DamfilmsServerException(404, "Usuario no encontrado");
			}

			iUsuarioRepository.deleteById(id);
			log.info("Usuario eliminado exitosamente: {}", nombre);
			return ResponseEntity.ok("Usuario eliminado exitosamente.");
		} catch (DamfilmsServerException ex)
		{
			log.error("Error al eliminar usuario: {}", ex.getMessage());
			DamfilmsServerException customException = new DamfilmsServerException(500, "Error al eliminar usuario", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(customException.getBodyExceptionMessage());
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/usuarios")
    public ResponseEntity<?> cambiarUsuario(@RequestBody Usuario usuarioModificado, @RequestParam String nombreUsuario) {
        try {
            // Comprobar si el usuario existe por nombre
        	Usuario usuarioExistente = iUsuarioRepository.findByNombre(nombreUsuario);
            if (usuarioExistente == null) {
                log.error("Usuario con nombre {} no encontrado.", usuarioModificado.getNombre());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }

            // Comprobar si el correo ya está en uso por otro usuario
            Optional<Usuario> usuarioConCorreoExistente = iUsuarioRepository.findByCorreo(usuarioModificado.getCorreo());
            if (usuarioConCorreoExistente.isPresent() && !usuarioConCorreoExistente.get().getCorreo().equals(usuarioModificado.getCorreo())) {
                log.error("Ya existe un usuario con el correo: {}", usuarioModificado.getCorreo());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo electrónico ya está registrado por otro usuario.");
            }

            // Actualizar el usuario
            Usuario usuarioActualizado = usuarioExistente;
            usuarioActualizado.setNombre(usuarioModificado.getNombre());
            usuarioActualizado.setCorreo(usuarioModificado.getCorreo());
            usuarioActualizado.setContrasena(usuarioModificado.getContrasena());
            usuarioActualizado.setConfiguracion(usuarioModificado.getConfiguracion());  // Si es necesario

            // Guardar el usuario modificado
            iUsuarioRepository.save(usuarioActualizado);
            log.info("Usuario actualizado exitosamente: {}", usuarioModificado.getNombre());
            return ResponseEntity.ok("Usuario actualizado exitosamente.");
        } catch (Exception ex) {
            log.error("Error al actualizar usuario: {}", ex.getMessage());
            DamfilmsServerException customException = new DamfilmsServerException(500, "Error al actualizar usuario", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customException.getBodyExceptionMessage());
        }
    }

	// ==================== Suscripcion ====================
    @RequestMapping(method = RequestMethod.POST, value = "/suscripciones")
    public ResponseEntity<?> crearSuscripcion(@RequestParam String nombreUsuario) 
    {
        try 
        {
            // Comprobar si el usuario existe
        	Usuario usuarioExistente = iUsuarioRepository.findByNombre(nombreUsuario);
            if (usuarioExistente==null) 
            {
                log.error("Usuario con nombre {} no encontrado.", nombreUsuario);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }

            // Obtener el usuario
            Usuario usuario = usuarioExistente;

            // Crear el SuscripcionId (clave compuesta)
            SuscripcionId suscripcionId = new SuscripcionId();
            suscripcionId.setUsuario(usuario);
            suscripcionId.setFechaInicio(java.sql.Date.valueOf(java.time.LocalDate.now()));  

            // Crear nueva suscripción
            Suscripcion nuevaSuscripcion = new Suscripcion();
            nuevaSuscripcion.setSuscripcionId(suscripcionId);
            nuevaSuscripcion.setDuracion(30);  
            nuevaSuscripcion.setFechaFin(java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(30)));  

            // Guardar la suscripción
            iSuscripcionRepository.save(nuevaSuscripcion);
            log.info("Suscripción creada exitosamente para el usuario: {}", usuario.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body("Suscripción creada exitosamente.");
        } 
        catch (Exception ex) 
        {
            log.error("Error al crear suscripción: {}", ex.getMessage());
            DamfilmsServerException customException = new DamfilmsServerException(500, "Error al crear suscripción", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customException.getBodyExceptionMessage());
        }
    }

    
    @RequestMapping(method = RequestMethod.GET, value = "/suscripciones")
    public ResponseEntity<?> verSuscripcion(@RequestParam String nombreUsuario) {
        try {
            // Comprobar si el usuario existe
        	Usuario usuarioExistente = iUsuarioRepository.findByNombre(nombreUsuario);
            if (usuarioExistente==null) {
                log.error("Usuario con nombre {} no encontrado.", nombreUsuario);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }

            // Obtener el usuario
            Usuario usuario = usuarioExistente;

            // Obtener suscripción del usuario
            Optional<Suscripcion> suscripcionExistente = iSuscripcionRepository.findByUsuario(usuario);
            if (suscripcionExistente.isEmpty()) {
                log.error("No se encontró una suscripción para el usuario: {}", nombreUsuario);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró una suscripción para el usuario.");
            }

            // Retornar la suscripción encontrada
            Suscripcion suscripcion = suscripcionExistente.get();
            log.info("Suscripción encontrada para el usuario: {}", nombreUsuario);
            return ResponseEntity.ok(suscripcion);
        } catch (Exception ex) {
            log.error("Error al ver suscripción: {}", ex.getMessage());
            DamfilmsServerException customException = new DamfilmsServerException(500, "Error al ver suscripción", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customException.getBodyExceptionMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/suscripciones")
    public ResponseEntity<?> cancelarSuscripcion(@RequestParam String nombreUsuario) {
        try {
            // Comprobar si el usuario existe
        	Usuario usuarioExistente = iUsuarioRepository.findByNombre(nombreUsuario);
            if (usuarioExistente==null) {
                log.error("Usuario con nombre {} no encontrado.", nombreUsuario);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }

            // Obtener el usuario
            Usuario usuario = usuarioExistente;

            // Obtener suscripción del usuario
            Optional<Suscripcion> suscripcionExistente = iSuscripcionRepository.findByUsuario(usuario);
            if (suscripcionExistente.isEmpty()) {
                log.error("No se encontró una suscripción para el usuario: {}", nombreUsuario);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró una suscripción para el usuario.");
            }

            Suscripcion suscripcion = suscripcionExistente.get();

            // Si la suscripción es premium, cambiarla a normal
            if (suscripcion.getDuracion() > 30) {  
                suscripcion.setDuracion(30);  
                iSuscripcionRepository.save(suscripcion);
                log.info("Suscripción premium cambiada a normal para el usuario: {}", nombreUsuario);
                return ResponseEntity.ok("Suscripción cambiada a normal exitosamente.");
            } else {
                // Si es una suscripción normal, eliminarla
            	iSuscripcionRepository.delete(suscripcion);
                log.info("Suscripción eliminada exitosamente para el usuario: {}", nombreUsuario);
                return ResponseEntity.ok("Suscripción eliminada exitosamente.");
            }
        } catch (Exception ex) {
            log.error("Error al cancelar suscripción: {}", ex.getMessage());
            DamfilmsServerException customException = new DamfilmsServerException(500, "Error al cancelar suscripción", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customException.getBodyExceptionMessage());
        }
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/suscripciones/tipos")
	public ResponseEntity<?> obtenerTiposSuscripciones()
	{
		try
		{
			List<String> tiposSuscripcion;

			tiposSuscripcion = iRoleRepository.entontrarTodosLosRoles();

			if (iRoleRepository.entontrarTodosLosRoles().isEmpty())
			{
				throw new DamfilmsServerException(404, "Ningun tipo de suscripcion encontrado");
			}

			log.info("Tipos enviados exitosamente: {}", tiposSuscripcion);
			return ResponseEntity.ok(tiposSuscripcion);
		} 
		catch (DamfilmsServerException ex)
		{
			log.error("Error al encontrar tipos de suscripcion: {}", ex.getMessage());
			DamfilmsServerException customException = new DamfilmsServerException(500, "Error al cambiar configuración",
					ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(customException.getBodyExceptionMessage());
		}
	}


}
