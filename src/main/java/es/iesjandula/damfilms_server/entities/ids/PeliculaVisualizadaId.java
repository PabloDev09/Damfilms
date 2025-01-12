package es.iesjandula.damfilms_server.entities.ids;

import es.iesjandula.damfilms_server.entities.Pelicula;
import es.iesjandula.damfilms_server.entities.Usuario;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PeliculaVisualizadaId 
{	
	@ManyToOne
	private Pelicula pelicula;
	
	@ManyToOne
	private Usuario usuario;
}
