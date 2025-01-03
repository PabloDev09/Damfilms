package es.iesjandula.damfilms_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.Documental;
import es.iesjandula.damfilms_server.entities.ids.DocumentalId;

@Repository
public interface IDocumentalRepository extends JpaRepository<Documental, DocumentalId>
{
	@Query("SELECT d FROM Documental d ORDER BY d.documentalId.fechaEstreno DESC")
	List<Documental> findTop10ByOrderByFechaEstrenoDesc() ;
	
	@Query("SELECT d FROM Documental d ORDER BY d.clasificacion DESC")
	List<Documental> findTop10ByOrderByClasificacionDesc() ;
}
