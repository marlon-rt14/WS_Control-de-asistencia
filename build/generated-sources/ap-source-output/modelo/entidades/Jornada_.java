package modelo.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Tipo;
import modelo.entidades.TipoEmpleado;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-11T22:37:46")
@StaticMetamodel(Jornada.class)
public class Jornada_ { 

    public static volatile ListAttribute<Jornada, TipoEmpleado> tipoEmpleadoList;
    public static volatile SingularAttribute<Jornada, Date> saleSegundoPeriodo;
    public static volatile SingularAttribute<Jornada, Integer> idJornada;
    public static volatile SingularAttribute<Jornada, Date> salePrimerPeriodo;
    public static volatile SingularAttribute<Jornada, Tipo> idTipo;
    public static volatile SingularAttribute<Jornada, Date> entraPrimerPeriodo;
    public static volatile SingularAttribute<Jornada, Date> entraSegundoPeriodo;

}