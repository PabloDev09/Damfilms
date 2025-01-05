package es.iesjandula.damfilms_server.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentalDetalle {
	
	private String titulo;
	
	private int duracion;
	
	private Date fechaEstreno;

}
