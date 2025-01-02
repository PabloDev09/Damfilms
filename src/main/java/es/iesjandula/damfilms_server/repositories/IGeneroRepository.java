package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.entities.Genero;

@Repository
public interface IGeneroRepository extends JpaRepository<Genero, String>
{

}
