/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.ejbs.UsuarioFacade;
import fenix.iure.entities.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Named(value = "loginMB")
@RequestScoped
public class LoginMB {

    private String username;
    private String password;

    private Usuario usuario = new Usuario();
    private List<Usuario> usuarios = new ArrayList<>();
    @Inject
    UsuarioFacade usuarioFacade;

    public LoginMB() {
    }

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        usuarios = usuarioFacade.findAll();
        for (Usuario usuario1 : usuarios) {
            if (usuario1.getLoginUsuario().equals(username) && usuario1.getSenhaUsuario().equals(password)) {
                context.getExternalContext().getSessionMap().put("user", username);
                try {
                    context.getExternalContext().redirect("/fenixiure/area_administrativa.jsf");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                FacesMessage mensagem = new FacesMessage(
                        "Login Inválido!");
                mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        }
    }


    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("/fenixiure/index.jsf");
        } catch (IOException e) {
            System.out.println("" + e.getMessage());
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
