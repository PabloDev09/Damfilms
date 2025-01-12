package es.iesjandula.damfilms_server.dtos;


import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto
{
    private String username ;
    
    private String correo ;
    
    private String password ;
    
    private String rol ;
    
    private List<String> rolesAsList ;

	/**
	 * Como del formulario web viene como un String
	 * Al crearse un objeto de tipo UserRegistrationDto, usará este método set
	 * Por ello, aprovecho este set para convertirlo a una List<String>
	 * 
	 * @param rol rol separados por coma
	 */
	public void setRoles(String rol)
	{
		this.rol = rol;
		
		// Creamos la lista de roles en base a los elementos separados por coma
		this.rolesAsList = Arrays.asList(this.rol.split(",")) ;
	}
}
