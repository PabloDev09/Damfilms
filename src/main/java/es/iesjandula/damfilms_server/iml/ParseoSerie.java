package es.iesjandula.damfilms_server.iml;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Genero;
import es.iesjandula.damfilms_server.entities.Serie;
import es.iesjandula.damfilms_server.entities.ids.SerieId;
import es.iesjandula.damfilms_server.interfaces.IParseoSerie;
import es.iesjandula.damfilms_server.repositories.IGeneroRepository;
import es.iesjandula.damfilms_server.repositories.ISerieRepository;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import es.iesjandula.damfilms_server.utils.DatesUtil;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoSerie implements IParseoSerie
{
	
	@Autowired
	private ISerieRepository serieRepository;
	
	@Autowired
	private IGeneroRepository generoRepository;

	@Override
	public void parseaFichero(Scanner scanner) throws DamfilmsServerException 
	{
		// TODO Auto-generated method stub
		
		scanner.nextLine();
		
		while(scanner.hasNextLine()) 
		{
			String lineaDelFichero = scanner.nextLine();
			
			String[] lineaDelFicheroTroceada = lineaDelFichero.split(",");
			
			SerieId serieId = new SerieId();
			
			serieId.setNombre(lineaDelFicheroTroceada[0]);
			try 
			{
				serieId.setFechaEstreno(DatesUtil.crearFechaDesdeString(lineaDelFicheroTroceada[1]));

			}
			catch (DamfilmsServerException damfilmsServerException) 
			{
				damfilmsServerException.printStackTrace();
			
			}
			
			Serie serie = new Serie();
			
			serie.setSerieId(serieId);
			serie.setDescripcion(lineaDelFicheroTroceada[2]);
			serie.setClasificacion(lineaDelFicheroTroceada[3]);
			Optional<Genero> optionalGenero = this.generoRepository.findById(lineaDelFicheroTroceada[4]);
			
			if(!optionalGenero.isPresent()) 
			{
				String mensajeError = "No existe el género";
				log.error(mensajeError);
				throw new DamfilmsServerException(1, mensajeError);
			}
			serie.setGenero(optionalGenero.get());
			
			this.serieRepository.saveAndFlush(serie);
		}
		
	}

}
