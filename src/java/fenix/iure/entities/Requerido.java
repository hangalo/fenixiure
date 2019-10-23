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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "requerido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requerido.findAll", query = "SELECT r FROM Requerido r")
    , @NamedQuery(name = "Requerido.findByIdRequerido", query = "SELECT r FROM Requerido r WHERE r.idRequerido = :idRequerido")
    , @NamedQuery(name = "Requerido.findByNomeRequerido", query = "SELECT r FROM Requerido r WHERE r.nomeRequerido = :nomeRequerido")
    , @NamedQuery(name = "Requerido.findByCasaRequerido", query = "SELECT r FROM Requerido r WHERE r.casaRequerido = :casaRequerido")
    , @NamedQuery(name = "Requerido.findByRuaRequerido", query = "SELECT r FROM Requerido r WHERE r.ruaRequerido = :ruaRequerido")
    , @NamedQuery(name = "Requerido.findByBairroRequerido", query = "SELECT r FROM Requerido r WHERE r.bairroRequerido = :bairroRequerido")})
public class Requerido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_requerido")
    private Integer idRequerido;
    @Size(max = 45)
    @Column(name = "nome_requerido")
    private String nomeRequerido;
    @Size(max = 45)
    @Column(name = "casa_requerido")
    private String casaRequerido;
    @Size(max = 45)
    @Column(name = "rua_requerido")
    private String ruaRequerido;
    @Size(max = 45)
    @Column(name = "bairro_requerido")
    private String bairroRequerido;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false)
    private Municipio idMunicipio;
    @JoinColumn(name = "id_advogado", referencedColumnName = "id_advogado")
    @ManyToOne(optional = false)
    private Advogado idAdvogado;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
    @ManyToOne(optional = false)
    private TipoPessoa idTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRequerido")
    private Collection<Processo> processoCollection;

    public Requerido() {
    }

    public Requerido(Integer idRequerido) {
        this.idRequerido = idRequerido;
    }

    public Integer getIdRequerido() {
        return idRequerido;
    }

    public void setIdRequerido(Integer idRequerido) {
        this.idRequerido = idRequerido;
    }

    public String getNomeRequerido() {
        return nomeRequerido;
    }

    public void setNomeRequerido(String nomeRequerido) {
        this.nomeRequerido = nomeRequerido;
    }

    public String getCasaRequerido() {
        return casaRequerido;
    }

    public void setCasaRequerido(String casaRequerido) {
        this.casaRequerido = casaRequerido;
    }

    public String getRuaRequerido() {
        return ruaRequerido;
    }

    public void setRuaRequerido(String ruaRequerido) {
        this.ruaRequerido = ruaRequerido;
    }

    public String getBairroRequerido() {
        return bairroRequerido;
    }

    public void setBairroRequerido(String bairroRequerido) {
        this.bairroRequerido = bairroRequerido;
    }

    public Municipio getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipio idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Advogado getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(Advogado idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public TipoPessoa getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoPessoa idTipo) {
        this.idTipo = idTipo;
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
        hash += (idRequerido != null ? idRequerido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Requerido)) {
            return false;
        }
        Requerido other = (Requerido) object;
        if ((this.idRequerido == null && other.idRequerido != null) || (this.idRequerido != null && !this.idRequerido.equals(other.idRequerido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fenix.iure.entities.Requerido[ idRequerido=" + idRequerido + " ]";
    }
    
}
