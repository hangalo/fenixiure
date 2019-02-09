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
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Aisha Lubadika
 */
@Named(value = "requerenteMBean")
@SessionScoped
public class RequerenteMBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private Requente requerente;
    private List<Requente> requerentes;
    private List<TipoPessoa> tipoPessoas;
    private List<Municipio> municipios;

    
    @Inject
    MunicipioFacade municipioFacade;
    @Inject
    TipoPessoaFacade tipoPessoaFacade;
    @Inject
    RequenteFacade requenteFacade;
    
  
    // Listas das pesquisas paramentrizadas
    private List<Requente> findByNome;
    private List<Requente> findbySobrenome;
    private List<Requente> findByNomeSobrenome;
    private List<Requente> findByBilheteIdentidade;
    private List<Requente> findByMunicipio;
    private List<Requente> findByTipoDecisao;
    
    // Variaveis para pesquisas paramentrizadas
    private String nome;
    private String sobrenome;
    private String bilheteIdentidade;
    private int idMunicipio;
    private int idTipoPessoa;
    
    
    public RequerenteMBean() {
    }

    @PostConstruct
    public void inicializar() {
        requerente = new Requente();
        

    }

    public Requente getRequerente() {
        return requerente;
    }

    public void setRequerente(Requente requerente) {
        this.requerente = requerente;
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

    public List<Requente> getFindByNome() {
        findByNome = requenteFacade.findByNome(nome);
        return findByNome;
    }

    public List<Requente> getFindbySobrenome() {
        findbySobrenome = requenteFacade.findBySobrenome(sobrenome);
        return findbySobrenome;
    }

    public List<Requente> getFindByNomeSobrenome() {
        if ((getNome() == null || getNome().isEmpty()) && (getSobrenome() == null)) {
            return null;

        } else if (((getSobrenome() == null) || (getSobrenome().isEmpty())) && ((getNome() != null || !getNome().isEmpty()))) {
            findByNome = requenteFacade.findByNome(nome);
            return findByNome;
        } else if ((getNome() == null || getNome().isEmpty() && getSobrenome() != null)) {
            findbySobrenome = requenteFacade.findBySobrenome(sobrenome);
            return findbySobrenome;
        } else if ((getNome() != null || !getNome().isEmpty()) && (getSobrenome() != null || !getSobrenome().isEmpty())) {
            findByNomeSobrenome = requenteFacade.findByNomeSobrenome(nome, sobrenome);
            return findByNomeSobrenome;
        }
        return null;
    }

    public List<Requente> getFindByBilheteIdentidade() {
        findByBilheteIdentidade = requenteFacade.findByBilheteIdentidade(bilheteIdentidade);
        return findByBilheteIdentidade;
    }

    
    public List<Requente> getFindByTipoPessoa() {
        findByTipoDecisao = requenteFacade.findByIdTipoPessoa(idTipoPessoa);
        return findByTipoDecisao;
    }

    public List<Requente> getFindByMunicipio() {
        findByMunicipio = requenteFacade.findByIdMunicipio(idMunicipio);
        return findByMunicipio;
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

    

    
    
    
    
    
    
    

}
