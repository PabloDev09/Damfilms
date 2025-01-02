package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.DocumentalVisualizado;
import es.iesjandula.damfilms_server.entities.ids.DocumentalVisualizadoId;

@Repository
public interface IDocumentalVisualizadoRepository extends JpaRepository<DocumentalVisualizado, DocumentalVisualizadoId>
{

}
