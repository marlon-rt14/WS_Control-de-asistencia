package vista.interfaces;

import java.util.List;
import modelo.entidades.Aula;

public interface IAulas {

	//SELECT LIST AULA
	List<Aula> getListAulas();

	//SELECT BY ID
	Aula getAula(int id);

	//DELETE 
	boolean deleteAula(int id);

	//SAVE 
	void saveAula(String descripcionAula);

	//UPDATE
	boolean updateAula(int id, String descripcionAula);

}
