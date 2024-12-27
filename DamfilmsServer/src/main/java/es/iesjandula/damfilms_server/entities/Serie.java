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
@Table
public class Serie 
{
	@EmbeddedId
	private SerieId serieId;
	
	@Column(nullable = false)
	private String descripcion;
	
	@Column(length = 5, nullable = false)
	private String clasificacion;
	
	@ManyToOne
	@JoinColumn(name = "genero_nombre", referencedColumnName = "nombre" , nullable = false)
	private Genero genero;
	
	@OneToMany(mappedBy = "serie")
	private List<Temporada> temporadas;
	
}
