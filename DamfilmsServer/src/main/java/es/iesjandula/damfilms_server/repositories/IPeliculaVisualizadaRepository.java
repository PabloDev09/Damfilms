package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.PeliculaVisualizada;
import es.iesjandula.damfilms_server.entities.ids.PeliculaVisualizadaId;

@Repository
public interface IPeliculaVisualizadaRepository extends JpaRepository<PeliculaVisualizada, PeliculaVisualizadaId>
{

}
