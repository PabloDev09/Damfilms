package es.iesjandula.damfilms_server.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.iesjandula.damfilms_server.entities.Role;


public interface IRoleRepository extends JpaRepository<Role, Long>
{
	Role findByRole(String role) ;
	
	@Query("SELECT DISTINCT r.role FROM Role r")
	List<String> entontrarTodosLosRoles();
	
}
