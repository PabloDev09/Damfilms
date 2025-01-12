package es.iesjandula.damfilms_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iesjandula.damfilms_server.dtos.UsuarioDto;
import es.iesjandula.damfilms_server.entities.DocumentalVisualizado;
import es.iesjandula.damfilms_server.entities.ids.DocumentalVisualizadoId;

@Repository
public interface IDocumentalVisualizadoRepository extends JpaRepository<DocumentalVisualizado, DocumentalVisualizadoId>
{

	@Query("SELECT new es.iesjandula.damfilms_server.dtos.UsuarioDto(u.nombre) " + "FROM DocumentalVisualizado dv "
			+ "JOIN dv.usuario u " + "WHERE u.nombre = :nombre")
	UsuarioDto encontrarUsuario(@Param("nombre") String nombre);

}
