


import java.util.Objects;


public class Municipio {
    private Integer idMunicipio;
    private String nomeMunicipio;
    private Provincia provinciaMunicipio;

    public Municipio() {
    }

    public Municipio(Integer idMunicipio, String nomeMunicipio, Provincia provinciaMunicipio) {
        this.idMunicipio = idMunicipio;
        this.nomeMunicipio = nomeMunicipio;
        this.provinciaMunicipio = provinciaMunicipio;
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

    public Provincia getProvinciaMunicipio() {
        return provinciaMunicipio;
    }

    public void setProvinciaMunicipio(Provincia provinciaMunicipio) {
        this.provinciaMunicipio = provinciaMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.idMunicipio);
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
        return this.nomeMunicipio;
    }

   
    
}
