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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Elísio Kavaimunwa
 */
@Entity
@Table(name = "acesso_sistema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcessoSistema.findAll", query = "SELECT a FROM AcessoSistema a")
    , @NamedQuery(name = "AcessoSistema.findByIdAcessoSistema", query = "SELECT a FROM AcessoSistema a WHERE a.idAcessoSistema = :idAcessoSistema")
    , @NamedQuery(name = "AcessoSistema.findByLoginJuiz", query = "SELECT a FROM AcessoSistema a WHERE a.loginJuiz = :loginJuiz")
    , @NamedQuery(name = "AcessoSistema.findByPasswordJuiz", query = "SELECT a FROM AcessoSistema a WHERE a.passwordJuiz = :passwordJuiz")})
public class AcessoSistema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_acesso_sistema")
    private Integer idAcessoSistema;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "login_juiz")
    private String loginJuiz;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password_juiz")
    private String passwordJuiz;
    @JoinColumn(name = "id_juiz", referencedColumnName = "id_juiz")
    @ManyToOne
    private Juiz idJuiz;

    public AcessoSistema() {
    }

    public AcessoSistema(Integer idAcessoSistema) {
        this.idAcessoSistema = idAcessoSistema;
    }

    public AcessoSistema(Integer idAcessoSistema, String loginJuiz, String passwordJuiz) {
        this.idAcessoSistema = idAcessoSistema;
        this.loginJuiz = loginJuiz;
        this.passwordJuiz = passwordJuiz;
    }

    public Integer getIdAcessoSistema() {
        return idAcessoSistema;
    }

    public void setIdAcessoSistema(Integer idAcessoSistema) {
        this.idAcessoSistema = idAcessoSistema;
    }

    public String getLoginJuiz() {
        return loginJuiz;
    }

    public void setLoginJuiz(String loginJuiz) {
        this.loginJuiz = loginJuiz;
    }

    public String getPasswordJuiz() {
        return passwordJuiz;
    }

    public void setPasswordJuiz(String passwordJuiz) {
        this.passwordJuiz = passwordJuiz;
    }

    public Juiz getIdJuiz() {
        return idJuiz;
    }

    public void setIdJuiz(Juiz idJuiz) {
        this.idJuiz = idJuiz;
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
        if (!(object instanceof AcessoSistema)) {
            return false;
        }
        AcessoSistema other = (AcessoSistema) object;
        if ((this.idAcessoSistema == null && other.idAcessoSistema != null) || (this.idAcessoSistema != null && !this.idAcessoSistema.equals(other.idAcessoSistema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fenix.iure.entities.AcessoSistema[ idAcessoSistema=" + idAcessoSistema + " ]";
    }
    
}
