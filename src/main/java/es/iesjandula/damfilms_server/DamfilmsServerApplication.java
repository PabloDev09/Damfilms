package es.iesjandula.damfilms_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import es.iesjandula.damfilms_server.parsers.interfaces.IGestorParseo;
import es.iesjandula.damfilms_server.utils.Constants;

@SpringBootApplication
public class DamfilmsServerApplication implements CommandLineRunner
{
	
	@Autowired
	private IGestorParseo iParseoFicheros;

	public static void main(String[] args) 
	{
		SpringApplication.run(DamfilmsServerApplication.class, args);
	}

	@Transactional(readOnly = false)
	public void run(String... args) throws Exception 
	{
		this.iParseoFicheros.parseaFichero(Constants.CSV_GENEROS);
		this.iParseoFicheros.parseaFichero(Constants.CSV_SERIES);
		this.iParseoFicheros.parseaFichero(Constants.CSV_DOCUMENTALES);
		this.iParseoFicheros.parseaFichero(Constants.CSV_PELICULAS);
		this.iParseoFicheros.parseaFichero(Constants.CSV_MODOS);
		this.iParseoFicheros.parseaFichero(Constants.CSV_CONFIGURACIONES);
		this.iParseoFicheros.parseaFichero(Constants.CSV_USUARIOS);
		this.iParseoFicheros.parseaFichero(Constants.CSV_PELICULAS_VISUALIZADAS);
		this.iParseoFicheros.parseaFichero(Constants.CSV_DOCUMENTALES);
		this.iParseoFicheros.parseaFichero(Constants.CSV_SERIES_VISUALIZADAS);
		
		
		
		
	}

}
