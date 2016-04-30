/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reorganizame.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import reorganizame.entity.Invitacion;

/**
 *
 * @author David
 */
@Stateless
public class InvitacionFacade extends AbstractFacade<Invitacion> {

    @PersistenceContext(unitName = "reorganizame-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InvitacionFacade() {
        super(Invitacion.class);
    }
    
}
