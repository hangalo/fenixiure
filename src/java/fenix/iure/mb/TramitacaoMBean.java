/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.ejbs.EspecieProcessoFacade;
import fenix.iure.ejbs.EstadoProcessoFacade;
import fenix.iure.ejbs.ProcessoFacade;
import fenix.iure.ejbs.TipoDecisaoFacade;
import fenix.iure.ejbs.TramitacaoFacade;
import fenix.iure.entities.EspecieProcesso;
import fenix.iure.entities.EstadoProcesso;
import fenix.iure.entities.Processo;
import fenix.iure.entities.TipoDecisao;
import fenix.iure.entities.Tramitacao;
import fenix.iure.util.GestorImpressao;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Named(value = "tramitacaoMBean")
@SessionScoped
public class TramitacaoMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Tramitacao tramitacao;
    private List<Tramitacao> tramitacoes;

    // Listas para carregar as comboboxs
    private List<Processo> processos;
    private List<TipoDecisao> decisoes;
    private List<EstadoProcesso> estados;
    private List<EspecieProcesso> especies;

    // Variavel para pesquisas parametrisadas.
    private int idProcesso;
    private int idEstado;
    private int idEspecie;
    private int idDecisao = 0;
    private Date dataInicio, dataFim;

    // Listas das pesquisas paramentrizadas
    private List<Tramitacao> findByProcesso;
    private List<Tramitacao> findByEstadoIntervaloDatas;
    private List<Tramitacao> findByEspecieIntervaloDatas;
    private List<Tramitacao> findByEspecieEstadoIntervaloDatas;
    private List<Tramitacao> findByDecisaoEstadoIntervaloDatas;
    private List<Tramitacao> FindByProcessosFindos;
    private List<Tramitacao> FindByProcessosFindosIntervaloTempo;
    private List<Tramitacao> FindByProcessosFindosDecisaoIntervaloTempo;
    private List<Tramitacao> resultados;
    private List<Tramitacao> FindByProcessoFindoEstado;

    //  Ingeçao so EntitiesManager
    @Inject
    TramitacaoFacade tramitacaoFacade;
    @Inject
    ProcessoFacade processoFacade;
    @Inject
    TipoDecisaoFacade tipoDecisaoFacade;
    @Inject
    EstadoProcessoFacade estadoProcessoFacade;
    @Inject
    EspecieProcessoFacade especieProcessoFacade;

    public TramitacaoMBean() {
    }

    @ManagedProperty(value = "#{gestorImpressao}")
    private GestorImpressao gestorImpressao;

    @PostConstruct
    public void inicializar() {
        tramitacao = new Tramitacao();
        tramitacoes = new ArrayList<>();
        processos = new ArrayList<>();
        decisoes = new ArrayList<>();
        estados = new ArrayList<>();
        findByProcesso = new ArrayList<>();
        findByEstadoIntervaloDatas = new ArrayList<>();

    }

    public Tramitacao getTramitacao() {
        return tramitacao;
    }

    public void setTramitacao(Tramitacao tramitacao) {
        this.tramitacao = tramitacao;
    }

    public List<Tramitacao> getTramitacoes() {
        tramitacoes = tramitacaoFacade.findAll();
        return tramitacoes;
    }

    public List<Processo> getProcessos() {
        processos = processoFacade.findAll();
        return processos;
    }

    public List<TipoDecisao> getDecisoes() {
        decisoes = tipoDecisaoFacade.findAll();
        return decisoes;
    }

    public List<EstadoProcesso> getEstados() {
        estados = estadoProcessoFacade.findAll();
        return estados;
    }
