/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.dao.JuizDAO;
import fenix.iure.modelo.Juiz;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
    private JuizDAO juizDAO;
    private List<Juiz> juizes;

    private List<Juiz> findByNome;
    private List<Juiz> findbySobrenome;
    private List<Juiz> findByNomeSobrenome;

    // Variaveis para pesquisas paramentrizadas
    private String nome;
    private String sobrenome;
    private String controleEditar = "listartodos"; // Controla as paginas de edição

    @PostConstruct
    public void inicializar() {
        juiz = new Juiz();
        juizDAO = new JuizDAO();

        findByNome = new ArrayList<>();
        findbySobrenome = new ArrayList<>();
        findByNomeSobrenome = new ArrayList<>();
    }

    public Juiz getJuiz() {
        return juiz;
    }

    public void setJuiz(Juiz juiz) {
        this.juiz = juiz;
    }

    public List<Juiz> getJuizes() {
        juizes = juizDAO.findAll();
        return juizes;
    }

    public String newSave() {
        juiz = new Juiz();
        return "juiz_listar?faces-redirect=true";
    }

    public void guardar(ActionEvent evt) {
        if (juizDAO.save(juiz)) {
            juiz = new Juiz();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }
    }

    public String startEdit() {
        return "juiz_listar?faces-redirect=true";
    }

    public void edit(javafx.event.ActionEvent event) {
        if (juizDAO.update(juiz)) {
            if (controleEditar.equalsIgnoreCase("listartodos")) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
                juizes = null;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("juiz_listar.jsf");
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

    }

    public String delete() {
        if (juizDAO.delete(juiz)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
            juizes = null;
            return "juiz_listar?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tErro ao eliminar dados!"));
            return null;
        }
    }

    public List<Juiz> getByNome() {
        findByNome = juizDAO.findByNome(nome);
        controleEditar = "byNome";
        return findByNome;
    }

    public List<Juiz> getBySobrenome() {
        findbySobrenome = juizDAO.findBySobrenome(sobrenome);
        controleEditar = "bySobrenome";

        return findbySobrenome;
    }

    public List<Juiz> getByNomeSobrenome() {
        findByNomeSobrenome = juizDAO.findByNomeSobrenome(nome, sobrenome);
        controleEditar = "byNomeSobrenome"; // controlo

        if ((getNome() == null || getNome().isEmpty()) && (getSobrenome() == null)) {
            return null;

        } else if (((getSobrenome() == null) || (getSobrenome().isEmpty())) && ((getNome() != null || !getNome().isEmpty()))) {
            findByNome = juizDAO.findByNome(nome);
            return findByNome;
        } else if ((getNome() == null || getNome().isEmpty() && getSobrenome() != null)) {
            findbySobrenome = juizDAO.findBySobrenome(sobrenome);
            return findbySobrenome;
        } else if ((getNome() != null || !getNome().isEmpty()) && (getSobrenome() != null || !getSobrenome().isEmpty())) {
            findByNomeSobrenome = juizDAO.findByNomeSobrenome(nome, sobrenome);
            return findByNomeSobrenome;
        }
        return null;

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

}
