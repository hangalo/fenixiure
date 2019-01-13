/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.ejbs;

import fenix.iure.entities.Requente;
import fenix.iure.entities.Requerido;
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
    
    public List<Requerido> findByNome(String nome) {
        Query query;
        query = em.createQuery("SELECT r FROM Requerido r WHERE r.nomeRequerido like :nomeRequerido ORDER BY r.nomeRequerido");
        query.setParameter("nomeRequerido", nome+"%");
        return query.getResultList();
    }
    
    public List<Requerido> findBySobrenome(String sobrenome) {
        Query query;
        query = em.createQuery("SELECT r FROM Requerido r WHERE r.sobrenomeRequerido like :sobrenomeRequerido ORDER BY r.sobrenomeRequerido");
        query.setParameter("sobrenomeRequerido", sobrenome+"%");
        return query.getResultList();
    }
    
    public List<Requerido> findByBilheteIdentidade(String bilhetIdentidade) {
        Query query;
        query = em.createQuery("SELECT r FROM Requerido r WHERE r.nBiRequerido like :nBiRequerido ORDER BY r.nBiRequerido");
        query.setParameter("nBiRequerido", bilhetIdentidade+"%");
        return query.getResultList();
    }
    
    public List<Requerido> findByNomeSobrenome(String nome, String sobrenome) {
        Query query;
        query = em.createQuery("SELECT r FROM Requerido r WHERE r.nomeRequerido = :nomeRequerido AND r.sobrenomeRequerido = :sobrenomeRequerido ORDER BY r.nomeRequerido");
        query.setParameter("nomeRequerido", nome);
        query.setParameter("sobrenomeRequerido", sobrenome);
        return query.getResultList();
    }
    
    public List<Requerido> findByIdMunicipio(int id) {
        Query query;
        query = em.createQuery("SELECT r FROM Requerido r INNER JOIN Municipio m ON r.idMunicipio.idMunicipio = m.idMunicipio WHERE r.idMunicipio.idMunicipio = :idMunicipio ORDER BY r.nomeRequerido");
        query.setParameter("idMunicipio", id);
        return query.getResultList();
    }
    
    public List<Requerido> findByIdTipoPessoa(int id) {
        Query query;
        query = em.createQuery("SELECT r FROM Requerido r INNER JOIN TipoPessoa t ON r.idTipo.idTipo = t.idTipo WHERE r.idTipo.idTipo = :idTipo ORDER BY r.nomeRequerido");
        query.setParameter("idTipo", id);
        return query.getResultList();
    }
    
    
}
