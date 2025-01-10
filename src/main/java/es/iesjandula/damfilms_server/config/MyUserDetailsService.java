package es.iesjandula.damfilms_server.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.dtos.UserRegistrationDto;
import es.iesjandula.damfilms_server.entities.Role;
import es.iesjandula.damfilms_server.entities.UserRole;
import es.iesjandula.damfilms_server.entities.UserRoleId;
import es.iesjandula.damfilms_server.entities.Usuario;
import es.iesjandula.damfilms_server.repositories.IRoleRepository;
import es.iesjandula.damfilms_server.repositories.IUserRoleRepository;
import es.iesjandula.damfilms_server.repositories.IUsuarioRepository;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import jakarta.transaction.Transactional;

/**
 * interfaz de spring security que se implementa para que podamos
 * consultar qué usuarios tienen autoridad o no para acceder a recursos y ver si existen o no
 *
 */
@Service
public class MyUserDetailsService implements UserDetailsService
{
	@Autowired
	private IUsuarioRepository iUsuarioRepository ;
	
	@Autowired
	private IRoleRepository iRoleRepository ;
	
	@Autowired
	private IUserRoleRepository iUserRoleRepository ;

	/**
	 * Este método sirve para comprobar si existe el usuario
	 * 
	 * @param username nombre de usuario
	 * @return true si el usuario existe
	 */
    public boolean existsByUsername(String username)
    {
        return this.iUsuarioRepository.findByCorreo(username).isPresent();
    }

    /**
     * Este método sirve para almacenar un nuevo usuario
     * @param userRegistrationDto información del usuario recogida en el formulario
     * @param encodedPassword contraseña cifrada
     * @throws MyException excepcion
     */
    public void saveUser(UserRegistrationDto userRegistrationDto, String encodedPassword) throws DamfilmsServerException
    {
    	if(this.existsByUsername(userRegistrationDto.getUsername())) 
    	{
    		throw new DamfilmsServerException(101, "El usuario ya existe") ;
    	}
    	
    	// Primero creamos el usuario ...
        Usuario user = new Usuario() ;
        user.setNombre(userRegistrationDto.getUsername()) ;
        user.setCorreo(userRegistrationDto.getCorreo());
        user.setContrasena(encodedPassword) ;
        user.setActive(true) ;
        
        List<Role> rolesList = new ArrayList<Role>() ;
        
        // Lo siguiente es asociar todos los roles al usuario
//        for (String roleString : userRegistrationDto.getRolesAsList())
//        {
//        	// Obtenemos la referencia al Role
//        	Role role = this.iRoleRepository.findByRole(roleString) ;
//        	
//    		// Si el role no existe, lanzaremos una excepción
//    		if (role == null)
//    		{
//    			throw new DamfilmsServerException("Role no encontrado") ;
//    		}
//    		
//    		rolesList.add(role) ;
//        }

		// ... y lo añadimos a BBDD
        this.iUsuarioRepository.saveAndFlush(user) ;
        
        for (Role role : rolesList)
        {
        	// Creamos una nueva instancia de UserRole y UserRoleId
        	UserRoleId userRoleId = new UserRoleId(user.getId(), role.getId()) ;
        	UserRole userRole 	  = new UserRole() ;
        	
        	// Setteamos todos los metodos
        	userRole.setUserRoleId(userRoleId) ;
        	userRole.setIdUsuario(user) ;
        	userRole.setIdRole(role) ;
        	
        	// Guardamos en BBDD
        	this.iUserRoleRepository.saveAndFlush(userRole) ;
        }
    }
    
