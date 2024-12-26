package es.iesjandula.damfilms_server.entities.ids;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaId implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -40917217227220215L;
	
	@Column(length = 150)
	private String titulo;
	
	private Date fechaEstreno;

}
