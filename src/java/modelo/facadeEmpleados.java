package modelo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.entidades.Empleado;
import vista.interfaces.IEmpleados;
import modelo.dao.EmpleadoJpaController;
import modelo.dao.exceptions.NonexistentEntityException;

public class facadeEmpleados extends conexion implements IEmpleados{

	public static final EmpleadoJpaController daoEmpleado = new EmpleadoJpaController(emf);
	
	@Override
	public List<Empleado> getListEmpleados() {
		return daoEmpleado.findEmpleadoEntities();
	}

	@Override
	public Empleado getEmpleado(int id) {
		return daoEmpleado.findEmpleado(id);
	}

	@Override
	public boolean deleteEmpleado(int id) {
		try {
			daoEmpleado.destroy(id);
			return true;
		} catch (NonexistentEntityException ex) {
			return false;
		}
	}

	@Override
	public void saveEmpleado(String cedula, String nombres, String Apellidos, String usuario, String funcion, String clave) {
		Empleado nuevo = new Empleado();
		nuevo.setCedula(cedula);
		nuevo.setNombres(nombres);
		nuevo.setApellidos(Apellidos);
		nuevo.setUsuario(usuario);
		nuevo.setFuncion(funcion);
		nuevo.setClave(clave);
		daoEmpleado.create(nuevo);
	}

	@Override
	public boolean updateEmpleado(int id, String cedula, String nombres, String Apellidos, String usuario, String funcion, String clave) {
		try {
			Empleado editar = daoEmpleado.findEmpleado(id);
			editar.setCedula(cedula);
			editar.setNombres(nombres);
			editar.setApellidos(Apellidos);
			editar.setUsuario(usuario);
			editar.setFuncion(funcion);
			editar.setClave(clave);
			daoEmpleado.edit(editar);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
}
