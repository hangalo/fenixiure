/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.ejbs;

import fenix.iure.entities.EspecieProcesso;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author informatica
 */
@Stateless
public class EspecieProcessoFacade extends AbstractFacade<EspecieProcesso> {

    @PersistenceContext(unitName = "fenixiurePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EspecieProcessoFacade() {
        super(EspecieProcesso.class);
    }
    
     public List<EspecieProcesso> findEspecieByNome(String nomeEspecie) {
        Query query;
        query = em.createQuery("SELECT a FROM EspecieProcesso a WHERE a.especieProcesso LIKE  :nomeEspecie ORDER BY a.nomeEspecie");
        query.setParameter("nomeEspecie", "%"+nomeEspecie+"%");
        return query.getResultList();
    }
      
    
}
