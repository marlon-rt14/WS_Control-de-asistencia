package modelo.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Asistencia;
import modelo.entidades.Tipo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-11T12:56:40")
@StaticMetamodel(FechaHabil.class)
public class FechaHabil_ { 

    public static volatile SingularAttribute<FechaHabil, Date> fecha;
    public static volatile SingularAttribute<FechaHabil, Integer> idFechaHabil;
    public static volatile SingularAttribute<FechaHabil, Tipo> idTipo;
    public static volatile ListAttribute<FechaHabil, Asistencia> asistenciaList;

}