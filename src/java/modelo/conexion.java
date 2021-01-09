package modelo;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class conexion {
	
	public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("WS_AttendanceControlPU");
	
}
