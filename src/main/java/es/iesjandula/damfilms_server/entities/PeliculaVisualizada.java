package es.iesjandula.damfilms_server.entities;

import es.iesjandula.damfilms_server.entities.ids.PeliculaVisualizadaId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PeliculaVisualizada
{

	@EmbeddedId
	private PeliculaVisualizadaId peliculaVisualizadaId;

	@Column(length = 3, nullable = false)
	private int tiempoVisto;
	
	@ManyToOne
	@JoinColumn(name = "usuario_nombre")
	@MapsId("usuario")
	private Usuario usuario;

}
