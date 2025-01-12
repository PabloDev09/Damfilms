package es.iesjandula.damfilms_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.damfilms_server.entities.UserRole;
import es.iesjandula.damfilms_server.entities.Usuario;
import es.iesjandula.damfilms_server.entities.ids.UserRoleId;

public interface IUserRoleRepository extends JpaRepository<UserRole, UserRoleId>
{
	List<UserRole> findByIdUsuario(Usuario user);
}
