package vista.interfaces;

import java.util.List;
import modelo.entidades.Empleado;
import modelo.entidades.Tipo;
import modelo.entidades.Jornada;
import modelo.entidades.TipoEmpleado;

public interface ITipoEmpleado {
	
	//SELECT LIST TIPOS EMPLEADOS
	List<TipoEmpleado> getListTiposEmpleados();
	
	//SELECT TIPO EMPLEADO
	TipoEmpleado getTipoEmpleado(int id);
	
	//DELETE
	boolean deleteTipoEmpleado(int id);
	
	//SAVE
	void saveTipoEmpleado(Empleado empleado, Tipo tipo, Jornada jornada);
	
	//UPDATE
	boolean updateTipoEmpleado(int id, Empleado empleado, Tipo tipo, Jornada jornada);
	
}
