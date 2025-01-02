package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.damfilms_server.entities.Configuracion;

public interface IConfiguracionRepository extends JpaRepository<Configuracion, Long>{

}
