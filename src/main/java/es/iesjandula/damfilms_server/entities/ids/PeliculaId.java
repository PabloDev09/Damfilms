package es.iesjandula.damfilms_server.entities.ids;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaId
{
	@Column(length = 150)
	private String titulo;

	private Date fechaEstreno;
}
