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
@Table(name = "juiz")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Juiz.findAll", query = "SELECT j FROM Juiz j")
    , @NamedQuery(name = "Juiz.findByIdJuiz", query = "SELECT j FROM Juiz j WHERE j.idJuiz = :idJuiz")
    , @NamedQuery(name = "Juiz.findByNomeJuiz", query = "SELECT j FROM Juiz j WHERE j.nomeJuiz = :nomeJuiz")
    , @NamedQuery(name = "Juiz.findBySobrenomeJuiz", query = "SELECT j FROM Juiz j WHERE j.sobrenomeJuiz = :sobrenomeJuiz")
    , @NamedQuery(name = "Juiz.findByDataInicioFuncoes", query = "SELECT j FROM Juiz j WHERE j.dataInicioFuncoes = :dataInicioFuncoes")
    , @NamedQuery(name = "Juiz.findByUsuarioJuiz", query = "SELECT j FROM Juiz j WHERE j.usuarioJuiz = :usuarioJuiz")
    , @NamedQuery(name = "Juiz.findBySenhaJuiz", query = "SELECT j FROM Juiz j WHERE j.senhaJuiz = :senhaJuiz")
    , @NamedQuery(name = "Juiz.findByTipoUsuario", query = "SELECT j FROM Juiz j WHERE j.tipoUsuario = :tipoUsuario")})
public class Juiz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_juiz")
    private Integer idJuiz;
    @Size(max = 125)
    @Column(name = "nome_juiz")
    private String nomeJuiz;
    @Size(max = 125)
    @Column(name = "sobrenome_juiz")
    private String sobrenomeJuiz;
    @Column(name = "data_inicio_funcoes")
    @Temporal(TemporalType.DATE)
    private Date dataInicioFuncoes;
    @Size(max = 125)
    @Column(name = "usuario_juiz")
    private String usuarioJuiz;
    @Size(max = 125)
    @Column(name = "senha_juiz")
    private String senhaJuiz;
    @Size(max = 125)
    @Column(name = "tipo_usuario")
    private String tipoUsuario;
    @OneToMany(mappedBy = "idJuiz")
    private Collection<AcessoSistema> acessoSistemaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idJuiz")
    private Collection<Processo> processoCollection;

    public Juiz() {
    }

    public Juiz(Integer idJuiz) {
        this.idJuiz = idJuiz;
    }

    public Integer getIdJuiz() {
        return idJuiz;
    }

    public void setIdJuiz(Integer idJuiz) {
        this.idJuiz = idJuiz;
    }

    public String getNomeJuiz() {
        return nomeJuiz;
    }

    public void setNomeJuiz(String nomeJuiz) {
        this.nomeJuiz = nomeJuiz;
    }

    public String getSobrenomeJuiz() {
        return sobrenomeJuiz;
    }

    public void setSobrenomeJuiz(String sobrenomeJuiz) {
        this.sobrenomeJuiz = sobrenomeJuiz;
    }

    public Date getDataInicioFuncoes() {
        return dataInicioFuncoes;
    }

    public void setDataInicioFuncoes(Date dataInicioFuncoes) {
        this.dataInicioFuncoes = dataInicioFuncoes;
    }

    public String getUsuarioJuiz() {
        return usuarioJuiz;
    }

    public void setUsuarioJuiz(String usuarioJuiz) {
        this.usuarioJuiz = usuarioJuiz;
    }

    public String getSenhaJuiz() {
        return senhaJuiz;
    }

    public void setSenhaJuiz(String senhaJuiz) {
        this.senhaJuiz = senhaJuiz;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @XmlTransient
    public Collection<AcessoSistema> getAcessoSistemaCollection() {
        return acessoSistemaCollection;
    }

    public void setAcessoSistemaCollection(Collection<AcessoSistema> acessoSistemaCollection) {
        this.acessoSistemaCollection = acessoSistemaCollection;
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
        hash += (idJuiz != null ? idJuiz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Juiz)) {
            return false;
        }
        Juiz other = (Juiz) object;
        if ((this.idJuiz == null && other.idJuiz != null) || (this.idJuiz != null && !this.idJuiz.equals(other.idJuiz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fenix.iure.entities.Juiz[ idJuiz=" + idJuiz + " ]";
    }
    
}
