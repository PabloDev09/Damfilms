package es.iesjandula.damfilms_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.Episodio;

@Repository
public interface IEpisodioRepository extends JpaRepository<Episodio, Integer>
{

	Episodio findByIdAndSerieId(Long episodioId, Long serieId);

	List<Episodio> findByTemporadaId(Long temporadaId);


}
