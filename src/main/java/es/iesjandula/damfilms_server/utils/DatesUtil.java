package es.iesjandula.damfilms_server.utils;

import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatesUtil 
{
	
	public static Date crearFechaDesdeString(String fechaString) throws DamfilmsServerException
	{
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try 
		{
			return formatter.parse(fechaString);
		}
		catch (ParseException parseException) 
		{
			throw new DamfilmsServerException(8, "Fecha con formato incorrecta: " + fechaString, parseException);
		}
	}

}
