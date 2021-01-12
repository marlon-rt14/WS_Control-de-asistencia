package modelo.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Asistencia;
import modelo.entidades.Horario;
import modelo.entidades.TipoEmpleado;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-11T22:37:46")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, String> apellidos;
    public static volatile SingularAttribute<Empleado, String> clave;
    public static volatile ListAttribute<Empleado, TipoEmpleado> tipoEmpleadoList;
    public static volatile SingularAttribute<Empleado, Integer> idEmpleado;
    public static volatile SingularAttribute<Empleado, String> cedula;
    public static volatile SingularAttribute<Empleado, String> usuario;
    public static volatile SingularAttribute<Empleado, String> funcion;
    public static volatile SingularAttribute<Empleado, String> nombres;
    public static volatile ListAttribute<Empleado, Horario> horarioList;
    public static volatile ListAttribute<Empleado, Asistencia> asistenciaList;

}