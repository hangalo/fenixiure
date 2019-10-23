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
@Table(name = "funcionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")
    , @NamedQuery(name = "Funcionario.findByIdFuncionario", query = "SELECT f FROM Funcionario f WHERE f.idFuncionario = :idFuncionario")
    , @NamedQuery(name = "Funcionario.findByNomeFuncionario", query = "SELECT f FROM Funcionario f WHERE f.nomeFuncionario = :nomeFuncionario")
    , @NamedQuery(name = "Funcionario.findBySobrenomeFuncionario", query = "SELECT f FROM Funcionario f WHERE f.sobrenomeFuncionario = :sobrenomeFuncionario")
    , @NamedQuery(name = "Funcionario.findByUsuarioFuncionario", query = "SELECT f FROM Funcionario f WHERE f.usuarioFuncionario = :usuarioFuncionario")
    , @NamedQuery(name = "Funcionario.findBySenhaFuncionario", query = "SELECT f FROM Funcionario f WHERE f.senhaFuncionario = :senhaFuncionario")
    , @NamedQuery(name = "Funcionario.findByTipoUsuario", query = "SELECT f FROM Funcionario f WHERE f.tipoUsuario = :tipoUsuario")})
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_funcionario")
    private Integer idFuncionario;
    @Size(max = 125)
    @Column(name = "nome_funcionario")
    private String nomeFuncionario;
    @Size(max = 125)
    @Column(name = "sobrenome_funcionario")
    private String sobrenomeFuncionario;
    @Size(max = 125)
    @Column(name = "usuario_funcionario")
    private String usuarioFuncionario;
    @Size(max = 125)
    @Column(name = "senha_funcionario")
    private String senhaFuncionario;
    @Size(max = 1)
    @Column(name = "tipo_usuario")
    private String tipoUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFuncionario")
    private Collection<AcessoSistemaFuncionario> acessoSistemaFuncionarioCollection;

    public Funcionario() {
    }

    public Funcionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getSobrenomeFuncionario() {
        return sobrenomeFuncionario;
    }

    public void setSobrenomeFuncionario(String sobrenomeFuncionario) {
        this.sobrenomeFuncionario = sobrenomeFuncionario;
    }

    public String getUsuarioFuncionario() {
        return usuarioFuncionario;
    }

    public void setUsuarioFuncionario(String usuarioFuncionario) {
        this.usuarioFuncionario = usuarioFuncionario;
    }

    public String getSenhaFuncionario() {
        return senhaFuncionario;
    }

    public void setSenhaFuncionario(String senhaFuncionario) {
        this.senhaFuncionario = senhaFuncionario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @XmlTransient
    public Collection<AcessoSistemaFuncionario> getAcessoSistemaFuncionarioCollection() {
        return acessoSistemaFuncionarioCollection;
    }

    public void setAcessoSistemaFuncionarioCollection(Collection<AcessoSistemaFuncionario> acessoSistemaFuncionarioCollection) {
        this.acessoSistemaFuncionarioCollection = acessoSistemaFuncionarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFuncionario != null ? idFuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.idFuncionario == null && other.idFuncionario != null) || (this.idFuncionario != null && !this.idFuncionario.equals(other.idFuncionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fenix.iure.entities.Funcionario[ idFuncionario=" + idFuncionario + " ]";
    }
    
}
