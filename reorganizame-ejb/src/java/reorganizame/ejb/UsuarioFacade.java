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
import reorganizame.entity.Usuario;

/**
 *
 * @author David
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "reorganizame-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario findUserByEmail(String email){
        Query q = em.createNamedQuery("Usuario.findByCorreo");
        List<Usuario> listausuarios = q.getResultList();
        Usuario resultado = null;
        if(listausuarios.size()>0){
            resultado = listausuarios.get(0);
        }
        return resultado;
    }
    
}
