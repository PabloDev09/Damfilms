package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.damfilms_server.entities.Pelicula;
import es.iesjandula.damfilms_server.entities.ids.PeliculaId;

public interface IPeliculaRepository extends JpaRepository<Pelicula, PeliculaId>{

}
