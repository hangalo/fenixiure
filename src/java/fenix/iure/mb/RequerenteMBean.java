/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.dao.MunicipioDAO;
import fenix.iure.dao.RequerenteDAO;
import fenix.iure.dao.TipoPessoaDAO;
import fenix.iure.ejbs.MunicipioFacade;
import fenix.iure.ejbs.RequenteFacade;
import fenix.iure.ejbs.TipoPessoaFacade;
import fenix.iure.entities.Municipio;
import fenix.iure.entities.Requente;
import fenix.iure.entities.TipoPessoa;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Aisha Lubadika
 */
@Named(value = "requerenteMBean")
@Dependent
public class RequerenteMBean {

    private static final long serialVersionUID = 1L;

    private Requente requerente;
    private RequerenteDAO requerenteDAO;
    private TipoPessoaDAO tipoPessoaDAO;
    private MunicipioDAO municipioDAO;
    private List<Requente> requerentes;
    private List<TipoPessoa> tipoPessoas;
    private List<Municipio> municipios;

    @Inject
    RequenteFacade requenteFacade;
    @Inject
    MunicipioFacade municipioFacade;
    @Inject
    TipoPessoaFacade tipoPessoaFacade;
    
    public RequerenteMBean() {
    }

    @PostConstruct
    public void inicializar() {
        requerente = new Requente();
        requerenteDAO = new RequerenteDAO();
        municipioDAO = new MunicipioDAO();
        tipoPessoaDAO = new TipoPessoaDAO();

    }

    public Requente getRequerente() {
        return requerente;
    }

    public void setRequerente(Requente requerente) {
        this.requerente = requerente;
    }

    public RequerenteDAO getRequerenteDAO() {
        return requerenteDAO;
    }

    public void setRequerenteDAO(RequerenteDAO requerenteDAO) {
        this.requerenteDAO = requerenteDAO;
    }

    public void guardar(ActionEvent evt) {
        requenteFacade.create(requerente);
            requerente = new Requente();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        /*} else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }*/
    }

    public List<Requente> getRequerentes() {
        requerentes = requenteFacade.findAll();
        return requerentes;
    }

    public String startEdit() {
        return "requerente_listar?faces-redirect=true";
    }

    
    
       public void edit(javafx.event.ActionEvent event) {
        requenteFacade.edit(requerente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
            requerentes = null;

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("requerente_listar.jsf");
            } catch (IOException ex) {
                Logger.getLogger(RequerenteMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         /*else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar\t", "\tErro ao editar dados"));
        }*/

    
    
    public String delete() {
        requenteFacade.remove(requerente);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
             requerentes = null;
             return "requerente_listar?faces-redirect=true";
        /*}else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tErro ao eliminar dados!"));
            return null;
        }  */    
    }

    public List<TipoPessoa> getTipoPessoas() {
        tipoPessoas = tipoPessoaFacade.findAll();
        return tipoPessoas;
    }

    public List<Municipio> getMunicipios() {
        municipios = municipioFacade.findAll();
                return municipios;
    }
    
    

}
