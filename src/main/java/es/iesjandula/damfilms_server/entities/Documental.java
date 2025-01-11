package es.iesjandula.damfilms_server.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Documental
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 150)
	private String titulo;

	@Column
	private Date fechaEstreno;

	@Column(length = 200, nullable = false)
	private String descripcion;

	@Column(length = 4, nullable = false)
	private int duracion;

	@Column(length = 3, nullable = false)
	private int clasificacion;

	@ManyToOne
	@JoinColumn(name = "genero_nombre", referencedColumnName = "nombre", nullable = false)
	private Genero genero;
}
