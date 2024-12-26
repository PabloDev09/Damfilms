package es.iesjandula.damfilms_server.entities.ids;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DocumentalId implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 788749229808621019L;
	
	@Column(length = 150)
	private String titulo;
	
	private Date fechaEstreno;
}
