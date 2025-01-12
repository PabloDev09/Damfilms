package es.iesjandula.damfilms_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.dtos.UsuarioDto;
import es.iesjandula.damfilms_server.entities.PeliculaVisualizada;
import es.iesjandula.damfilms_server.entities.ids.PeliculaVisualizadaId;

@Repository
public interface IPeliculaVisualizadaRepository extends JpaRepository<PeliculaVisualizada, PeliculaVisualizadaId>
{

	@Query("SELECT DISTINCT new es.iesjandula.damfilms_server.dtos.UsuarioDto(u.nombre) "
			+ "FROM PeliculaVisualizada p "
			+ "JOIN p.usuario u "
			+ "ON u.nombre = :nombre")
	UsuarioDto encontrarUsuario(@Param("nombre") String nombre);
	
	@Query("SELECT p "
			+ "FROM PeliculaVisualizada p "
			+ "JOIN p.usuario u "
			+ "ON u.nombre = :nombre")
	List<PeliculaVisualizada> encontrarPeliculasVisualizadasPorUsuario(@Param("nombre") String nombre);
}
