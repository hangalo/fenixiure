/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Aisha Lubadika
 */
public class EspecieProcesso implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer idEspecieProcesso;
    private String especieProcesso;

    public EspecieProcesso() {
    }

    public EspecieProcesso(Integer idEspecieProcesso, String especieProcesso) {
        this.idEspecieProcesso = idEspecieProcesso;
        this.especieProcesso = especieProcesso;
    }

    public Integer getIdEspecieProcesso() {
        return idEspecieProcesso;
    }

    public void setIdEspecieProcesso(Integer idEspecieProcesso) {
        this.idEspecieProcesso = idEspecieProcesso;
    }

    public String getEspecieProcesso() {
        return especieProcesso;
    }

    public void setEspecieProcesso(String especieProcesso) {
        this.especieProcesso = especieProcesso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.idEspecieProcesso);
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
        final EspecieProcesso other = (EspecieProcesso) obj;
        if (!Objects.equals(this.idEspecieProcesso, other.idEspecieProcesso)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EspecieProcesso{" + "idEspecieProcesso=" + idEspecieProcesso + ", especieProcesso=" + especieProcesso + '}';
    }

    
    
    
    
}
