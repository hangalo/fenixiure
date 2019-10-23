/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Entity
@Table(name = "advogado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Advogado.findAll", query = "SELECT a FROM Advogado a")
    , @NamedQuery(name = "Advogado.findByIdAdvogado", query = "SELECT a FROM Advogado a WHERE a.idAdvogado = :idAdvogado")
    , @NamedQuery(name = "Advogado.findByNomeAdvogado", query = "SELECT a FROM Advogado a WHERE a.nomeAdvogado = :nomeAdvogado")
    , @NamedQuery(name = "Advogado.findBySobrenomeAdvogado", query = "SELECT a FROM Advogado a WHERE a.sobrenomeAdvogado = :sobrenomeAdvogado")
    , @NamedQuery(name = "Advogado.findByDataInicioFuncoes", query = "SELECT a FROM Advogado a WHERE a.dataInicioFuncoes = :dataInicioFuncoes")})
public class Advogado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_advogado")
    private Integer idAdvogado;
    @Size(max = 45)
    @Column(name = "nome_advogado")
    private String nomeAdvogado;
    @Size(max = 45)
    @Column(name = "sobrenome_advogado")
    private String sobrenomeAdvogado;
    @Column(name = "data_inicio_funcoes")
    @Temporal(TemporalType.DATE)
    private Date dataInicioFuncoes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAdvogado")
    private Collection<Requerido> requeridoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAdvogado")
    private Collection<Requente> requenteCollection;

    public Advogado() {
    }

    public Advogado(Integer idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public Integer getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(Integer idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public String getNomeAdvogado() {
        return nomeAdvogado;
    }

    public void setNomeAdvogado(String nomeAdvogado) {
        this.nomeAdvogado = nomeAdvogado;
    }

    public String getSobrenomeAdvogado() {
        return sobrenomeAdvogado;
    }

    public void setSobrenomeAdvogado(String sobrenomeAdvogado) {
        this.sobrenomeAdvogado = sobrenomeAdvogado;
    }

    public Date getDataInicioFuncoes() {
        return dataInicioFuncoes;
    }

    public void setDataInicioFuncoes(Date dataInicioFuncoes) {
        this.dataInicioFuncoes = dataInicioFuncoes;
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
        hash += (idAdvogado != null ? idAdvogado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Advogado)) {
            return false;
        }
        Advogado other = (Advogado) object;
        if ((this.idAdvogado == null && other.idAdvogado != null) || (this.idAdvogado != null && !this.idAdvogado.equals(other.idAdvogado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fenix.iure.entities.Advogado[ idAdvogado=" + idAdvogado + " ]";
    }
    
}
