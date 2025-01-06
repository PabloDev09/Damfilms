package es.iesjandula.damfilms_server.iml;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Pelicula;
import es.iesjandula.damfilms_server.entities.PeliculaVisualizada;
import es.iesjandula.damfilms_server.entities.Usuario;
import es.iesjandula.damfilms_server.entities.ids.PeliculaId;
import es.iesjandula.damfilms_server.entities.ids.PeliculaVisualizadaId;
import es.iesjandula.damfilms_server.interfaces.IParseoPeliculaVisualizado;
import es.iesjandula.damfilms_server.repositories.IPeliculaRepository;
import es.iesjandula.damfilms_server.repositories.IPeliculaVisualizadaRepository;
import es.iesjandula.damfilms_server.repositories.IUsuarioRepository;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import es.iesjandula.damfilms_server.utils.DatesUtil;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoPeliculaVisualizada implements IParseoPeliculaVisualizado
{
	
	@Autowired
	private IPeliculaVisualizadaRepository peliculaVisualizadaRepository;
	
	@Autowired
	private IPeliculaRepository peliculaRepository;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public void parseaFichero(Scanner scanner) throws DamfilmsServerException 
	{
		// TODO Auto-generated method stub
		
		scanner.nextLine();
		
		while(scanner.hasNextLine()) 
		{
			String lineaDelFichero = scanner.nextLine();
			
			String[] lineaDelFicheroTroceada = lineaDelFichero.split(",");
			
			PeliculaVisualizadaId peliculaVisualizadaId = new PeliculaVisualizadaId();
			
			PeliculaId peliculaId = new PeliculaId();
			
			peliculaId.setTitulo(lineaDelFicheroTroceada[0]);
			try 
			{
				peliculaId.setFechaEstreno(DatesUtil.crearFechaDesdeString(lineaDelFicheroTroceada[1]));

			}
			catch (DamfilmsServerException damfilmsServerException) 
			{

				damfilmsServerException.printStackTrace();
			}
			
			Optional<Pelicula> optionalPelicula = this.peliculaRepository.findById(peliculaId);
			
			if(!optionalPelicula.isPresent()) 
			{
				String mensajeError = "No existe la película";
				log.error(mensajeError);
				throw new DamfilmsServerException(3, mensajeError);
			}
			
			peliculaVisualizadaId.setPelicula(optionalPelicula.get());
			
			Optional<Usuario> optionalUsurio = this.usuarioRepository.findById(lineaDelFicheroTroceada[2]);
			
			if(!optionalUsurio.isPresent()) 
			{
				String mensajeError = "No existe el usuario";
				log.error(mensajeError);
				throw new DamfilmsServerException(4, mensajeError);
			}
			
			peliculaVisualizadaId.setUsuario(optionalUsurio.get());
			
			PeliculaVisualizada peliculaVisualizada = new PeliculaVisualizada();
			
			peliculaVisualizada.setPeliculaVisualizadaId(peliculaVisualizadaId);
			peliculaVisualizada.setTiempoVisto(lineaDelFicheroTroceada[3]);
			
			this.peliculaVisualizadaRepository.saveAndFlush(peliculaVisualizada);
			
			
		}
		
	}

}
