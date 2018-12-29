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
import javax.persistence.Query;

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
        Query query;
        query = em.createQuery("SELECT a FROM Advogado a WHERE a.nomeAdvogado like :nomeAdvogado ORDER BY a.nomeAdvogado");
        query.setParameter("nomeAdvogado", nome+"%");
        return query.getResultList();
        
    }
    public List<Advogado> findBySobrenome(String sobrenome) {
       Query query;
        query = em.createQuery("SELECT a FROM Advogado a WHERE a.sobrenomeAdvogado like :sobrenomeAdvogado ORDER BY a.nomeAdvogado");
        query.setParameter("sobrenomeAdvogado", sobrenome+"%");
        return query.getResultList();
    }
    
     public List<Advogado> findByNomeSobrenome(String nome, String sobrenome) {
        Query query;
        query = em.createQuery("SELECT a FROM Advogado a WHERE a.nomeAdvogado = :nomeAdvogado AND a.sobrenomeAdvogado = :sobrenomeAdvogado ORDER BY a.nomeAdvogado");
        query.setParameter("nomeAdvogado", nome);
        query.setParameter("sobrenomeAdvogado", sobrenome);
        return query.getResultList();
    }
    public List<Advogado> findByIntervaloDataNascimento(Date dataInicio, Date dataFim) {
        /*Query query;
        query = em.createQuery("SELECT a FROM Advogado a WHERE a.dataNascimentoAdvogado between :? AND :? ORDER BY a.dataNascimentoAdvogado");
        query.setParameter("?", dataInicio);
        query.setParameter("?", dataFim);
        
        return query.getResultList();*/
        return null;
    }
    
    public List<Advogado> findByIntervaloDataInicioFuncoes(Date dataInicio, Date dataFim) {
       return null; 
    }
    
    public List<Advogado> findByDataNascimento(Date dataNacimento) {
        Query query;
        query = em.createQuery("SELECT a FROM Advogado a WHERE a.dataNascimentoAdvogado =:dataNascimentoAdvogado ORDER BY a.dataNascimentoAdvogado");
        query.setParameter("dataNascimentoAdvogado", dataNacimento);
        return query.getResultList();
    }
    
    
    
    public List<Advogado> findByDataInicioFuncao(Date dataInicioFuncao) {
        Query query;
        query = em.createQuery("SELECT a FROM Advogado a WHERE a.dataInicioFuncoes =:dataInicioFuncoes ORDER BY a.dataInicioFuncoes");
        query.setParameter("dataInicioFuncoes", dataInicioFuncao);
        return query.getResultList();
    }
    
    
    
    
    
}
