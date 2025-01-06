package es.iesjandula.damfilms_server.iml;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Configuracion;
import es.iesjandula.damfilms_server.entities.Modo;
import es.iesjandula.damfilms_server.interfaces.IParseoConfiguracion;
import es.iesjandula.damfilms_server.repositories.IConfiguracionRepository;
import es.iesjandula.damfilms_server.repositories.IModoRepository;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoConfiguracion implements IParseoConfiguracion
{
	
	@Autowired
	private IConfiguracionRepository configuracionRepository;
	
	@Autowired
	private IModoRepository modoRepository;

	@Override
	public void parseaFichero(Scanner scanner) throws DamfilmsServerException 
	{
		// TODO Auto-generated method stub
		
		scanner.nextLine();
		
		while(scanner.hasNextLine()) 
		{
			String lineaDelFichero = scanner.nextLine();
			
			String[] lineaDelFicheroTroceada = lineaDelFichero.split(",");
			
			Configuracion configuracion = new Configuracion();
			
			configuracion.setId(Long.valueOf(lineaDelFicheroTroceada[0]));
			configuracion.setIdioma(lineaDelFicheroTroceada[1]);
		
			Optional<Modo> optionalModo = this.modoRepository.findById(lineaDelFicheroTroceada[2]);
			
			if(!optionalModo.isPresent()) 
			{
				String mensajeError = "No existe el modo";
				log.error(mensajeError);
				throw new DamfilmsServerException(2, mensajeError);
			}
			
			configuracion.setModo(optionalModo.get());
			
			this.configuracionRepository.saveAndFlush(configuracion);
		}
		
	}

}
