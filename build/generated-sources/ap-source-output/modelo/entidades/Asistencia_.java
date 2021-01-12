package modelo.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Empleado;
import modelo.entidades.Estado;
import modelo.entidades.FechaHabil;
import modelo.entidades.TipoEmpleado;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-11T22:37:46")
@StaticMetamodel(Asistencia.class)
public class Asistencia_ { 

    public static volatile SingularAttribute<Asistencia, String> mensajeAsistencia;
    public static volatile SingularAttribute<Asistencia, Estado> idEstado;
    public static volatile SingularAttribute<Asistencia, FechaHabil> idFechaHabil;
    public static volatile SingularAttribute<Asistencia, Empleado> idEmpleado;
    public static volatile SingularAttribute<Asistencia, Integer> idAsistencia;
    public static volatile SingularAttribute<Asistencia, String> observaciones;
    public static volatile SingularAttribute<Asistencia, TipoEmpleado> idTipoEmpleado;
    public static volatile SingularAttribute<Asistencia, String> comentarios;

}