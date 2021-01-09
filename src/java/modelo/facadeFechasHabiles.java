package modelo;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.entidades.FechaHabil;
import modelo.entidades.Tipo;
import vista.interfaces.IFechasHabiles;
import modelo.dao.FechaHabilJpaController;
import modelo.dao.exceptions.NonexistentEntityException;

public class facadeFechasHabiles extends conexion implements IFechasHabiles{

	public static final FechaHabilJpaController daoFechaHabil = new FechaHabilJpaController(emf);
	
	@Override
	public List<FechaHabil> getListFechasHabiles() {
		return daoFechaHabil.findFechaHabilEntities();
	}

	@Override
	public FechaHabil getFechaHabil(int id) {
		return daoFechaHabil.findFechaHabil(id);
	}

	@Override
	public boolean deleteFechaHabil(int id) {
		try {
			daoFechaHabil.destroy(id);
			return true;
		} catch (NonexistentEntityException ex) {
			return false;
		}
	}

	@Override
	public void saveFechaHabil(Tipo tipo, Date fecha) {
		FechaHabil nuevo = new FechaHabil();
		nuevo.setIdTipo(tipo);
		nuevo.setFecha(fecha);
		daoFechaHabil.create(nuevo);
	}

	@Override
	public boolean updateFechaHabil(int id, Tipo tipo, Date fecha) {
		try {
			FechaHabil editar = daoFechaHabil.findFechaHabil(id);
			editar.setIdTipo(tipo);
			editar.setFecha(fecha);
			daoFechaHabil.edit(editar);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
}
