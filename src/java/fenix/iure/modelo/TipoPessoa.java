/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Elísio Kavaimunwa
 */
public class TipoPessoa implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer idTipoPessoa; 
    private String nomeTipoPessoa;

    public TipoPessoa() {
    }

    public TipoPessoa(Integer idTipoPessoa, String nomeTipoPessoa) {
        this.idTipoPessoa = idTipoPessoa;
        this.nomeTipoPessoa = nomeTipoPessoa;
    }

    public Integer getIdTipoPessoa() {
        return idTipoPessoa;
    }

    public void setIdTipoPessoa(Integer idTipoPessoa) {
        this.idTipoPessoa = idTipoPessoa;
    }

    public String getNomeTipoPessoa() {
        return nomeTipoPessoa;
    }

    public void setNomeTipoPessoa(String nomeTipoPessoa) {
        this.nomeTipoPessoa = nomeTipoPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.idTipoPessoa);
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
        final TipoPessoa other = (TipoPessoa) obj;
        if (!Objects.equals(this.idTipoPessoa, other.idTipoPessoa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoPessoa{" + "idTipoPessoa=" + idTipoPessoa + ", nomeTipoPessoa=" + nomeTipoPessoa + '}';
    }
    
    
    
}
