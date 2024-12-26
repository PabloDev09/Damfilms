package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.damfilms_server.entities.PeliculaVisualizada;
import es.iesjandula.damfilms_server.entities.ids.PeliculaVisualizadaId;

public interface IPeliculaVisualizadaRepository extends JpaRepository<PeliculaVisualizada, PeliculaVisualizadaId>{

}
