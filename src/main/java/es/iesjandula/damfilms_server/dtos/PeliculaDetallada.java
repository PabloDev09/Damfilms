package es.iesjandula.damfilms_server.dtos;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaDetallada 
{
	
	private String titulo;
	
	private int duracion;
	
	private Date fechaEstreno;

}
