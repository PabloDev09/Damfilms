package es.iesjandula.damfilms_server.parsers.impl;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Suscripcion;
import es.iesjandula.damfilms_server.entities.Usuario;
import es.iesjandula.damfilms_server.entities.ids.SuscripcionId;
import es.iesjandula.damfilms_server.parsers.interfaces.IParseo;
import es.iesjandula.damfilms_server.repositories.ISuscripcionRepository;
import es.iesjandula.damfilms_server.repositories.IUsuarioRepository;
import es.iesjandula.damfilms_server.utils.Constants;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import es.iesjandula.damfilms_server.utils.DatesUtil;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoSuscripcionImpl implements IParseo<Suscripcion>
{

	@Autowired
	private ISuscripcionRepository iSuscripcionRepository;

	@Autowired
	private IUsuarioRepository iUsuarioRepository;

	@Override
	public void parseaFichero(Scanner scanner) throws DamfilmsServerException
	{
		// TODO Auto-generated method stub

		scanner.nextLine();

		while (scanner.hasNextLine())
		{
			String lineaDelFichero = scanner.nextLine();

			String[] lineaDelFicheroTroceada = lineaDelFichero.split(Constants.CSV_DELIMITER);

			Suscripcion suscripcion = new Suscripcion();
			SuscripcionId suscripcionId = new SuscripcionId();

			Optional<Usuario> optionalUsuario = this.iUsuarioRepository
					.findById(Long.parseLong(lineaDelFicheroTroceada[0]));

			if (!optionalUsuario.isPresent())
			{
				String mensajeError = "No existe el usuario";
				log.error(mensajeError);
				throw new DamfilmsServerException(2, mensajeError);
			}

			suscripcionId.setUsuario(optionalUsuario.get());
			suscripcionId.setFechaInicio(DatesUtil.crearFechaDesdeString(lineaDelFicheroTroceada[1]));

			suscripcion.setSuscripcionId(suscripcionId);

			suscripcion.setTipo(lineaDelFicheroTroceada[2]);
			suscripcion.setDuracion(Integer.parseInt(lineaDelFicheroTroceada[3]));

			if (!(lineaDelFicheroTroceada[4]).trim().equals("NULL"))
			{
				suscripcion.setFechaFin(DatesUtil.crearFechaDesdeString(lineaDelFicheroTroceada[4]));
			}

			this.iSuscripcionRepository.saveAndFlush(suscripcion);
		}

	}

}
