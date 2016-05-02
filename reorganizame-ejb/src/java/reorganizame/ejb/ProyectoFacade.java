/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reorganizame.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import reorganizame.entity.Proyecto;
import reorganizame.entity.Usuario;

/**
 *
 * @author David
 */
@Stateless
public class ProyectoFacade extends AbstractFacade<Proyecto> {

    @PersistenceContext(unitName = "reorganizame-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProyectoFacade() {
        super(Proyecto.class);
    }
    public Integer findMaxProyectoId () {
        Query q;
        
        q = em.createQuery("SELECT MAX(p.idProyecto) FROM Proyecto p");
        return (Integer)q.getSingleResult();
    }
    
     public List<Proyecto> findProyectoByLider (Usuario lider) {
        Query q;
        
        q = em.createNamedQuery("Proyecto.findByLider");
        q.setParameter("idLider", lider);
        return q.getResultList();
    }
}
