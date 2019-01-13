/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.ejbs;

import fenix.iure.entities.Advogado;
import fenix.iure.entities.Municipio;
import fenix.iure.entities.Requente;
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
    
    public List<Requente> findByNome(String nome) {
        Query query;
        query = em.createQuery("SELECT r FROM Requente r WHERE r.nomeRequente like :nomeRequente ORDER BY r.nomeRequente");
        query.setParameter("nomeRequente", nome+"%");
        return query.getResultList();
    }
    
    public List<Requente> findBySobrenome(String sobrenome) {
        Query query;
        query = em.createQuery("SELECT r FROM Requente r WHERE r.sobrenomeRequerente like :sobrenomeRequerente ORDER BY r.sobrenomeRequerente");
        query.setParameter("sobrenomeRequerente", sobrenome+"%");
        return query.getResultList();
    }
    
    public List<Requente> findByBilheteIdentidade(String bilhetIdentidade) {
        Query query;
        query = em.createQuery("SELECT r FROM Requente r WHERE r.nBiRequerente like :nBiRequerente ORDER BY r.nBiRequerente");
        query.setParameter("nBiRequerente", bilhetIdentidade+"%");
        return query.getResultList();
    }
    
    public List<Requente> findByNomeSobrenome(String nome, String sobrenome) {
        Query query;
        query = em.createQuery("SELECT r FROM Requente r WHERE r.nomeRequente = :nomeRequente AND r.sobrenomeRequerente = :sobrenomeRequerente ORDER BY r.nomeRequente");
        query.setParameter("nomeRequente", nome);
        query.setParameter("sobrenomeRequerente", sobrenome);
        return query.getResultList();
    }
    
    
    
    public List<Requente> findByIdMunicipio(int id) {
        Query query;
        query = em.createQuery("SELECT r FROM Requente r INNER JOIN Municipio m ON r.idMunicipio.idMunicipio = m.idMunicipio WHERE r.idMunicipio.idMunicipio = :idMunicipio ORDER BY r.nomeRequente");
        query.setParameter("idMunicipio", id);
        return query.getResultList();
    }
    
    public List<Requente> findByIdTipoPessoa(int id) {
        Query query;
        query = em.createQuery("SELECT r FROM Requente r INNER JOIN TipoPessoa t ON r.idTipo.idTipo = t.idTipo WHERE r.idTipo.idTipo = :idTipo ORDER BY r.nomeRequente");
        query.setParameter("idTipo", id);
        return query.getResultList();
    }
    
}
