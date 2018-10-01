/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.Requerido;
import java.sql.ResultSet;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Elísio Kavaimunwa
 */
public class RequeridoDAOIT {
    
    public RequeridoDAOIT() {
    }

    /**
     * Test of save method, of class RequeridoDAO.
     */
    @Test
    public void testSave() {
        /*System.out.println("save");
        Requerido requerido = null;
        RequeridoDAO instance = new RequeridoDAO();
        boolean expResult = false;
        boolean result = instance.save(requerido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of update method, of class RequeridoDAO.
     */
    @Test
    public void testUpdate() {
        /*System.out.println("update");
        Requerido requerido = null;
        RequeridoDAO instance = new RequeridoDAO();
        boolean expResult = false;
        boolean result = instance.update(requerido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of delete method, of class RequeridoDAO.
     */
    @Test
    public void testDelete() {
        /*System.out.println("delete");
        Requerido requerido = null;
        RequeridoDAO instance = new RequeridoDAO();
        boolean expResult = false;
        boolean result = instance.delete(requerido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of findById method, of class RequeridoDAO.
     */
    @Test
    public void testFindById() {
        /*System.out.println("findById");
        Integer id = null;
        RequeridoDAO instance = new RequeridoDAO();
        Requerido expResult = null;
        Requerido result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of findAll method, of class RequeridoDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        RequeridoDAO instance = new RequeridoDAO();
        List<Requerido> expResult = null;
        List<Requerido> requeridos = instance.findAll();
        assertTrue(requeridos.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        for (Requerido requerido : requeridos) {
            System.out.println("Id: " + requerido.getIdRequerido());
            System.out.println("Nome: " + requerido.getNomeRequerido());
            System.out.println("Sobrenome: " + requerido.getSobrenomeRequerido());
            System.out.println("BI: " + requerido.getNbiRequerido());
            System.out.println("Casa: " + requerido.getCasaRequerido());
            System.out.println("Rua: " + requerido.getRuaRequerido());
            System.out.println("Bairro: " + requerido.getBairroRequerdo());
            
            
        }
    }

    /**
     * Test of popularComDados method, of class RequeridoDAO.
     */
    @Test
    public void testPopularComDados() {
        /*System.out.println("popularComDados");
        Requerido requerido = null;
        ResultSet rs = null;
        RequeridoDAO instance = new RequeridoDAO();
        instance.popularComDados(requerido, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }
    
}
