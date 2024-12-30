package es.iesjandula.damfilms_server.entities;

import es.iesjandula.damfilms_server.entities.ids.DocumentalVisualizadoId;
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
public class DocumentalVisualizado 
{
	@EmbeddedId
	private DocumentalVisualizadoId documentalVisualizadoId;
	
	@Column(length = 10, nullable = false)
	private String tiempoVisto;

}
