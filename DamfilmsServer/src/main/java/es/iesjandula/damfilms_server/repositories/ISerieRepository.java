package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.damfilms_server.entities.Serie;
import es.iesjandula.damfilms_server.entities.ids.SerieId;

public interface ISerieRepository extends JpaRepository<Serie, SerieId>{

}
