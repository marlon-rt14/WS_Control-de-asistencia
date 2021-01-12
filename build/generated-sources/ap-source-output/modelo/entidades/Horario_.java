package modelo.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Aula;
import modelo.entidades.Empleado;
import modelo.entidades.Materia;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-11T22:37:46")
@StaticMetamodel(Horario.class)
public class Horario_ { 

    public static volatile SingularAttribute<Horario, Date> horaFin;
    public static volatile SingularAttribute<Horario, Integer> idHorario;
    public static volatile SingularAttribute<Horario, Empleado> idEmpleado;
    public static volatile SingularAttribute<Horario, Materia> idMateria;
    public static volatile SingularAttribute<Horario, Aula> idAula;
    public static volatile SingularAttribute<Horario, Date> horaInicio;

}