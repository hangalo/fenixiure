/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.dao.TipoDecisaoDAO;
import fenix.iure.modelo.TipoDecisao;
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
 * @author Elísio Kavaimunwa
 */
@Named(value = "tipoDecisaoMBean")
@SessionScoped
public class TipoDecisaoMBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private TipoDecisao tipoDecisao;
    private TipoDecisaoDAO tipoDecisaoDAO;
    private List<TipoDecisao> tipos;
     
    
    
    public TipoDecisaoMBean() {
    }
    @PostConstruct
    public void inicializar() {
        tipoDecisao = new TipoDecisao();
        tipoDecisaoDAO = new TipoDecisaoDAO();
        
    }
    
    
    public void guardar(ActionEvent evt) {
        if (tipoDecisaoDAO.save(tipoDecisao)){
            tipoDecisao = new TipoDecisao();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }
    }
    
    public String startEdit() {
        return "tipo_decisao_listar?faces-redirect=true";
    }
    
    public void edit(javafx.event.ActionEvent event) {
        if (tipoDecisaoDAO.update(tipoDecisao)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
            tipos = null;

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("tipo_decisao_listar.jsf");
            } catch (IOException ex) {
                Logger.getLogger(TipoDecisaoMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar\t", "\tErro ao editar dados"));
        }

    }
    
    public String delete() {
        if (tipoDecisaoDAO.delete(tipoDecisao)) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
             tipos = null;
             return "tipo_decisao_listar?faces-redirect=true";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tErro ao eliminar dados!"));
            return null;
        }      
    }

    public TipoDecisao getTipoDecisao() {
        return tipoDecisao;
    }

    public void setTipoDecisao(TipoDecisao tipoDecisao) {
        this.tipoDecisao = tipoDecisao;
    }

    public List<TipoDecisao> getTipos() {
        tipos = tipoDecisaoDAO.findAll();
        return tipos;
    }
    
    
    

    
    
}