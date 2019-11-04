/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;

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
import fenix.iure.util.DateUtil;
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
import javax.faces.event.ValueChangeEvent;
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
    private Date dataInicio;
    private Date dataFim;

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
    private List<Processo> findByIntervaloDatas;

    // Buscar processos recentes
    private List<Processo> findRecentes;

    // Buscar processos findos
    private List<Processo> findProcessosFindos;
    
    // Variaveis para os metodos load
    private EspecieProcesso especieProcesso;
    private List<Processo> processosPorEspecie;
    

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
        processo.setDataEntrada(DateUtil.getDataActual());
        

    }

    public List<Processo> getProcessos() {
        processos = processoFacade.findRecentes();
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
               // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "","\tProcesso salvo com sucesso"));
           
            } catch (javax.ejb.EJBException ex) {
               // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "","\tJa existe um processo com esse Número"));
             } catch (java.lang.NumberFormatException ex) {
               // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","\tDeve preencher todos os campos"));
           }

        } else {
            newSave();
            JSFUtil.refresh();

        }

    }

    public String startEdit() {
        
        return "processo_lsta?faces-redirect=true";
        
    }

    public String edit(ActionEvent event) {
        String controlo = null;
        try {
            
            processoFacade.edit(processo);
            processo = new Processo();
            controlo = null;
            //JSFUtil.adicionarMensagemDeSucesso("Sucesso ao alterar dados!");
           
            return controlo;
            
        } catch (javax.ejb.EJBException | java.lang.NumberFormatException ex) {
            controlo = null;
            JSFUtil.adicionarMensagemDeErro("Número do processo ja existe!");
            return controlo;
        }

    }

    public void editPublico(ActionEvent event) {
        processoFacade.edit(processo);
       // JSFUtil.adicionarMensagemDeSucesso("Sucesso ao alterar dados!");
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
          //  JSFUtil.adicionarMensagemDeErro("Sucesso ao Eliminar dados!");
        } catch (Exception e) {
          //  JSFUtil.adicionarMensagemDeErro(e.getMessage());
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
        String relatorio = "processos_lista.jasper";
        HashMap parametros = new HashMap();
        gestorImpressao = new GestorImpressao(); // Analisar essa instrução. 
        gestorImpressao.imprimirPDF(relatorio, parametros);
        return null;

    }

    public String imprimirFichaProcesso(String parametro) {
        String relatorio = "processos_ficha_horizontal.jasper";
        HashMap parametros = new HashMap();
        parametros.put("numeroProcesso", parametro);
        gestorImpressao = new GestorImpressao();
        gestorImpressao.imprimirPDF(relatorio, parametros);

        return null;

    }
     public String imprimirProcessosPorEspecie(String parametro) {
        String relatorio = "processos_por_especie_horizontal.jasper";
        HashMap parametros = new HashMap();
        parametros.put("idEspecie", parametro);
        gestorImpressao = new GestorImpressao();
        gestorImpressao.imprimirPDF(relatorio, parametros);

        return null;

    }
     
     public String imprimirProcessosPorDatas() {
        String relatorio = "processos_por_datas_horizontal.jasper";
        HashMap parametros = new HashMap();
        parametros.put("dataInicio", dataInicio);
        parametros.put("dataFim", dataInicio);
        gestorImpressao = new GestorImpressao();
        gestorImpressao.imprimirPDF(relatorio, parametros);

        return null;


    }
     
      public String imprimirProcessosPorAutor(String parametro) {
        String relatorio = "processos_por_autor_horizontal.jasper";
        HashMap parametros = new HashMap();
        parametros.put("idAutor", parametro);
        gestorImpressao = new GestorImpressao();
        gestorImpressao.imprimirPDF(relatorio, parametros);

        return null;

    }
      public String imprimirProcessosPorReu(String parametro) {
        String relatorio = "processos_por_reu_horizontal.jasper";
        HashMap parametros = new HashMap();
        parametros.put("idReu", parametro);
        gestorImpressao = new GestorImpressao();
        gestorImpressao.imprimirPDF(relatorio, parametros);

        return null;

    }
    
    public void loadEspecies(ValueChangeEvent event) {
        especieProcesso = (EspecieProcesso) event.getNewValue();
        System.out.println("Cliente" + especieProcesso.getEspecieProcesso());

         //vendasAoCliente = vendaFacade.findClienteByCliente(cliente);

         findByEspecieProcesso = processoFacade.findByIdEspecie(idEspecieProcesso);

    }

    public EspecieProcesso getEspecieProcesso() {
        return especieProcesso;
    }

    public void setEspecieProcesso(EspecieProcesso especieProcesso) {
        this.especieProcesso = especieProcesso;
    }

    public List<Processo> getProcessosPorEspecie() {
        return processosPorEspecie;
    }

    public void setProcessosPorEspecie(List<Processo> processosPorEspecie) {
        this.processosPorEspecie = processosPorEspecie;
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

    public List<Processo> getFindByIntervaloDatas() {
        findByIntervaloDatas = processoFacade.findByIntervaloDataEntrada(dataInicio, dataFim);
        return findByIntervaloDatas;
    }
    
    

    
   

    
}
