package es.iesjandula.damfilms_server.entities.ids;


import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserRoleId implements Serializable
{
	/** Serial Version UID */
	private static final long serialVersionUID = -489563629705610366L ;

	private Long idUsuario ;
	
	private Long idRole ;
}
