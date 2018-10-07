/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.dao.MunicipioDAO;
import fenix.iure.dao.RequerenteDAO;
import fenix.iure.dao.TipoPessoaDAO;
import fenix.iure.modelo.Municipio;
import fenix.iure.modelo.Requerente;
import fenix.iure.modelo.TipoPessoa;
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

/**
 *
 * @author Aisha Lubadika
 */
@Named(value = "requerenteMBean")
@Dependent
public class RequerenteMBean {

    private static final long serialVersionUID = 1L;

    private Requerente requerente;
    private RequerenteDAO requerenteDAO;
    private TipoPessoaDAO tipoPessoaDAO;
    private MunicipioDAO municipioDAO;
    private List<Requerente> requerentes;
    private List<TipoPessoa> tipoPessoas;
    private List<Municipio> municipios;

    public RequerenteMBean() {
    }

    @PostConstruct
    public void inicializar() {
        requerente = new Requerente();
        requerenteDAO = new RequerenteDAO();
        municipioDAO = new MunicipioDAO();
        tipoPessoaDAO = new TipoPessoaDAO();

    }

    public Requerente getRequerente() {
        return requerente;
    }

    public void setRequerente(Requerente requerente) {
        this.requerente = requerente;
    }

    public RequerenteDAO getRequerenteDAO() {
        return requerenteDAO;
    }

    public void setRequerenteDAO(RequerenteDAO requerenteDAO) {
        this.requerenteDAO = requerenteDAO;
    }

    public void guardar(ActionEvent evt) {
        if (requerenteDAO.save(requerente)) {
            requerente = new Requerente();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }
    }

    public List<Requerente> getRequerentes() {
        requerentes = requerenteDAO.findAll();
        return requerentes;
    }

    public String startEdit() {
        return "requerente_listar?faces-redirect=true";
    }

    
    
       public void edit(javafx.event.ActionEvent event) {
        if (requerenteDAO.update(requerente)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
            requerentes = null;

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("requerente_listar.jsf");
            } catch (IOException ex) {
                Logger.getLogger(RequerenteMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar\t", "\tErro ao editar dados"));
        }

    }
    
    public String delete() {
        if (requerenteDAO.delete(requerente)) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
             requerentes = null;
             return "requerente_listar?faces-redirect=true";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tErro ao eliminar dados!"));
            return null;
        }      
    }

    public List<TipoPessoa> getTipoPessoas() {
        tipoPessoas = tipoPessoaDAO.findAll();
        return tipoPessoas;
    }

    public List<Municipio> getMunicipios() {
        municipios = municipioDAO.findAll();
                return municipios;
    }
    
    

}
