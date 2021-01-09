package modelo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.entidades.Materia;
import vista.interfaces.IMateria;
import modelo.dao.MateriaJpaController;
import modelo.dao.exceptions.IllegalOrphanException;
import modelo.dao.exceptions.NonexistentEntityException;

public class facadeMateria extends conexion implements IMateria{

	public static final MateriaJpaController daoMateria = new MateriaJpaController(emf);
	
	@Override
	public List<Materia> getListMaterias() {
		return daoMateria.findMateriaEntities();
	}

	@Override
	public Materia getMateria(int id) {
		return daoMateria.findMateria(id);
	}

	@Override
	public boolean deleteMateria(int id) {
		try {
			daoMateria.destroy(id);
			return true;
		} catch (IllegalOrphanException | NonexistentEntityException ex) {
			return false;
		}
	}

	@Override
	public void saveMateria(String descripcionMateria) {
		Materia nuevo = new Materia();
		nuevo.setDescripcionMateria(descripcionMateria);
		daoMateria.create(nuevo);
	}

	@Override
	public boolean updateMateria(int id, String descripcionMateria) {
		try {
			Materia editar = daoMateria.findMateria(id);
			editar.setDescripcionMateria(descripcionMateria);
			daoMateria.edit(editar);
			return true;
		} catch (NonexistentEntityException ex) {
			return false;
		} catch (Exception ex) {
			return false;
		}
	}
	
}
