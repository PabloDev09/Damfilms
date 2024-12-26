package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.damfilms_server.entities.DocumentalVisualizado;
import es.iesjandula.damfilms_server.entities.ids.DocumentalVisualizadoId;

public interface IDocumentalVisualizadoRepository extends JpaRepository<DocumentalVisualizado, DocumentalVisualizadoId>{

}
