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
public class TipoDecisao implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer idTipoDecisao;
    private String tipoDecisao;

    public TipoDecisao() {
    }

    public TipoDecisao(Integer idTipoDecisao, String tipoDecisao) {
        this.idTipoDecisao = idTipoDecisao;
        this.tipoDecisao = tipoDecisao;
    }

    public Integer getIdTipoDecisao() {
        return idTipoDecisao;
    }

    public void setIdTipoDecisao(Integer idTipoDecisao) {
        this.idTipoDecisao = idTipoDecisao;
    }

    public String getTipoDecisao() {
        return tipoDecisao;
    }

    public void setTipoDecisao(String tipoDecisao) {
        this.tipoDecisao = tipoDecisao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.idTipoDecisao);
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
        final TipoDecisao other = (TipoDecisao) obj;
        if (!Objects.equals(this.idTipoDecisao, other.idTipoDecisao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoDecisao{" + "idTipoDecisao=" + idTipoDecisao + ", tipoDecisao=" + tipoDecisao + '}';
    }
    
    
    
}
