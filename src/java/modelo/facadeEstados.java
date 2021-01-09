package modelo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.entidades.Estado;
import vista.interfaces.IEstados;
import modelo.dao.EstadoJpaController;
import modelo.dao.exceptions.NonexistentEntityException;

public class facadeEstados extends conexion implements IEstados{

	public static final EstadoJpaController daoEstado = new EstadoJpaController(emf);
	
	@Override
	public List<Estado> getListEstados() {
		return daoEstado.findEstadoEntities();
	}

	@Override
	public Estado getEstado(int id) {
		return daoEstado.findEstado(id);
	}

	@Override
	public boolean deleteEstado(int id) {
		try {
			daoEstado.destroy(id);
			return true;
		} catch (NonexistentEntityException ex) {
			return false;
		}
	}

	@Override
	public void saveEstado(String descripcionEstado) {
		Estado nuevo = new Estado();
		nuevo.setDescripcionEstado(descripcionEstado);
		daoEstado.create(nuevo);
	}

	@Override
	public boolean updateEstado(int id, String descripcionEstado) {
		try {
			Estado editar = daoEstado.findEstado(id);
			editar.setDescripcionEstado(descripcionEstado);
			daoEstado.edit(editar);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
}
