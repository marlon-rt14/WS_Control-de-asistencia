
package vista.interfaces;

import java.util.List;
import modelo.entidades.VistaJornada;

public interface IViewJornada {
	
	List<VistaJornada> getListVistaJornada();
	
	VistaJornada getVistaJornada(int id);
	
}
