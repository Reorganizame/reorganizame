package reorganizame.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import reorganizame.entity.Categoria;
import reorganizame.entity.Proyecto;
import reorganizame.entity.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-30T18:13:31")
@StaticMetamodel(Tarea.class)
public class Tarea_ { 

    public static volatile SingularAttribute<Tarea, String> descripcion;
    public static volatile SingularAttribute<Tarea, Date> fechaInicio;
    public static volatile SingularAttribute<Tarea, Integer> idTarea;
    public static volatile SingularAttribute<Tarea, Categoria> categoria;
    public static volatile SingularAttribute<Tarea, Proyecto> proyecto;
    public static volatile SingularAttribute<Tarea, String> nombre;
    public static volatile SingularAttribute<Tarea, Date> fechaFin;
    public static volatile SingularAttribute<Tarea, Usuario> autor;

}