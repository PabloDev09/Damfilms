package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.damfilms_server.entities.Genero;

public interface GeneroRepository extends JpaRepository<Genero, String>{

}