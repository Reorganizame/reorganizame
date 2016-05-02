package reorganizame.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import reorganizame.entity.Entrada;
import reorganizame.entity.Invitacion;
import reorganizame.entity.Miembro;
import reorganizame.entity.Tarea;
import reorganizame.entity.Usuario;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-30T20:23:37")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-01T21:30:22")
>>>>>>> ea4b732c11ad00847ae74af9ac24d42123b2a12a
@StaticMetamodel(Proyecto.class)
public class Proyecto_ { 

    public static volatile SingularAttribute<Proyecto, String> descripcion;
    public static volatile SingularAttribute<Proyecto, Integer> idProyecto;
    public static volatile SingularAttribute<Proyecto, Date> fechaInicio;
    public static volatile CollectionAttribute<Proyecto, Miembro> miembroCollection;
    public static volatile SingularAttribute<Proyecto, Usuario> lider;
    public static volatile CollectionAttribute<Proyecto, Invitacion> invitacionCollection;
    public static volatile CollectionAttribute<Proyecto, Tarea> tareaCollection;
    public static volatile SingularAttribute<Proyecto, String> nombre;
    public static volatile CollectionAttribute<Proyecto, Entrada> entradaCollection;

}