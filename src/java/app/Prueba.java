package app;

import java.util.List;
import java.util.Scanner;
import modelo.entidades.VistaDocente;
import modelo.facadeViewDocente;


public class Prueba {

	public static void main(String[] args) {
		facadeViewDocente fac = new facadeViewDocente();
		List<VistaDocente> listaDocentes = fac.getListVistaDocente();
		listaDocentes.forEach(x -> System.out.println(x.getDescripcionAula()));
	}

}
