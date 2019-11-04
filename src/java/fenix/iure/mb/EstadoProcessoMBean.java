/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.ejbs.EstadoProcessoFacade;
import fenix.iure.entities.EstadoProcesso;
import fenix.iure.util.JSFUtil;
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
@Named(value = "estadoProcessoMBean")
@SessionScoped
public class EstadoProcessoMBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private EstadoProcesso estadoProcesso;
    private List<EstadoProcesso> estadoProcessos;

    @Inject
    EstadoProcessoFacade estadoProcessoFacade;

    public EstadoProcessoMBean() {
    }

    @PostConstruct
    public void inicializar() {
        estadoProcesso = new EstadoProcesso();
        estadoProcessos = estadoProcessoFacade.findAll();

    }

    public EstadoProcesso getEstadoProcesso() {
        return estadoProcesso;
    }

    public void setEstadoProcesso(EstadoProcesso estadoProcesso) {
        this.estadoProcesso = estadoProcesso;
    }

    public List<EstadoProcesso> getEstadoProcessos() {
        estadoProcessos = estadoProcessoFacade.findAll();
        return estadoProcessos;
    }

    public void setEstadoProcessos(List<EstadoProcesso> estadoProcessos) {
        this.estadoProcessos = estadoProcessos;
    }

    public void guardar(ActionEvent evt) {
        estadoProcessoFacade.create(estadoProcesso);
        estadoProcesso = new EstadoProcesso();
        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        /*} else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }*/

        JSFUtil.refresh();;
    }

    public String startEdit() {
        return "estado_lstar?faces-redirect=true";
    }

    public void edit(javafx.event.ActionEvent event) {
        estadoProcessoFacade.edit(estadoProcesso);
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
        estadoProcesso = new EstadoProcesso();
    }

    /*else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar\t", "\tErro ao editar dados"));
        }*/

    public void delete() {
        estadoProcessoFacade.remove(estadoProcesso);
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
        estadoProcesso = new EstadoProcesso();
       

    }
}
