/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.ejbs;

import fenix.iure.entities.Requerido;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author informatica
 */
@Stateless
public class RequeridoFacade extends AbstractFacade<Requerido> {

    @PersistenceContext(unitName = "fenixiurePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RequeridoFacade() {
        super(Requerido.class);
    }
    
}