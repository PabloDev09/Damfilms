package es.iesjandula.damfilms_server.dtos;

import java.sql.Date;
import java.util.List;

import es.iesjandula.damfilms_server.entities.Temporada;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor 
public class SerieDetalle {
    private String titulo;
    private Date fechaEstreno;
    private List<Temporada> temporadas;
}
