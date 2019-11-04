/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;


import fenix.iure.ejbs.JuizFacade;
import fenix.iure.entities.Juiz;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Named(value = "juizMBean")
@SessionScoped
public class JuizMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public JuizMBean() {
    }

    private Juiz juiz;
    private List<Juiz> juizes;

    private List<Juiz> findByNome;
    private List<Juiz> findbySobrenome;
    private List<Juiz> findByNomeSobrenome;
    private List<Juiz> findByDataNascimento;
    private List<Juiz> findByDataInicioFuncoes;

    // Variaveis para pesquisas paramentrizadas
    private String nome;
    private String sobrenome;
    private String controleEditar = "listartodos"; // Controla as paginas de edição
    private Date dataDeNascimento;
    private Date dataInicioFuncoes;

    @Inject
    JuizFacade juizFacade;
    
    @PostConstruct
    public void inicializar() {
        juiz = new Juiz();
         findByNome = new ArrayList<>();
        findbySobrenome = new ArrayList<>();
        findByNomeSobrenome = new ArrayList<>();
        findByDataNascimento = new ArrayList<>();
        findByDataInicioFuncoes = new ArrayList<>();
    }

    public Juiz getJuiz() {
        return juiz;
    }

    public void setJuiz(Juiz juiz) {
        this.juiz = juiz;
    }

    public List<Juiz> getJuizes() {
        juizes = juizFacade.findAll();
        return juizes;
    }

    public String newSave() {
        juiz = new Juiz();
        return "juizes?faces-redirect=true";
    }

    public void guardar(ActionEvent evt) {
        juizFacade.create(juiz);
            juiz = new Juiz();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        /*} else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }*/
    }

    public String startEdit() {
        return "juizes?faces-redirect=true";
    }

    public void edit(javafx.event.ActionEvent event) {
        juizFacade.edit(juiz);
        juiz = new Juiz();
            if (controleEditar.equalsIgnoreCase("listartodos")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
                juizes = null;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("juizes.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(JuizMBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (controleEditar.equalsIgnoreCase("byNome")) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
                juizes = null;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("juiz_by_nome.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(JuizMBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (controleEditar.equalsIgnoreCase("bySobrenome")) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
                juizes = null;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("juiz_by_sobrenome.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(JuizMBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (controleEditar.equalsIgnoreCase("byDataNascimento")) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
                juizes = null;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("juiz_by_data_nascimento.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(JuizMBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (controleEditar.equalsIgnoreCase("byDataInicioFuncoes")) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
                juizes = null;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("juiz_by_data_inicio.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(JuizMBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (controleEditar.equalsIgnoreCase("byNomeSobrenome")) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
                juizes = null;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("juiz_by_nome_sobrenome.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(JuizMBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar\t", "\tErro ao editar dados"));
            }

        }

    

    public String delete() {
        juizFacade.remove(juiz);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
            juizes = null;
            return "juizes?faces-redirect=true";
        /*} else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tErro ao eliminar dados!"));
            return null;
        }*/
    }

    public List<Juiz> getByNome() {
        findByNome = juizFacade.findByNome(nome);
        controleEditar = "byNome";
        return findByNome;
    }

    public List<Juiz> getBySobrenome() {
        findbySobrenome = juizFacade.findBySobrenome(sobrenome);
        controleEditar = "bySobrenome";

        return findbySobrenome;
    }

    public List<Juiz> getByNomeSobrenome() {
        findByNomeSobrenome = juizFacade.findByNomeSobrenome(nome, sobrenome);
        controleEditar = "byNomeSobrenome"; // controlo

        if ((getNome() == null || getNome().isEmpty()) && (getSobrenome() == null)) {
            return null;

        } else if (((getSobrenome() == null) || (getSobrenome().isEmpty())) && ((getNome() != null || !getNome().isEmpty()))) {
            findByNome = juizFacade.findByNome(nome);
            return findByNome;
        } else if ((getNome() == null || getNome().isEmpty() && getSobrenome() != null)) {
            findbySobrenome = juizFacade.findBySobrenome(sobrenome);
            return findbySobrenome;
        } else if ((getNome() != null || !getNome().isEmpty()) && (getSobrenome() != null || !getSobrenome().isEmpty())) {
            findByNomeSobrenome = juizFacade.findByNomeSobrenome(nome, sobrenome);
            return findByNomeSobrenome;
        }
        return null;

    }
    
    public List<Juiz> getByDataNascimento() {
        findByDataNascimento = juizFacade.findByDataNascimento(dataDeNascimento);
        controleEditar = "byDataNascimento";
        return findByDataNascimento;
    }
    
    public List<Juiz> getByDataInicioFuncoes() {
        findByDataInicioFuncoes = juizFacade.findByDataInicioFuncao(dataInicioFuncoes);
        controleEditar = "byDataInicioFuncoes";
        return findByDataInicioFuncoes;
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

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataInicioFuncoes(Date dataInicioFuncoes) {
        this.dataInicioFuncoes = dataInicioFuncoes;
    }

    public Date getDataInicioFuncoes() {
        return dataInicioFuncoes;
    }
    
    

}
