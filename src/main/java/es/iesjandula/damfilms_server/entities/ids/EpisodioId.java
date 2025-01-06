package es.iesjandula.damfilms_server.entities.ids;

import es.iesjandula.damfilms_server.entities.Temporada;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
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

	@ManyToOne
	private Temporada temporada;
}
