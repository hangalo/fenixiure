/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.dao.TipoPessoaDAO;
import fenix.iure.ejbs.TipoPessoaFacade;
import fenix.iure.entities.TipoPessoa;
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
@Named(value = "tipoPessoaMBean")
@SessionScoped
public class TipoPessoaMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private TipoPessoa tipoPessoa;
    private TipoPessoaDAO tipoPessoaDAO;
    private List<TipoPessoa> tipoPessoas;

    @Inject
    TipoPessoaFacade tipoPessoaFacade;

    public TipoPessoaMBean() {
    }

    @PostConstruct
    public void inicializar() {
        tipoPessoa = new TipoPessoa();
        tipoPessoaDAO = new TipoPessoaDAO();

    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public List<TipoPessoa> getTipoPessoas() {
        tipoPessoas = tipoPessoaFacade.findAll();
        return tipoPessoas;
    }

    public String newSave() {
        tipoPessoa = new TipoPessoa();
        return "tipo_pessoa_listar?faces-redirect=true";
    }

    public void guardar(ActionEvent evt) {
        tipoPessoaFacade.create(tipoPessoa);
        tipoPessoa = new TipoPessoa();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        /*} else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }*/
    }

    public String startEdit() {
        return "tipo_pessoa_listar?faces-redirect=true";
    }

    public void edit(javafx.event.ActionEvent event) {
        tipoPessoaFacade.edit(tipoPessoa);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
            tipoPessoas = null;

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("tipo_pessoa_listar.jsf");
            } catch (IOException ex) {
                Logger.getLogger(TipoDecisaoMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        /*} else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar\t", "\tErro ao editar dados"));
        }*/

    }

    public String delete() {
           tipoPessoaFacade.remove(tipoPessoa);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
            tipoPessoas = null;
            return "tipo_pessoa_listar?faces-redirect=true";
        /*} else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tErro ao eliminar dados!"));
            return null;
        }*/
    }

}
