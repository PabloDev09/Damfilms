package es.iesjandula.damfilms_server.entities.ids;

import es.iesjandula.damfilms_server.entities.Serie;
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
public class SerieVisualizadaId
{
	@ManyToOne
	@JoinColumns
	(
		{
			@JoinColumn(name = "serie_nombre", referencedColumnName = "nombre"),
			@JoinColumn(name = "serie_fecha_estreno", referencedColumnName = "fechaEstreno")
		}
	)
	private Serie serie;
	
	private Long usuario;
}
