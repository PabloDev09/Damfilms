package es.iesjandula.damfilms_server.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Episodio")
public class Episodio 
{

	@Id
	private Integer numero;
	
	@ManyToOne
	private Temporada temporada;
	
	@Column
	private Integer duracion;
}
