/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.dao.EstadoProcessoDAO;
import fenix.iure.modelo.EstadoProcesso;
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

/**
 *
 * @author Aisha Lubadika
 */
@Named(value = "estadoProcessoMBean")
@SessionScoped
public class EstadoProcessoMBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private EstadoProcesso estadoProcesso;
    private EstadoProcessoDAO estadoProcessoDAO;
    private List<EstadoProcesso> estadoProcessos;
            
    public EstadoProcessoMBean() {
    }
    
    
        @PostConstruct
    public void inicializar() {
        estadoProcesso = new EstadoProcesso();
        estadoProcessoDAO = new EstadoProcessoDAO();
        
    }

    public EstadoProcesso getEstadoProcesso() {
        return estadoProcesso;
    }

    public void setEstadoProcesso(EstadoProcesso estadoProcesso) {
        this.estadoProcesso = estadoProcesso;
    }

    public EstadoProcessoDAO getEstadoProcessoDAO() {
        return estadoProcessoDAO;
    }

    public void setEstadoProcessoDAO(EstadoProcessoDAO estadoProcessoDAO) {
        this.estadoProcessoDAO = estadoProcessoDAO;
    }

    public List<EstadoProcesso> getEstadoProcessos() {
        estadoProcessos= estadoProcessoDAO.findAll();
        return estadoProcessos;
    }

    public void setEstadoProcessos(List<EstadoProcesso> estadoProcessos) {
        this.estadoProcessos = estadoProcessos;
    }
    
    
      public void guardar(ActionEvent evt) {
        if (estadoProcessoDAO.save(estadoProcesso)){
            estadoProcesso = new EstadoProcesso();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }
    }
    
    public String startEdit() {
        return "estado_listar?faces-redirect=true";
    }
    
    public void edit(javafx.event.ActionEvent event) {
        if (estadoProcessoDAO.update(estadoProcesso)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
            estadoProcessos = null;

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("estado_listar.jsf");
            } catch (IOException ex) {
                Logger.getLogger(EspecieProcessoMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar\t", "\tErro ao editar dados"));
        }

    }
    
    public String delete() {
        if (estadoProcessoDAO.delete(estadoProcesso)) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
             estadoProcessos = null;
             return "estado_listar?faces-redirect=true";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tErro ao eliminar dados!"));
            return null;
        }      
    }
    
}
