package modelo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.entidades.Tipo;
import vista.interfaces.ITipos;
import modelo.dao.TipoJpaController;
import modelo.dao.exceptions.IllegalOrphanException;
import modelo.dao.exceptions.NonexistentEntityException;

public class facadeTipos extends conexion implements ITipos{

	public static final TipoJpaController daoTipo = new TipoJpaController(emf);
	
	@Override
	public List<Tipo> getListTipos() {
		return daoTipo.findTipoEntities();
	}

	@Override
	public Tipo getTipo(int id) {
		return daoTipo.findTipo(id);
	}

	@Override
	public boolean deleteTipo(int id) {
		try {
			daoTipo.destroy(id);
			return true;
		} catch (IllegalOrphanException | NonexistentEntityException ex) {
			return false;
		}
	}

	@Override
	public void saveTipo(String descripcionTipo) {
		Tipo nuevo = new Tipo();
		nuevo.setDescripcionTipo(descripcionTipo);
		daoTipo.create(nuevo);
	}

	@Override
	public boolean updateTipo(int id, String descripcionTipo) {
		try {
			Tipo editar = daoTipo.findTipo(id);
			editar.setDescripcionTipo(descripcionTipo);
			daoTipo.edit(editar);
			return true;
		} catch (NonexistentEntityException ex) {
			return false;
		} catch (Exception ex) {
			return false;
		}
	}
	
}
