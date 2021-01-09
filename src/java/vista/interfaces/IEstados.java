package vista.interfaces;

import java.util.List;
import modelo.entidades.Estado;

public interface IEstados {
	
	//SELECT LIST ESTADOS
	List<Estado> getListEstados();
	
	//SELECT ESTADO
	Estado getEstado(int id);
	
	//DELETE ESTADO
	boolean deleteEstado(int id);
	
	//SAVE ESTADO
	void saveEstado(String descripcionEstado);
	
	//UPDATE ESSTADO
	boolean updateEstado(int id, String descripcionEstado);
	
}
