package es.iesjandula.damfilms_server.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.dtos.SerieDescripcion;
import es.iesjandula.damfilms_server.dtos.SerieDetalle;
import es.iesjandula.damfilms_server.entities.Serie;
import es.iesjandula.damfilms_server.entities.ids.SerieId;

@Repository
public interface ISerieRepository extends JpaRepository<Serie, SerieId>
{
	@Query("SELECT s FROM Serie s ORDER BY s.serieId.fechaEstreno DESC")
	List<Serie> findTop10ByOrderByFechaEstrenoDesc() ;
	
	@Query("SELECT s FROM Serie s ORDER BY s.clasificacion DESC")
	List<Serie> findTop10ByOrderByClasificacionDesc() ;
	
	// Para encontrar una serie por su ID
	@Query("SELECT s FROM Serie s WHERE s.id = :serieId")
	Optional<Serie> findById(@Param("serieId") Long serieId);

	// Para encontrar series por género
	@Query("SELECT s FROM Serie s WHERE s.genero = :genero")
	List<Serie> findByGenero(@Param("genero") String genero);


	@Query("SELECT new es.iesjandula.damfilms_server.dtos.SerieDetalleDTO(s.serieId.titulo, s.fechaEstreno, s.temporadas) "
		       + "FROM Serie s "
		       + "WHERE s.serieId.titulo = :titulo AND s.fechaEstreno = :fechaEstreno")
		SerieDetalle encontrarSerieDetallada(@Param("titulo") String titulo,
		                                        @Param("fechaEstreno") Date fechaEstreno);

		@Query("SELECT new es.iesjandula.damfilms_server.dtos.SerieDescripcionDTO(s.serieId.titulo, g.nombre, s.descripcion, s.temporadas) "
		       + "FROM Serie s JOIN s.genero g "
		       + "WHERE s.serieId.titulo = :titulo AND s.fechaEstreno = :fechaEstreno")
		SerieDescripcion encontrarSerieDescripcion(@Param("titulo") String titulo,
		                                              @Param("fechaEstreno") Date fechaEstreno);

}
