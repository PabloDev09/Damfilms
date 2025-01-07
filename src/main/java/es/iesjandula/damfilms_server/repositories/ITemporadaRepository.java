package es.iesjandula.damfilms_server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.Temporada;
import es.iesjandula.damfilms_server.entities.ids.TemporadaId;

@Repository
public interface ITemporadaRepository extends JpaRepository<Temporada, TemporadaId>
{
	// Para encontrar una temporada por su ID
	@Query("SELECT t FROM Temporada t WHERE t.id = :temporadaId")
	Optional<Temporada> findById(@Param("temporadaId") Long temporadaId);

	// Para encontrar temporadas por el ID de la serie
	@Query("SELECT t FROM Temporada t WHERE t.serie.id = :serieId")
	List<Temporada> findBySerieId(@Param("serieId") Long serieId);


}
