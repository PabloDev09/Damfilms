package es.iesjandula.damfilms_server.entities;

import java.util.List;

import es.iesjandula.damfilms_server.entities.ids.TemporadaId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Temporada
{
	@EmbeddedId
	private TemporadaId temporadaId;
	
	@MapsId(value="serie")
	@ManyToOne
 
	@JoinColumn(name = "nombre_titulo", nullable = false, referencedColumnName = "titulo")

	private Serie serie;

	@OneToMany(mappedBy = "temporada")
	private List<Episodio> episodios;

}
