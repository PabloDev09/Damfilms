package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.damfilms_server.entities.Modo;

public interface IModoRepository extends JpaRepository<Modo, String>{

}
