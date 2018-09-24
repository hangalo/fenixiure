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
public class Processo implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer codigoProcesso;
    private String numeroProcesso;
    private EspecieProcesso especieProcesso;
    private Requerente requerente;
    private Requerido requerido;
    private Advogado advogado;
    private Juiz juiz;
    private Date dataEntrada;
    private EstadoProcesso estadoProcesso;
    private Date dataConclusao;
    private String resumoDespacho;
    private TipoDecisao tipoDecisao;

    public Integer getCodigoProcesso() {
        return codigoProcesso;
    }

    public void setCodigoProcesso(Integer codigoProcesso) {
        this.codigoProcesso = codigoProcesso;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public EspecieProcesso getEspecieProcesso() {
        return especieProcesso;
    }

    public void setEspecieProcesso(EspecieProcesso especieProcesso) {
        this.especieProcesso = especieProcesso;
    }

    public Requerente getRequerente() {
        return requerente;
    }

    public void setRequerente(Requerente requerente) {
        this.requerente = requerente;
    }

    public Requerido getRequerido() {
        return requerido;
    }

    public void setRequerido(Requerido requerido) {
        this.requerido = requerido;
    }

    public Advogado getAdvogado() {
        return advogado;
    }

    public void setAdvogado(Advogado advogado) {
        this.advogado = advogado;
    }

    public Juiz getJuiz() {
        return juiz;
    }

    public void setJuiz(Juiz juiz) {
        this.juiz = juiz;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public EstadoProcesso getEstadoProcesso() {
        return estadoProcesso;
    }

    public void setEstadoProcesso(EstadoProcesso estadoProcesso) {
        this.estadoProcesso = estadoProcesso;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getResumoDespacho() {
        return resumoDespacho;
    }

    public void setResumoDespacho(String resumoDespacho) {
        this.resumoDespacho = resumoDespacho;
    }

    public TipoDecisao getTipoDecisao() {
        return tipoDecisao;
    }

    public void setTipoDecisao(TipoDecisao tipoDecisao) {
        this.tipoDecisao = tipoDecisao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.codigoProcesso);
        hash = 97 * hash + Objects.hashCode(this.numeroProcesso);
        hash = 97 * hash + Objects.hashCode(this.especieProcesso);
        hash = 97 * hash + Objects.hashCode(this.requerente);
        hash = 97 * hash + Objects.hashCode(this.requerido);
        hash = 97 * hash + Objects.hashCode(this.advogado);
        hash = 97 * hash + Objects.hashCode(this.juiz);
        hash = 97 * hash + Objects.hashCode(this.dataEntrada);
        hash = 97 * hash + Objects.hashCode(this.estadoProcesso);
        hash = 97 * hash + Objects.hashCode(this.dataConclusao);
        hash = 97 * hash + Objects.hashCode(this.resumoDespacho);
        hash = 97 * hash + Objects.hashCode(this.tipoDecisao);
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
        final Processo other = (Processo) obj;
        if (!Objects.equals(this.numeroProcesso, other.numeroProcesso)) {
            return false;
        }
        if (!Objects.equals(this.resumoDespacho, other.resumoDespacho)) {
            return false;
        }
        if (!Objects.equals(this.codigoProcesso, other.codigoProcesso)) {
            return false;
        }
        if (!Objects.equals(this.especieProcesso, other.especieProcesso)) {
            return false;
        }
        if (!Objects.equals(this.requerente, other.requerente)) {
            return false;
        }
        if (!Objects.equals(this.requerido, other.requerido)) {
            return false;
        }
        if (!Objects.equals(this.advogado, other.advogado)) {
            return false;
        }
        if (!Objects.equals(this.juiz, other.juiz)) {
            return false;
        }
        if (!Objects.equals(this.dataEntrada, other.dataEntrada)) {
            return false;
        }
        if (!Objects.equals(this.estadoProcesso, other.estadoProcesso)) {
            return false;
        }
        if (!Objects.equals(this.dataConclusao, other.dataConclusao)) {
            return false;
        }
        if (!Objects.equals(this.tipoDecisao, other.tipoDecisao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Processo{" + "codigoProcesso=" + codigoProcesso + ", numeroProcesso=" + numeroProcesso + ", especieProcesso=" + especieProcesso + ", requerente=" + requerente + ", requerido=" + requerido + ", advogado=" + advogado + ", juiz=" + juiz + ", dataEntrada=" + dataEntrada + ", estadoProcesso=" + estadoProcesso + ", dataConclusao=" + dataConclusao + ", resumoDespacho=" + resumoDespacho + ", tipoDecisao=" + tipoDecisao + '}';
    }
    
    
    
    
}