//retornar boolean...

    /* public void guardar(ActionEvent evt) {
        if (tramitacao != null) {
            tramitacaoFacade.create(tramitacao);
            tramitacao = new Tramitacao();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }
    }*/

    public String guardar(ActionEvent evt) {
        String controlo = null;
        if (tramitacao != null) {
            tramitacaoFacade.create(tramitacao);
            tramitacao = new Tramitacao();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
            return controlo;

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
            return controlo;
        }
    }

    public String startEdit() {
        return "tramitacao_lstar?faces-redirect=true";
    }

    public void edit(javafx.event.ActionEvent event) {
        tramitacaoFacade.edit(tramitacao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar:\t", "\tDado alterado com sucesso"));
        tramitacoes = null;

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("tramitacao_lstar.jsf");
        } catch (IOException ex) {
            Logger.getLogger(ProcessoMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String delete() {
        tramitacaoFacade.remove(tramitacao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
        tramitacoes = null;
        return "tramitacao_lstar?faces-redirect=true";
    }

    public int getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(int idProcesso) {
        this.idProcesso = idProcesso;
    }

    public List<Tramitacao> getFindByProcesso() {
        findByProcesso = tramitacaoFacade.findByIdProcesso(idProcesso);
        return findByProcesso;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public List<Tramitacao> getFindByEstadoIntervaloDatas() {
        findByEstadoIntervaloDatas = tramitacaoFacade.findByIdEstadoDatas(idEstado, dataInicio, dataFim);
        return findByEstadoIntervaloDatas;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getIdDecisao() {
        return idDecisao;
    }

    public void setIdDecisao(int idDecisao) {
        this.idDecisao = idDecisao;
    }

    public GestorImpressao getGestorImpressao() {
        return gestorImpressao;
    }

    public void setGestorImpressao(GestorImpressao gestorImpressao) {
        this.gestorImpressao = gestorImpressao;
    }

    public List<Tramitacao> getFindByEspecieIntervaloDatas() {
        findByEspecieIntervaloDatas = tramitacaoFacade.findByIdEspecieDatas(idEspecie, dataInicio, dataFim);
        return findByEspecieIntervaloDatas;
    }

    public List<EspecieProcesso> getEspecies() {
        especies = especieProcessoFacade.findAll();
        return especies;
    }

    public List<Tramitacao> getFindByEspecieEstadoIntervaloDatas() {
        findByEspecieEstadoIntervaloDatas = tramitacaoFacade.findByIdEspecieEstadoDatas(idEspecie, idEstado, dataInicio, dataFim);
        return findByEspecieEstadoIntervaloDatas;
    }

    public List<Tramitacao> getFindByDecisaoEstadoIntervaloDatas() {
        findByDecisaoEstadoIntervaloDatas = tramitacaoFacade.findByIdDecisaoEstadoDatas(idEstado, idDecisao, dataInicio, dataFim);
        return findByDecisaoEstadoIntervaloDatas;
    }

    public List<Tramitacao> getFindByProcessosFindos() {
        FindByProcessosFindos = tramitacaoFacade.findByIdProcessoFindos();
        return FindByProcessosFindos;
    }

    public List<Tramitacao> getFindByProcessosFindosIntervaloTempo() {
        FindByProcessosFindosIntervaloTempo = tramitacaoFacade.findByIdProcessoFindosIntervaloTempo(dataInicio, dataFim);
        return FindByProcessosFindosIntervaloTempo;
    }

    public List<Tramitacao> getFindByProcessosFindosDecisaoIntervaloTempo() {
        FindByProcessosFindosDecisaoIntervaloTempo = tramitacaoFacade.findByIdProcessoFindosDecisaoIntervaloTempo(idDecisao, dataInicio, dataFim);
        return FindByProcessosFindosDecisaoIntervaloTempo;
    }

    public String imprimirProcessos() {
        String relatorio = "artigo_lista.jasper";
        HashMap parametros = new HashMap();
        gestorImpressao = new GestorImpressao(); // Analisar essa instrução. 
        gestorImpressao.imprimirPDF(relatorio, parametros);
        return null;

    }

    /*public String imprimirProfessoresPorDepartamento(){
        Departamento dep = professorDepartamento.getDepartamento();
        String relatorio = "professores_por_departamento.jasper";
        HashMap parametros = new HashMap();
        parametros.put("departamento", dep.getNomeDepartamento());
        gestorImpressao.imprimirPDF(relatorio, parametros);

        return null;

    }*/
    public List<Tramitacao> getResultados() {
        resultados = tramitacaoFacade.findByIdProcessoFindos();
        if (idDecisao != 0 && dataInicio != null && dataFim != null) {
            resultados = tramitacaoFacade.findByIdProcessoFindosDecisaoIntervaloTempo(idDecisao, dataInicio, dataFim);
        } else if (idDecisao == 0 && dataInicio != null && dataFim != null) {
            resultados = tramitacaoFacade.findByIdProcessoFindosIntervaloTempo(dataInicio, dataFim);
        }
        return resultados;
    }

    public List<Tramitacao> getFindByProcessoFindoEstado() {
        idEstado = 3;
        FindByProcessoFindoEstado = tramitacaoFacade.findByIdProcessoEstado(idEstado);
        return FindByProcessoFindoEstado;
    }
    
    

}