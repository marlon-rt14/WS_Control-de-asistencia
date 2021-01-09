package vista.interfaces;

import java.util.List;
import modelo.entidades.Tipo;

public interface ITipos {
	
	//SELECT LIST TIPOS
	List<Tipo> getListTipos();
	
	//SELECT TIPOS
	Tipo getTipo(int id);
	
	//DELETE
	boolean deleteTipo(int id);
	
	//SAVE
	void saveTipo(String descripcionTipo);
	
	//UPDATE
	boolean updateTipo(int id, String descripcionTipo);
	
}
