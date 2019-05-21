/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Entity
@Table(name = "tipo_decisao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDecisao.findAll", query = "SELECT t FROM TipoDecisao t"),
    @NamedQuery(name = "TipoDecisao.findByIdTipoDecisao", query = "SELECT t FROM TipoDecisao t WHERE t.idTipoDecisao = :idTipoDecisao"),
    @NamedQuery(name = "TipoDecisao.findByTipoDecisao", query = "SELECT t FROM TipoDecisao t WHERE t.tipoDecisao = :tipoDecisao")})
public class TipoDecisao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_decisao")
    private Integer idTipoDecisao;
    @Size(max = 45)
    @Column(name = "tipo_decisao")
    private String tipoDecisao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDecisao")
    private Collection<ProcessoFindo> processoFindoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDecisao")
    private Collection<Tramitacao> tramitacaoCollection;

    public TipoDecisao() {
    }

    public TipoDecisao(Integer idTipoDecisao) {
        this.idTipoDecisao = idTipoDecisao;
    }

    public Integer getIdTipoDecisao() {
        return idTipoDecisao;
    }

    public void setIdTipoDecisao(Integer idTipoDecisao) {
        this.idTipoDecisao = idTipoDecisao;
    }

    public String getTipoDecisao() {
        return tipoDecisao;
    }

    public void setTipoDecisao(String tipoDecisao) {
        this.tipoDecisao = tipoDecisao;
    }

    @XmlTransient
    public Collection<ProcessoFindo> getProcessoFindoCollection() {
        return processoFindoCollection;
    }

    public void setProcessoFindoCollection(Collection<ProcessoFindo> processoFindoCollection) {
        this.processoFindoCollection = processoFindoCollection;
    }

    @XmlTransient
    public Collection<Tramitacao> getTramitacaoCollection() {
        return tramitacaoCollection;
    }

    public void setTramitacaoCollection(Collection<Tramitacao> tramitacaoCollection) {
        this.tramitacaoCollection = tramitacaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDecisao != null ? idTipoDecisao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDecisao)) {
            return false;
        }
        TipoDecisao other = (TipoDecisao) object;
        if ((this.idTipoDecisao == null && other.idTipoDecisao != null) || (this.idTipoDecisao != null && !this.idTipoDecisao.equals(other.idTipoDecisao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fenix.iure.entities.TipoDecisao[ idTipoDecisao=" + idTipoDecisao + " ]";
    }
    
}
