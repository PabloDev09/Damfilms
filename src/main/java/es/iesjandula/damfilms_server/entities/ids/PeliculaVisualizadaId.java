package es.iesjandula.damfilms_server.entities.ids;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PeliculaVisualizadaId 
{	
	private long idPelicula;
	
	private long usuario;
}
