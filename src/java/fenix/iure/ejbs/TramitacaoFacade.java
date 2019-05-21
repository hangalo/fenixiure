/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.ejbs;

import fenix.iure.entities.Tramitacao;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Stateless
public class TramitacaoFacade extends AbstractFacade<Tramitacao> {

    @PersistenceContext(unitName = "fenixiurePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TramitacaoFacade() {
        super(Tramitacao.class);
    }
    
    public List<Tramitacao> findByIdProcesso(int id) {
        Query query;
        query = em.createQuery("SELECT t FROM Tramitacao t INNER JOIN Processo p ON t.idProcesso.idProcesso = p.idProcesso WHERE t.idProcesso.idProcesso = :idProcesso ORDER BY t.dataConclusaoTramitacao DESC");
        query.setParameter("idProcesso", id);
        return query.getResultList();
    }
    public List<Tramitacao> findByIdProcessoEstado(int id) {
        String estadoProcesso = "Findo";
        Query query;
        query = em.createQuery("SELECT t FROM Tramitacao t INNER JOIN Processo p ON t.idProcesso.idProcesso = p.idProcesso WHERE t.idProcesso.idProcesso = :idProcesso AND t.idEstado.estadoProcesso = :estadoProcesso ORDER BY t.dataConclusaoTramitacao DESC");
        query.setParameter("idProcesso", id);
        query.setParameter("estadoProcesso", estadoProcesso);
        
        return query.getResultList();
    }
    public List<Tramitacao> findByIdProcessoFindos() {
        String estadoProcesso = "Findo";
        Query query;
        query = em.createQuery("SELECT t FROM Tramitacao t WHERE t.idEstado.estadoProcesso = :estadoProcesso ORDER BY t.dataConclusaoTramitacao DESC");
        query.setParameter("estadoProcesso", estadoProcesso);
        return query.getResultList();
    }
    public List<Tramitacao> findByIdProcessoFindosIntervaloTempo(Date dataInicio, Date dataFim) {
        String estadoProcesso = "Findo";
        Query query;
        query = em.createQuery("SELECT t FROM Tramitacao t WHERE  t.idEstado.estadoProcesso = :estadoProcesso AND t.idProcesso.dataEntrada BETWEEN :dataInicio AND :dataFim ORDER BY t.dataConclusaoTramitacao DESC");
        query.setParameter("estadoProcesso", estadoProcesso);
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);
        
        return query.getResultList();
    }
     public List<Tramitacao> findByIdProcessoFindosDecisaoIntervaloTempo(int idDecisao, Date dataInicio, Date dataFim) {
        String estadoProcesso = "Findo";
        Query query;
        query = em.createQuery("SELECT t FROM Tramitacao t  WHERE t.idEstado.estadoProcesso = :estadoProcesso AND t.idTipoDecisao.idTipoDecisao = :idDecisao AND t.idProcesso.dataEntrada BETWEEN :dataInicio AND :dataFim  ORDER BY t.dataConclusaoTramitacao DESC");
        query.setParameter("estadoProcesso", estadoProcesso);
        query.setParameter("idDecisao", idDecisao);
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);
        
        
        return query.getResultList();
    }
    
    
    
    public List<Tramitacao> findByIdEstadoDatas(int idEstado, Date dataInicio, Date dataFim) {
        Query query;
        query = em.createQuery("SELECT t FROM Tramitacao t INNER JOIN EstadoProcesso ep ON t.idEstado.idEstadoProcesso = ep.idEstadoProcesso WHERE t.idProcesso.dataEntrada BETWEEN :dataInicio AND :dataFim AND t.idEstado.idEstadoProcesso = :idEstadoProcesso ORDER BY t.dataConclusaoTramitacao DESC");
        query.setParameter("idEstadoProcesso", idEstado);
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);
        
        
        return query.getResultList();
    }
    
    
    
     public List<Tramitacao> findByIdEspecieDatas(int idEspecie, Date dataInicio, Date dataFim) {
        Query query;
        query = em.createQuery("SELECT t FROM Tramitacao t WHERE t.idProcesso.dataEntrada BETWEEN :dataInicio AND :dataFim AND t.idProcesso.idEspecieProcesso.idEspecieProcesso = :idEspecie ORDER BY t.dataConclusaoTramitacao DESC");
        query.setParameter("idEspecie", idEspecie);
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);
        
        
        return query.getResultList();
    }
     
     public List<Tramitacao> findByIdEspecieEstadoDatas(int idEspecie, int idEstado, Date dataInicio, Date dataFim) {
        Query query;
        query = em.createQuery("SELECT t FROM Tramitacao t INNER JOIN EstadoProcesso ep ON t.idEstado.idEstadoProcesso = ep.idEstadoProcesso WHERE t.idProcesso.dataEntrada BETWEEN :dataInicio AND :dataFim AND t.idProcesso.idEspecieProcesso.idEspecieProcesso = :idEspecie AND t.idEstado.idEstadoProcesso = :idEstadoProcesso ORDER BY t.dataConclusaoTramitacao DESC");
        query.setParameter("idEspecie", idEspecie);
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("idEstadoProcesso", idEstado);  
        query.setParameter("dataFim", dataFim);
        
        
        return query.getResultList();
    }
     
     public List<Tramitacao> findByIdDecisaoEstadoDatas(int idEstado, int idDecisao, Date dataInicio, Date dataFim) {
        Query query;
        query = em.createQuery("SELECT t FROM Tramitacao t INNER JOIN EstadoProcesso ep ON t.idEstado.idEstadoProcesso = ep.idEstadoProcesso WHERE t.idProcesso.dataEntrada BETWEEN :dataInicio AND :dataFim AND t.idEstado.idEstadoProcesso = :idEstadoProcesso AND t.idTipoDecisao.idTipoDecisao = :idDecisao ORDER BY t.dataConclusaoTramitacao DESC");
        query.setParameter("idEstadoProcesso", idEstado);
        query.setParameter("idDecisao", idDecisao);
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);
        
        
        return query.getResultList();
    }
    
   
    
}
