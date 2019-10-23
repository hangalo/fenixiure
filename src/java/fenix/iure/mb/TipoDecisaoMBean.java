/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;


import fenix.iure.ejbs.TipoDecisaoFacade;
import fenix.iure.entities.TipoDecisao;
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
 * @author Elísio Kavaimunwa
 */
@Named(value = "tipoDecisaoMBean")
@SessionScoped
public class TipoDecisaoMBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private TipoDecisao tipoDecisao;
    private List<TipoDecisao> tipos;
    
    @Inject
    TipoDecisaoFacade tipoDecisaoFacade;
    
    
    public TipoDecisaoMBean() {
    }
    @PostConstruct
    public void inicializar() {
        tipoDecisao = new TipoDecisao();
        
    }
    
    
    public void guardar(ActionEvent evt) {
        tipoDecisaoFacade.create(tipoDecisao);
            tipoDecisao = new TipoDecisao();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        /*} else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }*/
    }
    
    public String startEdit() {
        return "tipo_decisoes?faces-redirect=true";
    }
    
    public void edit(javafx.event.ActionEvent event) {
        tipoDecisaoFacade.edit(tipoDecisao);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
            tipos = null;

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("tipo_decisoes.jsf");
            } catch (IOException ex) {
                Logger.getLogger(TipoDecisaoMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

    
    
    public String delete() {
        tipoDecisaoFacade.remove(tipoDecisao);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
             tipos = null;
             return "tipo_decisoes?faces-redirect=true";
             
    }

    public TipoDecisao getTipoDecisao() {
        return tipoDecisao;
    }

    public void setTipoDecisao(TipoDecisao tipoDecisao) {
        this.tipoDecisao = tipoDecisao;
    }

    public List<TipoDecisao> getTipos() {
        tipos = tipoDecisaoFacade.findAll();
        return tipos;
    }
    
    
    

    
    
}
