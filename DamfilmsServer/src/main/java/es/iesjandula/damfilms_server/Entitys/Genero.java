package es.iesjandula.damfilms_server.Entitys;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Genero")
public class Genero 
{

	  @Id
	  @Column(length =25)
	  private String nombre;
	  
	  @Column(length =200)
	  private String descripcion;
	  
	  @OneToMany(mappedBy="genero")
	  private List<Serie> series;
	  
	  
	  
}
