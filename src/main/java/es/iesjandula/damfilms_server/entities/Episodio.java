package es.iesjandula.damfilms_server.entities;

import es.iesjandula.damfilms_server.entities.ids.EpisodioId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
	
	@MapsId(value = "temporada")
	@ManyToOne
	private Temporada temporada;

	@Column(length = 150, nullable = false)
	private String nombre;
	
	@Column(length = 3, nullable = false)
	private int duracion;
}
