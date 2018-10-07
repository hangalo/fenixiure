/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.EspecieProcesso;
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
public class EspecieProcessoDAOTest {
    
    public EspecieProcessoDAOTest() {
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
     * Test of save method, of class EspecieProcessoDAO.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        EspecieProcesso especieProcesso = new EspecieProcesso();
        EspecieProcessoDAO instance = new EspecieProcessoDAO();
        especieProcesso.setEspecieProcesso("Intimação");
      
       // boolean expResult = false;
       // boolean result = instance.save(especieProcesso);
       // assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
    @Test
    public void testUpdate() {
        System.out.println("update");
        EspecieProcesso especieProcesso = new EspecieProcesso();
        EspecieProcessoDAO instance = new EspecieProcessoDAO();
        especieProcesso.setIdEspecieProcesso(1);
        especieProcesso.setEspecieProcesso("ahahaha");
        boolean expResult = false;
        boolean result = instance.update(especieProcesso);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class EspecieProcessoDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        EspecieProcesso especieProcesso = new EspecieProcesso();
        EspecieProcessoDAO instance = new EspecieProcessoDAO();
        especieProcesso.setIdEspecieProcesso(1);
        boolean expResult = false;
        boolean result = instance.delete(especieProcesso);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class EspecieProcessoDAO.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Integer id = 2;
        EspecieProcessoDAO instance = new EspecieProcessoDAO();
        EspecieProcesso expResult = null;
        EspecieProcesso result = instance.findById(id);
        assertTrue(result!=null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class EspecieProcessoDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        EspecieProcessoDAO instance = new EspecieProcessoDAO();
        //List<EspecieProcesso> expResult = null;
        List<EspecieProcesso> result = instance.findAll();
        assertTrue(result.size()>0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
     
   /**  
    @Test
    public void testPopularComDados() {
        System.out.println("popularComDados");
        EspecieProcesso especieProcesso = null;
        ResultSet rs = null;
        EspecieProcessoDAO instance = new EspecieProcessoDAO();
        instance.popularComDados(especieProcesso, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
