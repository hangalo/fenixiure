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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Entity
@Table(name = "processo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Processo.findAll", query = "SELECT p FROM Processo p")
    , @NamedQuery(name = "Processo.findByIdProcesso", query = "SELECT p FROM Processo p WHERE p.idProcesso = :idProcesso")
    , @NamedQuery(name = "Processo.findByNumeroProcesso", query = "SELECT p FROM Processo p WHERE p.numeroProcesso = :numeroProcesso")
    , @NamedQuery(name = "Processo.findByDataEntrada", query = "SELECT p FROM Processo p WHERE p.dataEntrada = :dataEntrada")})
public class Processo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_processo")
    private Integer idProcesso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "numero_processo")
    private String numeroProcesso;
    @Column(name = "data_entrada")
    @Temporal(TemporalType.DATE)
    private Date dataEntrada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProcesso")
    private Collection<Tramitacao> tramitacaoCollection;
    @JoinColumn(name = "id_especie_processo", referencedColumnName = "id_especie_processo")
    @ManyToOne(optional = false)
    private EspecieProcesso idEspecieProcesso;
    @JoinColumn(name = "id_juiz", referencedColumnName = "id_juiz")
    @ManyToOne(optional = false)
    private Juiz idJuiz;
    @JoinColumn(name = "id_requente", referencedColumnName = "id_requente")
    @ManyToOne(optional = false)
    private Requente idRequente;
    @JoinColumn(name = "id_requerido", referencedColumnName = "id_requerido")
    @ManyToOne(optional = false)
    private Requerido idRequerido;

    public Processo() {
    }

    public Processo(Integer idProcesso) {
        this.idProcesso = idProcesso;
    }

    public Processo(Integer idProcesso, String numeroProcesso) {
        this.idProcesso = idProcesso;
        this.numeroProcesso = numeroProcesso;
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

    @XmlTransient
    public Collection<Tramitacao> getTramitacaoCollection() {
        return tramitacaoCollection;
    }

    public void setTramitacaoCollection(Collection<Tramitacao> tramitacaoCollection) {
        this.tramitacaoCollection = tramitacaoCollection;
    }

    public EspecieProcesso getIdEspecieProcesso() {
        return idEspecieProcesso;
    }

    public void setIdEspecieProcesso(EspecieProcesso idEspecieProcesso) {
        this.idEspecieProcesso = idEspecieProcesso;
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
