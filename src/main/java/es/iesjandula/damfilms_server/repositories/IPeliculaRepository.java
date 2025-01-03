package es.iesjandula.damfilms_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.Pelicula;
import es.iesjandula.damfilms_server.entities.ids.PeliculaId;

@Repository
public interface IPeliculaRepository extends JpaRepository<Pelicula, PeliculaId>
{

	@Query("SELECT p FROM Pelicula p ORDER BY p.peliculaId.fechaEstreno DESC")
	List<Pelicula> findTop10ByOrderByFechaEstrenoDesc() ;
	
	@Query("SELECT p FROM Pelicula p ORDER BY p.clasificacion DESC")
	List<Pelicula> findTop10ByOrderByClasificacionDesc() ;
	
}
