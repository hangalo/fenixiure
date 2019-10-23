/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.ejbs;

import fenix.iure.entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "fenixiurePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public boolean buscarUsuario(String loginUsuario, String senhaUsuario){
        boolean retorno = false;
        Query query;
        query = em.createQuery("SELECT u FROM Usuario u WHERE u.loginUsuario = :loginUsuario AND u.senhaUsuario = :senhaUsuario");
        query.setParameter("loginUsuario", loginUsuario);
        query.setParameter("senhaUsuario", senhaUsuario);
        query.getResultList();
        if (query.getResultList().size() > 0) {
            retorno = true;
            return  retorno;
        }else{
            retorno = false;
            return  retorno;
        }
    }
    
    
    
    
}
