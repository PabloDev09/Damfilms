package es.iesjandula.damfilms_server.dtos;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerieDetalle {
    private String titulo;
    private Date fechaEstreno;

    

    
}
