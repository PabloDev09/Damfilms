package es.iesjandula.damfilms_server.dtos;

import java.util.List;

import es.iesjandula.damfilms_server.entities.Temporada;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerieDescripcion {
	
    private String titulo;
    private String descripcion;
    
    
}
