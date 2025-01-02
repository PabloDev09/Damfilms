package es.iesjandula.damfilms_server.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Deshabilitar CSRF

            .authorizeHttpRequests(authz -> authz
                // Recursos públicos
                .requestMatchers("/", "/index.html","/home.html", "/Log_in.html", "/sign_in.html", "/css/**", "/img/**","/js/**","/static/**","/eula.html").permitAll()
                // Acceso según roles
                .requestMatchers("/catalog-movies.html").hasRole("PREMIUM")
                .requestMatchers("/catalog-series.html", "/catalog-documentals.html").hasAnyRole("PREMIUM", "INVITADO")
                // Prohibir películas para usuarios invitados
                .requestMatchers("/catalog-movies.html").not().hasRole("INVITADO")
                // Requiere autenticación para cualquier otra página
                .anyRequest().authenticated()
            )

            // Configuración de login
            .formLogin(login -> login
                .loginPage("/Log_in.html")
            )

            // Configuración de logout
            .logout(logout -> logout
                .logoutSuccessUrl("/index.html")
            );

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new InMemoryUserDetailsManager(
            User.withUsername("premium").password("{noop}premium").roles("PREMIUM").build(),
            User.withUsername("invitado").password("{noop}invitado").roles("INVITADO").build(),
            User.withUsername("admin").password("{noop}admin").roles("ADMIN").build(),
            User.withUsername("user").password("{noop}user").roles("USER").build()
        );
    }

    /**
     * Configuración de páginas de error personalizadas
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/not-found.html"));
            factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/forbidden.html"));
        };
    }
}
