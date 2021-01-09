package vista.interfaces;

import java.util.List;
import modelo.entidades.FechaHabil;
import modelo.entidades.Tipo;
import java.util.Date;

public interface IFechasHabiles {
	
	//SELECT LIST FECHAS HABILES
	List<FechaHabil> getListFechasHabiles();
	
	//SELECT FECHA HABIL
	FechaHabil getFechaHabil(int id);
	
	//DELETE
	boolean deleteFechaHabil(int id);
	
	//SAVE
	void saveFechaHabil(Tipo tipo, Date fecha);
	
	//UPDATE
	boolean updateFechaHabil(int id, Tipo tipo, Date fecha);
	
}
