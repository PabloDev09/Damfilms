package es.iesjandula.damfilms_server.entities;

import es.iesjandula.damfilms_server.entities.ids.SerieVisualizadaId;
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
public class SerieVisualizada
{
	@EmbeddedId
	private SerieVisualizadaId serieVisualizadaId;
	
	@ManyToOne
	@JoinColumn(name = "serie_id", referencedColumnName = "id")
	private Serie serie;
	
	@ManyToOne
	@JoinColumn(name = "usuario_nombre")
	@MapsId("usuario")
	private Usuario usuario;

	@Column(length = 5, nullable = false)
	private int episodiosVistos;
	
}
