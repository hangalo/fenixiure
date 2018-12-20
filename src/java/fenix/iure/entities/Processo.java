/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author informatica
 */
@Entity
@Table(name = "processo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Processo.findAll", query = "SELECT p FROM Processo p")
    , @NamedQuery(name = "Processo.findByIdProcesso", query = "SELECT p FROM Processo p WHERE p.idProcesso = :idProcesso")
    , @NamedQuery(name = "Processo.findByNumeroProcesso", query = "SELECT p FROM Processo p WHERE p.numeroProcesso = :numeroProcesso")
    , @NamedQuery(name = "Processo.findByDataEntrada", query = "SELECT p FROM Processo p WHERE p.dataEntrada = :dataEntrada")
    , @NamedQuery(name = "Processo.findByDataConclusao", query = "SELECT p FROM Processo p WHERE p.dataConclusao = :dataConclusao")
    , @NamedQuery(name = "Processo.findByResumoDespacho", query = "SELECT p FROM Processo p WHERE p.resumoDespacho = :resumoDespacho")})
public class Processo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_processo")
    private Integer idProcesso;
    @Size(max = 45)
    @Column(name = "numero_processo")
    private String numeroProcesso;
    @Column(name = "data_entrada")
    @Temporal(TemporalType.DATE)
    private Date dataEntrada;
    @Column(name = "data_conclusao")
    @Temporal(TemporalType.DATE)
    private Date dataConclusao;
    @Size(max = 200)
    @Column(name = "resumo_despacho")
    private String resumoDespacho;
    @JoinColumn(name = "id_advogado", referencedColumnName = "id_advogado")
    @ManyToOne(optional = false)
    private Advogado idAdvogado;
    @JoinColumn(name = "id_especie_processo", referencedColumnName = "id_especie_processo")
    @ManyToOne(optional = false)
    private EspecieProcesso idEspecieProcesso;
    @JoinColumn(name = "id_estado_processo", referencedColumnName = "id_estado_processo")
    @ManyToOne(optional = false)
    private EstadoProcesso idEstadoProcesso;
    @JoinColumn(name = "id_juiz", referencedColumnName = "id_juiz")
    @ManyToOne(optional = false)
    private Juiz idJuiz;
    @JoinColumn(name = "id_requente", referencedColumnName = "id_requente")
    @ManyToOne(optional = false)
    private Requente idRequente;
    @JoinColumn(name = "id_requerido", referencedColumnName = "id_requerido")
    @ManyToOne(optional = false)
    private Requerido idRequerido;
    @JoinColumn(name = "id_tipo_decisao", referencedColumnName = "id_tipo_decisao")
    @ManyToOne(optional = false)
    private TipoDecisao idTipoDecisao;

    public Processo() {
    }

    public Processo(Integer idProcesso) {
        this.idProcesso = idProcesso;
    }

    public Integer getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Integer idProcesso) {
        this.idProcesso = idProcesso;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
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

    public String getResumoDespacho() {
        return resumoDespacho;
    }

    public void setResumoDespacho(String resumoDespacho) {
        this.resumoDespacho = resumoDespacho;
    }

    public Advogado getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(Advogado idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public EspecieProcesso getIdEspecieProcesso() {
        return idEspecieProcesso;
    }

    public void setIdEspecieProcesso(EspecieProcesso idEspecieProcesso) {
        this.idEspecieProcesso = idEspecieProcesso;
    }

    public EstadoProcesso getIdEstadoProcesso() {
        return idEstadoProcesso;
    }

    public void setIdEstadoProcesso(EstadoProcesso idEstadoProcesso) {
        this.idEstadoProcesso = idEstadoProcesso;
    }

    public Juiz getIdJuiz() {
        return idJuiz;
    }

    public void setIdJuiz(Juiz idJuiz) {
        this.idJuiz = idJuiz;
    }

    public Requente getIdRequente() {
        return idRequente;
    }

    public void setIdRequente(Requente idRequente) {
        this.idRequente = idRequente;
    }

    public Requerido getIdRequerido() {
        return idRequerido;
    }

    public void setIdRequerido(Requerido idRequerido) {
        this.idRequerido = idRequerido;
    }

    public TipoDecisao getIdTipoDecisao() {
        return idTipoDecisao;
    }

    public void setIdTipoDecisao(TipoDecisao idTipoDecisao) {
        this.idTipoDecisao = idTipoDecisao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcesso != null ? idProcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Processo)) {
            return false;
        }
        Processo other = (Processo) object;
        if ((this.idProcesso == null && other.idProcesso != null) || (this.idProcesso != null && !this.idProcesso.equals(other.idProcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fenix.iure.entities.Processo[ idProcesso=" + idProcesso + " ]";
    }
    
}
