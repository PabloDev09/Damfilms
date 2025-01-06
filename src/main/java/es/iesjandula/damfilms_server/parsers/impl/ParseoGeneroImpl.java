package es.iesjandula.damfilms_server.parsers.impl;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Genero;
import es.iesjandula.damfilms_server.parsers.interfaces.IParseo;
import es.iesjandula.damfilms_server.repositories.IGeneroRepository;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoGeneroImpl implements IParseo<Genero>
{

	@Autowired
	private IGeneroRepository iGeneroRepository;

	@Override
	public void parseaFichero(Scanner scanner) throws DamfilmsServerException
	{
		// TODO Auto-generated method stub

		scanner.nextLine();

		while(scanner.hasNextLine())
		{
			String lineaDelFichero = scanner.nextLine();

			String[] lineaDelFicheroTroceada = lineaDelFichero.split(",");

			Genero genero = new Genero();

			genero.setNombre(lineaDelFicheroTroceada[0]);
			genero.setDescripcion(lineaDelFicheroTroceada[1]);

			this.iGeneroRepository.saveAndFlush(genero);
		}

	}

}
