/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author informatica
 */
public class Provincia implements Serializable{

    private static final long serialVersionUID = 1L;
      private Integer idProvincia;
    private String nomeProvincia;
    

    public Provincia() {
    }

    public Provincia(Integer idProvincia, String nomeProvincia) {
        this.idProvincia = idProvincia;
        this.nomeProvincia = nomeProvincia;
        
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNomeProvincia() {
        return nomeProvincia;
    }

    public void setNomeProvincia(String nomeProvincia) {
        this.nomeProvincia = nomeProvincia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.idProvincia);
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
        final Provincia other = (Provincia) obj;
        if (!Objects.equals(this.idProvincia, other.idProvincia)) {
            return false;
        }
        return true;
    }
    
       
    

    @Override
    public String toString() {
        return this.nomeProvincia;
    }

   

   
   
}
