package es.iesjandula.damfilms_server.interfaces;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.iesjandula.damfilms_server.utils.DamfilmsServerException;

@Configuration
public interface IGestorParseo 
{
	
	@Bean
	void parseaFichero(String nombreFichero) throws DamfilmsServerException;

}
