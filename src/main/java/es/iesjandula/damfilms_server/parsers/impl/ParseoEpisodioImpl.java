package es.iesjandula.damfilms_server.parsers.impl;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Episodio;
import es.iesjandula.damfilms_server.entities.Serie;
import es.iesjandula.damfilms_server.entities.Temporada;
import es.iesjandula.damfilms_server.entities.ids.EpisodioId;
import es.iesjandula.damfilms_server.entities.ids.TemporadaId;
import es.iesjandula.damfilms_server.parsers.interfaces.IParseo;
import es.iesjandula.damfilms_server.repositories.IEpisodioRepository;
import es.iesjandula.damfilms_server.repositories.ISerieRepository;
import es.iesjandula.damfilms_server.repositories.ITemporadaRepository;
import es.iesjandula.damfilms_server.utils.Constants;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import es.iesjandula.damfilms_server.utils.DatesUtil;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoEpisodioImpl implements IParseo<Episodio>
{

	@Autowired
	private IEpisodioRepository iEpisodioRepository;
	
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

			Episodio episodio = new Episodio();
			EpisodioId episodioId = new EpisodioId();
			
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
			
			Optional<Temporada> optionalTemporada = this.iTemporadaRepository.findById(temporadaId);

			if(!optionalTemporada.isPresent())
			{
				String mensajeError = "No existe la temporada";
				log.error(mensajeError);
				throw new DamfilmsServerException(2, mensajeError);
			}
			
			episodioId.setTemporada(optionalTemporada.get());
			episodioId.setNumero(Integer.parseInt(lineaDelFicheroTroceada[3]));
			
			episodio.setEpisodioId(episodioId);
			episodio.setNombre(lineaDelFicheroTroceada[4]);
			episodio.setDuracion(Integer.parseInt(lineaDelFicheroTroceada[5]));

			this.iEpisodioRepository.saveAndFlush(episodio);
		}

	}

}
