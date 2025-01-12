package es.iesjandula.damfilms_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService ;

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Deshabilitar CSRF

            .authorizeHttpRequests(authz -> authz
                // Recursos públicos
                .requestMatchers("/","/eula", "/inicio", "/login", "/signin", "/css/**", "/img/**","/js/**","/static/**","/modo","/usuarios","/configuracion","/suscripciones", "/suscripciones/tipos").permitAll()
                // Acceso según roles
                .requestMatchers("/home", "/peliculas", "/cuenta-usuario").hasAnyAuthority("PREMIUM", "GRATUITA")
                .requestMatchers("/series", "/documentales").hasAnyAuthority("PREMIUM")
                // Prohibir películas para usuarios invitados
                // Requiere autenticación para cualquier otra página
                .anyRequest().authenticated()
            )

            // Configuración de login
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/home") // Redirección después de login
            )

            // Configuración de logout
            .logout(logout -> logout
                .logoutSuccessUrl("/inicio")
            );

        return http.build();
    }

	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		// Usaremos 'BCryptPasswordEncoder' como forma de cifrar las contraseñas
		return new BCryptPasswordEncoder() ;
	}

	/**
	 * Este método devuelve una instancia de DaoAuthenticationProvider que indica 
	 * cómo se controlan los usuarios (UserDetailsService) y cómo se codifican las contraseñas (PasswordEnconder) 
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider() ;
		
		authProvider.setUserDetailsService(this.myUserDetailsService) ;
		authProvider.setPasswordEncoder(this.passwordEncoder()) ;
		
		return authProvider ;
	}

    /**
     * Configuración de páginas de error personalizadas
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/not-found"));
            factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/forbidden"));
        };
    }
}
