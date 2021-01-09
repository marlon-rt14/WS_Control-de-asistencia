package vista.interfaces;

import java.util.List;
import modelo.entidades.Empleado;

public interface IEmpleados {

	//SELECT LIST EMPLEADOS
	List<Empleado> getListEmpleados();

	//SELECT BY ID
	Empleado getEmpleado(int id);

	//DELETE
	boolean deleteEmpleado(int id);

	//SAVE EMPLEADO
	void saveEmpleado(String cedula, String nombres, String Apellidos,
		String usuario, String funcion, String clave);

	//UPDATE EMPLEADO
	boolean updateEmpleado(int id, String cedula, String nombres, String Apellidos,
		String usuario, String funcion, String clave);

}
