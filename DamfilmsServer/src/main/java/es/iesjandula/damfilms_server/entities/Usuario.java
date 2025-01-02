package es.iesjandula.damfilms_server.entities;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Usuario 
{

	  @Id
	  @Column(length = 100)
	  private String nombre;
	  
	  @Column(length = 150, nullable = false)
	  private String correo;
	  
	  @Column(length = 75, nullable = false)
	  private String contrasena;
	  
	  @OneToOne
	  @JoinColumn(name = "configuracion_id", referencedColumnName = "id", nullable = false)
	  private Configuracion configuracion;
	  
	  @OneToMany(mappedBy = "usuario")
	  private List<Suscripcion> suscripciones;
	  
	  
	  
	  
}