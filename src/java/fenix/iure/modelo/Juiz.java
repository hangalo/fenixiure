/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Elísio Kavaimunwa
 */
public class Juiz implements Serializable{
     private static final long serialVersionUID = 1L;
     
     private Integer idJuiz;
     private String nomeJuiz;
     private String sobreNomeJuiz;
     private Date dataNascimento;
     private Date dataInicioFuncoes;

    public Juiz() {
    }

    public Juiz(Integer idJuiz, String nomeJuiz, String sobreNomeJuiz, Date dataNascimento, Date dataInicioFuncoes) {
        this.idJuiz = idJuiz;
        this.nomeJuiz = nomeJuiz;
        this.sobreNomeJuiz = sobreNomeJuiz;
        this.dataNascimento = dataNascimento;
        this.dataInicioFuncoes = dataInicioFuncoes;
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

    public String getSobreNomeJuiz() {
        return sobreNomeJuiz;
    }

    public void setSobreNomeJuiz(String sobreNomeJuiz) {
        this.sobreNomeJuiz = sobreNomeJuiz;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataInicioFuncoes() {
        return dataInicioFuncoes;
    }

    public void setDataInicioFuncoes(Date dataInicioFuncoes) {
        this.dataInicioFuncoes = dataInicioFuncoes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idJuiz);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Juiz other = (Juiz) obj;
        if (!Objects.equals(this.idJuiz, other.idJuiz)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Juiz{" + "idJuiz=" + idJuiz + ", nomeJuiz=" + nomeJuiz + ", sobreNomeJuiz=" + sobreNomeJuiz + ", dataNascimento=" + dataNascimento + ", dataInicioFuncoes=" + dataInicioFuncoes + '}';
    }
     
    
     
    
}
