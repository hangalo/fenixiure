/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.dao.AdvogadoDAO;
import fenix.iure.modelo.Advogado;
import fenix.iure.util.DateUtil;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Named(value = "advoadoMBean")
@SessionScoped
public class AdvoadoMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Advogado advogado;
    private AdvogadoDAO advogadoDAO;
    private List<Advogado> advogados;
    
    // Listas das pesquisas paramentrizadas
    private List<Advogado> findByNome;
    private List<Advogado> findbySobrenome;
    private List<Advogado> findByNomeSobrenome;
    private List<Advogado> findbyDatNascimento;
    private List<Advogado> findbyDataInicioFuncoes;
    private List<Advogado> findbyIntervaloDatNascimento;
    private List<Advogado> findbyIntervaloDataInicioFuncoes;
    
    
    // Variaveis para pesquisas paramentrizadas
    private String nome;
    private String sobrenome;
    private String dataDeNascimento;
    private String dataInicioFuncoes;
    private String dataInicioInicioFuncoes;
    private String dataFimInicioFuncoes;
    private String dataInicioDataNascimento;
    private String dataFimDataNascimento;
    private String controleEditar = "listartodos"; // Controla as paginas de edição

    public AdvoadoMBean() {
    }

    @PostConstruct
    public void inicializar() {
        advogado = new Advogado();
        advogadoDAO = new AdvogadoDAO();
        findByNome = new ArrayList<>();
        findbySobrenome = new ArrayList<>();
        findByNomeSobrenome = new ArrayList<>();
        findbyDatNascimento = new ArrayList<>();
        findbyDataInicioFuncoes = new ArrayList<>();
        findbyIntervaloDatNascimento = new ArrayList<>();
        findbyIntervaloDataInicioFuncoes = new ArrayList<>();
    }

    public Advogado getAdvogado() {
        return advogado;
    }

    public void setAdvogado(Advogado advogado) {
        this.advogado = advogado;
    }

    public List<Advogado> getAdvogados() {
        advogados = advogadoDAO.findAll();
        return advogados;
    }

    public String newSave() {
        advogado = new Advogado();
        return "advogado_listar?faces-redirect=true";
    }

    public void guardar(ActionEvent evt) {
        if (advogadoDAO.save(advogado)) {
            advogado = new Advogado();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }
    }

    public String startEdit() {
        return "advogado_listar?faces-redirect=true";
    }

    
    public void edit(javafx.event.ActionEvent event) {
        if (advogadoDAO.update(advogado)) {
            if (controleEditar.equalsIgnoreCase("listartodos")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
                advogados = null;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("advogado_listar.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(AdvoadoMBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (controleEditar.equalsIgnoreCase("byNome")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
                advogados = null;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("advogado_by_nome.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(AdvoadoMBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (controleEditar.equalsIgnoreCase("bySobrenome")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
                advogados = null;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("advogado_by_sobrenome.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(AdvoadoMBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (controleEditar.equalsIgnoreCase("byDataNascimento")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
                advogados = null;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("advogado_by_data_nascimento.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(AdvoadoMBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            else if (controleEditar.equalsIgnoreCase("byDataInicioFuncoes")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
                advogados = null;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("advogado_by_data_inicio.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(AdvoadoMBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (controleEditar.equalsIgnoreCase("byNomeSobrenome")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
                advogados = null;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("advogado_by_nome_sobrenome.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(AdvoadoMBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar\t", "\tErro ao editar dados"));
        }

    }

    public String delete() {
        if (advogadoDAO.delete(advogado)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
            advogados = null;
            return "advogado_listar?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tErro ao eliminar dados!"));
            return null;
        }
    }

    public AdvogadoDAO getAdvogadoDAO() {
        return advogadoDAO;
    }

    public void setAdvogadoDAO(AdvogadoDAO advogadoDAO) {
        this.advogadoDAO = advogadoDAO;
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

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getDataInicioFuncoes() {
        return dataInicioFuncoes;
    }

    public void setDataInicioFuncoes(String dataInicioFuncoes) {
        this.dataInicioFuncoes = dataInicioFuncoes;
    }

    public String getDataInicioInicioFuncoes() {
        return dataInicioInicioFuncoes;
    }

    public void setDataInicioInicioFuncoes(String dataInicioInicioFuncoes) {
        this.dataInicioInicioFuncoes = dataInicioInicioFuncoes;
    }

    public String getDataFimInicioFuncoes() {
        return dataFimInicioFuncoes;
    }

    public void setDataFimInicioFuncoes(String dataFimInicioFuncoes) {
        this.dataFimInicioFuncoes = dataFimInicioFuncoes;
    }

    public String getDataInicioDataNascimento() {
        return dataInicioDataNascimento;
    }

    public void setDataInicioDataNascimento(String dataInicioDataNascimento) {
        this.dataInicioDataNascimento = dataInicioDataNascimento;
    }

    public String getDataFimDataNascimento() {
        return dataFimDataNascimento;
    }

    public void setDataFimDataNascimento(String dataFimDataNascimento) {
        this.dataFimDataNascimento = dataFimDataNascimento;
    }

    public List<Advogado> getByNome() {
        findByNome = advogadoDAO.findByNome(nome);
        controleEditar = "byNome";
        return findByNome;
    }
    public List<Advogado> getBySobrenome() {
        findbySobrenome = advogadoDAO.findBySobrenome(sobrenome);
        controleEditar = "bySobrenome";
        return findbySobrenome;
    }
    public List<Advogado> getByDataNascimento() {
        findbyDatNascimento = advogadoDAO.findByDataNascimento(dataDeNascimento);
        controleEditar = "byDataNascimento";
        return findbyDatNascimento;
    }
    public List<Advogado> getByNomeSobrenome(){
        controleEditar = "byNomeSobrenome"; // controlo
        if ((getNome() == null || getNome().isEmpty()) && (getSobrenome() == null)) {
            return null;

        } else if (((getSobrenome() == null) || (getSobrenome().isEmpty())) && ((getNome() != null || !getNome().isEmpty()))) {
            findByNome = advogadoDAO.findByNome(nome);
            return findByNome;
        } else if ((getNome() == null || getNome().isEmpty() && getSobrenome() != null)) {
            findbySobrenome = advogadoDAO.findBySobrenome(sobrenome);
            return findbySobrenome;
        } else if ((getNome() != null || !getNome().isEmpty()) && (getSobrenome() != null || !getSobrenome().isEmpty())) {
            findByNomeSobrenome = advogadoDAO.findByNomeSobrenome(nome, sobrenome);
            return findByNomeSobrenome;
        }
        return null;
    }


}
