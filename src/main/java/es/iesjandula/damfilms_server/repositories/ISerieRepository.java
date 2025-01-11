package es.iesjandula.damfilms_server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.dtos.SerieDescripcion;
import es.iesjandula.damfilms_server.dtos.SerieDetalle;
import es.iesjandula.damfilms_server.entities.Serie;

@Repository
public interface ISerieRepository extends JpaRepository<Serie, String>
{
	@Query("SELECT s FROM Serie s ORDER BY s.fechaEstreno DESC")
	List<Serie> findTop10ByOrderByFechaEstrenoDesc() ;
	
	@Query("SELECT s FROM Serie s ORDER BY s.clasificacion DESC")
	List<Serie> findTop10ByOrderByClasificacionDesc() ;
	
	// Para encontrar una serie por su ID
	Optional<Serie> findById(String titulo);

	// Para encontrar series por género
	@Query("SELECT s FROM Serie s WHERE s.genero = :genero")
	List<Serie> findByGenero(@Param("genero") String genero);


	@Query("SELECT new es.iesjandula.damfilms_server.dtos.SerieDetalle(s.titulo, s.fechaEstreno) "
		       + "FROM Serie s "
		       + "WHERE s.titulo = :titulo")
		SerieDetalle encontrarSerieDetallada(@Param("titulo") String nombre);

		@Query("SELECT new es.iesjandula.damfilms_server.dtos.SerieDescripcion(s.titulo, s.descripcion) "
		       + "FROM Serie s "
		       + "WHERE s.titulo = :titulo ")
		SerieDescripcion encontrarSerieDescripcion(@Param("titulo") String nombre);

}
