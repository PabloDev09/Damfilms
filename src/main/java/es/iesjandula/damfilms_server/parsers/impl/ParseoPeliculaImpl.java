package es.iesjandula.damfilms_server.parsers.impl;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Genero;
import es.iesjandula.damfilms_server.entities.Pelicula;
import es.iesjandula.damfilms_server.entities.ids.PeliculaId;
import es.iesjandula.damfilms_server.parsers.interfaces.IParseo;
import es.iesjandula.damfilms_server.repositories.IGeneroRepository;
import es.iesjandula.damfilms_server.repositories.IPeliculaRepository;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import es.iesjandula.damfilms_server.utils.DatesUtil;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoPeliculaImpl implements IParseo<Pelicula>
{

	@Autowired
	private IPeliculaRepository iPeliculaRepository;

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

			PeliculaId peliculaId = new PeliculaId();

			peliculaId.setTitulo(lineaDelFicheroTroceada[0]);
			try
			{
				peliculaId.setFechaEstreno(DatesUtil.crearFechaDesdeString(lineaDelFicheroTroceada[1]));

			}
			catch (DamfilmsServerException damfilmsServerException)
			{

				damfilmsServerException.printStackTrace();
			}

			Pelicula pelicula = new Pelicula();

			pelicula.setPeliculaId(peliculaId);
			pelicula.setDescripcion(lineaDelFicheroTroceada[2]);
			pelicula.setDuracion(Integer.valueOf(lineaDelFicheroTroceada[3]));
			pelicula.setClasificacion(lineaDelFicheroTroceada[4]);
			Optional<Genero> optionalGenero = this.iGeneroRepository.findById(lineaDelFicheroTroceada[5]);

			if(!optionalGenero.isPresent())
			{
				String mensajeError = "No existe el género";
				log.error(mensajeError);
				throw new DamfilmsServerException(1, mensajeError);
			}
			pelicula.setGenero(optionalGenero.get());

			this.iPeliculaRepository.saveAndFlush(pelicula);
		}

	}

}
