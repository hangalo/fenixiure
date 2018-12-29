/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.ejbs;

import fenix.iure.entities.Advogado;
import fenix.iure.entities.Juiz;
import java.util.Date;
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
public class JuizFacade extends AbstractFacade<Juiz> {

    @PersistenceContext(unitName = "fenixiurePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JuizFacade() {
        super(Juiz.class);
    }
    
     public List<Juiz> findByNome(String nome) {
        Query query;
        query = em.createQuery("SELECT j FROM Juiz j WHERE j.nomeJuiz like :nomeJuiz ORDER BY j.nomeJuiz");
        query.setParameter("nomeJuiz", nome+"%");
        return query.getResultList();
        
    }
    public List<Juiz> findBySobrenome(String sobrenome) {
       Query query;
        query = em.createQuery("SELECT j FROM Juiz j WHERE j.sobrenomeJuiz like :sobrenomeJuiz ORDER BY j.sobrenomeJuiz");
        query.setParameter("sobrenomeJuiz", sobrenome+"%");
        return query.getResultList();
    }
    
     public List<Juiz> findByNomeSobrenome(String nome, String sobrenome) {
        Query query;
        query = em.createQuery("SELECT j FROM Juiz j WHERE j.nomeJuiz = :nomeJuiz AND j.sobrenomeJuiz = :sobrenomeJuiz ORDER BY j.nomeJuiz");
        query.setParameter("nomeJuiz", nome);
        query.setParameter("sobrenomeJuiz", sobrenome);
        return query.getResultList();
    }
    public List<Juiz> findByIntervaloDataNascimento(Date dataInicio, Date dataFim) {
       return null; 
    }
    
    public List<Juiz> findByIntervaloDataInicioFuncoes(Date dataInicio, Date dataFim) {
       return null; 
    }
    
    public List<Juiz> findByDataNascimento(Date dataNacimento) {
        Query query;
        query = em.createQuery("SELECT j FROM Juiz j WHERE j.dataNascimentoJuiz =:dataNascimentoJuiz ORDER BY j.dataNascimentoJuiz");
        query.setParameter("dataNascimentoJuiz", dataNacimento);
        return query.getResultList();
    }
    
    public List<Juiz> findByDataInicioFuncao(Date dataInicioFuncao) {
        Query query;
        query = em.createQuery("SELECT j FROM Juiz j WHERE j.dataInicioFuncoes =:dataInicioFuncoes ORDER BY j.dataInicioFuncoes");
        query.setParameter("dataInicioFuncoes", dataInicioFuncao);
        return query.getResultList();
    }
    
   
}
