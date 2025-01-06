package es.iesjandula.damfilms_server.iml;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.interfaces.IGestorParseo;
import es.iesjandula.damfilms_server.interfaces.IParseoConfiguracion;
import es.iesjandula.damfilms_server.interfaces.IParseoDocumental;
import es.iesjandula.damfilms_server.interfaces.IParseoDocumentalVisualizado;
import es.iesjandula.damfilms_server.interfaces.IParseoGenero;
import es.iesjandula.damfilms_server.interfaces.IParseoModo;
import es.iesjandula.damfilms_server.interfaces.IParseoPelicula;
import es.iesjandula.damfilms_server.interfaces.IParseoPeliculaVisualizado;
import es.iesjandula.damfilms_server.interfaces.IParseoSerie;
import es.iesjandula.damfilms_server.interfaces.IParseoSerieVisualizado;
import es.iesjandula.damfilms_server.interfaces.IParseoUsuarios;
import es.iesjandula.damfilms_server.utils.Constants;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;

@Service
public class GestorParseo implements IGestorParseo
{
	
	@Autowired
	private IParseoPelicula parseoPelicula;
	
	@Autowired
	private IParseoDocumental parseoDocumental;
	
	@Autowired
	private IParseoSerie parseoSerie;
	
	@Autowired
	private IParseoGenero parseoGenero;
	
	@Autowired
	private IParseoConfiguracion parseoConfiguracion;
	
	@Autowired
	private IParseoModo parseoModo;
	
	@Autowired
	private IParseoUsuarios parseoUsuarios;
	
	@Autowired
	private IParseoPeliculaVisualizado parseoPeliculaVisualizado;
	
	@Autowired
	private IParseoDocumentalVisualizado parseoDocumentalVisualizado;
	
	@Autowired
	private IParseoSerieVisualizado parseoSerieVisualizado;

	@Override
	public void parseaFichero(String nombreFichero) throws DamfilmsServerException {

		switch(nombreFichero) 
		{
			case Constants.PELICULAS:
				Scanner scannerPeliculas = this.abrirFichero(nombreFichero);
				
				this.parseoPelicula.parseaFichero(scannerPeliculas);
				
				scannerPeliculas.close();
				break;
				
			case Constants.DOCUMENTALES:
				Scanner scannerDocumentales = this.abrirFichero(nombreFichero);
				
				this.parseoDocumental.parseaFichero(scannerDocumentales);
				
				scannerDocumentales.close();
				break;
				
			case Constants.SERIES:
				Scanner scannerSeries = this.abrirFichero(nombreFichero);
				
				this.parseoSerie.parseaFichero(scannerSeries);
				
				scannerSeries.close();
				break;
				
			case Constants.GENEROS:
				Scanner scannerGeneros = this.abrirFichero(nombreFichero);
				
				this.parseoGenero.parseaFichero(scannerGeneros);
				
				scannerGeneros.close();
				break;
				
			case Constants.CONFIGURACION:
				Scanner scannerConfiguracion = this.abrirFichero(nombreFichero);
				
				this.parseoConfiguracion.parseaFichero(scannerConfiguracion);
				
				scannerConfiguracion.close();
				break;
				
			case Constants.MODO:
				Scanner scannerModo = this.abrirFichero(nombreFichero);
				
				this.parseoModo.parseaFichero(scannerModo);
				
				scannerModo.close();
				break;
				
			case Constants.USUARIOS:
				Scanner scannerUsuarios = this.abrirFichero(nombreFichero);
				
				this.parseoUsuarios.parseaFichero(scannerUsuarios);
				
				scannerUsuarios.close();
				break;
				
			case Constants.PELICULA_VISUALIZADA:
				Scanner scannerPeliculaVisualizada = this.abrirFichero(nombreFichero);
				
				this.parseoPeliculaVisualizado.parseaFichero(scannerPeliculaVisualizada);
				
				scannerPeliculaVisualizada.close();
				break;
				
			case Constants.DOCUMENTAL_VISUALIZADO:
				Scanner scannerDocumentalVisualizada = this.abrirFichero(nombreFichero);
				
				this.parseoDocumentalVisualizado.parseaFichero(scannerDocumentalVisualizada);
				
				scannerDocumentalVisualizada.close();
				break;
				
			case Constants.SERIE_VISUALIZADA:
				Scanner scannerSerieVisualizada = this.abrirFichero(nombreFichero);
				
				this.parseoSerieVisualizado.parseaFichero(scannerSerieVisualizada);
				
				scannerSerieVisualizada.close();
				break;
				
			default:
				throw new DamfilmsServerException(4, "Fichero" + nombreFichero + "no encontrado");
		}
	}
	
	private Scanner abrirFichero(String nombreFichero) throws DamfilmsServerException
	{
		try
		{
			// Get file from resource
			File fichero = this.getFileFromResource(nombreFichero) ;
			
			return new Scanner(fichero) ;
		}
		catch (FileNotFoundException fileNotFoundException)
		{
			throw new DamfilmsServerException(5, "Fichero " + nombreFichero + " no encontrado!", fileNotFoundException) ;
		}
		catch (URISyntaxException uriSyntaxException)
		{
			throw new DamfilmsServerException(6, "Fichero " + nombreFichero + " no encontrado!", uriSyntaxException) ;
		}
		
	}
	
	private File getFileFromResource(String nombreFichero) throws URISyntaxException
	{
        ClassLoader classLoader = getClass().getClassLoader() ;
        
        URL resource = classLoader.getResource(nombreFichero) ;
        
        if (resource == null)
        {
            throw new IllegalArgumentException("Fichero no encontrado! " + nombreFichero) ;
        }

        return new File(resource.toURI()) ;
    }

}
