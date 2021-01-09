package modelo.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.FechaHabil;
import modelo.entidades.TipoEmpleado;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-08T22:20:21")
@StaticMetamodel(Tipo.class)
public class Tipo_ { 

    public static volatile ListAttribute<Tipo, TipoEmpleado> tipoEmpleadoList;
    public static volatile SingularAttribute<Tipo, Integer> idTipo;
    public static volatile ListAttribute<Tipo, FechaHabil> fechaHabilList;
    public static volatile SingularAttribute<Tipo, String> descripcionTipo;

}