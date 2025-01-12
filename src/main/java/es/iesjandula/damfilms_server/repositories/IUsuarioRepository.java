package es.iesjandula.damfilms_server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.dtos.UsuarioLogin;
import es.iesjandula.damfilms_server.entities.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>
{
	Usuario findByNombre(String nombre);
	
	Optional<Usuario> findByCorreo(String correo);
	
	@Query("SELECT new es.iesjandula.damfilms_server.dtos.UsuarioLogin(u.correo, u.contrasena) "
			+ "FROM Usuario u "
			+ "WHERE u.correo = :email AND u.contrasena = :contrasenia ")
	UsuarioLogin encontrarUsuarioYContrasenia(@Param("email") String email, @Param("contrasenia") String contrasenia);
}
