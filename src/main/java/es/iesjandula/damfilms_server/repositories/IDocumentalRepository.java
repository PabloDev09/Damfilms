package es.iesjandula.damfilms_server.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.dtos.DocumentalDescripcion;
import es.iesjandula.damfilms_server.dtos.DocumentalDetalle;
import es.iesjandula.damfilms_server.entities.Documental;
import es.iesjandula.damfilms_server.entities.ids.DocumentalId;

@Repository
public interface IDocumentalRepository extends JpaRepository<Documental, DocumentalId>
{
	@Query("SELECT d FROM Documental d ORDER BY d.documentalId.fechaEstreno DESC")
	List<Documental> findTop10ByOrderByFechaEstrenoDesc() ;
	
	@Query("SELECT d FROM Documental d ORDER BY d.clasificacion DESC")
	List<Documental> findTop10ByOrderByClasificacionDesc() ;
	
	@Query("SELECT new es.iesjandula.damfilms_server.dtos.DocumentalDetalle(d.documentalId.titulo, d.duracion, d.documentalId.fechaEstreno) "
			+ "FROM Documental d "
			+ "WHERE d.documentalId.titulo = :titulo AND d.documentalId.fechaEstreno = :fechaEstreno")
	DocumentalDetalle encontrarDocumentalDetallado(@Param("titulo") String titulo,
											@Param("fechaEstreno") Date fechaEstreno);
	
	@Query("SELECT new es.iesjandula.damfilms_server.dtos.DocumentalDescripcion(d.documentalId.titulo, g.nombre, d.descripcion) "
			+ "FROM Documental d JOIN d.genero g "
			+ "WHERE d.documentalId.titulo = :titulo AND d.documentalId.fechaEstreno = :fechaEstreno")
	DocumentalDescripcion encontrarDocumentalDescripcion(@Param("titulo") String titulo,
													 @Param("fechaEstreno") Date fechaEstreno);

}
