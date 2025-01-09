package es.iesjandula.damfilms_server.entities.ids;

import es.iesjandula.damfilms_server.entities.Documental;
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
public class DocumentalVisualizadoId
{	
	@ManyToOne
	@JoinColumns
	(
		{
			@JoinColumn(name = "documental_titulo", referencedColumnName = "titulo"),
			@JoinColumn(name = "documental_fecha_estreno", referencedColumnName = "fechaEstreno")
		}
	)
	private Documental documental;
	
	private Long usuario;
}
