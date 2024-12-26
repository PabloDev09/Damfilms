package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.damfilms_server.entities.Episodio;

public interface IEpisodioRepository extends JpaRepository<Episodio, Integer>{

}
