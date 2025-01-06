package es.iesjandula.damfilms_server.parsers.impl;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Configuracion;
import es.iesjandula.damfilms_server.entities.Usuario;
import es.iesjandula.damfilms_server.parsers.interfaces.IParseo;
import es.iesjandula.damfilms_server.repositories.IConfiguracionRepository;
import es.iesjandula.damfilms_server.repositories.IUsuarioRepository;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoUsuarioImpl implements IParseo<Usuario>
{

	@Autowired
	private IUsuarioRepository iUsuarioRepository;

	@Autowired
	private IConfiguracionRepository iConfiguracionRepository;

	@Override
	public void parseaFichero(Scanner scanner) throws DamfilmsServerException
	{
		// TODO Auto-generated method stub

		scanner.nextLine();

		while(scanner.hasNextLine())
		{
			String lineaDelFichero = scanner.nextLine();

			String[] lineaDelFicheroTroceada = lineaDelFichero.split(",");

			Usuario usuario = new Usuario();

			usuario.setNombre(lineaDelFicheroTroceada[0]);
			usuario.setCorreo(lineaDelFicheroTroceada[1]);
			usuario.setContrasena(lineaDelFicheroTroceada[2]);

			Optional<Configuracion> optionalConfiguracion = this.iConfiguracionRepository.findById(Long.valueOf(lineaDelFicheroTroceada[3]));

			if(!optionalConfiguracion.isPresent())
			{
				String mensajeError = "No existe la configuración";
				log.error(mensajeError);
				throw new DamfilmsServerException(3, mensajeError);
			}

			usuario.setConfiguracion(optionalConfiguracion.get());

			this.iUsuarioRepository.saveAndFlush(usuario);
		}

	}

}
