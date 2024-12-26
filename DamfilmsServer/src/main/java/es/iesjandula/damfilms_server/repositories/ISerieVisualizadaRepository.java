package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.damfilms_server.entities.SerieVisualizada;
import es.iesjandula.damfilms_server.entities.ids.SerieVisualizadaId;

public interface ISerieVisualizadaRepository extends JpaRepository<SerieVisualizada, SerieVisualizadaId>{

}
