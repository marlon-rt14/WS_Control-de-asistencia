package modelo;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dao.JornadaJpaController;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Jornada;
import vista.interfaces.IJornada;

public class facadeJornada extends conexion implements IJornada{

	public static final JornadaJpaController daoJornada = new JornadaJpaController(emf);
	
	@Override
	public List<Jornada> getListJornada() {
		return daoJornada.findJornadaEntities();
	}

	@Override
	public Jornada getJornada(int id) {
		return daoJornada.findJornada(id);
	}

	@Override
	public boolean deleteJornada(int id) {
		try {
			daoJornada.destroy(id);
			return true;
		} catch (NonexistentEntityException ex) {
			return false;
		}
	}

	@Override
	public void saveJornada(Date entraPrimerPeriodo, Date salePrimerPeriodo, Date entraSegundoPeriodo, Date saleSegundoPeriodo) {
		Jornada nuevo = new Jornada();
		nuevo.setEntraPrimerPeriodo(entraPrimerPeriodo);
		nuevo.setSalePrimerPeriodo(salePrimerPeriodo);
		nuevo.setEntraSegundoPeriodo(entraSegundoPeriodo);
		nuevo.setSaleSegundoPeriodo(saleSegundoPeriodo);
		daoJornada.create(nuevo);
	}

	@Override
	public boolean updateJornada(int id, Date entraPrimerPeriodo, Date salePrimerPeriodo, Date entraSegundoPeriodo, Date saleSegundoPeriodo) {
		try {
			Jornada editar = daoJornada.findJornada(id);
			editar.setEntraPrimerPeriodo(entraPrimerPeriodo);
			editar.setSalePrimerPeriodo(salePrimerPeriodo);
			editar.setEntraSegundoPeriodo(entraSegundoPeriodo);
			editar.setSaleSegundoPeriodo(saleSegundoPeriodo);
			daoJornada.edit(editar);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
}
