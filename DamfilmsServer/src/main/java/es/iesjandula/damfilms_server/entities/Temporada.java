package es.iesjandula.damfilms_server.entities;

import java.util.List;

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
@Table(name="Temporada")
public class Temporada 
{
	@EmbeddedId
	private TemporadaId temporadaId;
	
	@ManyToOne
	private Serie serie;
	
	@OneToMany(mappedBy="temporada")
	private List<Episodio> episodios;

}
