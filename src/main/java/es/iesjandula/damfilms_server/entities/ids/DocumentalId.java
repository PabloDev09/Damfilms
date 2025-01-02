package es.iesjandula.damfilms_server.entities.ids;

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
public class DocumentalId 
{
	@Column(length = 150)
	private String titulo;
	
	private Date fechaEstreno;
}
