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
public class Requerido implements Serializable{
      private static final long serialVersionUID = 1L;
      private Integer idRequerido;
      private String nomeRequerido;
      private String sobrenomeRequerido;
      private String nbiRequerido;
      private String casaRequerido;
      private String ruaRequerido;
      private String bairroRequerdo;
      private Municipio municipio;
      private TipoPessoa tipo;

    public Requerido() {
    }

    public Requerido(Integer idRequerido, String nomeRequerido, String sobrenomeRequerido, String nbiRequerido, String casaRequerido, String ruaRequerido, String bairroRequerdo, Municipio municipio, TipoPessoa tipo) {
        this.idRequerido = idRequerido;
        this.nomeRequerido = nomeRequerido;
        this.sobrenomeRequerido = sobrenomeRequerido;
        this.nbiRequerido = nbiRequerido;
        this.casaRequerido = casaRequerido;
        this.ruaRequerido = ruaRequerido;
        this.bairroRequerdo = bairroRequerdo;
        this.municipio = municipio;
        this.tipo = tipo;
    }

   

    public Integer getIdRequerido() {
        return idRequerido;
    }

    public void setIdRequerido(Integer idRequerido) {
        this.idRequerido = idRequerido;
    }

    public String getNomeRequerido() {
        return nomeRequerido;
    }

    public void setNomeRequerido(String nomeRequerido) {
        this.nomeRequerido = nomeRequerido;
    }

    public String getSobrenomeRequerido() {
        return sobrenomeRequerido;
    }

    public void setSobrenomeRequerido(String sobrenomeRequerido) {
        this.sobrenomeRequerido = sobrenomeRequerido;
    }

    public String getNbiRequerido() {
        return nbiRequerido;
    }

    public void setNbiRequerido(String nbiRequerido) {
        this.nbiRequerido = nbiRequerido;
    }

    public String getCasaRequerido() {
        return casaRequerido;
    }

    public void setCasaRequerido(String casaRequerido) {
        this.casaRequerido = casaRequerido;
    }

    public String getRuaRequerido() {
        return ruaRequerido;
    }

    public void setRuaRequerido(String ruaRequerido) {
        this.ruaRequerido = ruaRequerido;
    }

    public String getBairroRequerdo() {
        return bairroRequerdo;
    }

    public void setBairroRequerdo(String bairroRequerdo) {
        this.bairroRequerdo = bairroRequerdo;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }

 

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.idRequerido);
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
        final Requerido other = (Requerido) obj;
        if (!Objects.equals(this.idRequerido, other.idRequerido)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Requerido{" + "idRequerido=" + idRequerido + ", nomeRequerido=" + nomeRequerido + ", sobrenomeRequerido=" + sobrenomeRequerido + ", nbiRequerido=" + nbiRequerido + ", casaRequerido=" + casaRequerido + ", ruaRequerido=" + ruaRequerido + ", bairroRequerdo=" + bairroRequerdo + ", municipio=" + municipio + ", tipo=" + tipo + '}';
    }

   
      
      
      
      
}
