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
@Table(name = "tipo_pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPessoa.findAll", query = "SELECT t FROM TipoPessoa t"),
    @NamedQuery(name = "TipoPessoa.findByIdTipo", query = "SELECT t FROM TipoPessoa t WHERE t.idTipo = :idTipo"),
    @NamedQuery(name = "TipoPessoa.findByNomeTipo", query = "SELECT t FROM TipoPessoa t WHERE t.nomeTipo = :nomeTipo")})
public class TipoPessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo")
    private Integer idTipo;
    @Size(max = 45)
    @Column(name = "nome_tipo")
    private String nomeTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipo")
    private Collection<Requerido> requeridoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipo")
    private Collection<Requente> requenteCollection;

    public TipoPessoa() {
    }

    public TipoPessoa(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    @XmlTransient
    public Collection<Requerido> getRequeridoCollection() {
        return requeridoCollection;
    }

    public void setRequeridoCollection(Collection<Requerido> requeridoCollection) {
        this.requeridoCollection = requeridoCollection;
    }

    @XmlTransient
    public Collection<Requente> getRequenteCollection() {
        return requenteCollection;
    }

    public void setRequenteCollection(Collection<Requente> requenteCollection) {
        this.requenteCollection = requenteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPessoa)) {
            return false;
        }
        TipoPessoa other = (TipoPessoa) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fenix.iure.entities.TipoPessoa[ idTipo=" + idTipo + " ]";
    }
    
}
