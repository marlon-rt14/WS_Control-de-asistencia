package vista.interfaces;

import java.util.List;
import java.util.Date;
import modelo.entidades.Jornada;

public interface IJornada {

	//SELECT LIST JORNADA
	List<Jornada> getListJornada();

	//SELECT JORNADA
	Jornada getJornada(int id);

	//DELETE
	boolean deleteJornada(int id);

	//SAVE 
	void saveJornada(Date entraPrimerPeriodo, Date salePrimerPeriodo,
		Date entraSegundoPeriodo, Date saleSegundoPeriodo);

	//UPDATE 
	boolean updateJornada(int id, Date entraPrimerPeriodo, Date salePrimerPeriodo,
		Date entraSegundoPeriodo, Date saleSegundoPeriodo);

}
