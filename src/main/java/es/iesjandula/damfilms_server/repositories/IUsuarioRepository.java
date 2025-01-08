package es.iesjandula.damfilms_server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, String>
{
	public Optional<Usuario> findByNombre(String nombre);
	
	public Optional<Usuario> findByCorreo(String correo);
}
