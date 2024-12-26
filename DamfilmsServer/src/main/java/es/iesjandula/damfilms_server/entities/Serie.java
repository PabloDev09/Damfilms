package es.iesjandula.damfilms_server.entities;

import java.util.List;

import es.iesjandula.damfilms_server.entities.ids.SerieId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Serie")
public class Serie 
{
	@EmbeddedId
	private SerieId serieId;
	
	@Column(length =200, nullable = false)
	private String descripcion;
	
	
	@Column(length =200, nullable = false)
	private String clasificacion;
	
	@ManyToOne
	@JoinColumn(name = "nombre_genero", nullable = false)
	private Genero nombreGenero;
	
	@OneToMany(mappedBy="serie")
	private List<Temporada> temporadas;
	
}
