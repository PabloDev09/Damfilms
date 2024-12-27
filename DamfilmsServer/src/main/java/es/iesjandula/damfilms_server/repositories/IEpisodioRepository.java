package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.Episodio;

@Repository
public interface IEpisodioRepository extends JpaRepository<Episodio, Integer>
{

}
