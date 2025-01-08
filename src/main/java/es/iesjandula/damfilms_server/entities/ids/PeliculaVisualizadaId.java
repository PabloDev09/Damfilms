package es.iesjandula.damfilms_server.entities.ids;

import es.iesjandula.damfilms_server.entities.Pelicula;
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
public class PeliculaVisualizadaId 
{	
	@ManyToOne
	@JoinColumns
	(
		{
			@JoinColumn(name = "pelicula_titulo", referencedColumnName = "titulo"),
			@JoinColumn(name = "pelicula_fecha_estreno", referencedColumnName = "fechaEstreno")
		}
	)
	private Pelicula pelicula;
	
	private String usuario;
}
