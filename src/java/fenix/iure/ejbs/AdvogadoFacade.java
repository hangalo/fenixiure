/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.ejbs;

import fenix.iure.entities.Advogado;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author informatica
 */
@Stateless
public class AdvogadoFacade extends AbstractFacade<Advogado> {

    @PersistenceContext(unitName = "fenixiurePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdvogadoFacade() {
        super(Advogado.class);
    }

    public List<Advogado> findByNome(String nome) {
      return null;
    }
    public List<Advogado> findBySobrenome(String sobrenome) {
       return null;
    }
    
     public List<Advogado> findByNomeSobrenome(String nome, String sobrenome) {
        
       return null; 
    }
    public List<Advogado> findByIntervaloDataNascimento(Date dataInicio, Date dataFim) {
       return null; 
    }
    
    public List<Advogado> findByIntervaloDataInicioFuncoes(Date dataInicio, Date dataFim) {
       return null; 
    }
    
    public List<Advogado> findByDataNascimento(Date dataNacimento) {
       return null;
    }
    
    public List<Advogado> findByDataInicioFuncao(Date dataInicioFuncao) {
        
       return null; 
    }
    
    
    
    
}
