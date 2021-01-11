package vista.interfaces;

import java.util.List;
import modelo.entidades.VistaNombreCompleto;

public interface IViewNombreCompleto {
	
	List<VistaNombreCompleto> getListNombresCompletos();
	
	VistaNombreCompleto getNombreCompleto(int id);
}
