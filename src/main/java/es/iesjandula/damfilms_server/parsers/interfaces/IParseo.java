package es.iesjandula.damfilms_server.parsers.interfaces;

import java.util.Scanner;

import es.iesjandula.damfilms_server.utils.DamfilmsServerException;

public interface IParseo<T>
{
	void parseaFichero(Scanner scanner) throws DamfilmsServerException;
}
