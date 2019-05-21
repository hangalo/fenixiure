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
@Table(name = "requente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requente.findAll", query = "SELECT r FROM Requente r"),
    @NamedQuery(name = "Requente.findByIdRequente", query = "SELECT r FROM Requente r WHERE r.idRequente = :idRequente"),
    @NamedQuery(name = "Requente.findByNomeRequente", query = "SELECT r FROM Requente r WHERE r.nomeRequente = :nomeRequente"),
    @NamedQuery(name = "Requente.findByCasaRequerente", query = "SELECT r FROM Requente r WHERE r.casaRequerente = :casaRequerente"),
    @NamedQuery(name = "Requente.findByRuaRequerente", query = "SELECT r FROM Requente r WHERE r.ruaRequerente = :ruaRequerente"),
    @NamedQuery(name = "Requente.findByBairroRequerente", query = "SELECT r FROM Requente r WHERE r.bairroRequerente = :bairroRequerente")})
public class Requente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_requente")
    private Integer idRequente;
    @Size(max = 45)
    @Column(name = "nome_requente")
    private String nomeRequente;
    @Size(max = 45)
    @Column(name = "casa_requerente")
    private String casaRequerente;
    @Size(max = 45)
    @Column(name = "rua_requerente")
    private String ruaRequerente;
    @Size(max = 45)
    @Column(name = "bairro_requerente")
    private String bairroRequerente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRequente")
    private Collection<Processo> processoCollection;
    @JoinColumn(name = "id_advogado", referencedColumnName = "id_advogado")
    @ManyToOne(optional = false)
    private Advogado idAdvogado;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false)
    private Municipio idMunicipio;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
    @ManyToOne(optional = false)
    private TipoPessoa idTipo;

    public Requente() {
    }

    public Requente(Integer idRequente) {
        this.idRequente = idRequente;
    }

    public Integer getIdRequente() {
        return idRequente;
    }

    public void setIdRequente(Integer idRequente) {
        this.idRequente = idRequente;
    }

    public String getNomeRequente() {
        return nomeRequente;
    }

    public void setNomeRequente(String nomeRequente) {
        this.nomeRequente = nomeRequente;
    }

    public String getCasaRequerente() {
        return casaRequerente;
    }

    public void setCasaRequerente(String casaRequerente) {
        this.casaRequerente = casaRequerente;
    }

    public String getRuaRequerente() {
        return ruaRequerente;
    }

    public void setRuaRequerente(String ruaRequerente) {
        this.ruaRequerente = ruaRequerente;
    }

    public String getBairroRequerente() {
        return bairroRequerente;
    }

    public void setBairroRequerente(String bairroRequerente) {
        this.bairroRequerente = bairroRequerente;
    }

    @XmlTransient
    public Collection<Processo> getProcessoCollection() {
        return processoCollection;
    }

    public void setProcessoCollection(Collection<Processo> processoCollection) {
        this.processoCollection = processoCollection;
    }

    public Advogado getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(Advogado idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public Municipio getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipio idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public TipoPessoa getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoPessoa idTipo) {
        this.idTipo = idTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRequente != null ? idRequente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Requente)) {
            return false;
        }
        Requente other = (Requente) object;
        if ((this.idRequente == null && other.idRequente != null) || (this.idRequente != null && !this.idRequente.equals(other.idRequente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fenix.iure.entities.Requente[ idRequente=" + idRequente + " ]";
    }
    
}
