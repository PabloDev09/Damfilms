package es.iesjandula.damfilms_server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table (name = "suscripcion_gratuita")
public class SuscripcionGratuita extends Suscripcion{

}
