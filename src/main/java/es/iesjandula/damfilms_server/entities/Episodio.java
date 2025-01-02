package es.iesjandula.damfilms_server.entities;

import es.iesjandula.damfilms_server.entities.ids.EpisodioId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Episodio 
{
	@EmbeddedId
	private EpisodioId episodioId;
	
	@ManyToOne
	@JoinColumns
	(
		{
			@JoinColumn(name = "temporada_numero", nullable = false, referencedColumnName = "numero"),
			@JoinColumn(name = "temporada_fecha_estreno", nullable = false, referencedColumnName = "fechaEstreno")
		}
	)
	private Temporada temporada;
	
	@Column(nullable = false)
	private Integer duracion;
}
