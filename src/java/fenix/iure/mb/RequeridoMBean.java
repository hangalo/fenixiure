/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.dao.MunicipioDAO;
import fenix.iure.dao.RequeridoDAO;
import fenix.iure.dao.TipoPessoaDAO;
import fenix.iure.modelo.Municipio;
import fenix.iure.modelo.Requerido;
import fenix.iure.modelo.TipoPessoa;
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
 * @author Aisha Lubadika
 */
@Named(value = "requeridoMBean")
@SessionScoped
public class RequeridoMBean implements Serializable {

   private static final long serialVersionUID = 1L;

    private Requerido requerido;
    private RequeridoDAO requeridoDAO;
    private TipoPessoaDAO tipoPessoaDAO;
    private MunicipioDAO municipioDAO;
    private List<Requerido> requeridos;
    private List<TipoPessoa> tipoPessoas;
    private List<Municipio> municipios;
    
    public RequeridoMBean() {
    }
    
       @PostConstruct
    public void inicializar() {
        requerido = new Requerido();
        requeridoDAO = new RequeridoDAO();
        municipioDAO = new MunicipioDAO();
        tipoPessoaDAO = new TipoPessoaDAO();

    }

    public Requerido getRequerido() {
        return requerido;
    }

    public void setRequerido(Requerido requerido) {
        this.requerido = requerido;
    }

    public RequeridoDAO getRequeridoDAO() {
        return requeridoDAO;
    }

    public void setRequeridoDAO(RequeridoDAO requeridoDAO) {
        this.requeridoDAO = requeridoDAO;
    }
    
      public void guardar(ActionEvent evt) {
        if (requeridoDAO.save(requerido)) {
            requerido = new Requerido();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }
    }

    public List<Requerido> getRequeridos() {
        requeridos = requeridoDAO.findAll();
        return requeridos;
    }
      
       public String startEdit() {
        return "requerente_listar?faces-redirect=true";
    }

    
    
       public void edit(javafx.event.ActionEvent event) {
        if (requeridoDAO.update(requerido)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
            requerido = null;

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("requeridos_listar.jsf");
            } catch (IOException ex) {
                Logger.getLogger(RequeridoMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar\t", "\tErro ao editar dados"));
        }

    }
    
    public String delete() {
        if (requeridoDAO.delete(requerido)) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
             requeridos = null;
             return "requeridos_listar?faces-redirect=true";
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
