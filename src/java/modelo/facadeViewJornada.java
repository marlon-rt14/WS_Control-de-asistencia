
package modelo;

import java.util.List;
import modelo.dao.VistaJornadaJpaController;
import modelo.entidades.VistaJornada;
import vista.interfaces.IViewJornada;

public class facadeViewJornada extends conexion implements IViewJornada{

	private static final VistaJornadaJpaController daoViewJornada = new VistaJornadaJpaController(emf);
	
	@Override
	public List<VistaJornada> getListVistaJornada() {
		return daoViewJornada.findVistaJornadaEntities();
	}

	@Override
	public VistaJornada getVistaJornada(int id) {
		return daoViewJornada.findVistaJornada(id);
	}
	
}
