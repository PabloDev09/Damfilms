package es.iesjandula.damfilms_server.entities;

import es.iesjandula.damfilms_server.entities.ids.SerieVisualizadaId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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

	@Column(length = 5, nullable = false)
	private int episodiosVistos;
}
