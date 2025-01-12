package es.iesjandula.damfilms_server.parsers.impl;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Documental;
import es.iesjandula.damfilms_server.entities.DocumentalVisualizado;
import es.iesjandula.damfilms_server.entities.Usuario;
import es.iesjandula.damfilms_server.entities.ids.DocumentalVisualizadoId;
import es.iesjandula.damfilms_server.parsers.interfaces.IParseo;
import es.iesjandula.damfilms_server.repositories.IDocumentalRepository;
import es.iesjandula.damfilms_server.repositories.IDocumentalVisualizadoRepository;
import es.iesjandula.damfilms_server.repositories.IUsuarioRepository;
import es.iesjandula.damfilms_server.utils.Constants;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoDocumentalVisualizadoImpl implements IParseo<DocumentalVisualizado>
{

	@Autowired
	private IDocumentalVisualizadoRepository documentalVisualizadoRepository;

	@Autowired
	private IDocumentalRepository documentalRepository;

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public void parseaFichero(Scanner scanner) throws DamfilmsServerException
	{
		// TODO Auto-generated method stub

		scanner.nextLine();

		while (scanner.hasNextLine())
		{
			String lineaDelFichero = scanner.nextLine();

			String[] lineaDelFicheroTroceada = lineaDelFichero.split(Constants.CSV_DELIMITER);

			DocumentalVisualizado documentalVisualizado = new DocumentalVisualizado();

			Optional<Documental> optionalDocumental = this.documentalRepository
					.findById(Long.parseLong(lineaDelFicheroTroceada[0]));

			if (!optionalDocumental.isPresent())
			{
				String mensajeError = "No existe el documental";
				log.error(mensajeError);
				throw new DamfilmsServerException(5, mensajeError);
			}

			documentalVisualizado.setDocumental(optionalDocumental.get());

			Optional<Usuario> optionalUsurio = this.usuarioRepository
					.findById(Long.parseLong(lineaDelFicheroTroceada[1]));

			if (!optionalUsurio.isPresent())
			{
				String mensajeError = "No existe el usuario";
				log.error(mensajeError);
				throw new DamfilmsServerException(4, mensajeError);
			}

			documentalVisualizado.setUsuario(optionalUsurio.get());

			DocumentalVisualizadoId documentalVisualizadoId = new DocumentalVisualizadoId(
					optionalDocumental.get().getId(), optionalUsurio.get().getId());

			documentalVisualizado.setDocumentalVisualizadoId(documentalVisualizadoId);

			documentalVisualizado.setTiempoVisto(Integer.parseInt(lineaDelFicheroTroceada[2]));

			this.documentalVisualizadoRepository.saveAndFlush(documentalVisualizado);

		}

	}

}
