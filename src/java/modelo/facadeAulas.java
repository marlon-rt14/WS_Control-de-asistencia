
package modelo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.entidades.Aula;
import vista.interfaces.IAulas;
import modelo.dao.AulaJpaController;
import modelo.dao.exceptions.IllegalOrphanException;
import modelo.dao.exceptions.NonexistentEntityException;

public class facadeAulas extends conexion implements IAulas{

	public static final AulaJpaController daoAula = new AulaJpaController(emf);
	
	@Override
	public List<Aula> getListAulas() {
		return daoAula.findAulaEntities();
	}

	@Override
	public Aula getAula(int id) {
		return daoAula.findAula(id);
	}

	@Override
	public boolean deleteAula(int id) {
		try {
			daoAula.destroy(id);
			return true;
		} catch (IllegalOrphanException | NonexistentEntityException ex ) {
			return false;
		}
	}

	@Override
	public void saveAula(String descripcionAula) {
		Aula nuevo = new Aula();
		nuevo.setDescripcionAula(descripcionAula);
	}

	@Override
	public boolean updateAula(int id, String descripcionAula) {
		try {
			Aula editar = daoAula.findAula(id);
			editar.setDescripcionAula(descripcionAula);
			daoAula.edit(editar);
			return true;
		} catch (NonexistentEntityException ex) {
			return false;
		} catch (Exception ex) {
			return false;
		}
	}
	
}
