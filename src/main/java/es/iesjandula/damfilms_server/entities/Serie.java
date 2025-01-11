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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 150)
	private String titulo;

	@Column(nullable = false)
    private Date fechaEstreno;
    
	@Column(nullable = false)
	private String descripcion;

	@Column(length = 3, nullable = false)
	private int clasificacion;
	
	@Column(length = 4, nullable = false)
	private int duracion;

	@ManyToOne
	@JoinColumn(name = "genero_nombre", referencedColumnName = "nombre", nullable = false)
	private Genero genero;

	@OneToMany(mappedBy = "serie")
	private List<Temporada> temporadas;

}
