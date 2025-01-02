package es.iesjandula.damfilms_server.entities;

import es.iesjandula.damfilms_server.entities.ids.DocumentalId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
	@EmbeddedId
	private DocumentalId documentalId;
	
	@Column(length = 200, nullable = false)
	private String descripcion;
	
	@Column
	private Integer duracion;
	
	@Column(length = 10, nullable = false)
	private String clasificacion;
	
	@ManyToOne
	@JoinColumn(name = "genero_nombre", referencedColumnName = "nombre" , nullable = false)
	private Genero genero;
}
