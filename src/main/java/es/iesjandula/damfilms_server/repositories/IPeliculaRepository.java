package es.iesjandula.damfilms_server.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.dtos.PeliculaDescripcion;
import es.iesjandula.damfilms_server.dtos.PeliculaDetallada;
import es.iesjandula.damfilms_server.entities.Pelicula;
import es.iesjandula.damfilms_server.entities.ids.PeliculaId;

@Repository
public interface IPeliculaRepository extends JpaRepository<Pelicula, PeliculaId>
{

	@Query("SELECT p FROM Pelicula p ORDER BY p.peliculaId.fechaEstreno DESC")
	List<Pelicula> findTop10ByOrderByFechaEstrenoDesc() ;
	
	@Query("SELECT p FROM Pelicula p ORDER BY p.clasificacion DESC")
	List<Pelicula> findTop10ByOrderByClasificacionDesc() ;
	
	@Query("SELECT new es.iesjandula.damfilms_server.dtos.PeliculaDetallada(p.peliculaId.titulo, p.duracion, p.peliculaId.fechaEstreno) "
			+ "FROM Pelicula p "
			+ "WHERE p.peliculaId.titulo = :titulo AND p.peliculaId.fechaEstreno = :fechaEstreno")
	PeliculaDetallada encontrarPeliculaDetallado(@Param("titulo") String titulo,
												   @Param("fechaEstreno") Date fechaEstreno);
	
	@Query("SELECT new es.iesjandula.damfilms_server.dtos.PeliculaDescripcion(p.peliculaId.titulo, g.nombre, p.descripcion) "
			+ "FROM Pelicula p JOIN p.genero g "
			+ "WHERE p.peliculaId.titulo = :titulo AND p.peliculaId.fechaEstreno = :fechaEstreno")
	PeliculaDescripcion encontrarPeliculaDescripcion(@Param("titulo") String titulo,
													   @Param("fechaEstreno") Date fechaEstreno);
	
}
