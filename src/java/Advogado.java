/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Elísio Kavaimunwa
 */
public class Advogado implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer idAdvogado;
    private String nomeAdvogado;
    private String sobreNomeAdvogado;
    private Date dataNascimento;
    private Date dataInicioFuncoes;

    public Advogado() {
    }

    public Advogado(Integer idAdvogado, String nomeAdvogado, String sobreNomeAdvogado, Date dataNascimento, Date dataInicioFuncoes) {
        this.idAdvogado = idAdvogado;
        this.nomeAdvogado = nomeAdvogado;
        this.sobreNomeAdvogado = sobreNomeAdvogado;
        this.dataNascimento = dataNascimento;
        this.dataInicioFuncoes = dataInicioFuncoes;
    }

    public Integer getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(Integer idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public String getNomeAdvogado() {
        return nomeAdvogado;
    }

    public void setNomeAdvogado(String nomeAdvogado) {
        this.nomeAdvogado = nomeAdvogado;
    }

    public String getSobreNomeAdvogado() {
        return sobreNomeAdvogado;
    }

    public void setSobreNomeAdvogado(String sobreNomeAdvogado) {
        this.sobreNomeAdvogado = sobreNomeAdvogado;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataInicioFuncoes() {
        return dataInicioFuncoes;
    }

    public void setDataInicioFuncoes(Date dataInicioFuncoes) {
        this.dataInicioFuncoes = dataInicioFuncoes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idAdvogado);
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
        final Advogado other = (Advogado) obj;
        if (!Objects.equals(this.idAdvogado, other.idAdvogado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Advogado{" + "idAdvogado=" + idAdvogado + ", nomeAdvogado=" + nomeAdvogado + ", sobreNomeAdvogado=" + sobreNomeAdvogado + ", dataNascimento=" + dataNascimento + ", dataInicioFuncoes=" + dataInicioFuncoes + '}';
    }
    
    
}
