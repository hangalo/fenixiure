/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.Juiz;
import fenix.iure.util.DateUtil;
import java.sql.Date;
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
public class JuizDAOTest {

    /**
     * Test of save method, of class JuizDAO.
     */
    @Test
    public void testSave() {
        /*System.out.println("save");
        String dataNacString = "1993/04/9";
        String dataIniString = "2018/08/21";
        java.util.Date dataNasFormatada = DateUtil.strToDate(dataNacString);
        java.util.Date dataIniFormatada = DateUtil.strToDate(dataIniString);
        Juiz juiz = new Juiz(null, "Eufrazia ", "Kavaimunwa", dataNasFormatada, dataIniFormatada);
        JuizDAO instance = new JuizDAO();
        boolean expResult = false;
        boolean result = instance.save(juiz);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");*/
    }

    /**
     * Test of update method, of class JuizDAO.
     */
    @Test
    public void testUpdate() {
        /*String dataNacString = "1993/04/9";
        String dataIniString = "2018/08/21";
        java.util.Date dataNasFormatada = DateUtil.strToDate(dataNacString);
        java.util.Date dataIniFormatada = DateUtil.strToDate(dataIniString);
        Juiz juiz = new Juiz(1, "Cardoso ", "Kavaimunwa", dataNasFormatada, dataIniFormatada);
        JuizDAO instance = new JuizDAO();
        boolean expResult = false;
        boolean result = instance.update(juiz);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");*/
    }

    /**
     * Test of delete method, of class JuizDAO.
     */
    @Test
    public void testDelete() {
       /* System.out.println("delete");
        Juiz juiz = new Juiz();
        juiz.setIdJuiz(1);
        JuizDAO instance = new JuizDAO();
        boolean expResult = false;
        boolean result = instance.delete(juiz);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");*/
    }

    /**
     * Test of findById method, of class JuizDAO.
     */
    @Test
    public void testFindById() {
        /*System.out.println("findById");
        Integer id = 4;
        JuizDAO instance = new JuizDAO();
        Juiz expResult = null;
        Juiz juiz = instance.findById(id);
        assertTrue(juiz != null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        System.out.println("Id: " + juiz.getIdJuiz() );
        System.out.println("Nome: " + juiz.getNomeJuiz() );
        System.out.println("Sobrenome: " + juiz.getSobreNomeJuiz() );
        System.out.println("DtaNascimento: " + DateUtil.formataData(juiz.getDataNascimento()) );
        System.out.println("DtaInicioFunção: " + DateUtil.formataData(juiz.getDataInicioFuncoes()) );
    */
    }

    /**
     * Test of findAll method, of class JuizDAO.
     */
    @Test
    public void testFindAll() {
        /*System.out.println("findAll");
        JuizDAO instance = new JuizDAO();
        List<Juiz> expResult = null;
        List<Juiz> juizes = instance.findAll();
        assertTrue(juizes.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        for (Juiz juiz : juizes) {
            System.out.println("Id: " + juiz.getIdJuiz() );
            System.out.println("Nome: " + juiz.getNomeJuiz() );
            System.out.println("Sobrenome: " + juiz.getSobreNomeJuiz() );
            System.out.println("DtaNascimento: " + DateUtil.formataData(juiz.getDataNascimento()) );
            System.out.println("DtaInicioFunção: " + DateUtil.formataData(juiz.getDataInicioFuncoes()) );
            System.out.println("\n");
        }*/
    }

    /**
     * Test of popularComDados method, of class JuizDAO.
     */
    @Test
    public void testPopularComDados() {
        /*System.out.println("popularComDados");
        Juiz juiz = null;
        ResultSet rs = null;
        JuizDAO instance = new JuizDAO();
        instance.popularComDados(juiz, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

}
