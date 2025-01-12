package es.iesjandula.damfilms_server.parsers.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Configuracion;
import es.iesjandula.damfilms_server.entities.Documental;
import es.iesjandula.damfilms_server.entities.DocumentalVisualizado;
import es.iesjandula.damfilms_server.entities.Episodio;
import es.iesjandula.damfilms_server.entities.Genero;
import es.iesjandula.damfilms_server.entities.Modo;
import es.iesjandula.damfilms_server.entities.Pelicula;
import es.iesjandula.damfilms_server.entities.PeliculaVisualizada;
import es.iesjandula.damfilms_server.entities.Role;
import es.iesjandula.damfilms_server.entities.Serie;
import es.iesjandula.damfilms_server.entities.SerieVisualizada;
import es.iesjandula.damfilms_server.entities.Temporada;
import es.iesjandula.damfilms_server.entities.Usuario;
import es.iesjandula.damfilms_server.parsers.interfaces.IGestorParseo;
import es.iesjandula.damfilms_server.parsers.interfaces.IParseo;
import es.iesjandula.damfilms_server.utils.Constants;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;

@Service
public class GestorParseoImpl implements IGestorParseo 
{

	@Autowired
	IParseo<Pelicula> iParseoPelicula;
	
	@Autowired
	IParseo<Documental> iParseoDocumental;
	
	@Autowired
	IParseo<Serie> iParseoSerie;
	
	@Autowired
	IParseo<Temporada> iParseoTemporada;
	
	@Autowired
	IParseo<Episodio> iParseoEpisodio;
	
	@Autowired
	IParseo<Genero> iParseoGenero;
	
	@Autowired
	IParseo<Configuracion> iParseoConfiguracion;
	
	@Autowired
	IParseo<Modo> iParseoModo;
	
	@Autowired
	IParseo<Usuario> iParseoUsuario;
	
	@Autowired
	IParseo<PeliculaVisualizada> iParseoPeliculaVisualizada;
	
	@Autowired
	IParseo<SerieVisualizada> iParseoSerieVisualizada;
	
	@Autowired
	IParseo<DocumentalVisualizado> iParseoDocumentalVisualizado;

	@Autowired
	IParseo<Role> iParseoRol;
	
	@Override
	public void parseaFichero(String nombreFichero) throws DamfilmsServerException 
	{

		switch (nombreFichero) {
		case Constants.CSV_PELICULAS:
			Scanner scannerPeliculas = this.abrirFichero(nombreFichero);

			this.iParseoPelicula.parseaFichero(scannerPeliculas);

			scannerPeliculas.close();
			break;

		case Constants.CSV_DOCUMENTALES:
			Scanner scannerDocumentales = this.abrirFichero(nombreFichero);

			this.iParseoDocumental.parseaFichero(scannerDocumentales);

			scannerDocumentales.close();
			break;

		case Constants.CSV_SERIES:
			Scanner scannerSeries = this.abrirFichero(nombreFichero);

			this.iParseoSerie.parseaFichero(scannerSeries);

			scannerSeries.close();
			break;
		case Constants.CSV_TEMPORADAS:
			Scanner scannerTemporadas = this.abrirFichero(nombreFichero);

			this.iParseoTemporada.parseaFichero(scannerTemporadas);

			scannerTemporadas.close();
			break;
		case Constants.CSV_EPISODIOS:
			Scanner scannerEpisodios = this.abrirFichero(nombreFichero);

			this.iParseoEpisodio.parseaFichero(scannerEpisodios);

			scannerEpisodios.close();
			break;

		case Constants.CSV_GENEROS:
			Scanner scannerGeneros = this.abrirFichero(nombreFichero);

			this.iParseoGenero.parseaFichero(scannerGeneros);

			scannerGeneros.close();
			break;

		case Constants.CSV_CONFIGURACIONES:
			Scanner scannerConfiguracion = this.abrirFichero(nombreFichero);

			this.iParseoConfiguracion.parseaFichero(scannerConfiguracion);

			scannerConfiguracion.close();
			break;

		case Constants.CSV_MODOS:
			Scanner scannerModo = this.abrirFichero(nombreFichero);

			this.iParseoModo.parseaFichero(scannerModo);

			scannerModo.close();
			break;

		case Constants.CSV_USUARIOS:
			Scanner scannerUsuarios = this.abrirFichero(nombreFichero);

			this.iParseoUsuario.parseaFichero(scannerUsuarios);

			scannerUsuarios.close();
			break;

		case Constants.CSV_PELICULAS_VISUALIZADAS:
			Scanner scannerPeliculaVisualizada = this.abrirFichero(nombreFichero);

			this.iParseoPeliculaVisualizada.parseaFichero(scannerPeliculaVisualizada);

			scannerPeliculaVisualizada.close();
			break;

		case Constants.CSV_DOCUMENTALES_VISUALIZADOS:
			Scanner scannerDocumentalVisualizada = this.abrirFichero(nombreFichero);

			this.iParseoDocumentalVisualizado.parseaFichero(scannerDocumentalVisualizada);

			scannerDocumentalVisualizada.close();
			break;

		case Constants.CSV_SERIES_VISUALIZADAS:
			Scanner scannerSerieVisualizada = this.abrirFichero(nombreFichero);

			this.iParseoSerieVisualizada.parseaFichero(scannerSerieVisualizada);

			scannerSerieVisualizada.close();
			break;
		case Constants.CSV_TIPOS_SUSCRIPCIONES:
			Scanner scannerTiposSuscripciones = this.abrirFichero(nombreFichero);

			this.iParseoRol.parseaFichero(scannerTiposSuscripciones);

			scannerTiposSuscripciones.close();
			break;
			
		default:
			throw new DamfilmsServerException(4, "Fichero" + nombreFichero + "no encontrado");
		}
	}

	private Scanner abrirFichero(String nombreFichero) throws DamfilmsServerException {
		try 
		{
			// Get file from resource
			File fichero = this.getFileFromResource(nombreFichero);

			return new Scanner(fichero);
		} 
		catch (FileNotFoundException fileNotFoundException) 
		{
			throw new DamfilmsServerException(5, "Fichero " + nombreFichero + " no encontrado!", fileNotFoundException);
		} 
		catch (URISyntaxException uriSyntaxException) 
		{
			throw new DamfilmsServerException(6, "Fichero " + nombreFichero + " no encontrado!", uriSyntaxException);
		}

	}

	private File getFileFromResource(String nombreFichero) throws URISyntaxException {
		ClassLoader classLoader = getClass().getClassLoader();

		URL resource = classLoader.getResource(nombreFichero);

		if (resource == null) {
			throw new IllegalArgumentException("Fichero no encontrado! " + nombreFichero);
		}

		return new File(resource.toURI());
	}

}
