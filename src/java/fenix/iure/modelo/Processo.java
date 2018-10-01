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
    
    private Integer idProcesso;
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

    public Processo() {
    }

    public Processo(Integer idProcesso, String numeroProcesso, EspecieProcesso especieProcesso, Requerente requerente, Requerido requerido, Advogado advogado, Juiz juiz, Date dataEntrada, EstadoProcesso estadoProcesso, Date dataConclusao, String resumoDespacho, TipoDecisao tipoDecisao) {
        this.idProcesso = idProcesso;
        this.numeroProcesso = numeroProcesso;
        this.especieProcesso = especieProcesso;
        this.requerente = requerente;
        this.requerido = requerido;
        this.advogado = advogado;
        this.juiz = juiz;
        this.dataEntrada = dataEntrada;
        this.estadoProcesso = estadoProcesso;
        this.dataConclusao = dataConclusao;
        this.resumoDespacho = resumoDespacho;
        this.tipoDecisao = tipoDecisao;
    }

    public Integer getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Integer idProcesso) {
        this.idProcesso = idProcesso;
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
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.idProcesso);
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
        if (!Objects.equals(this.idProcesso, other.idProcesso)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Processo{" + "idProcesso=" + idProcesso + ", numeroProcesso=" + numeroProcesso + ", especieProcesso=" + especieProcesso + ", requerente=" + requerente + ", requerido=" + requerido + ", advogado=" + advogado + ", juiz=" + juiz + ", dataEntrada=" + dataEntrada + ", estadoProcesso=" + estadoProcesso + ", dataConclusao=" + dataConclusao + ", resumoDespacho=" + resumoDespacho + ", tipoDecisao=" + tipoDecisao + '}';
    }
    
    
    
    
}
