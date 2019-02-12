/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Named(value = "loginMB")
@RequestScoped
public class LoginMB {

    private String username;
    private String password;
    

    public LoginMB() {
    }

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (this.username.equals("admin") && this.password.equals("admin")) {
            context.getExternalContext().getSessionMap().put("user", username);
            try {
                context.getExternalContext().redirect("area_administrativa.jsf");
            } catch (IOException e) {
                System.out.println("" + e.getMessage());
            }
        } else {
            FacesMessage mensagem = new FacesMessage(
                    "Login Inválido!");
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, mensagem);

        }
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("index.jsf");
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
