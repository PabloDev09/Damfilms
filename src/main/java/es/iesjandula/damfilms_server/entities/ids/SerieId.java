package es.iesjandula.damfilms_server.entities.ids;

import java.util.Date;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SerieId
{
	private String nombre;

	private Date fechaEstreno;
}
