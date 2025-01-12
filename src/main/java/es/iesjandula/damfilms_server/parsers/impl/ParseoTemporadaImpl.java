package es.iesjandula.damfilms_server.parsers.impl;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Serie;
import es.iesjandula.damfilms_server.entities.Temporada;
import es.iesjandula.damfilms_server.entities.ids.TemporadaId;
import es.iesjandula.damfilms_server.parsers.interfaces.IParseo;
import es.iesjandula.damfilms_server.repositories.ISerieRepository;
import es.iesjandula.damfilms_server.repositories.ITemporadaRepository;
import es.iesjandula.damfilms_server.utils.Constants;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import es.iesjandula.damfilms_server.utils.DatesUtil;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoTemporadaImpl implements IParseo<Temporada>
{

	@Autowired
	private ITemporadaRepository iTemporadaRepository;

	@Autowired
	private ISerieRepository iSerieRepository;

	@Override
	public void parseaFichero(Scanner scanner) throws DamfilmsServerException
	{
		// TODO Auto-generated method stub

		scanner.nextLine();

		while(scanner.hasNextLine())
		{
			String lineaDelFichero = scanner.nextLine();

			String[] lineaDelFicheroTroceada = lineaDelFichero.split(Constants.CSV_DELIMITER);

			Temporada temporada = new Temporada();
			TemporadaId temporadaId = new TemporadaId();
			
			Optional<Serie> optionalSerie = this.iSerieRepository.findById(Long.parseLong(lineaDelFicheroTroceada[0]));

			if(!optionalSerie.isPresent())
			{
				String mensajeError = "No existe la serie";
				log.error(mensajeError);
				throw new DamfilmsServerException(2, mensajeError);
			}

			temporadaId.setSerie(optionalSerie.get());
			temporadaId.setNumero(Integer.parseInt(lineaDelFicheroTroceada[1]));
			temporadaId.setFechaEstreno(DatesUtil.crearFechaDesdeString(lineaDelFicheroTroceada[2]));

			temporada.setTemporadaId(temporadaId);
			temporada.setSerie(optionalSerie.get());
			this.iTemporadaRepository.saveAndFlush(temporada);
		}

	}

}
