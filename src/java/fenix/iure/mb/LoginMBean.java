/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;


import fenix.iure.ejbs.FuncionarioFacade;
import fenix.iure.ejbs.JuizFacade;
import fenix.iure.ejbs.UsuarioFacade;
import fenix.iure.entities.Funcionario;
import fenix.iure.entities.Juiz;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Named(value = "loginMBean")
@SessionScoped
public class LoginMBean implements Serializable {

    public LoginMBean() {

    }
    private String usuario = "nada";
    private String senha = "nada";
    ;
    private String tipoUsuario = "nada";

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Inject
    UsuarioFacade usuarioFacade;
    @Inject
    FuncionarioFacade funcionarioFacade;
    @Inject
    JuizFacade juizFacade;

    private List<Juiz> juizes;
    private List<Funcionario> funcionarios;

    public void logar() throws IOException {
        juizes = juizFacade.findAll();
        funcionarios = funcionarioFacade.findAll();
        FacesContext context = FacesContext.getCurrentInstance();

        for (Juiz juize : juizes) {
            if (juize.getUsuarioJuiz().equals(usuario) && juize.getSenhaJuiz().equals(senha) && juize.getTipoUsuario().equals("J")) {
                context.getExternalContext().getSessionMap().put("user", usuario);
                try {
                    context.getExternalContext().redirect("/fenixiure/area_administrativa_1.jsf");
                } catch (Exception e) {
                }

            } else if (!(juize.getUsuarioJuiz().equals(usuario) && juize.getSenhaJuiz().equals(senha) && juize.getTipoUsuario().equals("J"))) {
                for (Funcionario funcionario : funcionarios) {
                    if (funcionario.getUsuarioFuncionario().equals(usuario) && funcionario.getSenhaFuncionario().equals(senha) && funcionario.getTipoUsuario().equals("F")) {
                        context.getExternalContext().getSessionMap().put("user", usuario);
                        try {
                            context.getExternalContext().redirect("/fenixiure/reservado/admin/processos.jsf");

                        } catch (Exception e) {
                        }

                    }

                }

            } else {
                FacesMessage mensagem = new FacesMessage("Usuário Inválido!");
                mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, mensagem);

            }

        }

    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("/fenixiure/login.jsf");
        } catch (IOException e) {
            System.out.println("" + e.getMessage());
        }
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
