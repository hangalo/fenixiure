/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.ejbs;

import fenix.iure.entities.EspecieProcesso;
import fenix.iure.entities.Processo;
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
public class ProcessoFacade extends AbstractFacade<Processo> {

    @PersistenceContext(unitName = "fenixiurePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcessoFacade() {
        super(Processo.class);
    }
    
    public List<Processo> findByNumero(String numero) {
        Query query;
        query = em.createQuery("SELECT p FROM Processo p WHERE p.numeroProcesso like :numeroProcesso ORDER BY p.dataEntrada");
        query.setParameter("numeroProcesso", numero);
        return query.getResultList();
        
    }
    public Processo findById(Integer idProcesso) {
        Query query;
        query = em.createQuery("SELECT p FROM Processo p WHERE p.idProcesso like :idProcesso");
        query.setParameter("idProcesso", idProcesso);
        return (Processo) query.getSingleResult();
        
    }
    
    public List<Processo> findAllDorderByDataDesc() {
        Query query;
        query = em.createQuery("SELECT p FROM Processo p ORDER BY p.dataEntrada DESC");
        query.setMaxResults(5);
        return query.getResultList();
        
    }
    public List<Processo> findRecentes() {
        Query query;
        query = em.createQuery("SELECT p FROM Processo p ORDER BY p.dataEntrada DESC");
        query.setMaxResults(6);
        return query.getResultList();
        
    }
    
    public List<Processo> findByDataEntrada(Date dataEntrada) {
        Query query;
        query = em.createQuery("SELECT p FROM Processo p WHERE p.dataEntrada =:dataEntrada ORDER BY p.dataEntrada DESC");
        query.setParameter("dataEntrada", dataEntrada);
        return query.getResultList();
        
    }public List<Processo> findByIntervaloDataEntrada(Date dataInicio, Date dataFim) {
        Query query;
        query = em.createQuery("SELECT p FROM Processo p WHERE p.dataEntrada BETWEEN :dataInicio AND :dataFim ORDER BY p.dataEntrada DESC");
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);
        
        return query.getResultList();
        
    }
    
    public List<Processo> findByDataConclusao(Date dataConclusao) {
        Query query;
        query = em.createQuery("SELECT p FROM Processo p WHERE p.dataConclusao =:dataConclusao ORDER BY p.dataConclusao DESC");
        query.setParameter("dataConclusao", dataConclusao);
        return query.getResultList();
        
    }
    
    public List<Processo> findByIdEspecie(int id) {
        Query query;
        query = em.createQuery("SELECT p FROM Processo p INNER JOIN EspecieProcesso ep ON p.idEspecieProcesso.idEspecieProcesso = ep.idEspecieProcesso WHERE p.idEspecieProcesso.idEspecieProcesso = :idEspecieProcesso ORDER BY p.dataEntrada DESC");
        query.setParameter("idEspecieProcesso", id);
        return query.getResultList();
    }
    
    public List<Processo> findByEspecie(EspecieProcesso especieProcesso) {
        Query query;
        query = em.createQuery("SELECT p FROM Processo p WHERE p.idEspecieProcesso = :especieProcesso ORDER BY p.dataEntrada DESC");
        query.setParameter("especieProcesso", especieProcesso);
        return query.getResultList();
    }
    
    
    public List<Processo> findByIdEstado(int id) {
        Query query;
        query = em.createQuery("SELECT p FROM Processo p INNER JOIN EstadoProcesso ep ON p.idEstadoProcesso.idEstadoProcesso = ep.idEstadoProcesso WHERE p.idEstadoProcesso.idEstadoProcesso = :idEstadoProcesso ORDER BY p.dataEntrada DESC");
        query.setParameter("idEstadoProcesso", id);
        return query.getResultList();
    }
    
    public List<Processo> findByIdRequerente(int id) {
        Query query;
        query = em.createQuery("SELECT p FROM Processo p INNER JOIN Requente ep ON p.idRequente.idRequente = ep.idRequente WHERE p.idRequente.idRequente = :idRequente ORDER BY p.dataEntrada DESC");
        query.setParameter("idRequente", id);
        return query.getResultList();
    }
    public List<Processo> findByIdRequerido(int id) {
        Query query;
        query = em.createQuery("SELECT p FROM Processo p INNER JOIN Requerido ep ON p.idRequerido.idRequerido = ep.idRequerido WHERE p.idRequerido.idRequerido = :idRequerido ORDER BY p.dataEntrada DESC");
        query.setParameter("idRequerido", id);
        return query.getResultList();
    }
    
    public List<Processo> findByIdJuiz(int id) {
        Query query;
        query = em.createQuery("SELECT p FROM Processo p INNER JOIN Juiz ep ON p.idJuiz.idJuiz = ep.idJuiz WHERE p.idJuiz.idJuiz = :idJuiz ORDER BY p.dataEntrada DESC");
        query.setParameter("idJuiz", id);
        return query.getResultList();
    }
    
    public List<Processo> findByIdAdvogado(int id) {
        Query query;
        query = em.createQuery("SELECT p FROM Processo p INNER JOIN Advogado ep ON p.idAdvogado.idAdvogado = ep.idAdvogado WHERE p.idAdvogado.idAdvogado = :idAdvogado ORDER BY p.dataEntrada DESC");
        query.setParameter("idAdvogado", id);
        return query.getResultList();
    }
    
    public List<Processo> findByIdTipoDecisao(int id) {
        Query query;
        query = em.createQuery("SELECT p FROM Processo p INNER JOIN TipoDecisao ep ON p.idTipoDecisao.idTipoDecisao = ep.idTipoDecisao WHERE p.idTipoDecisao.idTipoDecisao = :idTipoDecisao ORDER BY p.dataEntrada DESC");
        query.setParameter("idTipoDecisao", id);
        return query.getResultList();
    }
}
