package vista.interfaces;

import java.util.List;
import modelo.entidades.ViewNombreCompleto;

public interface IViewNombreCompleto {
	
	List<ViewNombreCompleto> getListNombresCompletos();
	
	ViewNombreCompleto getNombreCompleto(int id);
}
