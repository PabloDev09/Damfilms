package es.iesjandula.damfilms_server.parsers.impl;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Role;
import es.iesjandula.damfilms_server.parsers.interfaces.IParseo;
import es.iesjandula.damfilms_server.repositories.IRoleRepository;
import es.iesjandula.damfilms_server.utils.Constants;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoTipoSuscripcionImpl implements IParseo<Role>
{

	@Autowired
	private IRoleRepository iRoleRepository;

	@Override
	public void parseaFichero(Scanner scanner) throws DamfilmsServerException
	{
		// TODO Auto-generated method stub

		scanner.nextLine();

		while(scanner.hasNextLine())
		{
			String lineaDelFichero = scanner.nextLine();

			String[] lineaDelFicheroTroceada = lineaDelFichero.split(Constants.CSV_DELIMITER);

			Role role = new Role();
			role.setRole(lineaDelFicheroTroceada[0]);

			this.iRoleRepository.saveAndFlush(role);
		}

	}

}
