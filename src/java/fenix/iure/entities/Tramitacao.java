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
 * @author Elísio Kavaimunwa
 */
@Entity
@Table(name = "tramitacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tramitacao.findAll", query = "SELECT t FROM Tramitacao t")
    , @NamedQuery(name = "Tramitacao.findByIdTramitacao", query = "SELECT t FROM Tramitacao t WHERE t.idTramitacao = :idTramitacao")
    , @NamedQuery(name = "Tramitacao.findByDataConclusaoTramitacao", query = "SELECT t FROM Tramitacao t WHERE t.dataConclusaoTramitacao = :dataConclusaoTramitacao")
    , @NamedQuery(name = "Tramitacao.findByDispachoTramitacao", query = "SELECT t FROM Tramitacao t WHERE t.dispachoTramitacao = :dispachoTramitacao")
    , @NamedQuery(name = "Tramitacao.findByEstadoProcesso", query = "SELECT t FROM Tramitacao t WHERE t.estadoProcesso = :estadoProcesso")
    , @NamedQuery(name = "Tramitacao.findByDataTermino", query = "SELECT t FROM Tramitacao t WHERE t.dataTermino = :dataTermino")})
public class Tramitacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tramitacao")
    private Integer idTramitacao;
    @Column(name = "data_conclusao_tramitacao")
    @Temporal(TemporalType.DATE)
    private Date dataConclusaoTramitacao;
    @Size(max = 20000)
    @Column(name = "dispacho_tramitacao")
    private String dispachoTramitacao;
    @Size(max = 125)
    @Column(name = "estado_processo")
    private String estadoProcesso;
    @Column(name = "data_termino")
    @Temporal(TemporalType.DATE)
    private Date dataTermino;
    @JoinColumn(name = "id_processo", referencedColumnName = "id_processo")
    @ManyToOne(optional = false)
    private Processo idProcesso;
    @JoinColumn(name = "id_tipo_decisao", referencedColumnName = "id_tipo_decisao")
    @ManyToOne(optional = false)
    private TipoDecisao idTipoDecisao;

    public Tramitacao() {
    }

    public Tramitacao(Integer idTramitacao) {
        this.idTramitacao = idTramitacao;
    }

    public Integer getIdTramitacao() {
        return idTramitacao;
    }

    public void setIdTramitacao(Integer idTramitacao) {
        this.idTramitacao = idTramitacao;
    }

    public Date getDataConclusaoTramitacao() {
        return dataConclusaoTramitacao;
    }

    public void setDataConclusaoTramitacao(Date dataConclusaoTramitacao) {
        this.dataConclusaoTramitacao = dataConclusaoTramitacao;
    }

    public String getDispachoTramitacao() {
        return dispachoTramitacao;
    }

    public void setDispachoTramitacao(String dispachoTramitacao) {
        this.dispachoTramitacao = dispachoTramitacao;
    }

    public String getEstadoProcesso() {
        return estadoProcesso;
    }

    public void setEstadoProcesso(String estadoProcesso) {
        this.estadoProcesso = estadoProcesso;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Processo getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Processo idProcesso) {
        this.idProcesso = idProcesso;
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
        hash += (idTramitacao != null ? idTramitacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tramitacao)) {
            return false;
        }
        Tramitacao other = (Tramitacao) object;
        if ((this.idTramitacao == null && other.idTramitacao != null) || (this.idTramitacao != null && !this.idTramitacao.equals(other.idTramitacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fenix.iure.entities.Tramitacao[ idTramitacao=" + idTramitacao + " ]";
    }
    
}
