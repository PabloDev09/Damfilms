package es.iesjandula.damfilms_server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.Configuracion;

@Repository
public interface IConfiguracionRepository extends JpaRepository<Configuracion, Long>
{
	public Optional<Configuracion> findByIdioma(String idioma);

}
