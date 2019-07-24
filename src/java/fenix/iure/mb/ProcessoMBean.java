/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import fenix.iure.ejbs.AdvogadoFacade;
import fenix.iure.ejbs.EspecieProcessoFacade;
import fenix.iure.ejbs.EstadoProcessoFacade;
import fenix.iure.ejbs.JuizFacade;
import fenix.iure.ejbs.ProcessoFacade;
import fenix.iure.ejbs.RequenteFacade;
import fenix.iure.ejbs.RequeridoFacade;
import fenix.iure.ejbs.TipoDecisaoFacade;
import fenix.iure.entities.Advogado;
import fenix.iure.entities.EspecieProcesso;
import fenix.iure.entities.EstadoProcesso;
import fenix.iure.entities.Juiz;
import fenix.iure.entities.Processo;
import fenix.iure.entities.Requente;
import fenix.iure.entities.Requerido;
import fenix.iure.entities.TipoDecisao;
import fenix.iure.util.GestorImpressao;
import fenix.iure.util.JSFUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
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
import javax.inject.Inject;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Named(value = "processoMBean")
@SessionScoped
public class ProcessoMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Processo processo;
    private List<Processo> processos;
    // Listas para as comboboxs
    private List<EspecieProcesso> especies;
    private List<EstadoProcesso> estados;
    private List<TipoDecisao> tipos;
    private List<Requente> requerentes;
    private List<Requerido> requeridos;
    private List<Advogado> advogados;
    private List<Juiz> juizes;

    // Variaveis para pesquisas paramentrizadas
    private String numero;
    private Date dataEntrada;
    private Date dataConclusao;
    private int idEspecieProcesso;
    private int idEstadoProcesso;
    private int idRequerente;
    private int idRequerido;
    private int idAdvogado;
    private int idTipoDecisao;
    private int idJuiz;

    // Listas das pesquisas paramentrizadas
    private List<Processo> findByNumero;
    private List<Processo> findByDataEntrada;
    private List<Processo> findByDataConclusao;
    private List<Processo> findByEspecieProcesso;
    private List<Processo> findByEstadoProcesso;
    private List<Processo> findByRequerente;
    private List<Processo> findByRequerido;
    private List<Processo> findByAdvogado;
    private List<Processo> findByTipoDecisao;
    private List<Processo> findByJuiz;

    // Buscar processos recentes
    private List<Processo> findRecentes;

    // Buscar processos findos
    private List<Processo> findProcessosFindos;

    @Inject
    ProcessoFacade processoFacade;
    @Inject
    EspecieProcessoFacade especieProcessoFacade;
    @Inject
    EstadoProcessoFacade estadoProcessoFacade;
    @Inject
    TipoDecisaoFacade tipoDecisaoFacade;
    @Inject
    RequenteFacade requenteFacade;
    @Inject
    RequeridoFacade requeridoFacade;
    @Inject
    AdvogadoFacade advogadoFacade;
    @Inject
    JuizFacade juizFacade;

    public ProcessoMBean() {
    }

    // Variaveis para os relatorios
    @ManagedProperty(value = "#{gestorImpressao}")
    private GestorImpressao gestorImpressao;

    @PostConstruct
    public void inicializar() {
        processo = new Processo();
        processos = new ArrayList<>();

        especies = new ArrayList<>();

        estados = new ArrayList<>();

        tipos = new ArrayList<>();

        requerentes = new ArrayList<>();

        requeridos = new ArrayList<>();

        advogados = new ArrayList<>();

        juizes = new ArrayList<>();

        findRecentes = new ArrayList<>();

    }

    public List<Processo> getProcessos() {
        processos = processoFacade.findAll();
        return processos;
    }

    public String newSave() {
        processo = new Processo();
        return "processo_lsta?faces-redirect=true";
    }

    public void guardar(ActionEvent evt) {
        if (processo != null) {
            try {
                processoFacade.create(processo);
                processo = new Processo();
                JSFUtil.adicionarMensagemDeSucesso("Processo salvo com sucesso!");

            } catch (javax.ejb.EJBException ex) {
                JSFUtil.adicionarMensagemDeErro("Número do processo ja existe!");
            } catch (NumberFormatException ex) {
                JSFUtil.adicionarMensagemDeErro(ex.getMessage());
            }

        } else {
            newSave();

        }

    }

    public String startEdit() {
        return "processo_lsta?faces-redirect=true";
    }

    public String edit(javafx.event.ActionEvent event) {
        String controlo = null;
        try {
            processoFacade.edit(processo);
            processo = new Processo();
            controlo = null;
            JSFUtil.adicionarMensagemDeSucesso("Sucesso ao alterar dados!");

            return controlo;

        } catch (javax.ejb.EJBException | java.lang.NumberFormatException ex) {
            controlo = null;
            JSFUtil.adicionarMensagemDeErro("Número do processo ja existe!");
            return controlo;
        }

    }

    public void editPublico(javafx.event.ActionEvent event) {
        processoFacade.edit(processo);
        JSFUtil.adicionarMensagemDeSucesso("Sucesso ao alterar dados!");
        requerentes = null;

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("processo_lsta_p.jsf");
        } catch (IOException ex) {
            Logger.getLogger(ProcessoMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String delete() {
        try {
            processoFacade.remove(processo);
            processos = null;
            JSFUtil.adicionarMensagemDeErro("Sucesso ao Eliminar dados!");
        } catch (Exception e) {
            JSFUtil.adicionarMensagemDeErro(e.getMessage());
        }

        return "processo_lsta?faces-redirect=true";
    }

    public List<EspecieProcesso> getEspecies() {
        especies = especieProcessoFacade.findAll();
        return especies;
    }

    public List<EstadoProcesso> getEstados() {
        estados = estadoProcessoFacade.findAll();
        return estados;
    }

    public List<TipoDecisao> getTipos() {
        tipos = tipoDecisaoFacade.findAll();
        return tipos;
    }

    public List<Requente> getRequerentes() {
        requerentes = requenteFacade.findAll();
        return requerentes;
    }

    public List<Requerido> getRequeridos() {
        requeridos = requeridoFacade.findAll();
        return requeridos;
    }

    public List<Advogado> getAdvogados() {
        advogados = advogadoFacade.findAll();
        return advogados;
    }

    public List<Juiz> getJuizes() {
        juizes = juizFacade.findAll();
        return juizes;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public int getIdEspecieProcesso() {
        return idEspecieProcesso;
    }

    public void setIdEspecieProcesso(int idEspecieProcesso) {
        this.idEspecieProcesso = idEspecieProcesso;
    }

    public int getIdEstadoProcesso() {
        return idEstadoProcesso;
    }

    public void setIdEstadoProcesso(int idEstadoProcesso) {
        this.idEstadoProcesso = idEstadoProcesso;
    }

    public int getIdRequerente() {
        return idRequerente;
    }

    public void setIdRequerente(int idRequerente) {
        this.idRequerente = idRequerente;
    }

    public int getIdRequerido() {
        return idRequerido;
    }

    public void setIdRequerido(int idRequerido) {
        this.idRequerido = idRequerido;
    }

    public int getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(int idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public int getIdTipoDecisao() {
        return idTipoDecisao;
    }

    public void setIdTipoDecisao(int idTipoDecisao) {
        this.idTipoDecisao = idTipoDecisao;
    }

    public int getIdJuiz() {
        return idJuiz;
    }

    public void setIdJuiz(int idJuiz) {
        this.idJuiz = idJuiz;
    }

    public List<Processo> getFindByNumero() {
        findByNumero = processoFacade.findByNumero(numero);
        return findByNumero;
    }

    public List<Processo> getFindByDataEntrada() {
        findByDataEntrada = processoFacade.findByDataEntrada(dataEntrada);
        return findByDataEntrada;
    }

    public List<Processo> getFindByDataConclusao() {
        findByDataConclusao = processoFacade.findByDataConclusao(dataConclusao);
        return findByDataConclusao;
    }

    public List<Processo> getFindByEspecieProcesso() {
        findByEspecieProcesso = processoFacade.findByIdEspecie(idEspecieProcesso);
        return findByEspecieProcesso;
    }

    public List<Processo> getFindByEstadoProcesso() {
        findByEstadoProcesso = processoFacade.findByIdEstado(idEstadoProcesso);
        return findByEstadoProcesso;
    }

    public List<Processo> getFindByRequerente() {
        findByRequerente = processoFacade.findByIdRequerente(idRequerente);
        return findByRequerente;
    }

    public List<Processo> getFindByRequerido() {
        findByRequerido = processoFacade.findByIdRequerido(idRequerido);
        return findByRequerido;
    }

    public List<Processo> getFindByAdvogado() {
        findByAdvogado = processoFacade.findByIdAdvogado(idAdvogado);
        return findByAdvogado;
    }

    public List<Processo> getFindByTipoDecisao() {
        findByTipoDecisao = processoFacade.findByIdTipoDecisao(idTipoDecisao);
        return findByTipoDecisao;
    }

    public List<Processo> getFindByJuiz() {
        findByJuiz = processoFacade.findByIdJuiz(idJuiz);
        return findByJuiz;
    }

    public List<Processo> getFindRecentes() {
        findRecentes = processoFacade.findRecentes();
        return findRecentes;
    }

    public GestorImpressao getGestorImpressao() {
        return gestorImpressao;
    }

    public void setGestorImpressao(GestorImpressao gestorImpressao) {
        this.gestorImpressao = gestorImpressao;
    }

    public String imprimirListaArtigo() {
        String relatorio = "processos_lista_insignia.jasper";
        HashMap parametros = new HashMap();
        gestorImpressao = new GestorImpressao(); // Analisar essa instrução. 
        gestorImpressao.imprimirPDF(relatorio, parametros);
        return null;

    }

    public String imprimirFichaProcesso(String parametro) {
        String relatorio = "processos_por_numero.jasper";
        HashMap parametros = new HashMap();
        parametros.put("numeroProcesso", parametro);
        gestorImpressao = new GestorImpressao();
        gestorImpressao.imprimirPDF(relatorio, parametros);

        return null;

    }

    public List<Processo> getFindProcessosFindos() {
        findProcessosFindos = processoFacade.findProcessosFindos();
        return findProcessosFindos;
    }

    private Integer indexTabela;

    
    public Integer getIndexTabela() {
        for (int i = 1; i <= processos.size(); i++) {
            indexTabela = i;
        }
        return indexTabela;
    }

    public void setIndexTabela(Integer indexTabela) {
        this.indexTabela = indexTabela;
    }

}
