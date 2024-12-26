package es.iesjandula.damfilms_server.entities.ids;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SerieId implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private Date fechaEstreno;
}
