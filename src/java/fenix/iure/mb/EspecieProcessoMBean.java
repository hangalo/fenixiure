/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.dao.EspecieProcessoDAO;
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
     private EspecieProcessoDAO especieProcessoDAO;
     private List<EspecieProcesso> especieProcessos;
    
     @Inject
     EspecieProcessoFacade especieProcessoFacade;
     
     public EspecieProcessoMBean() {
    }
     
     
    
    
   @PostConstruct
   public void inicializar() {
        especieProcesso = new EspecieProcesso();
        especieProcessoDAO = new EspecieProcessoDAO();
        
    }

    public EspecieProcesso getEspecieProcesso() {
        return especieProcesso;
    }

    public void setEspecieProcesso(EspecieProcesso especieProcesso) {
        this.especieProcesso = especieProcesso;
    }

    public EspecieProcessoDAO getEspecieProcessoDAO() {
        return especieProcessoDAO;
    }

    public void setEspecieProcessoDAO(EspecieProcessoDAO especieProcessoDAO) {
        this.especieProcessoDAO = especieProcessoDAO;
    }

    public List<EspecieProcesso> getEspecieProcessos() {
        especieProcessos= especieProcessoFacade.findAll();
        return especieProcessos;
    }

    public void setEspecieProcessos(List<EspecieProcesso> especieProcessos) {
        this.especieProcessos = especieProcessos;
    }
   
    
        
    public void guardar(ActionEvent evt) {
        especieProcessoFacade.create(especieProcesso);
            especieProcesso = new EspecieProcesso();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        /*} else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }*/
    }
    
    /*public String startEdit() {
        return "especie_processo_listar?faces-redirect=true";
    }
    
    public void edit(javafx.event.ActionEvent event) {
        especieProcessoFacade.edit(especieProcesso);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
            especieProcessos = null;

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("especie_processo_listar.jsf");
            } catch (IOException ex) {
                Logger.getLogger(EspecieProcessoMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }/*
         else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar\t", "\tErro ao editar dados"));
        }*/

    
    /*
    public String delete() {
        especieProcessoFacade.remove(especieProcesso);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
             especieProcessos = null;
             return "especie_processo_listar?faces-redirect=true";
        /*}else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tErro ao eliminar dados!"));
            return null;
        }  */    
    //}
    
    
     public String startEdit() {
        return "especie_processo_listar?faces-redirect=true";
    }
    
    public void edit(javafx.event.ActionEvent event) {
        especieProcessoFacade.edit(especieProcesso);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
            especieProcessos = null;

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("especie_processo_listar.jsf");
            } catch (IOException ex) {
                Logger.getLogger(EspecieProcessoMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         /*else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar\t", "\tErro ao editar dados"));
        }*/

    
    
    public String delete() {
        especieProcessoFacade.remove(especieProcesso);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
             especieProcessos = null;
             return "especie_processo_listar?faces-redirect=true";
        /*}else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tErro ao eliminar dados!"));
            return null;
        }  */    
    }
}
