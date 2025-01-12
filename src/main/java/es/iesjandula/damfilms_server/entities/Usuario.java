package es.iesjandula.damfilms_server.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Usuario
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

	@Column(length = 100, nullable = false, unique = true)
	private String nombre;

	@Column(length = 150, nullable = false, unique = true)
	private String correo;

	@Column(length = 75, nullable = false)
	private String contrasena;

	@ManyToOne
	@JoinColumn(name = "configuracion_id", referencedColumnName = "id")
	private Configuracion configuracion;
	
	@Column
    private Boolean active;
	
	@OneToMany(mappedBy = "usuario")
	private List<Suscripcion> suscripciones;
	
	@OneToMany(mappedBy = "usuario")
	private List<PeliculaVisualizada> peliculasVisualizadas;
	
	@OneToMany(mappedBy = "usuario")
	private List<DocumentalVisualizado> documentalesVisualizados;
	
	@OneToMany(mappedBy = "usuario")
	private List<SerieVisualizada> seriesVisualizadas;

}