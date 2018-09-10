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
public class Municipio implements  Serializable {
      private static final long serialVersionUID = 1L;
      private Integer idMunicipio;
      private String nomeMunicipio;
      private Provincia idProvincia;

    public Municipio() {
    }

    public Municipio(Integer idMunicipio, String nomeMunicipio, Provincia idProvincia) {
        this.idMunicipio = idMunicipio;
        this.nomeMunicipio = nomeMunicipio;
        this.idProvincia = idProvincia;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public Provincia getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Provincia idProvincia) {
        this.idProvincia = idProvincia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idMunicipio);
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
        final Municipio other = (Municipio) obj;
        if (!Objects.equals(this.idMunicipio, other.idMunicipio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Municipio{" + "idMunicipio=" + idMunicipio + ", nomeMunicipio=" + nomeMunicipio + ", idProvincia=" + idProvincia + '}';
    }
      
}
