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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Entity
@Table(name = "processo_findo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcessoFindo.findAll", query = "SELECT p FROM ProcessoFindo p"),
    @NamedQuery(name = "ProcessoFindo.findByDataTermino", query = "SELECT p FROM ProcessoFindo p WHERE p.dataTermino = :dataTermino"),
    @NamedQuery(name = "ProcessoFindo.findByIdProcessoFindo", query = "SELECT p FROM ProcessoFindo p WHERE p.idProcessoFindo = :idProcessoFindo")})
public class ProcessoFindo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_termino")
    @Temporal(TemporalType.DATE)
    private Date dataTermino;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_processo_findo")
    private Integer idProcessoFindo;
    @JoinColumn(name = "id_tipo_decisao", referencedColumnName = "id_tipo_decisao")
    @ManyToOne(optional = false)
    private TipoDecisao idTipoDecisao;
    @JoinColumn(name = "id_processo", referencedColumnName = "id_processo")
    @ManyToOne(optional = false)
    private Processo idProcesso;

    public ProcessoFindo() {
    }

    public ProcessoFindo(Integer idProcessoFindo) {
        this.idProcessoFindo = idProcessoFindo;
    }

    public ProcessoFindo(Integer idProcessoFindo, Date dataTermino) {
        this.idProcessoFindo = idProcessoFindo;
        this.dataTermino = dataTermino;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Integer getIdProcessoFindo() {
        return idProcessoFindo;
    }

    public void setIdProcessoFindo(Integer idProcessoFindo) {
        this.idProcessoFindo = idProcessoFindo;
    }

    public TipoDecisao getIdTipoDecisao() {
        return idTipoDecisao;
    }

    public void setIdTipoDecisao(TipoDecisao idTipoDecisao) {
        this.idTipoDecisao = idTipoDecisao;
    }

    public Processo getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Processo idProcesso) {
        this.idProcesso = idProcesso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcessoFindo != null ? idProcessoFindo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcessoFindo)) {
            return false;
        }
        ProcessoFindo other = (ProcessoFindo) object;
        if ((this.idProcessoFindo == null && other.idProcessoFindo != null) || (this.idProcessoFindo != null && !this.idProcessoFindo.equals(other.idProcessoFindo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fenix.iure.entities.ProcessoFindo[ idProcessoFindo=" + idProcessoFindo + " ]";
    }
    
}
