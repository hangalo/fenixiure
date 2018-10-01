/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.EstadoProcesso;
import java.sql.ResultSet;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Elísio Kavaimunwa
 */
public class EstadoProcessoDAOIT {
    
    public EstadoProcessoDAOIT() {
    }

    /**
     * Test of save method, of class EstadoProcessoDAO.
     */
    @Test
    public void testSave() {
        /*System.out.println("save");
        EstadoProcesso estadoProcesso = null;
        EstadoProcessoDAO instance = new EstadoProcessoDAO();
        boolean expResult = false;
        boolean result = instance.save(estadoProcesso);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of update method, of class EstadoProcessoDAO.
     */
    @Test
    public void testUpdate() {
        /*System.out.println("update");
        EstadoProcesso estadoProcesso = null;
        EstadoProcessoDAO instance = new EstadoProcessoDAO();
        boolean expResult = false;
        boolean result = instance.update(estadoProcesso);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of delete method, of class EstadoProcessoDAO.
     */
    @Test
    public void testDelete() {
        /*System.out.println("delete");
        EstadoProcesso estadoProcesso = null;
        EstadoProcessoDAO instance = new EstadoProcessoDAO();
        boolean expResult = false;
        boolean result = instance.delete(estadoProcesso);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of findById method, of class EstadoProcessoDAO.
     */
    @Test
    public void testFindById() {
        /*System.out.println("findById");
        Integer id = null;
        EstadoProcessoDAO instance = new EstadoProcessoDAO();
        EstadoProcesso expResult = null;
        EstadoProcesso result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of findAll method, of class EstadoProcessoDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        EstadoProcessoDAO instance = new EstadoProcessoDAO();
        List<EstadoProcesso> expResult = null;
        List<EstadoProcesso> estados = instance.findAll();
        assertTrue(estados.size()> 0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        for (EstadoProcesso estado : estados) {
            System.out.println("Id: " + estado.getIdEstadoProcesso());
            System.out.println("Estado: " + estado.getEstadoProcesso());
        }
    }

    /**
     * Test of popularComDados method, of class EstadoProcessoDAO.
     */
    @Test
    public void testPopularComDados() {
        /*System.out.println("popularComDados");
        EstadoProcesso estadoProcesso = null;
        ResultSet rs = null;
        EstadoProcessoDAO instance = new EstadoProcessoDAO();
        instance.popularComDados(estadoProcesso, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }
    
}
