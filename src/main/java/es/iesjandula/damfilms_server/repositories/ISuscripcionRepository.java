package es.iesjandula.damfilms_server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.Suscripcion;
import es.iesjandula.damfilms_server.entities.Usuario;
import es.iesjandula.damfilms_server.entities.ids.SuscripcionId;

@Repository
public interface ISuscripcionRepository extends JpaRepository<Suscripcion, SuscripcionId>
{
	public Optional<Suscripcion> findByUsuario(Usuario Usuario);
	
	public Optional<Suscripcion> findByTipo(String tipo);
	
	@Query("SELECT DISTINCT s.tipo FROM Suscripcion s")
	public List<String> encontrarTodosLosTipos();
}
