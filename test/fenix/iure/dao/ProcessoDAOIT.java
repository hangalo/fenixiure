/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.Advogado;
import fenix.iure.modelo.EspecieProcesso;
import fenix.iure.modelo.EstadoProcesso;
import fenix.iure.modelo.Juiz;
import fenix.iure.modelo.Processo;
import fenix.iure.modelo.Requerente;
import fenix.iure.modelo.Requerido;
import fenix.iure.modelo.TipoDecisao;
import fenix.iure.util.DateUtil;
import java.sql.ResultSet;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Elísio Kavaimunwa
 */
public class ProcessoDAOIT {
    
    
    
    /**
     * Test of save method, of class ProcessoDAO.
     */
    @Test
    public void testSave() {
        /*System.out.println("save");
        Processo processo = new Processo();
        String numeroProcesso ="0021547";
        String dataConclusao = "1993/04/09";
        String dataEntrada = "2018/08/21";
        java.util.Date dataConclusaoFormatada = DateUtil.strToDate(dataConclusao);
        java.util.Date dataEntradaFormatada = DateUtil.strToDate(dataEntrada);
        
        String resumo ="Este é apenas um teste";
        EspecieProcesso especieProcesso = new EspecieProcesso();
        especieProcesso.setIdEspecieProcesso(1);
        Requerente requerente = new Requerente();
        requerente.setIdRequerente(1);
        Requerido requerido = new Requerido();
        requerido.setIdRequerido(1);
        Advogado advogado = new Advogado();
        advogado.setIdAdvogado(2);
        Juiz juiz = new Juiz();
        juiz.setIdJuiz(1);
        EstadoProcesso estadoProcesso = new EstadoProcesso();
        estadoProcesso.setIdEstadoProcesso(1);
        TipoDecisao tipoDecisao = new TipoDecisao();
        tipoDecisao.setIdTipoDecisao(1);
        
        processo.setNumeroProcesso(numeroProcesso);
        processo.setDataEntrada(dataConclusaoFormatada);
        processo.setDataConclusao(dataEntradaFormatada);
        processo.setResumoDespacho(resumo);
        processo.setEspecieProcesso(especieProcesso);
        processo.setRequerente(requerente);
        processo.setRequerido(requerido);
        processo.setAdvogado(advogado);
        processo.setJuiz(juiz);
        processo.setEstadoProcesso(estadoProcesso);
        processo.setTipoDecisao(tipoDecisao);
        
        
        ProcessoDAO instance = new ProcessoDAO();
        boolean expResult = false;
        boolean result = instance.save(processo);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");*/
    }

    /**
     * Test of update method, of class ProcessoDAO.
     */
    @Test
    public void testUpdate() {
        /*System.out.println("update");
        Processo processo = new Processo();
       
        String numeroProcesso ="0000019";
        String dataConclusao = "1993/04/09";
        String dataEntrada = "2018/08/21";
        java.util.Date dataConclusaoFormatada = DateUtil.strToDate(dataConclusao);
        java.util.Date dataEntradaFormatada = DateUtil.strToDate(dataEntrada);
        
        String resumo ="Este é apenas um teste";
        EspecieProcesso especieProcesso = new EspecieProcesso();
        especieProcesso.setIdEspecieProcesso(1);
        Requerente requerente = new Requerente();
        requerente.setIdRequerente(1);
        Requerido requerido = new Requerido();
        requerido.setIdRequerido(1);
        Advogado advogado = new Advogado();
        advogado.setIdAdvogado(2);
        Juiz juiz = new Juiz();
        juiz.setIdJuiz(1);
        EstadoProcesso estadoProcesso = new EstadoProcesso();
        estadoProcesso.setIdEstadoProcesso(1);
        TipoDecisao tipoDecisao = new TipoDecisao();
        tipoDecisao.setIdTipoDecisao(1);
        
        processo.setCodigoProcesso(1);
        processo.setNumeroProcesso(numeroProcesso);
        processo.setDataEntrada(dataConclusaoFormatada);
        processo.setDataConclusao(dataEntradaFormatada);
        processo.setResumoDespacho(resumo);
        processo.setEspecieProcesso(especieProcesso);
        processo.setRequerente(requerente);
        processo.setRequerido(requerido);
        processo.setAdvogado(advogado);
        processo.setJuiz(juiz);
        processo.setEstadoProcesso(estadoProcesso);
        processo.setTipoDecisao(tipoDecisao);
        
        ProcessoDAO instance = new ProcessoDAO();

        boolean expResult = false;
        boolean result = instance.update(processo);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype");*/
    }

