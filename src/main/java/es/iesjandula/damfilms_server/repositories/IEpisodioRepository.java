package es.iesjandula.damfilms_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.Episodio;

@Repository
public interface IEpisodioRepository extends JpaRepository<Episodio, Integer>
{
	// Para encontrar un episodio por su ID y el ID de la serie
	@Query("SELECT e FROM Episodio e WHERE e.id = :episodioId AND e.serie.id = :serieId")
	Episodio findByIdAndSerieId(@Param("episodioId") Long episodioId, @Param("serieId") Long serieId);

	// Para encontrar episodios por el ID de la temporada
	@Query("SELECT e FROM Episodio e WHERE e.temporada.id = :temporadaId")
	List<Episodio> findByTemporadaId(@Param("temporadaId") Long temporadaId);


}
