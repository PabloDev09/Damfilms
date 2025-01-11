package es.iesjandula.damfilms_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.Episodio;
import es.iesjandula.damfilms_server.entities.ids.TemporadaId;

@Repository
public interface IEpisodioRepository extends JpaRepository<Episodio, Integer>
{
	// Para encontrar un episodio por su ID y el ID de la serie
	@Query("SELECT e FROM Episodio e WHERE e.episodioId.numero = :episodioNumero AND e.temporada.serie.titulo = :serieNombre")
	Episodio findByIdAndSerieNombre(@Param("episodioNumero") Integer episodioNumero, @Param("serieNombre") String serieNombre);


	// Para encontrar episodios por el ID de la temporada
	@Query("SELECT e FROM Episodio e WHERE e.temporada.temporadaId.numero = :temporadaNumero AND e.temporada.temporadaId.serie.titulo = :serieNombre")
	List<Episodio> findByTemporadaId(@Param("temporadaNumero") Integer temporadaNumero, @Param("serieNombre") String serieNombre);


}
