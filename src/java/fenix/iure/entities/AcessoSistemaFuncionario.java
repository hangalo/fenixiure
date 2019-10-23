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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "acesso_sistema_funcionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcessoSistemaFuncionario.findAll", query = "SELECT a FROM AcessoSistemaFuncionario a")
    , @NamedQuery(name = "AcessoSistemaFuncionario.findByIdAcessoSistema", query = "SELECT a FROM AcessoSistemaFuncionario a WHERE a.idAcessoSistema = :idAcessoSistema")
    , @NamedQuery(name = "AcessoSistemaFuncionario.findByLoginFuncionario", query = "SELECT a FROM AcessoSistemaFuncionario a WHERE a.loginFuncionario = :loginFuncionario")
    , @NamedQuery(name = "AcessoSistemaFuncionario.findByPasswordFuncionario", query = "SELECT a FROM AcessoSistemaFuncionario a WHERE a.passwordFuncionario = :passwordFuncionario")})
public class AcessoSistemaFuncionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_acesso_sistema")
    private Integer idAcessoSistema;
    @Size(max = 45)
    @Column(name = "login_funcionario")
    private String loginFuncionario;
    @Size(max = 45)
    @Column(name = "password_funcionario")
    private String passwordFuncionario;
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id_funcionario")
    @ManyToOne(optional = false)
    private Funcionario idFuncionario;

    public AcessoSistemaFuncionario() {
    }

    public AcessoSistemaFuncionario(Integer idAcessoSistema) {
        this.idAcessoSistema = idAcessoSistema;
    }

    public Integer getIdAcessoSistema() {
        return idAcessoSistema;
    }

    public void setIdAcessoSistema(Integer idAcessoSistema) {
        this.idAcessoSistema = idAcessoSistema;
    }

    public String getLoginFuncionario() {
        return loginFuncionario;
    }

    public void setLoginFuncionario(String loginFuncionario) {
        this.loginFuncionario = loginFuncionario;
    }

    public String getPasswordFuncionario() {
        return passwordFuncionario;
    }

    public void setPasswordFuncionario(String passwordFuncionario) {
        this.passwordFuncionario = passwordFuncionario;
    }

    public Funcionario getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Funcionario idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcessoSistema != null ? idAcessoSistema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcessoSistemaFuncionario)) {
            return false;
        }
        AcessoSistemaFuncionario other = (AcessoSistemaFuncionario) object;
        if ((this.idAcessoSistema == null && other.idAcessoSistema != null) || (this.idAcessoSistema != null && !this.idAcessoSistema.equals(other.idAcessoSistema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fenix.iure.entities.AcessoSistemaFuncionario[ idAcessoSistema=" + idAcessoSistema + " ]";
    }
    
}
