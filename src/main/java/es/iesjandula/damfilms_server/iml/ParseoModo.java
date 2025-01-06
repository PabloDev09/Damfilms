package es.iesjandula.damfilms_server.iml;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Modo;
import es.iesjandula.damfilms_server.interfaces.IParseoModo;
import es.iesjandula.damfilms_server.repositories.IModoRepository;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoModo implements IParseoModo
{
	
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
			
			Modo modo = new Modo();
			
			modo.setNombre(lineaDelFicheroTroceada[0]);
			modo.setDescripcion(lineaDelFicheroTroceada[1]);
			
			this.modoRepository.saveAndFlush(modo);
		}
		
	}

}
