/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.ejbs;

import fenix.iure.entities.AcessoSistemaFuncionario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Stateless
public class AcessoSistemaFuncionarioFacade extends AbstractFacade<AcessoSistemaFuncionario> {

    @PersistenceContext(unitName = "fenixiurePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcessoSistemaFuncionarioFacade() {
        super(AcessoSistemaFuncionario.class);
    }
    
}
