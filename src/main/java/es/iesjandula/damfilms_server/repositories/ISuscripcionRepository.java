package es.iesjandula.damfilms_server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.Suscripcion;
import es.iesjandula.damfilms_server.entities.Usuario;
import es.iesjandula.damfilms_server.entities.ids.SuscripcionId;

@Repository
public interface ISuscripcionRepository extends JpaRepository<Suscripcion, SuscripcionId>
{
	public Optional<Suscripcion> findByUsuario(Usuario Usuario);
}
