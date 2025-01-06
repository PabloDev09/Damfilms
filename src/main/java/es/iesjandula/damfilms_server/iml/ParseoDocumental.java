package es.iesjandula.damfilms_server.iml;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Documental;
import es.iesjandula.damfilms_server.entities.Genero;
import es.iesjandula.damfilms_server.entities.ids.DocumentalId;
import es.iesjandula.damfilms_server.interfaces.IParseoDocumental;
import es.iesjandula.damfilms_server.repositories.IDocumentalRepository;
import es.iesjandula.damfilms_server.repositories.IGeneroRepository;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import es.iesjandula.damfilms_server.utils.DatesUtil;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoDocumental implements IParseoDocumental
{
	
	@Autowired
	private IDocumentalRepository documentalRepository;
	
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
			
			DocumentalId documentalId = new DocumentalId();
			
			documentalId.setTitulo(lineaDelFicheroTroceada[0]);
			try 
			{
				documentalId.setFechaEstreno(DatesUtil.crearFechaDesdeString(lineaDelFicheroTroceada[1]));

			}
			catch (DamfilmsServerException damfilmsServerException) 
			{
				damfilmsServerException.printStackTrace();
			
			}
			
			Documental documental = new Documental();
			
			documental.setDocumentalId(documentalId);
			documental.setDescripcion(lineaDelFicheroTroceada[2]);
			documental.setDuracion(Integer.valueOf(lineaDelFicheroTroceada[3]));
			documental.setClasificacion(lineaDelFicheroTroceada[4]);
			Optional<Genero> optionalGenero = this.generoRepository.findById(lineaDelFicheroTroceada[5]);
			
			if(!optionalGenero.isPresent()) 
			{
				String mensajeError = "No existe el género";
				log.error(mensajeError);
				throw new DamfilmsServerException(1, mensajeError);
			}
			documental.setGenero(optionalGenero.get());
			
			this.documentalRepository.saveAndFlush(documental);
		}
		
	}

}
