package reorganizame.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import reorganizame.entity.Proyecto;
import reorganizame.entity.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-03T23:26:00")
@StaticMetamodel(Invitacion.class)
public class Invitacion_ { 

    public static volatile SingularAttribute<Invitacion, Proyecto> idProyecto;
    public static volatile SingularAttribute<Invitacion, Usuario> idUsuario;
    public static volatile SingularAttribute<Invitacion, Integer> idInvitacion;

}