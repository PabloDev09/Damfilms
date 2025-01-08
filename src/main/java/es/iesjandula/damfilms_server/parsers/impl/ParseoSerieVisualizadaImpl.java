package es.iesjandula.damfilms_server.parsers.impl;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Serie;
import es.iesjandula.damfilms_server.entities.SerieVisualizada;
import es.iesjandula.damfilms_server.entities.Usuario;
import es.iesjandula.damfilms_server.entities.ids.SerieVisualizadaId;
import es.iesjandula.damfilms_server.parsers.interfaces.IParseo;
import es.iesjandula.damfilms_server.repositories.ISerieRepository;
import es.iesjandula.damfilms_server.repositories.ISerieVisualizadaRepository;
import es.iesjandula.damfilms_server.repositories.IUsuarioRepository;
import es.iesjandula.damfilms_server.utils.Constants;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoSerieVisualizadaImpl implements IParseo<SerieVisualizada>
{

	@Autowired
	private ISerieVisualizadaRepository iSerieVisualizadaRepository;

	@Autowired
	private ISerieRepository iSerieRepository;

	@Autowired
	private IUsuarioRepository iUsuarioRepository;

	@Override
	public void parseaFichero(Scanner scanner) throws DamfilmsServerException
	{
		// TODO Auto-generated method stub

		scanner.nextLine();

		while(scanner.hasNextLine())
		{
			String lineaDelFichero = scanner.nextLine();

			String[] lineaDelFicheroTroceada = lineaDelFichero.split(Constants.CSV_DELIMITER);

			SerieVisualizadaId serieVisualizadaId = new SerieVisualizadaId();

			Optional<Serie> optionalSerie = this.iSerieRepository.findById(lineaDelFicheroTroceada[0]);

			if(!optionalSerie.isPresent())
			{
				String mensajeError = "No existe la serie";
				log.error(mensajeError);
				throw new DamfilmsServerException(7, mensajeError);
			}

			serieVisualizadaId.setSerie(optionalSerie.get());

			SerieVisualizada serieVisualizada = new SerieVisualizada();

			serieVisualizada.setSerieVisualizadaId(serieVisualizadaId);
			
			Optional<Usuario> optionalUsuario = this.iUsuarioRepository.findById(lineaDelFicheroTroceada[1]);

			if(!optionalUsuario.isPresent())
			{
				String mensajeError = "No existe el usuario";
				log.error(mensajeError);
				throw new DamfilmsServerException(8, mensajeError);
			}
			
			serieVisualizada.setUsuario(optionalUsuario.get());
			serieVisualizada.setEpisodiosVistos(Integer.parseInt(lineaDelFicheroTroceada[2]));

			this.iSerieVisualizadaRepository.saveAndFlush(serieVisualizada);


		}

	}

}
