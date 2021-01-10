
package modelo;

import java.util.List;
import modelo.dao.VistaDocenteJpaController;
import modelo.entidades.VistaDocente;
import vista.interfaces.IViewDocente;

public class facadeViewDocente extends conexion implements IViewDocente{
	
	public static final VistaDocenteJpaController daoViewDocente = new VistaDocenteJpaController(emf);

	@Override
	public List<VistaDocente> getListVistaDocente() {
		return daoViewDocente.findVistaDocenteEntities();
	}

	@Override
	public VistaDocente getVistaDocente(int id) {
		return daoViewDocente.findVistaDocente(id);
	}
	
}
