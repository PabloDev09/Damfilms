package es.iesjandula.damfilms_server.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLogin {
	
	private String email;
	
	private String contrasenia;

}
