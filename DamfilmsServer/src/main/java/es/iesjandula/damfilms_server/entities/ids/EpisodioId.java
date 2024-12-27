package es.iesjandula.damfilms_server.entities.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EpisodioId 
{	
	private Integer numero;
	
	@Column(length = 150)
	private String nombre;
}
