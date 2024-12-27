package es.iesjandula.damfilms_server.entities;

import java.sql.Date;

import es.iesjandula.damfilms_server.entities.ids.SuscripcionId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="Suscripcion")
public class Suscripcion {
	
	  @EmbeddedId
	  private SuscripcionId suscripcionId;
	  
	  @Column(length = 4)
	  private int duracion;
	  
	  @Column(nullable = true)
	  private Date fechaFin;
	  
	  @MapsId(value = "usuario")
	  @ManyToOne
	  private Usuario usuario;
}


