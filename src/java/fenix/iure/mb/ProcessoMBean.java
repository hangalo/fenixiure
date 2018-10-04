/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.dao.AdvogadoDAO;
import fenix.iure.dao.EspecieProcessoDAO;
import fenix.iure.dao.EstadoProcessoDAO;
import fenix.iure.dao.JuizDAO;
import fenix.iure.dao.ProcessoDAO;
import fenix.iure.dao.RequerenteDAO;
import fenix.iure.dao.RequeridoDAO;
import fenix.iure.dao.TipoDecisaoDAO;
import fenix.iure.modelo.Advogado;
import fenix.iure.modelo.EspecieProcesso;
import fenix.iure.modelo.EstadoProcesso;
import fenix.iure.modelo.Juiz;
import fenix.iure.modelo.Processo;
import fenix.iure.modelo.Requerente;
import fenix.iure.modelo.Requerido;
import fenix.iure.modelo.TipoDecisao;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Named(value = "processoMBean")
@SessionScoped
public class ProcessoMBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Processo processo;
    private ProcessoDAO processoDAO;
    private List<Processo> processos;
    
    private EspecieProcessoDAO especieProcessoDAO;
    private RequerenteDAO requerenteDAO;
    private RequeridoDAO requeridoDAO;
    private AdvogadoDAO advogadoDAO;
    private JuizDAO juizDAO;
    private EstadoProcessoDAO estadoProcessoDAO;
    private TipoDecisaoDAO tipoDecisaoDAO;

    private List<EspecieProcesso> especies;
    private List<Requerente> requerentes;
    private List<Requerido> requeridos; 
    private List<Advogado> advogados;
    private List<Juiz> juizes;
    private List<EstadoProcesso> estados;
    private List<TipoDecisao> tipoDecisoes;
    
    // Colocar aqui as variaveis de pesquisas parametrisadas
    private Integer idEstadoProcesso;
    
    
    
    
    // Colocar aqui as listas que retornam as pesquisas parametrizadas
    private List<Processo> byEstadoProcessos;
    
    
    
    public ProcessoMBean() {
    }
    
    @PostConstruct
    public void inicializar() {
        especieProcessoDAO = new EspecieProcessoDAO();
        processo = new Processo();
        processoDAO = new ProcessoDAO();
        requerenteDAO = new RequerenteDAO();
        requeridoDAO = new RequeridoDAO();
        advogadoDAO = new AdvogadoDAO();
        juizDAO = new JuizDAO();
        estadoProcessoDAO = new EstadoProcessoDAO();
        tipoDecisaoDAO = new TipoDecisaoDAO();
        
        processos = new ArrayList<>();
        especies = new ArrayList<>();
        requerentes = new ArrayList<>();
        requeridos = new ArrayList<>();
        advogados = new ArrayList<>();
        juizes = new ArrayList<>();
        estados = new ArrayList<>();
        tipoDecisoes = new ArrayList<>();
        
        // Inicializar listas das pesquisas paramentrizadas
        byEstadoProcessos = new ArrayList<>();
        
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public List<Processo> getProcessos() {
        processos = processoDAO.findAll();
        return processos;
    }

    public List<EspecieProcesso> getEspecies() {
        especies = especieProcessoDAO.findAll();
        return especies;
    }

    public List<Requerente> getRequerentes() {
        requerentes = requerenteDAO.findAll();
        return requerentes;
    }

    public List<Requerido> getRequeridos() {
        requeridos = requeridoDAO.findAll();
        return requeridos;
    }

    public List<Advogado> getAdvogados() {
        advogados = advogadoDAO.findAll();
        return advogados;
    }

    public List<Juiz> getJuizes() {
        juizes = juizDAO.findAll();
        return juizes;
    }

    public List<EstadoProcesso> getEstados() {
        estados = estadoProcessoDAO.findAll();
        return estados;
    }

    public List<TipoDecisao> getTipoDecisoes() {
        tipoDecisoes = tipoDecisaoDAO.findAll();
        return tipoDecisoes;
    }
    
    
    // SETs e GETs para os paramentros das pesquisas paramentrizadas
    public Integer getIdEstadoProcesso() {
        return idEstadoProcesso;
    }

    public void setIdEstadoProcesso(Integer idEstadoProcesso) {
        this.idEstadoProcesso = idEstadoProcesso;
    }
    
    
    
    // Fim SETs e GETs
    
    // Inicio GETs das listas para as pesquisas paramentrizadas
    public List<Processo> getByEstadoProcessos() {
        EstadoProcesso estadoProcesso = new EstadoProcesso();
        byEstadoProcessos = processoDAO.findByEstadoProcesso(estadoProcesso);
        return byEstadoProcessos;
    }
    
    // Fim GETs

    
    
    public String newSave() {
        processo = new Processo();
        return "processo_listar?faces-redirect=true";
    }
    
    public void guardar(ActionEvent evt) {
        if (processoDAO.save(processo)){
            processo = new Processo();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }
    }
    
    public String startEdit() {
        return "processo_listar?faces-redirect=true";
    }
    
    public void edit(javafx.event.ActionEvent event) {
        if (processoDAO.update(processo)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
            processos = null;

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("processo_listar.jsf");
            } catch (IOException ex) {
                Logger.getLogger(ProcessoMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar\t", "\tErro ao editar dados"));
        }

    }
    
    public String delete() {
        if (processoDAO.delete(processo)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
            advogados = null;
            return "processo_listar?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tErro ao eliminar dados!"));
            return null;
        }
    }

   

    
   




    
    
    
    
    
}
