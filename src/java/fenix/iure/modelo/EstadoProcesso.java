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
 * @author Aisha Lubadika
 */
public class EstadoProcesso implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer idEstadoProcesso;
    private String estadoProcesso;

    public EstadoProcesso() {
    }

    public EstadoProcesso(Integer idEstadoProcesso, String estadoProcesso) {
        this.idEstadoProcesso = idEstadoProcesso;
        this.estadoProcesso = estadoProcesso;
    }

    public Integer getIdEstadoProcesso() {
        return idEstadoProcesso;
    }

    public void setIdEstadoProcesso(Integer idEstadoProcesso) {
        this.idEstadoProcesso = idEstadoProcesso;
    }

    public String getEstadoProcesso() {
        return estadoProcesso;
    }

    public void setEstadoProcesso(String estadoProcesso) {
        this.estadoProcesso = estadoProcesso;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.idEstadoProcesso);
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
        final EstadoProcesso other = (EstadoProcesso) obj;
        if (!Objects.equals(this.idEstadoProcesso, other.idEstadoProcesso)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EstadoProcesso{" + "idEstadoProcesso=" + idEstadoProcesso + ", estadoProcesso=" + estadoProcesso + '}';
    }
    
    
    

}
