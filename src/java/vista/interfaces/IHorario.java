package vista.interfaces;

import java.util.List;
import java.util.Date;
import modelo.entidades.Empleado;
import modelo.entidades.Aula;
import modelo.entidades.Horario;
import modelo.entidades.Materia;

public interface IHorario {

	//SELECT LIST HORARIO
	List<Horario> getListHorario();

	//SELECT HORARIO
	Horario getHorario(int id);

	//DELETE 
	boolean deleteHorario(int id);

	//SAVE 
	void saveHorario(Empleado empleado, Aula aula, Materia materia, Date horaInicio, Date horaFin);

	//UPDATE
	boolean updateHorario(int id, Empleado empleado, Aula aula, Materia materia, Date horaInicio, Date horaFin);

}
