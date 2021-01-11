
package modelo;

import java.util.List;
import modelo.entidades.VistaNombreCompleto;
import vista.interfaces.IViewNombreCompleto;
import modelo.dao.VistaNombreCompletoJpaController;

public class facadeViewNombreCompleto extends conexion implements IViewNombreCompleto{

	private static final VistaNombreCompletoJpaController daoNombreCompleto = new VistaNombreCompletoJpaController(emf);
	
	@Override
	public List<VistaNombreCompleto> getListNombresCompletos() {
		return daoNombreCompleto.findVistaNombreCompletoEntities();
	}

	@Override
	public VistaNombreCompleto getNombreCompleto(int id) {
		return daoNombreCompleto.findVistaNombreCompleto(id);
	}
	
}
