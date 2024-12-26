package es.iesjandula.damfilms_server.entities.ids;

import java.io.Serializable;

import es.iesjandula.damfilms_server.entities.Serie;
import es.iesjandula.damfilms_server.entities.Usuario;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SerieVisualizadaId implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7955490310406148894L;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "nombre_serie", referencedColumnName = "nombre"),
		@JoinColumn(name = "fecha_esteno_serie", referencedColumnName = "fechaEstreno")
	})
	private Serie serie;
	
	@ManyToOne
	@JoinColumn(name = "nombre_usuario")
	private Usuario usuario;

}
