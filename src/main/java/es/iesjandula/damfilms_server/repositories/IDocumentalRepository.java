package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.Documental;
import es.iesjandula.damfilms_server.entities.ids.DocumentalId;

@Repository
public interface IDocumentalRepository extends JpaRepository<Documental, DocumentalId>
{

}
