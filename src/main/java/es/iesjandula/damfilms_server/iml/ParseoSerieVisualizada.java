package es.iesjandula.damfilms_server.iml;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.damfilms_server.entities.Serie;
import es.iesjandula.damfilms_server.entities.SerieVisualizada;
import es.iesjandula.damfilms_server.entities.Usuario;
import es.iesjandula.damfilms_server.entities.ids.SerieId;
import es.iesjandula.damfilms_server.entities.ids.SerieVisualizadaId;
import es.iesjandula.damfilms_server.interfaces.IParseoSerieVisualizado;
import es.iesjandula.damfilms_server.repositories.ISerieRepository;
import es.iesjandula.damfilms_server.repositories.ISerieVisualizadaRepository;
import es.iesjandula.damfilms_server.repositories.IUsuarioRepository;
import es.iesjandula.damfilms_server.utils.DamfilmsServerException;
import es.iesjandula.damfilms_server.utils.DatesUtil;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ParseoSerieVisualizada implements IParseoSerieVisualizado
{
	
	@Autowired
	private ISerieVisualizadaRepository serieVisualizadaRepository;
	
	@Autowired
	private ISerieRepository serieRepository;
	
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
			
			SerieVisualizadaId serieVisualizadaId = new SerieVisualizadaId();
			
			SerieId serieId = new SerieId();
			
			serieId.setNombre(lineaDelFicheroTroceada[0]);
			try 
			{
				serieId.setFechaEstreno(DatesUtil.crearFechaDesdeString(lineaDelFicheroTroceada[1]));

			}
			catch (DamfilmsServerException damfilmsServerException) 
			{
				damfilmsServerException.printStackTrace();
			
			}
			
			Optional<Serie> optionalSerie = this.serieRepository.findById(serieId);
			
			if(!optionalSerie.isPresent()) 
			{
				String mensajeError = "No existe la serie";
				log.error(mensajeError);
				throw new DamfilmsServerException(7, mensajeError);
			}
			
			serieVisualizadaId.setSerie(optionalSerie.get());
			
			Optional<Usuario> optionalUsurio = this.usuarioRepository.findById(lineaDelFicheroTroceada[2]);
			
			if(!optionalUsurio.isPresent()) 
			{
				String mensajeError = "No existe el usuario";
				log.error(mensajeError);
				throw new DamfilmsServerException(8, mensajeError);
			}
			
			serieVisualizadaId.setUsuario(optionalUsurio.get());
			
			SerieVisualizada serieVisualizada = new SerieVisualizada();
			
			serieVisualizada.setSerieVisualizadaId(serieVisualizadaId);
			serieVisualizada.setTiempoVisto(lineaDelFicheroTroceada[3]);
			
			this.serieVisualizadaRepository.saveAndFlush(serieVisualizada);
			
			
		}
		
	}

}
