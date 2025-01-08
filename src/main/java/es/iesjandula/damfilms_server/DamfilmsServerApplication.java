package es.iesjandula.damfilms_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import es.iesjandula.damfilms_server.parsers.interfaces.IGestorParseo;
import es.iesjandula.damfilms_server.repositories.IConfiguracionRepository;
import es.iesjandula.damfilms_server.repositories.IDocumentalRepository;
import es.iesjandula.damfilms_server.repositories.IDocumentalVisualizadoRepository;
import es.iesjandula.damfilms_server.repositories.IEpisodioRepository;
import es.iesjandula.damfilms_server.repositories.IGeneroRepository;
import es.iesjandula.damfilms_server.repositories.IModoRepository;
import es.iesjandula.damfilms_server.repositories.IPeliculaRepository;
import es.iesjandula.damfilms_server.repositories.IPeliculaVisualizadaRepository;
import es.iesjandula.damfilms_server.repositories.ISerieRepository;
import es.iesjandula.damfilms_server.repositories.ISerieVisualizadaRepository;
import es.iesjandula.damfilms_server.repositories.ISuscripcionRepository;
import es.iesjandula.damfilms_server.repositories.ITemporadaRepository;
import es.iesjandula.damfilms_server.repositories.IUsuarioRepository;
import es.iesjandula.damfilms_server.utils.Constants;

@SpringBootApplication
public class DamfilmsServerApplication implements CommandLineRunner
{
	
	@Autowired
	private IGestorParseo iParseoFicheros;
	
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
	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	private IModoRepository modoRepository;
	
	@Autowired
	private IConfiguracionRepository configuracionRepository;
	
	@Autowired
	private ISuscripcionRepository suscripcionRepository;
	
	@Autowired
	private IGeneroRepository generoRepository;

	public static void main(String[] args) 
	{
		SpringApplication.run(DamfilmsServerApplication.class, args);
	}

	@Transactional(readOnly = false)
	public void run(String... args) throws Exception 
	{
		if(this.generoRepository.findAll().isEmpty())
		{
			this.iParseoFicheros.parseaFichero(Constants.CSV_GENEROS);
		}
		if(this.iSerieRepository.findAll().isEmpty()) {
			this.iParseoFicheros.parseaFichero(Constants.CSV_SERIES);
		}
		if(this.iDocumentalRepository.findAll().isEmpty()) {
			this.iParseoFicheros.parseaFichero(Constants.CSV_DOCUMENTALES);	
		}
		
		if(this.iPeliculaRepository.findAll().isEmpty())
		{
			this.iParseoFicheros.parseaFichero(Constants.CSV_PELICULAS);
		}
		if(this.modoRepository.findAll().isEmpty())
		{
			this.iParseoFicheros.parseaFichero(Constants.CSV_MODOS);
		}
		if(this.configuracionRepository.findAll().isEmpty()) {
			this.iParseoFicheros.parseaFichero(Constants.CSV_CONFIGURACIONES);
		}
		if(this.usuarioRepository.findAll().isEmpty())
		{
			this.iParseoFicheros.parseaFichero(Constants.CSV_USUARIOS);
		}
		if(this.iPeliculaVisualizadaRepository.findAll().isEmpty())
		{
			this.iParseoFicheros.parseaFichero(Constants.CSV_PELICULAS_VISUALIZADAS);
		}
		
		
		
	}

}
