package modelo.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Horario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-11T22:37:46")
@StaticMetamodel(Aula.class)
public class Aula_ { 

    public static volatile SingularAttribute<Aula, String> descripcionAula;
    public static volatile SingularAttribute<Aula, Integer> idAula;
    public static volatile ListAttribute<Aula, Horario> horarioList;

}