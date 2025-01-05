package es.iesjandula.damfilms_server.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaDescripcion 
{
	
	private String titulo;
	
	private String genero;
	
	private String descripcion;

}
