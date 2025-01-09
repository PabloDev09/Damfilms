package es.iesjandula.damfilms_server.parsers.impl;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Documental;
import es.iesjandula.damfilms_server.entities.DocumentalVisualizado;
import es.iesjandula.damfilms_server.entities.Usuario;
import es.iesjandula.damfilms_server.entities.ids.DocumentalId;
import es.iesjandula.damfilms_server.entities.ids.DocumentalVisualizadoId;
import es.iesjandula.damfilms_server.parsers.interfaces.IParseo;
import es.iesjandula.damfilms_server.repositories.IDocumentalRepository;
import es.iesjandula.damfilms_server.repositories.IDocumentalVisualizadoRepository;
import es.iesjandula.damfilms_server.repositories.IUsuarioRepository;
import es.iesjandula.damfilms_server.utils.Constants;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import es.iesjandula.damfilms_server.utils.DatesUtil;
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

		while(scanner.hasNextLine())
		{
			String lineaDelFichero = scanner.nextLine();

			String[] lineaDelFicheroTroceada = lineaDelFichero.split(Constants.CSV_DELIMITER);


			DocumentalVisualizadoId documentalVisualizadoId = new DocumentalVisualizadoId();

			DocumentalId documentalId = new DocumentalId();

			documentalId.setTitulo(lineaDelFicheroTroceada[0]);
			try
			{
				documentalId.setFechaEstreno(DatesUtil.crearFechaDesdeString(lineaDelFicheroTroceada[1]));

			}
			catch (DamfilmsServerException damfilmsServerException)
			{
				damfilmsServerException.printStackTrace();

			}

			Optional<Documental> optionalDocumental = this.documentalRepository.findById(documentalId);

			if(!optionalDocumental.isPresent())
			{
				String mensajeError = "No existe el documental";
				log.error(mensajeError);
				throw new DamfilmsServerException(5, mensajeError);
			}

			documentalVisualizadoId.setDocumental(optionalDocumental.get());

			DocumentalVisualizado documentalVisualizado = new DocumentalVisualizado();

			documentalVisualizado.setDocumentalVisualizadoId(documentalVisualizadoId);
			
			Optional<Usuario> optionalUsurio = this.usuarioRepository.findById(Long.parseLong(lineaDelFicheroTroceada[2]));

			if(!optionalUsurio.isPresent())
			{
				String mensajeError = "No existe el usuario";
				log.error(mensajeError);
				throw new DamfilmsServerException(6, mensajeError);
			}
			
			documentalVisualizado.setUsuario(optionalUsurio.get());
			documentalVisualizado.setTiempoVisto(Integer.parseInt(lineaDelFicheroTroceada[3]));

			this.documentalVisualizadoRepository.saveAndFlush(documentalVisualizado);

		}

	}

}
