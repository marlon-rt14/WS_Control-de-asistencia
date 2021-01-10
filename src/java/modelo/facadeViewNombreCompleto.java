
package modelo;

import java.util.List;
import modelo.entidades.ViewNombreCompleto;
import vista.interfaces.IViewNombreCompleto;
import modelo.dao.ViewNombreCompletoJpaController;

public class facadeViewNombreCompleto extends conexion implements IViewNombreCompleto{

	private static final ViewNombreCompletoJpaController daoNombreCompleto = new ViewNombreCompletoJpaController(emf);
	
	@Override
	public List<ViewNombreCompleto> getListNombresCompletos() {
		return daoNombreCompleto.findViewNombreCompletoEntities();
	}

	@Override
	public ViewNombreCompleto getNombreCompleto(int id) {
		return daoNombreCompleto.findViewNombreCompleto(id);
	}
	
}
