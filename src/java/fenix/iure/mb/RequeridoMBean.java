/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.dao.MunicipioDAO;
import fenix.iure.dao.RequeridoDAO;
import fenix.iure.dao.TipoPessoaDAO;
import fenix.iure.ejbs.MunicipioFacade;
import fenix.iure.ejbs.RequeridoFacade;
import fenix.iure.ejbs.TipoPessoaFacade;
import fenix.iure.entities.Municipio;
import fenix.iure.entities.Requerido;
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
    
    
    @Inject
    RequeridoFacade requeridoFacade;
    @Inject
    TipoPessoaFacade tipoPessoaFacade;
    @Inject
    MunicipioFacade municipioFacade;
    
    // Listas das pesquisas paramentrizadas
    private List<Requerido> findByNome;
    private List<Requerido> findbySobrenome;
    private List<Requerido> findByNomeSobrenome;
    private List<Requerido> findByBilheteIdentidade;
    private List<Requerido> findByMunicipio;
    private List<Requerido> findByTipoPessoa;
    
    // Variaveis para pesquisas paramentrizadas
    private String nome;
    private String sobrenome;
    private String bilheteIdentidade;
    private int idMunicipio;
    private int idTipoPessoa;
    
    
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

    
    
      public void guardar(ActionEvent evt) {
        requeridoFacade.create(requerido);
            requerido = new Requerido();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        
    }

    public List<Requerido> getRequeridos() {
        requeridos = requeridoFacade.findAll();
        return requeridos;
    }
      
       public String startEdit() {
        return "requerido_listar?faces-redirect=true";
    }

    
    
       public void edit(javafx.event.ActionEvent event) {
        requeridoFacade.edit(requerido);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
            requerido = null;

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("requerido_listar.jsf");
            } catch (IOException ex) {
                Logger.getLogger(RequeridoMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         

    
    
    public String delete() {
        requeridoFacade.remove(requerido);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
             requeridos = null;
             return "requerido_listar?faces-redirect=true";
        }     
    

    public List<TipoPessoa> getTipoPessoas() {
        tipoPessoas = tipoPessoaFacade.findAll();
        return tipoPessoas;
    }

    public List<Municipio> getMunicipios() {
        municipios = municipioFacade.findAll();
                return municipios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getBilheteIdentidade() {
        return bilheteIdentidade;
    }

    public void setBilheteIdentidade(String bilheteIdentidade) {
        this.bilheteIdentidade = bilheteIdentidade;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public int getIdTipoPessoa() {
        return idTipoPessoa;
    }

    public void setIdTipoPessoa(int idTipoPessoa) {
        this.idTipoPessoa = idTipoPessoa;
    }

    public List<Requerido> getFindByNome() {
        findByNome = requeridoFacade.findByNome(nome);
        return findByNome;
    }

    public List<Requerido> getFindbySobrenome() {
        findbySobrenome = requeridoFacade.findBySobrenome(sobrenome);
        return findbySobrenome;
    }

    public List<Requerido> getFindByNomeSobrenome() {
        if ((getNome() == null || getNome().isEmpty()) && (getSobrenome() == null)) {
            return null;

        } else if (((getSobrenome() == null) || (getSobrenome().isEmpty())) && ((getNome() != null || !getNome().isEmpty()))) {
            findByNome = requeridoFacade.findByNome(nome);
            return findByNome;
        } else if ((getNome() == null || getNome().isEmpty() && getSobrenome() != null)) {
            findbySobrenome = requeridoFacade.findBySobrenome(sobrenome);
            return findbySobrenome;
        } else if ((getNome() != null || !getNome().isEmpty()) && (getSobrenome() != null || !getSobrenome().isEmpty())) {
            findByNomeSobrenome = requeridoFacade.findByNomeSobrenome(nome, sobrenome);
            return findByNomeSobrenome;
        }
        return null;
    }

    public List<Requerido> getFindByBilheteIdentidade() {
        findByBilheteIdentidade = requeridoFacade.findByBilheteIdentidade(bilheteIdentidade);
        return findByBilheteIdentidade;
    }

    public List<Requerido> getFindByMunicipio() {
        return findByMunicipio;
    }

    public List<Requerido> getFindByTipoPessoa() {
        return findByTipoPessoa;
    }
    
    
    

}
