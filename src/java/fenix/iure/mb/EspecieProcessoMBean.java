/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.ejbs.EspecieProcessoFacade;
import fenix.iure.entities.EspecieProcesso;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Aisha Lubadika
 */
@Named(value = "especieProcessoMBean")
@SessionScoped
public class EspecieProcessoMBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private EspecieProcesso especieProcesso;
    private List<EspecieProcesso> especieProcessos;

    @Inject
    EspecieProcessoFacade especieProcessoFacade;

    public EspecieProcessoMBean() {
    }

    @PostConstruct
    public void inicializar() {
        especieProcesso = new EspecieProcesso();
        especieProcessos = especieProcessoFacade.findAll();
    }

    public EspecieProcesso getEspecieProcesso() {
        return especieProcesso;
    }

    public void setEspecieProcesso(EspecieProcesso especieProcesso) {
        this.especieProcesso = especieProcesso;
    }

    public List<EspecieProcesso> getEspecieProcessos() {
        especieProcessos = especieProcessoFacade.findAll();
        return especieProcessos;
    }

    public void guardar(ActionEvent evt) {
        especieProcessoFacade.create(especieProcesso);
        especieProcesso = new EspecieProcesso();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));

    }

    public String startEdit() {
        return "especies?faces-redirect=true";
    }

    public void edit(javafx.event.ActionEvent event) {
        especieProcessoFacade.edit(especieProcesso);
       // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
        especieProcessos = null;
        especieProcesso = new EspecieProcesso();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("especies.jsf");
        } catch (IOException ex) {
            Logger.getLogger(EspecieProcessoMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String delete() {
        especieProcessoFacade.remove(especieProcesso);
       // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
        especieProcessos = null;
        return "especies?faces-redirect=true";

    }
}
