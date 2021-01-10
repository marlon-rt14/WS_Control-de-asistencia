package vista.interfaces;

import java.util.List;
import modelo.entidades.VistaDocente;

public interface IViewDocente {
	
	List<VistaDocente> getListVistaDocente();
	
	VistaDocente getVistaDocente(int id);
	
}
