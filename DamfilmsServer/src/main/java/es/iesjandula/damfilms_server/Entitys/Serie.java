package es.iesjandula.damfilms_server.Entitys;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
	
	@Column(length =200)
	private String descripcion;
	
	
	@Column(length =200)
	private String clasificacion;
	
	
	@ManyToOne
	private Genero genero;
	
	@OneToMany(mappedBy="serie")
	private List<Temporada> temporadas;
	
}
