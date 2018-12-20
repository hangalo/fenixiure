/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.ejbs;

import fenix.iure.entities.Requente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author informatica
 */
@Stateless
public class RequenteFacade extends AbstractFacade<Requente> {

    @PersistenceContext(unitName = "fenixiurePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RequenteFacade() {
        super(Requente.class);
    }
    
}
