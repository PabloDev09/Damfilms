package es.iesjandula.damfilms_server.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Configuracion
{
	@Id
	@Column
	private Long id;

	@Column(length = 60)
	private String idioma;

	@ManyToOne
	@JoinColumn(name = "modo_nombre", referencedColumnName = "nombre")
	private Modo modo;
	
	@OneToMany(mappedBy = "configuracion")
	private List<Usuario> usuarios;
}
