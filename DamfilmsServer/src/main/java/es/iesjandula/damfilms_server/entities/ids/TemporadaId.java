package es.iesjandula.damfilms_server.entities.ids;

import java.sql.Date;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TemporadaId

{

	private int numero;
	
	private Date fechaEstreno;
}
