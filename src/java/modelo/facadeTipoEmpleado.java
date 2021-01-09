
package modelo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.entidades.Empleado;
import modelo.entidades.Jornada;
import modelo.entidades.Tipo;
import modelo.entidades.TipoEmpleado;
import vista.interfaces.ITipoEmpleado;
import modelo.dao.TipoEmpleadoJpaController;
import modelo.dao.exceptions.NonexistentEntityException;

public class facadeTipoEmpleado extends conexion implements ITipoEmpleado{

	public static final TipoEmpleadoJpaController daoTipoEmpleado = new TipoEmpleadoJpaController(emf);
	
	@Override
	public List<TipoEmpleado> getListTiposEmpleados() {
		return daoTipoEmpleado.findTipoEmpleadoEntities();
	}

	@Override
	public TipoEmpleado getTipoEmpleado(int id) {
		return daoTipoEmpleado.findTipoEmpleado(id);
	}

	@Override
	public boolean deleteTipoEmpleado(int id) {
		try {
			daoTipoEmpleado.destroy(id);
			return true;
		} catch (NonexistentEntityException ex) {
			return false;
		}
	}

	@Override
	public void saveTipoEmpleado(Empleado empleado, Tipo tipo, Jornada jornada) {
		TipoEmpleado nuevo = new TipoEmpleado();
		nuevo.setIdEmpleado(empleado);
		nuevo.setIdTipo(tipo);
		nuevo.setIdJornada(jornada);
		daoTipoEmpleado.create(nuevo);
	}

	@Override
	public boolean updateTipoEmpleado(int id, Empleado empleado, Tipo tipo, Jornada jornada) {
		try {
			TipoEmpleado editar = daoTipoEmpleado.findTipoEmpleado(id);
			editar.setIdEmpleado(empleado);
			editar.setIdTipo(tipo);
			editar.setIdJornada(jornada);
			daoTipoEmpleado.edit(editar);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
}
