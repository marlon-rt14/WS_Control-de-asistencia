
package modelo;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.entidades.Asistencia;
import modelo.entidades.Empleado;
import modelo.entidades.Estado;
import modelo.entidades.FechaHabil;
import modelo.entidades.TipoEmpleado;
import vista.interfaces.IAsistencia;
import modelo.dao.AsistenciaJpaController;
import modelo.dao.exceptions.NonexistentEntityException;

public class facadeAsistencia extends conexion implements IAsistencia{

	public static final AsistenciaJpaController daoAsistencia = new AsistenciaJpaController(emf);
	
	@Override
	public List<Asistencia> getListAsistencia() {
		return daoAsistencia.findAsistenciaEntities();
	}

	@Override
	public Asistencia getAsistencia(int id) {
		return daoAsistencia.findAsistencia(id);
	}

	@Override
	public boolean deleteAsistencia(int id) {
		try {
			daoAsistencia.destroy(id);
			return true;
		} catch (NonexistentEntityException ex) {
			return false;
		}
	}

	@Override
	public void saveAsistencia(TipoEmpleado tipoEmpleado, Empleado empleado, String mensajeAsistencia, Date fecha, Estado estado, String comentarios, String observaciones) {
		Asistencia nuevo = new Asistencia();
		nuevo.setIdTipoEmpleado(tipoEmpleado);
		nuevo.setIdEmpleado(empleado);
		nuevo.setMensajeAsistencia(mensajeAsistencia);
		nuevo.setFecha(fecha);
		nuevo.setIdEstado(estado);
		nuevo.setComentarios(comentarios);
		nuevo.setObservaciones(observaciones);
		daoAsistencia.create(nuevo);
	}

	@Override
	public boolean updateAsistencia(int id, TipoEmpleado tipoEmpleado, Empleado empleado, String mensajeAsistencia, Date fecha, Estado estado, String comentarios, String observaciones) {
		try {
			Asistencia editar = daoAsistencia.findAsistencia(id);
			editar.setIdTipoEmpleado(tipoEmpleado);
			editar.setIdEmpleado(empleado);
			editar.setMensajeAsistencia(mensajeAsistencia);
			editar.setFecha(fecha);
			editar.setIdEstado(estado);
			editar.setComentarios(comentarios);
			editar.setObservaciones(observaciones);
			daoAsistencia.edit(editar);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
}
