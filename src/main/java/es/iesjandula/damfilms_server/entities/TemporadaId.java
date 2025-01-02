package es.iesjandula.damfilms_server.entities;

import java.io.Serializable;

import java.sql.Date;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TemporadaId implements Serializable

{

	private static final long serialVersionUID = -7619204901327516425L;

	private Integer numero;
	
	private Date fechaEstreno;
}
