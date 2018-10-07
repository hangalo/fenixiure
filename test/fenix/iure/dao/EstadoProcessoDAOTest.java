/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.EstadoProcesso;
import java.sql.ResultSet;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aisha Lubadika
 */
public class EstadoProcessoDAOTest {
    
    public EstadoProcessoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class EstadoProcessoDAO.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        EstadoProcesso estadoProcesso = new EstadoProcesso();
        EstadoProcessoDAO instance = new EstadoProcessoDAO();
        estadoProcesso.setEstadoProcesso("Em andamento");
         //estadoProcesso.setEstadoProcesso("Findo");
        //boolean expResult = false;
        boolean result = instance.save(estadoProcesso);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class EstadoProcessoDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        EstadoProcesso estadoProcesso = new EstadoProcesso();
        EstadoProcessoDAO instance = new EstadoProcessoDAO();
        estadoProcesso.setIdEstadoProcesso(1);
        estadoProcesso.setEstadoProcesso("Findo");
       
        //boolean expResult = false;
        boolean result = instance.update(estadoProcesso);
       // assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class EstadoProcessoDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        EstadoProcesso estadoProcesso = new EstadoProcesso();
        EstadoProcessoDAO instance = new EstadoProcessoDAO();
        estadoProcesso.setIdEstadoProcesso(2);
        boolean expResult = false;
        boolean result = instance.delete(estadoProcesso);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class EstadoProcessoDAO.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Integer id = 1;
        EstadoProcessoDAO instance = new EstadoProcessoDAO();
       // EstadoProcesso expResult = null;
        EstadoProcesso result = instance.findById(id);
        assertTrue(result!=null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class EstadoProcessoDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        EstadoProcessoDAO instance = new EstadoProcessoDAO();
        List<EstadoProcesso> expResult = null;
        List<EstadoProcesso> result = instance.findAll();
        assertTrue(result.size()>0);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of popularComDados method, of class EstadoProcessoDAO.
  
    @Test
    public void testPopularComDados() {
        System.out.println("popularComDados");
        EstadoProcesso estadoProcesso = null;
        ResultSet rs = null;
        EstadoProcessoDAO instance = new EstadoProcessoDAO();
        instance.popularComDados(estadoProcesso, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
       */
}
