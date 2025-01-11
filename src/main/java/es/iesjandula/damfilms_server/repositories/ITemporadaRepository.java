package es.iesjandula.damfilms_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.Temporada;
import es.iesjandula.damfilms_server.entities.ids.TemporadaId;

@Repository
public interface ITemporadaRepository extends JpaRepository<Temporada, TemporadaId>
{
	// Para encontrar temporadas por el nombre de la serie
	@Query("SELECT t FROM Temporada t WHERE t.serie.id = :serieId")
	List<Temporada> findBySerieId(@Param("serieId") Long serieId);


}
