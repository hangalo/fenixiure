/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.ejbs.AcessoSistemaFacade;
import fenix.iure.entities.AcessoSistema;
import fenix.iure.filters.Util;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;



@Named(value = "loginMB")
@SessionScoped
public class LoginMB implements Serializable {

    private String nomeUsuario;
    private String password;

    @EJB
    AcessoSistemaFacade acessoSistemaFacade;

    private AcessoSistema usuarioAutenticado;

    public AcessoSistema getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(AcessoSistema usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }

    public LoginMB() {
    }

    public String autenticar() {
        FacesContext context = FacesContext.getCurrentInstance();
        usuarioAutenticado = acessoSistemaFacade.encontrarUsuarioLogin(nomeUsuario);
        if (usuarioAutenticado != null) {
            context.getExternalContext().getSessionMap().put("login", nomeUsuario);
            if (usuarioAutenticado.getPasswordJuiz().equals(password)) {
                HttpSession hs = Util.getSession();
                hs.setAttribute("login", nomeUsuario);
                try {
                    context.getExternalContext().redirect("/fenixiure/area_administrativa_1.jsf");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Senha Inexistente! Tente Novamente.", "A Senha não Existe"));
            return null;
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Usuário Inexistente! Tente Novamente.", "O Usuário não Existe"));
        return null;
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("/fenixiure/login.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
