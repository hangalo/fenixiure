/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.ejbs.ProcessoFacade;
import fenix.iure.ejbs.ProcessoFindoFacade;
import fenix.iure.ejbs.TipoDecisaoFacade;
import fenix.iure.entities.Processo;
import fenix.iure.entities.ProcessoFindo;
import fenix.iure.entities.TipoDecisao;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Named(value = "processoFindoMBean")
@SessionScoped
public class ProcessoFindoMBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private ProcessoFindo processoFindo; 
    private List<ProcessoFindo> processoFindos;
    
    // Listas para as comboboxs
    private List<Processo> processos;
    private List<TipoDecisao> decisoes;
    
    @Inject
    ProcessoFindoFacade processoFindoFacade;
    @Inject
    ProcessoFacade processoFacade;
    @Inject
    TipoDecisaoFacade tipoDecisaoFacade;
    
    public ProcessoFindoMBean() {
    }
    
    @PostConstruct
    public void inicializar() {
        processoFindo = new ProcessoFindo();
        processos =  new ArrayList<>(); 
        decisoes =  new ArrayList<>();
        processoFindos =  new ArrayList<>();
    }

    public List<ProcessoFindo> getProcessoFindos() {
        processoFindos = processoFindoFacade.findAll();
        return processoFindos;
    }

    public List<Processo> getProcessos() {
        processos = processoFacade.findAll();
        return processos;
    }

    public List<TipoDecisao> getDecisoes() {
        decisoes = tipoDecisaoFacade.findAll();
        return decisoes;
    }

    public ProcessoFindo getProcessoFindo() {
        return processoFindo;
    }

    public void setProcessoFindo(ProcessoFindo processoFindo) {
        this.processoFindo = processoFindo;
    }
    
    public String newSave() {
        processoFindo = new ProcessoFindo();
        return "processo_findo_lsta?faces-redirect=true";
    }
    
    public void guardar(ActionEvent evt) {
        if (processoFindo != null) {
            processoFindoFacade.create(processoFindo);
            processoFindo = new ProcessoFindo();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }
    }
    
    public String startEdit() {
        return "processo_findo_lsta?faces-redirect=true";
    }
    public void edit(javafx.event.ActionEvent event) {
        processoFindoFacade.edit(processoFindo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
            processoFindos = null;

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("processo_findo_lsta.jsf");
            } catch (IOException ex) {
                Logger.getLogger(ProcessoFindoMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public String delete() {
        processoFindoFacade.remove(processoFindo);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
             processos = null;
             return "processo_findo_lsta?faces-redirect=true";    
    }
    
    
    
}
