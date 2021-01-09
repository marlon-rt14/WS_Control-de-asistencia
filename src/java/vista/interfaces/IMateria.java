package vista.interfaces;

import java.util.List;
import modelo.entidades.Materia;

public interface IMateria {
	
	//SELECT LILST MATERIAS
	List<Materia> getListMaterias();
	
	//SELECT MATERIA
	Materia getMateria(int id);
	
	//DELETE
	boolean deleteMateria(int id);
	
	//SAVE
	void saveMateria(String descripcionMateria);
	
	//UPDATE
	boolean updateMateria(int id, String descripcionMateria);
	
}
