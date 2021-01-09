package modelo;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.entidades.Aula;
import modelo.entidades.Empleado;
import modelo.entidades.Horario;
import modelo.entidades.Materia;
import vista.interfaces.IHorario;
import modelo.dao.HorarioJpaController;
import modelo.dao.exceptions.NonexistentEntityException;

public class facadeHorario extends conexion implements IHorario{

	public static final HorarioJpaController daoHorario = new HorarioJpaController(emf);
	
	@Override
	public List<Horario> getListHorario() {
		return daoHorario.findHorarioEntities();
	}

	@Override
	public Horario getHorario(int id) {
		return daoHorario.findHorario(id);
	}

	@Override
	public boolean deleteHorario(int id) {
		try {
			daoHorario.destroy(id);
			return true;
		} catch (NonexistentEntityException ex) {
			return false;
		}
	}

	@Override
	public void saveHorario(Empleado empleado, Aula aula, Materia materia, Date horaInicio, Date horaFin) {
		Horario nuevo = new Horario();
		nuevo.setIdEmpleado(empleado);
		nuevo.setIdAula(aula);
		nuevo.setIdMateria(materia);
		nuevo.setHoraInicio(horaInicio);
		nuevo.setHoraFin(horaFin);
		daoHorario.create(nuevo);
	}

	@Override
	public boolean updateHorario(int id, Empleado empleado, Aula aula, Materia materia, Date horaInicio, Date horaFin) {
		try {
			Horario editar = daoHorario.findHorario(id);
			editar.setIdEmpleado(empleado);
			editar.setIdAula(aula);
			editar.setIdMateria(materia);
			editar.setHoraInicio(horaInicio);
			editar.setHoraFin(horaFin);
			daoHorario.edit(editar);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
}
