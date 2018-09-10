/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.dao.MunicipioDAO;
import fenix.iure.modelo.Municipio;
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
@Named(value = "municipioMBean")
@SessionScoped
public class MunicipioMBean implements Serializable {

  
    private static final long serialVersionUID = 1L;
    
    private Municipio municipio;
    private MunicipioDAO municipioDAO;
    private List<Municipio> municipios;
    
    
    public MunicipioMBean() {
    }
      @PostConstruct
    public void inicializar() {
        municipio = new Municipio();
        municipioDAO = new MunicipioDAO();
        
    }    

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public MunicipioDAO getMunicipioDAO() {
        return municipioDAO;
    }

    public void setMunicipioDAO(MunicipioDAO municipioDAO) {
        this.municipioDAO = municipioDAO;
    }

    public List<Municipio> getMunicipios() {
        municipios= municipioDAO.findAll();
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
    
    
     public void guardar(ActionEvent evt) {
        if (municipioDAO.save(municipio)){
            municipio = new Municipio();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }
    }
    
    public String startEdit() {
        return "municipio_listar?faces-redirect=true";
    }
    
    public void edit(javafx.event.ActionEvent event) {
        if (municipioDAO.update(municipio)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
            municipios = null;

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("municipio_listar.jsf");
            } catch (IOException ex) {
                Logger.getLogger(MunicipioMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar\t", "\tErro ao editar dados"));
        }

    }
    
    public String delete() {
        if (municipioDAO.delete(municipio)) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
             municipios = null;
             return "municipio_listar?faces-redirect=true";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tErro ao eliminar dados!"));
            return null;
        }      
    }
    
    
    
}
