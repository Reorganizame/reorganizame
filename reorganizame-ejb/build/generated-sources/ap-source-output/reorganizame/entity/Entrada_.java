package reorganizame.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import reorganizame.entity.Proyecto;
import reorganizame.entity.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-30T20:24:40")
@StaticMetamodel(Entrada.class)
public class Entrada_ { 

    public static volatile SingularAttribute<Entrada, String> titulo;
    public static volatile SingularAttribute<Entrada, Integer> idEntrada;
    public static volatile SingularAttribute<Entrada, Date> fecha;
    public static volatile SingularAttribute<Entrada, String> contenido;
    public static volatile SingularAttribute<Entrada, Date> fechaEdicion;
    public static volatile SingularAttribute<Entrada, Usuario> creador;
    public static volatile SingularAttribute<Entrada, Proyecto> proyecto;

}