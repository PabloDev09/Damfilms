package es.iesjandula.damfilms_server.parsers.impl;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Genero;
import es.iesjandula.damfilms_server.entities.Serie;
import es.iesjandula.damfilms_server.parsers.interfaces.IParseo;
import es.iesjandula.damfilms_server.repositories.IGeneroRepository;
import es.iesjandula.damfilms_server.repositories.ISerieRepository;
import es.iesjandula.damfilms_server.utils.Constants;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import es.iesjandula.damfilms_server.utils.DatesUtil;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoSerieImpl implements IParseo<Serie>
{

	@Autowired
	private ISerieRepository iSerieRepository;

	@Autowired
	private IGeneroRepository iGeneroRepository;

	@Override
	public void parseaFichero(Scanner scanner) throws DamfilmsServerException
	{
		// TODO Auto-generated method stub

		scanner.nextLine();

		while (scanner.hasNextLine())
		{
			String lineaDelFichero = scanner.nextLine();

			String[] lineaDelFicheroTroceada = lineaDelFichero.split(Constants.CSV_DELIMITER);

			Serie serie = new Serie();

			serie.setTitulo(lineaDelFicheroTroceada[0]);
			serie.setFechaEstreno(DatesUtil.crearFechaDesdeString(lineaDelFicheroTroceada[1]));
			serie.setDescripcion(lineaDelFicheroTroceada[2]);

			serie.setClasificacion(Integer.valueOf(lineaDelFicheroTroceada[3]));
			Optional<Genero> optionalGenero = this.iGeneroRepository.findById(lineaDelFicheroTroceada[4]);

			if (!optionalGenero.isPresent())
			{
				String mensajeError = "No existe el género";
				log.error(mensajeError);
				throw new DamfilmsServerException(1, mensajeError);
			}
			serie.setGenero(optionalGenero.get());

			this.iSerieRepository.saveAndFlush(serie);
		}

	}

}
