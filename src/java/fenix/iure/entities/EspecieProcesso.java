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
 * @author informatica
 */
@Entity
@Table(name = "especie_processo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EspecieProcesso.findAll", query = "SELECT e FROM EspecieProcesso e")
    , @NamedQuery(name = "EspecieProcesso.findByIdEspecieProcesso", query = "SELECT e FROM EspecieProcesso e WHERE e.idEspecieProcesso = :idEspecieProcesso")
    , @NamedQuery(name = "EspecieProcesso.findByEspecieProcesso", query = "SELECT e FROM EspecieProcesso e WHERE e.especieProcesso = :especieProcesso")})
public class EspecieProcesso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_especie_processo")
    private Integer idEspecieProcesso;
    @Size(max = 150)
    @Column(name = "especie_processo")
    private String especieProcesso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEspecieProcesso")
    private Collection<Processo> processoCollection;

    public EspecieProcesso() {
    }

    public EspecieProcesso(Integer idEspecieProcesso) {
        this.idEspecieProcesso = idEspecieProcesso;
    }

    public Integer getIdEspecieProcesso() {
        return idEspecieProcesso;
    }

    public void setIdEspecieProcesso(Integer idEspecieProcesso) {
        this.idEspecieProcesso = idEspecieProcesso;
    }

    public String getEspecieProcesso() {
        return especieProcesso;
    }

    public void setEspecieProcesso(String especieProcesso) {
        this.especieProcesso = especieProcesso;
    }

    @XmlTransient
    public Collection<Processo> getProcessoCollection() {
        return processoCollection;
    }

    public void setProcessoCollection(Collection<Processo> processoCollection) {
        this.processoCollection = processoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEspecieProcesso != null ? idEspecieProcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EspecieProcesso)) {
            return false;
        }
        EspecieProcesso other = (EspecieProcesso) object;
        if ((this.idEspecieProcesso == null && other.idEspecieProcesso != null) || (this.idEspecieProcesso != null && !this.idEspecieProcesso.equals(other.idEspecieProcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fenix.iure.entities.EspecieProcesso[ idEspecieProcesso=" + idEspecieProcesso + " ]";
    }
    
}
