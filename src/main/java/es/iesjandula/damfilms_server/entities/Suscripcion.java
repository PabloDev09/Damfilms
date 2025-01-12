package es.iesjandula.damfilms_server.entities;

import java.util.Date;

import es.iesjandula.damfilms_server.entities.ids.SuscripcionId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table
public class Suscripcion
{

	@EmbeddedId
	private SuscripcionId suscripcionId;

	@Column(length = 10)
	private String tipo;
	
	@Column(length = 5)
	private int duracion;
	
	@Column(nullable = true)
	private Date fechaFin;

	@MapsId(value = "usuario")
	@ManyToOne
	private Usuario usuario;
}