    public void updateUser(UserRegistrationDto userRegistrationDto) throws DamfilmsServerException
    {    	
    	if(!this.existsByUsername(userRegistrationDto.getUsername())) 
    	{
    		throw new DamfilmsServerException(102, "El usuario no existe") ;
    	}
    	
    	Usuario foundUser = this.iUsuarioRepository.findByNombre(userRegistrationDto.getUsername());
        
    	List<UserRole> userRoles = this.iUserRoleRepository.findByidUsuario(foundUser);
    	for (UserRole role : userRoles)
    	{
    		this.iUserRoleRepository.delete(role);
    	}
    	
        List<Role> rolesList = new ArrayList<Role>() ;
        
        // Lo siguiente es asociar todos los roles al usuario
        for (String roleString : userRegistrationDto.getRolesAsList())
        {
        	// Obtenemos la referencia al Role
        	Role role = this.iRoleRepository.findByRole(roleString) ;
        	
    		// Si el role no existe, lanzaremos una excepción
    		if (role == null)
    		{
    			throw new DamfilmsServerException(103, "Role no encontrado") ;
    		}
    		
    		rolesList.add(role) ;
        }

        this.iUsuarioRepository.saveAndFlush(foundUser) ;
        
        for (Role role : rolesList)
        {
        	// Creamos una nueva instancia de UserRole y UserRoleId
        	UserRoleId userRoleId = new UserRoleId(foundUser.getId(), role.getId()) ;
        	UserRole userRole 	  = new UserRole() ;
        	
        	// Setteamos todos los metodos
        	userRole.setUserRoleId(userRoleId) ;
        	userRole.setIdUsuario(foundUser) ;
        	userRole.setIdRole(role) ;
        	
        	// Guardamos en BBDD
        	this.iUserRoleRepository.saveAndFlush(userRole) ;
        }
    }
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException
	{
		// Busca el usuario por su "username" que viene por parámetro
		Optional<Usuario> userOptional = this.iUsuarioRepository.findByCorreo(correo) ;

		// Si el usuario no existe, lanzaremos una excepción
		if (userOptional.isEmpty())
		{
			throw new UsernameNotFoundException("Usuario no encontrado") ;
		}
		
		Usuario usuario = userOptional.get();
		
		// Buscamos los roles asociados al usuario
		List<UserRole> userRoles = this.iUserRoleRepository.findByidUsuario(usuario) ;

		// Llamamos a este método para que en base a los roles (entendidos por nuestra aplicación),
		// los convierta en GrantedAuthority, que es lo que entiende Spring Security en cuanto a roles
		List<GrantedAuthority> authorities = this.getUserAuthority(userRoles) ;

		// Con el usuario y sus lista de authorities, obtenemos los detalles del usuario
		// Es una especie de usuario, pero entendida por Spring Security
		return this.getUserDetails(usuario, authorities) ;
	}

	/**
	 * Convierte nuestra lista de roles en una lista de GrantedAutority 
	 * que es el mismo concepto pero entendido por Spring Security 
	 * 
	 * @param  userRoles lista de roles del usuario
	 * @return lista de GrantedAuthority
	 */
	private List<GrantedAuthority> getUserAuthority(List<UserRole> userRoles)
	{
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>() ;

		for (UserRole userRole : userRoles)
		{
			roles.add(new SimpleGrantedAuthority(userRole.getIdRole().getRole())) ;
		}
		
		return roles ;
	}

	/**
	 * Con el usuario y sus lista de authorities, obtenemos los detalles del usuario
	 * Es una especie de usuario, pero entendida por Spring Security
	 * 
	 * @param user usuario de nuestro modelo de datos
	 * @param authorities lista de GrantedAuthority
	 * @return una instancia de UserDetails
	 */
	private UserDetails getUserDetails(Usuario user, List<GrantedAuthority> authorities)
	{
		boolean accountNonExpired 	  = true ;
		boolean credentialsNonExpired = true ;
		boolean accountNonLocked	  = true ;
		
		return new org.springframework.security.core.userdetails.User(user.getCorreo(),
																	  user.getContrasena(),
																	  user.getActive(),
																	  accountNonExpired, 
																	  credentialsNonExpired,
																	  accountNonLocked, 
																	  authorities) ;
	}
}
