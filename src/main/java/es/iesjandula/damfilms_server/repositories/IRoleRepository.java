package es.iesjandula.damfilms_server.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.damfilms_server.entities.Role;


public interface IRoleRepository extends JpaRepository<Role, Integer>
{
	Role findByRole(String role) ;
}
