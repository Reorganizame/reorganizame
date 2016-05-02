package reorganizame.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import reorganizame.entity.Tarea;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-30T20:23:37")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-01T21:30:22")
>>>>>>> ea4b732c11ad00847ae74af9ac24d42123b2a12a
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, String> descripcion;
    public static volatile SingularAttribute<Categoria, Integer> idCategoria;
    public static volatile CollectionAttribute<Categoria, Tarea> tareaCollection;
    public static volatile SingularAttribute<Categoria, String> nombre;

}