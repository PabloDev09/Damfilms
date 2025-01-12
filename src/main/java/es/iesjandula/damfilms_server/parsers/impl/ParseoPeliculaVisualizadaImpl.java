package es.iesjandula.damfilms_server.parsers.impl;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Pelicula;
import es.iesjandula.damfilms_server.entities.PeliculaVisualizada;
import es.iesjandula.damfilms_server.entities.Usuario;
import es.iesjandula.damfilms_server.entities.ids.PeliculaVisualizadaId;
import es.iesjandula.damfilms_server.parsers.interfaces.IParseo;
import es.iesjandula.damfilms_server.repositories.IPeliculaRepository;
import es.iesjandula.damfilms_server.repositories.IPeliculaVisualizadaRepository;
import es.iesjandula.damfilms_server.repositories.IUsuarioRepository;
import es.iesjandula.damfilms_server.utils.Constants;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoPeliculaVisualizadaImpl implements IParseo<PeliculaVisualizada>
{

	@Autowired
	private IPeliculaVisualizadaRepository iPeliculaVisualizadaRepository;

	@Autowired
	private IPeliculaRepository iPeliculaRepository;

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

			PeliculaVisualizada peliculaVisualizada = new PeliculaVisualizada();
			
			Optional<Pelicula> optionalPelicula = this.iPeliculaRepository.findById(Long.parseLong(lineaDelFicheroTroceada[0]));

			if(!optionalPelicula.isPresent())
			{
				String mensajeError = "No existe la pelicula";
				log.error(mensajeError);
				throw new DamfilmsServerException(3, mensajeError);
			}

			peliculaVisualizada.setPelicula(optionalPelicula.get());
			
			Optional<Usuario> optionalUsuario = this.iUsuarioRepository.findById(Long.parseLong(lineaDelFicheroTroceada[1]));

			if(!optionalUsuario.isPresent())
			{
				String mensajeError = "No existe el usuario";
				log.error(mensajeError);
				throw new DamfilmsServerException(4, mensajeError);
			}
			
			peliculaVisualizada.setUsuario(optionalUsuario.get());
			PeliculaVisualizadaId peliculaVisualizadaId = new PeliculaVisualizadaId(optionalPelicula.get(), optionalUsuario.get());
			
			peliculaVisualizada.setPeliculaVisualizadaId(peliculaVisualizadaId);
			peliculaVisualizada.setTiempoVisto(Integer.parseInt(lineaDelFicheroTroceada[2]));

			this.iPeliculaVisualizadaRepository.saveAndFlush(peliculaVisualizada);


		}

	}

}
