package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.damfilms_server.entities.Temporada;
import es.iesjandula.damfilms_server.entities.ids.TemporadaId;

public interface ITemporadaRepository extends JpaRepository<Temporada, TemporadaId>{

}
