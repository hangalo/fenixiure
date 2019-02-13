/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.ejbs.UsuarioFacade;
import fenix.iure.entities.EspecieProcesso;
import fenix.iure.entities.Usuario;
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
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Named(value = "usuarioMBean")
@RequestScoped
public class UsuarioMBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Usuario usuario;
    private List<Usuario> usuarios;
    
    @Inject
    UsuarioFacade usuarioFacade;

    public UsuarioMBean() {
    }
    
    @PostConstruct
    public void inicializar() {
        usuario = new Usuario();
        usuarios = new ArrayList<>();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        usuarios = usuarioFacade.findAll();
        return usuarios;
    }
    
    public void guardar(ActionEvent evt) {
        usuarioFacade.create(usuario);
            usuario = new Usuario();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        
    }
    public String startEdit() {
        return "usuario_lstar?faces-redirect=true";
    }
    
    public void edit(javafx.event.ActionEvent event) {
        usuarioFacade.edit(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
            usuario = null;

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("usuario_lstar.jsf");
            } catch (IOException ex) {
                Logger.getLogger(UsuarioMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

    
    
    public String delete() {
         usuarioFacade.remove(usuario);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
             usuario = null;
             return "usuario_lstar?faces-redirect=true";
       
    }
    
    

}
