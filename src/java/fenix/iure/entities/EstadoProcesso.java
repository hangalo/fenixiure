/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Entity
@Table(name = "estado_processo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoProcesso.findAll", query = "SELECT e FROM EstadoProcesso e")
    , @NamedQuery(name = "EstadoProcesso.findByIdEstadoProcesso", query = "SELECT e FROM EstadoProcesso e WHERE e.idEstadoProcesso = :idEstadoProcesso")
    , @NamedQuery(name = "EstadoProcesso.findByEstadoProcesso", query = "SELECT e FROM EstadoProcesso e WHERE e.estadoProcesso = :estadoProcesso")})
public class EstadoProcesso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_processo")
    private Integer idEstadoProcesso;
    @Size(max = 125)
    @Column(name = "estado_processo")
    private String estadoProcesso;

    public EstadoProcesso() {
    }

    public EstadoProcesso(Integer idEstadoProcesso) {
        this.idEstadoProcesso = idEstadoProcesso;
    }

    public Integer getIdEstadoProcesso() {
        return idEstadoProcesso;
    }

    public void setIdEstadoProcesso(Integer idEstadoProcesso) {
        this.idEstadoProcesso = idEstadoProcesso;
    }

    public String getEstadoProcesso() {
        return estadoProcesso;
    }

    public void setEstadoProcesso(String estadoProcesso) {
        this.estadoProcesso = estadoProcesso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoProcesso != null ? idEstadoProcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoProcesso)) {
            return false;
        }
        EstadoProcesso other = (EstadoProcesso) object;
        if ((this.idEstadoProcesso == null && other.idEstadoProcesso != null) || (this.idEstadoProcesso != null && !this.idEstadoProcesso.equals(other.idEstadoProcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fenix.iure.entities.EstadoProcesso[ idEstadoProcesso=" + idEstadoProcesso + " ]";
    }
    
}
