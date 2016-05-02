package reorganizame.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import reorganizame.entity.Entrada;
import reorganizame.entity.Invitacion;
import reorganizame.entity.Miembro;
import reorganizame.entity.Proyecto;
import reorganizame.entity.Tarea;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-30T20:24:40")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile CollectionAttribute<Usuario, Miembro> miembroCollection;
    public static volatile CollectionAttribute<Usuario, Invitacion> invitacionCollection;
    public static volatile CollectionAttribute<Usuario, Tarea> tareaCollection;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile SingularAttribute<Usuario, String> alias;
    public static volatile SingularAttribute<Usuario, String> apellidos;
    public static volatile CollectionAttribute<Usuario, Entrada> entradaCollection;
    public static volatile CollectionAttribute<Usuario, Proyecto> proyectoCollection;
    public static volatile SingularAttribute<Usuario, String> contrasena;
    public static volatile SingularAttribute<Usuario, Date> fechaNacimiento;
    public static volatile SingularAttribute<Usuario, String> correo;

}