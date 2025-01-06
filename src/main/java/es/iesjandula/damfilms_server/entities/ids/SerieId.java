package es.iesjandula.damfilms_server.entities.ids;


import java.util.Date;

import jakarta.persistence.Column;
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
	@Column(length = 150)
	private String nombre;
	
	private Date fechaEstreno;
}
