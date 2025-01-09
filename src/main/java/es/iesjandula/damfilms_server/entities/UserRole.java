package es.iesjandula.damfilms_server.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
@Table(name = "user_role")
public class UserRole
{
	@EmbeddedId
	private UserRoleId userRoleId ;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	@MapsId("idUsuario")
	private Usuario idUsuario ;
	
	@ManyToOne
	@JoinColumn(name = "id_role")
	@MapsId("idRole")
	private Role idRole ;
}
