package es.iesjandula.damfilms_server.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name="Usuario")
public class Usuario {

	  @Id
	  @Column(length =100)
	  private String nombre;
	  
	  @Column(length =100)
	  private String correo;
	  
	  @Column(length =100)
	  private String contraseña;
	  
	  
	  @Column(length =150)
	  private String apellidos;
	  
	  @OneToOne
	  @JoinColumn(name = "configuracion_id", referencedColumnName = "id")
	  private Configuracion configuracion;
	  
	  
	  
	  
}