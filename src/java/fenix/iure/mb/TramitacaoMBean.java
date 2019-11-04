/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import fenix.iure.dao.TramitacaoDAO;
import fenix.iure.ejbs.EspecieProcessoFacade;
import fenix.iure.ejbs.EstadoProcessoFacade;
import fenix.iure.ejbs.ProcessoFacade;
import fenix.iure.ejbs.TipoDecisaoFacade;
import fenix.iure.ejbs.TramitacaoFacade;
import fenix.iure.entities.Advogado;
import fenix.iure.entities.EspecieProcesso;
import fenix.iure.entities.Estado;
import fenix.iure.entities.EstadoProcesso;
import fenix.iure.entities.Processo;
import fenix.iure.entities.TipoDecisao;
import fenix.iure.entities.Tramitacao;
import fenix.iure.util.DateUtil;
import fenix.iure.util.GestorImpressao;
import fenix.iure.util.JSFUtil;
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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.apache.jasper.tagplugins.jstl.If;

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
    private List<Processo> processosNaoFindos;
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

    // Lista para verificar processos findos por numero
    private List<Tramitacao> processosFindosPorNumero;

    // Variavel para a actualizacao do campo dataT+ermino via ajax
    private String tipoEstado;

    // Variavel para carregar o processo da tramitacao
    private Processo processo;
    private List<Tramitacao> tramitacaoDoProcesso;

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

    // Objecto auxiliar para as buscas com JDBC
    TramitacaoDAO tramitacaoDAO;

    public TramitacaoMBean() {
    }

    @ManagedProperty(value = "#{gestorImpressao}")
    private GestorImpressao gestorImpressao;

    @PostConstruct
    public void inicializar() {
        tramitacao = new Tramitacao();
        tramitacao.setDataTermino(DateUtil.getDataActual());
        tramitacao.setDataConclusaoTramitacao(DateUtil.getDataActual());
        
        tramitacao.setDataConclusaoTramitacao(DateUtil.getDataActual());
        tramitacoes = new ArrayList<>();
        processos = new ArrayList<>();
        decisoes = new ArrayList<>();
        estados = new ArrayList<>();
        findByProcesso = new ArrayList<>();
        findByEstadoIntervaloDatas = new ArrayList<>();
        processosFindosPorNumero = new ArrayList<>();
        tramitacaoDAO = new TramitacaoDAO();

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

    public void guardar(ActionEvent evt) {
        if (tramitacao != null) {
            tramitacaoFacade.create(tramitacao);
            tramitacao = new Tramitacao();
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));

        } else {
           // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
           
            JSFUtil.refresh();
        }
    }

    // Metodo para verificar processos findos
    public List<Tramitacao> getProcessosFindosPorNumero() {
        processosFindosPorNumero = tramitacaoFacade.findByIdProcessoFindosPorNumero(tramitacao);
        for (Tramitacao tramitacoe : processosFindosPorNumero) {
            System.out.println("Estado: " + tramitacoe.getEstadoProcesso());
        }
        return processosFindosPorNumero;
    }

    //Esse método não permite regitar tramaitações quando o processo ja se encontra no seu estado findo 
    /* public void guardar(ActionEvent evt) {
        if (tramitacao != null) {
            getProcessosFindosPorNumero();
            if (processosFindosPorNumero.size() > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tEste processo ja foi Findo!"));

            } else {
                tramitacaoFacade.create(tramitacao);
                tramitacao = new Tramitacao();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar\t", "\tErro ao guardar os dados"));
        }
    }

    public String startEdit() {
        return "tramitacao_lstar?faces-redirect=true";
    }
     */
    public void edit(javafx.event.ActionEvent event) {
        tramitacaoFacade.edit(tramitacao);
        //JSFUtil.adicionarMensagemDeSucesso("Sucesso ao alterar dados!");
        tramitacoes = null;
        tramitacao = new Tramitacao();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("tramitacao_lstar.jsf");
        } catch (IOException ex) {
            Logger.getLogger(ProcessoMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String delete() {
        tramitacaoFacade.remove(tramitacao);
        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar\t", "\tDados Eliminados com sucesso!"));
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
        //processo = processoFacade.findById(idProcesso);
        return findByProcesso;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public List<Tramitacao> getFindByEstadoIntervaloDatas() {
        //findByEstadoIntervaloDatas = tramitacaoFacade.findByIdEstadoDatas(idEstado, dataInicio, dataFim);
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
        // findByEspecieIntervaloDatas = tramitacaoFacade.findByIdEspecieDatas(idEspecie, dataInicio, dataFim);
        return findByEspecieIntervaloDatas;
    }

    public List<EspecieProcesso> getEspecies() {
        especies = especieProcessoFacade.findAll();
        return especies;
    }

    public List<Tramitacao> getFindByEspecieEstadoIntervaloDatas() {
        //findByEspecieEstadoIntervaloDatas = tramitacaoFacade.findByIdEspecieEstadoDatas(idEspecie, idEstado, dataInicio, dataFim);
        return findByEspecieEstadoIntervaloDatas;
    }

    public List<Tramitacao> getFindByDecisaoEstadoIntervaloDatas() {
        //findByDecisaoEstadoIntervaloDatas = tramitacaoFacade.findByIdDecisaoEstadoDatas(idEstado, idDecisao, dataInicio, dataFim);
        return findByDecisaoEstadoIntervaloDatas;
    }

    /*public List<Tramitacao> getFindByProcessosFindos() {
        FindByProcessosFindos = tramitacaoFacade.findByIdProcessoFindos();
        return FindByProcessosFindos;
    }*/
    public List<Tramitacao> getFindByProcessosFindos() {
        FindByProcessosFindos = tramitacaoFacade.findByIdProcessoFindos();
        return FindByProcessosFindos;
    }

    public List<Tramitacao> getFindByProcessosFindosIntervaloTempo() {
        //FindByProcessosFindosIntervaloTempo = tramitacaoFacade.findByIdProcessoFindosIntervaloTempo(dataInicio, dataFim);
        return FindByProcessosFindosIntervaloTempo;
    }

    public List<Tramitacao> getFindByProcessosFindosDecisaoIntervaloTempo() {
        //FindByProcessosFindosDecisaoIntervaloTempo = tramitacaoFacade.findByIdProcessoFindosDecisaoIntervaloTempo(idDecisao, dataInicio, dataFim);
        return FindByProcessosFindosDecisaoIntervaloTempo;
    }

    public String imprimirProcessos() {
        String relatorio = "artigo_lista.jasper";
        HashMap parametros = new HashMap();
        gestorImpressao = new GestorImpressao(); // Analisar essa instrução. 
        gestorImpressao.imprimirPDF(relatorio, parametros);
        return null;

    }

    // OBS.: A busca por datas não retorna valores
    public List<Tramitacao> getResultados() {
        //Retorna processos findos
        resultados = tramitacaoDAO.buscarProcessosFindos();
        //Retorna busca por decisão e datas 
        if (idDecisao == 0 && dataInicio != null && dataFim != null) {
            resultados = tramitacaoDAO.buscarProcessosFindosPorDatas(dataInicio, dataFim);
            //Retorna Decisao
        } else if (idDecisao != 0 && dataInicio == null && dataFim == null) {
            resultados = tramitacaoDAO.buscarProcessosFindosPorDecisao(idDecisao);

        } else if (idDecisao != 0 && dataInicio != null && dataFim != null) {
            resultados = tramitacaoDAO.buscarProcessosFindosPorDatasDecisão(dataInicio, dataFim, idDecisao);

        }
        return resultados;
    }

    public List<Tramitacao> getFindByProcessoFindoEstado() {
        idEstado = 3;
        FindByProcessoFindoEstado = tramitacaoFacade.findByIdProcessoEstado(idEstado);
        return FindByProcessoFindoEstado;
    }

    // Método para a Enum de Estados
    public List<SelectItem> getOpEstados() {
        List<SelectItem> list = new ArrayList<>();
        for (Estado estado : Estado.values()) {
            list.add(new SelectItem(estado, estado.getExtensao()));
        }
        return list;
    }

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }



    public String imprimirListaArtigo() {
        String relatorio = "tramites_do_processo_horizontal.jasper";
        HashMap parametros = new HashMap();
        gestorImpressao = new GestorImpressao(); // Analisar essa instrução. 
        gestorImpressao.imprimirPDF(relatorio, parametros);
        return null;

    }

    public String imprimirTramitacoesDoProcesso() {
        int parametro = idProcesso;
        String relatorio = "tramites_do_processo_horizontal.jasper";
        HashMap parametros = new HashMap();
        parametros.put("id_processo", parametro);
        gestorImpressao = new GestorImpressao();
        gestorImpressao.imprimirPDF(relatorio, parametros);
           
        return null;

    }

    public String imprimirProcessosFindos() {
        String relatorio = "processos_findos_horizontal.jasper";
        HashMap parametros = new HashMap();
        gestorImpressao = new GestorImpressao();
        gestorImpressao.imprimirPDF(relatorio, parametros);

        return null;

    }
    Integer controleImpressaoRelatorio = 0;

    public String imprimirRelatorio2() {
        if (idDecisao != 0 && dataInicio == null && dataFim == null) {
            int parametro = idDecisao;
            String relatorio = "processos_findos_por_decisao_horizontal.jasper";
            HashMap parametros = new HashMap();
            parametros.put("idDecisao", parametro);
            gestorImpressao = new GestorImpressao();
            gestorImpressao.imprimirPDF(relatorio, parametros);

            return null;

        }else if (idDecisao != 0 && dataInicio != null && dataFim != null) {
            int parametro = idDecisao;
            Date parametro2 = dataInicio;
            Date parametro3 = dataFim;
            String relatorio = "processos_findos_por_decisao_datas_horizontal.jasper";
            HashMap parametros = new HashMap();
            parametros.put("idDecisao", parametro);
            parametros.put("dataInicio", parametro2);
            parametros.put("dataFim", parametro3);
            gestorImpressao = new GestorImpressao();
            gestorImpressao.imprimirPDF(relatorio, parametros);

            return null;

        }else if (idDecisao == 0 && dataInicio != null && dataFim != null) {
            Date parametro1 = dataInicio;
            Date parametro2 = dataFim;
            String relatorio = "processos_findos_por_datas_horizontal.jasper";
            HashMap parametros = new HashMap();
            parametros.put("dataInicio", parametro1);
            parametros.put("dataFim", parametro2);
            gestorImpressao = new GestorImpressao();
            gestorImpressao.imprimirPDF(relatorio, parametros);

        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "","\tDeve fazer uma busca!!!"));
           
        }
        
        return null;
    }

   

    public String imprimirProcessosFindosPorDecisao() {
        int parametro = idDecisao;
        String relatorio = "processos_findos_por_decisao.jasper";
        HashMap parametros = new HashMap();
        parametros.put("idDecisao", parametro);
        gestorImpressao = new GestorImpressao();
        gestorImpressao.imprimirPDF(relatorio, parametros);

        return null;

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

    public Processo getProcesso() {
        //  processo = processoFacade.findById(idProcesso);
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

}
