package es.iesjandula.damfilms_server.entities.ids;

import java.util.Date;

import es.iesjandula.damfilms_server.entities.Usuario;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SuscripcionId
{
	@ManyToOne
	private Usuario usuario;

	private Date fechaInicio;
}
