package vista.interfaces;

import java.util.List;
import modelo.entidades.Asistencia;
import modelo.entidades.TipoEmpleado;
import modelo.entidades.Empleado;
import modelo.entidades.FechaHabil;
import modelo.entidades.Estado;

public interface IAsistencia {

	//SELECT
	List<Asistencia> getListAsistencia();

	//SELECT BY ID
	Asistencia getAsistencia(int id);

	//DELETE ASISTENCIA
	boolean deleteAsistencia(int id);

	//SAVE ASISTENCIA
	void saveAsistencia(TipoEmpleado tipoEmpleado, Empleado empleado, String mensajeAsistencia,
		FechaHabil fechaHabil, Estado estado, String comentarios, String observaciones);

	//UPDATE ASISTENCIA
	boolean updateAsistencia(int id, TipoEmpleado tipoEmpleado, Empleado empleado,
		String mensajeAsistencia, FechaHabil fechaHabil, Estado estado,
		String comentarios, String observaciones);

}
