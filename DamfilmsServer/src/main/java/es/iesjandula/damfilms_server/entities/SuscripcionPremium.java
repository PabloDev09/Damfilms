package es.iesjandula.damfilms_server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table (name = "suscripcion_premium")
public class SuscripcionPremium extends Suscripcion
{

}
