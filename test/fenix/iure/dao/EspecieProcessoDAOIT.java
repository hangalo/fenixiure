/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.EspecieProcesso;
import java.sql.ResultSet;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Elísio Kavaimunwa
 */
public class EspecieProcessoDAOIT {
    
    public EspecieProcessoDAOIT() {
    }

    /**
     * Test of save method, of class EspecieProcessoDAO.
     */
    @Test
    public void testSave() {
        /*System.out.println("save");
        EspecieProcesso especieProcesso = null;
        EspecieProcessoDAO instance = new EspecieProcessoDAO();
        boolean expResult = false;
        boolean result = instance.save(especieProcesso);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of update method, of class EspecieProcessoDAO.
     */
    @Test
    public void testUpdate() {
        /*System.out.println("update");
        EspecieProcesso especieProcesso = null;
        EspecieProcessoDAO instance = new EspecieProcessoDAO();
        boolean expResult = false;
        boolean result = instance.update(especieProcesso);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of delete method, of class EspecieProcessoDAO.
     */
    @Test
    public void testDelete() {
        /*System.out.println("delete");
        EspecieProcesso especieProcesso = null;
        EspecieProcessoDAO instance = new EspecieProcessoDAO();
        boolean expResult = false;
        boolean result = instance.delete(especieProcesso);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of findById method, of class EspecieProcessoDAO.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Integer id = 1;
        EspecieProcessoDAO instance = new EspecieProcessoDAO();
        EspecieProcesso especieProcesso = new EspecieProcesso();
        especieProcesso.setIdEspecieProcesso(id);
        EspecieProcesso especie = instance.findById(id);
        assertTrue(especie != null);
        System.out.println("Id: " + especie.getIdEspecieProcesso());
        System.out.println("Especie: " + especie.getEspecieProcesso());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        
        
        
    }

    /**
     * Test of findAll method, of class EspecieProcessoDAO.
     */
    @Test
    public void testFindAll() {
        /*System.out.println("findAll");
        EspecieProcessoDAO instance = new EspecieProcessoDAO();
        List<EspecieProcesso> expResult = null;
        List<EspecieProcesso> especies = instance.findAll();
        assertTrue(especies.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        for (EspecieProcesso especie : especies) {
            System.out.println("Id: " + especie.getIdEspecieProcesso());
            System.out.println("Especie: " + especie.getEspecieProcesso());
        }*/
    }

    /**
     * Test of popularComDados method, of class EspecieProcessoDAO.
     */
    @Test
    public void testPopularComDados() {
        /*System.out.println("popularComDados");
        EspecieProcesso especieProcesso = null;
        ResultSet rs = null;
        EspecieProcessoDAO instance = new EspecieProcessoDAO();
        instance.popularComDados(especieProcesso, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }
    
}
