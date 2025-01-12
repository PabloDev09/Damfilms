package es.iesjandula.damfilms_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.repositories.IRoleRepository;
import jakarta.annotation.PostConstruct;

@Service
public class Controller
{

	@Autowired
	private IRoleRepository iRoleRepository;

	@PostConstruct
	public void init()
	{

		System.out.println();
		System.out.println(this.iRoleRepository.entontrarTodosLosRoles());
		System.out.println();

	}

}
