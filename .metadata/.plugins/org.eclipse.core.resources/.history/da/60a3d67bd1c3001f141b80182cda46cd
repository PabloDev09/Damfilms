package es.iesjandula.damfilms_server.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Configuracion")
public class Configuracion {

	  @Id
	  @Column
	  private Long id;
	  
	  @Column(length =60)
	  private String idioma;
	  
	  @ManyToOne
	  @JoinColumn(name = "modo_nombre", referencedColumnName = "nombre")
	  private Modo modo;
	
}

