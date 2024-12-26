package es.iesjandula.damfilms_server.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	  
	  @Column(length =100)
	  private String tipo;
	  
	  @Column(length =100)
	  private String duracion;
	  
	  @Column
	  private Date fechaFin;
	  
}


