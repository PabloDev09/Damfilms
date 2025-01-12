package es.iesjandula.damfilms_server.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pelicula
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 150, nullable = false)
	private String titulo;

	@Column(nullable = false)
	private Date fechaEstreno;

	@Column(length = 200, nullable = false)
	private String descripcion;

	@Column(length = 4, nullable = false)
	private Integer duracion;

	@Column(length = 3, nullable = false)
	private Integer clasificacion;

	@ManyToOne
	@JoinColumn(name = "genero_nombre", referencedColumnName = "nombre", nullable = false)
	private Genero genero;
	
	@OneToMany(mappedBy="pelicula")
	private List<PeliculaVisualizada> peliculasVisualizadas;
}
