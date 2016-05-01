package reorganizame.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import reorganizame.entity.Tarea;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-01T21:30:22")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, String> descripcion;
    public static volatile SingularAttribute<Categoria, Integer> idCategoria;
    public static volatile CollectionAttribute<Categoria, Tarea> tareaCollection;
    public static volatile SingularAttribute<Categoria, String> nombre;

}