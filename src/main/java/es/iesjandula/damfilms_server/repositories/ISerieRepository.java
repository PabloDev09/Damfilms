package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.Serie;
import es.iesjandula.damfilms_server.entities.ids.SerieId;

@Repository
public interface ISerieRepository extends JpaRepository<Serie, SerieId>
{

}
