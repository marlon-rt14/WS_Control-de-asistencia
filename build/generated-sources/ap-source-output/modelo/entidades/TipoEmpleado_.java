package modelo.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Asistencia;
import modelo.entidades.Empleado;
import modelo.entidades.Jornada;
import modelo.entidades.Tipo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-11T22:37:46")
@StaticMetamodel(TipoEmpleado.class)
public class TipoEmpleado_ { 

    public static volatile SingularAttribute<TipoEmpleado, Empleado> idEmpleado;
    public static volatile SingularAttribute<TipoEmpleado, Integer> idTipoEmpleado;
    public static volatile SingularAttribute<TipoEmpleado, Jornada> idJornada;
    public static volatile SingularAttribute<TipoEmpleado, Tipo> idTipo;
    public static volatile ListAttribute<TipoEmpleado, Asistencia> asistenciaList;

}