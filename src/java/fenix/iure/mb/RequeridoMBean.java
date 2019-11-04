/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.ejbs.AdvogadoFacade;
import fenix.iure.ejbs.MunicipioFacade;
import fenix.iure.ejbs.RequeridoFacade;
import fenix.iure.ejbs.TipoPessoaFacade;
import fenix.iure.entities.Advogado;
import fenix.iure.entities.Municipio;
import fenix.iure.entities.Requente;
import fenix.iure.entities.Requerido;
import fenix.iure.entities.TipoPessoa;
import fenix.iure.util.GestorImpressao;
import fenix.iure.util.JSFUtil;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
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
    private List<Requerido> requeridos;
    private List<TipoPessoa> tipoPessoas;
    private List<Municipio> municipios;
    private List<Advogado> advogados;

    @Inject
    MunicipioFacade municipioFacade;
    @Inject
    TipoPessoaFacade tipoPessoaFacade;
    @Inject
    RequeridoFacade requeridoFacade;
    @Inject
    AdvogadoFacade advogadoFacade;

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

    public RequeridoMBean() {
    }
    
    // Variaveis para os relatorios
    @ManagedProperty(value = "#{gestorImpressao}")
    private GestorImpressao gestorImpressao;

    @PostConstruct
    public void inicializar() {
        requerido = new Requerido();
        requeridos = requeridoFacade.findAll();

    }

    

    public void guardar(ActionEvent evt) {
        requeridoFacade.create(requerido);
        requerido = new Requerido();
        //FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados");
       // FacesContext.getCurrentInstance().addMessage(null, msg);
        JSFUtil.refresh();
    }

    
    public String startEdit() {
        return "requeridos?faces-redirect=true";
    }

    public void edit(javafx.event.ActionEvent event) {
        requeridoFacade.edit(requerido);
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
        requeridos = null;
        requerido = new Requerido();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("requeridos.jsf");
        } catch (IOException ex) {
            Logger.getLogger(RequeridoMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JSFUtil.refresh();
    }

    

    public String delete() {
        requeridoFacade.remove(requerido);
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
        requeridos = null;
        return "requeridos?faces-redirect=true";
       
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

    public List<Advogado> getAdvogados() {
        advogados = advogadoFacade.findAll();
        return advogados;
    }
    
     public String imprimirFichaReu(String parametro) {
        String relatorio = "reu_ficha_horizontal.jasper";
        HashMap parametros = new HashMap();
        parametros.put("idReu", parametro);
        gestorImpressao = new GestorImpressao();
        gestorImpressao.imprimirPDF(relatorio, parametros);

        return null;

    }

    public GestorImpressao getGestorImpressao() {
        return gestorImpressao;
    }

    public void setGestorImpressao(GestorImpressao gestorImpressao) {
        this.gestorImpressao = gestorImpressao;
    }

    public Requerido getRequerido() {
        return requerido;
    }

    public void setRequerido(Requerido requerido) {
        this.requerido = requerido;
    }

    public List<Requerido> getRequeridos() {
        requeridos = requeridoFacade.findAll();
        return requeridos;
    }

}
