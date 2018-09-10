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
public class Requerente  implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer idRequerente;
    private String nomeRequerente;
    private String sobrenomeRequerente;
    private String nbiRequerente;
    private String casaRequerente;
    private String ruaRequerente;
    private String bairroRequerente;
    private Municipio municipio;
    private TipoPessoa idTipo;

    public Requerente() {
    }

    public Requerente(Integer idRequerente, String nomeRequerente, String sobrenomeRequerente, String nbiRequerente, String casaRequerente, String ruaRequerente, String bairroRequerente, Municipio municipio, TipoPessoa idTipo) {
        this.idRequerente = idRequerente;
        this.nomeRequerente = nomeRequerente;
        this.sobrenomeRequerente = sobrenomeRequerente;
        this.nbiRequerente = nbiRequerente;
        this.casaRequerente = casaRequerente;
        this.ruaRequerente = ruaRequerente;
        this.bairroRequerente = bairroRequerente;
        this.municipio = municipio;
        this.idTipo = idTipo;
    }

    public Integer getIdRequerente() {
        return idRequerente;
    }

    public void setIdRequerente(Integer idRequerente) {
        this.idRequerente = idRequerente;
    }

    public String getNomeRequerente() {
        return nomeRequerente;
    }

    public void setNomeRequerente(String nomeRequerente) {
        this.nomeRequerente = nomeRequerente;
    }

    public String getSobrenomeRequerente() {
        return sobrenomeRequerente;
    }

    public void setSobrenomeRequerente(String sobrenomeRequerente) {
        this.sobrenomeRequerente = sobrenomeRequerente;
    }

    public String getNbiRequerente() {
        return nbiRequerente;
    }

    public void setNbiRequerente(String nbiRequerente) {
        this.nbiRequerente = nbiRequerente;
    }

    public String getCasaRequerente() {
        return casaRequerente;
    }

    public void setCasaRequerente(String casaRequerente) {
        this.casaRequerente = casaRequerente;
    }

    public String getRuaRequerente() {
        return ruaRequerente;
    }

    public void setRuaRequerente(String ruaRequerente) {
        this.ruaRequerente = ruaRequerente;
    }

    public String getBairroRequerente() {
        return bairroRequerente;
    }

    public void setBairroRequerente(String bairroRequerente) {
        this.bairroRequerente = bairroRequerente;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public TipoPessoa getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoPessoa idTipo) {
        this.idTipo = idTipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idRequerente);
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
        final Requerente other = (Requerente) obj;
        if (!Objects.equals(this.idRequerente, other.idRequerente)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Requerente{" + "idRequerente=" + idRequerente + ", nomeRequerente=" + nomeRequerente + ", sobrenomeRequerente=" + sobrenomeRequerente + ", nbiRequerente=" + nbiRequerente + ", casaRequerente=" + casaRequerente + ", ruaRequerente=" + ruaRequerente + ", bairroRequerente=" + bairroRequerente + ", municipio=" + municipio + ", idTipo=" + idTipo + '}';
    }
    
    
    
    
    
}