    /**
     * Test of delete method, of class ProcessoDAO.
     */
    @Test
    public void testDelete() {
        /*System.out.println("delete");
        Processo processo = new Processo();
        processo.setCodigoProcesso(2);
        ProcessoDAO instance = new ProcessoDAO();
        boolean expResult = false;
        boolean result = instance.delete(processo);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        */
    }

    /**
     * Test of findById method, of class ProcessoDAO.
     */
    @Test
    public void testFindById() {
        /*System.out.println("findById");
        Integer id = 3;
        ProcessoDAO instance = new ProcessoDAO();
        Processo expResult = null;
        Processo processo = instance.findById(id);
        assertTrue(processo != null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        

        System.out.println("Id: " +processo.getCodigoProcesso());
        System.out.println("Numero: "+ processo.getNumeroProcesso());
        System.out.println("Data Entrada: " + DateUtil.formataData(processo.getDataEntrada()));
        System.out.println("Data Conclusão: " + DateUtil.formataData(processo.getDataConclusao()));
        System.out.println("Resumo: " + processo.getResumoDespacho());
        System.out.println("Requerente: " + processo.getRequerente().getNomeRequerente());
        System.out.println("Requerido: " + processo.getRequerido().getNomeRequerido());
        System.out.println("Advogado: " + processo.getAdvogado().getNomeAdvogado());
        System.out.println("Juiz: " + processo.getJuiz().getNomeJuiz());
        System.out.println("Estado: " + processo.getEstadoProcesso().getEstadoProcesso());
        System.out.println("Especie: " + processo.getEspecieProcesso().getEspecieProcesso());
        System.out.println("Tipo Decisão: " + processo.getTipoDecisao().getTipoDecisao());
        System.out.println("\n");*/
        
        
        
    }

    /**
     * Test of findAll method, of class ProcessoDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        ProcessoDAO instance = new ProcessoDAO();
        List<Processo> expResult = null;
        List<Processo> processos = instance.findAll();
        assertTrue(processos.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        
        for (Processo processo : processos) {
            System.out.println("Id: " +processo.getCodigoProcesso());
            System.out.println("Numero: "+ processo.getNumeroProcesso());
            System.out.println("Data Entrada: " + DateUtil.formataData(processo.getDataEntrada()));
            System.out.println("Data Conclusão: " + DateUtil.formataData(processo.getDataConclusao()));
            System.out.println("Resumo: " + processo.getResumoDespacho());
            System.out.println("Requerente: " + processo.getRequerente().getNomeRequerente());
            System.out.println("Requerido: " + processo.getRequerido().getNomeRequerido());
            System.out.println("Advogado: " + processo.getAdvogado().getNomeAdvogado());
            System.out.println("Juiz: " + processo.getJuiz().getNomeJuiz());
            System.out.println("Estado: " + processo.getEstadoProcesso().getEstadoProcesso());
            System.out.println("Especie: " + processo.getEspecieProcesso().getEspecieProcesso());
            System.out.println("Tipo Decisão: " + processo.getTipoDecisao().getTipoDecisao());
            System.out.println("\n");
        }
        
    }

    /**
     * Test of popularComDados method, of class ProcessoDAO.
     */
    @Test
    public void testPopularComDados() {
        /*System.out.println("popularComDados");
        Processo processo = null;
        ResultSet rs = null;
        ProcessoDAO instance = new ProcessoDAO();
        instance.popularComDados(processo, rs);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");*/
    }
    
}
