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

@Repository
public interface IDocumentalRepository extends JpaRepository<Documental, Long>
{
	@Query("SELECT d FROM Documental d ORDER BY d.fechaEstreno DESC")
	List<Documental> findTop10ByOrderByFechaEstrenoDesc() ;
	
	@Query("SELECT d FROM Documental d ORDER BY d.clasificacion DESC")
	List<Documental> findTop10ByOrderByClasificacionDesc() ;
	
	@Query("SELECT new es.iesjandula.damfilms_server.dtos.DocumentalDetalle(d.titulo, d.duracion, d.fechaEstreno) "
			+ "FROM Documental d "
			+ "WHERE d.titulo = :titulo AND d.fechaEstreno = :fechaEstreno")
	DocumentalDetalle encontrarDocumentalDetallado(@Param("titulo") String titulo,
											@Param("fechaEstreno") Date fechaEstreno);
	
	@Query("SELECT new es.iesjandula.damfilms_server.dtos.DocumentalDescripcion(d.titulo, g.nombre, d.descripcion) "
			+ "FROM Documental d JOIN d.genero g "
			+ "WHERE d.titulo = :titulo AND d.fechaEstreno = :fechaEstreno")
	DocumentalDescripcion encontrarDocumentalDescripcion(@Param("titulo") String titulo,
													 @Param("fechaEstreno") Date fechaEstreno);

}
