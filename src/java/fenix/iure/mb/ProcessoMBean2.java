/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.ejbs.AdvogadoFacade;
import fenix.iure.ejbs.EspecieProcessoFacade;
import fenix.iure.ejbs.EstadoProcessoFacade;
import fenix.iure.ejbs.JuizFacade;
import fenix.iure.ejbs.ProcessoFacade;
import fenix.iure.ejbs.RequenteFacade;
import fenix.iure.ejbs.RequeridoFacade;
import fenix.iure.ejbs.TipoDecisaoFacade;
import fenix.iure.entities.Advogado;
import fenix.iure.entities.EspecieProcesso;
import fenix.iure.entities.EstadoProcesso;
import fenix.iure.entities.Juiz;
import fenix.iure.entities.Processo;
import fenix.iure.entities.Requente;
import fenix.iure.entities.Requerido;
import fenix.iure.entities.TipoDecisao;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

import javax.inject.Named;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Named(value = "processoMBean2")
@ViewScoped
public class ProcessoMBean2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Processo processo;
    private List<Processo> processos;

    @Inject
    ProcessoFacade processoFacade;
    @Inject
    EspecieProcessoFacade especieProcessoFacade;
    @Inject
    EstadoProcessoFacade estadoProcessoFacade;
    @Inject
    RequenteFacade requenteFacade;
    @Inject
    RequeridoFacade requeridoFacade;
    @Inject
    AdvogadoFacade advogadoFacade;
    @Inject
    JuizFacade juizFacade;
    @Inject
    TipoDecisaoFacade tipoDecisaoFacade;
    

    // Listas das pesquisas paramentrizadas
    private List<EspecieProcesso> especies;
    private List<Requente> requerentes;
    private List<Requerido> requeridos; 
    private List<Advogado> advogados;
    private List<Juiz> juizes;
    private List<EstadoProcesso> estados;
    private List<TipoDecisao> tipoDecisoes;
    
    // Variaveis para pesquisas paramentrizadas
    private String numero;
    private Date dataEntrada;
    private Date dataConclusao;
    
    public ProcessoMBean2() {
    }

    @PostConstruct
    public void inicializar() {
        processos = new ArrayList<>();
        especies = new ArrayList<>();
        requerentes = new ArrayList<>();
        requeridos = new ArrayList<>();
        advogados = new ArrayList<>();
        juizes = new ArrayList<>();
        estados = new ArrayList<>();
        tipoDecisoes = new ArrayList<>();
        
    }
 public void guardar(java.awt.event.ActionEvent evt) {
        processoFacade.create(processo);
            processo = new Processo();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        
    }


    public String startEdit() {
        return "processo_listar2?faces-redirect=true";
    }

    
    
       public void edit(javafx.event.ActionEvent event) {
        processoFacade.edit(processo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
            requerentes = null;

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("processo_listar2.jsf");
            } catch (IOException ex) {
                Logger.getLogger(RequerenteMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    public String delete() {
        processoFacade.remove(processo);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
             requerentes = null;
             return "processo_listar2?faces-redirect=true";
           
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public List<Processo> getProcessos() {
        processos = processoFacade.findAll();
        return processos;
    }

    public List<EspecieProcesso> getEspecies() {
        especies = especieProcessoFacade.findAll();
        return especies;
    }

    public List<Requente> getRequerentes() {
        requerentes = requenteFacade.findAll();
        return requerentes;
    }

    public List<Requerido> getRequeridos() {
        requeridos = requeridoFacade.findAll();
        return requeridos;
    }

    public List<Advogado> getAdvogados() {
        advogados = advogadoFacade.findAll();
        return advogados;
    }

    public List<Juiz> getJuizes() {
        juizes = juizFacade.findAll();
        return juizes;
    }

    public List<EstadoProcesso> getEstados() {
        estados = estadoProcessoFacade.findAll();
        return estados;
    }

    public List<TipoDecisao> getTipoDecisoes() {
        tipoDecisoes =tipoDecisaoFacade.findAll();
        return tipoDecisoes;
    }
    
    

    
}
