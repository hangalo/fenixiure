/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.Advogado;
import fenix.iure.util.DateUtil;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Elísio Kavaimunwa
 */
public class AdvogadoDAOTest {

    /**
     * Test of save method, of class AdvogadoDAO.
     */
    @Test
    public void testSave() {
        /*System.out.println("save");
        String dataNacString = "1993/04/9";
        String dataIniString = "2018/08/21";
        java.util.Date dataNasFormatada = DateUtil.strToDate(dataNacString);
        java.util.Date dataIniFormatada = DateUtil.strToDate(dataIniString);
        Advogado advogado = new Advogado(null, "Vladmiro", "Kavaimunwa", dataNasFormatada, dataIniFormatada);
        AdvogadoDAO instance = new AdvogadoDAO();
        boolean expResult = false;
        boolean result = instance.save(advogado);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");*/
    }

    /**
     * Test of update method, of class AdvogadoDAO.
     */
    @Test
    public void testUpdate() {
        /*System.out.println("update");
        String dataNacString = "1993/04/9";
        String dataIniString = "2018/08/21";
        java.util.Date dataNasFormatada = DateUtil.strToDate(dataNacString);
        java.util.Date dataIniFormatada = DateUtil.strToDate(dataIniString);
        Advogado advogado = new Advogado(1, "Iladio", "Kavaimunwa", dataNasFormatada, dataIniFormatada);
        AdvogadoDAO instance = new AdvogadoDAO();
        boolean expResult = false;
        boolean result = instance.update(advogado);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");*/
    }

    /**
     * Test of delete method, of class AdvogadoDAO.
     */
    @Test
    public void testDelete() {
        /*System.out.println("delete");
        Advogado advogado = new Advogado();
        advogado.setIdAdvogado(1);
        AdvogadoDAO instance = new AdvogadoDAO();
        boolean expResult = false;
        boolean result = instance.delete(advogado);
        assertTrue(result);;
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");*/
    }

    /**
     * Test of findById method, of class AdvogadoDAO.
     */
    @Test
    public void testFindById() {
        /*System.out.println("findById");
        Integer id = 2;
        AdvogadoDAO instance = new AdvogadoDAO();
        Advogado expResult = null;
        Advogado advogado = instance.findById(id);
        assertTrue(advogado != null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        System.out.println("Id: " + advogado.getIdAdvogado());
        System.out.println("Nome: " + advogado.getNomeAdvogado());
        System.out.println("Sobrenome: " + advogado.getSobreNomeAdvogado());
        System.out.println("DtaNascimento: " + DateUtil.formataData(advogado.getDataNascimento()));
        System.out.println("DtaInicioFunção: " + DateUtil.formataData(advogado.getDataInicioFuncoes()));
    */
        }

    /**
     * Test of findAll method, of class AdvogadoDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        AdvogadoDAO instance = new AdvogadoDAO();
        List<Advogado> expResult = null;
        List<Advogado> advogados = instance.findAll();
        assertTrue(advogados.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        for (Advogado advogado : advogados) {
            System.out.println("Id: " + advogado.getIdAdvogado());
            System.out.println("Nome: " + advogado.getNomeAdvogado());
            System.out.println("Sobrenome: " + advogado.getSobreNomeAdvogado());
            System.out.println("DtaNascimento: " + DateUtil.formataData(advogado.getDataNascimento()));
            System.out.println("DtaInicioFunção: " + DateUtil.formataData(advogado.getDataInicioFuncoes()));
            System.out.println("\n");
        }
    }

    /**
     * Test of popularComDados method, of class AdvogadoDAO.
     */
    @Test
    public void testPopularComDados() {
        /*System.out.println("popularComDados");
        Advogado advogado = null;
        ResultSet rs = null;
        AdvogadoDAO instance = new AdvogadoDAO();
        instance.popularComDados(advogado, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

}
