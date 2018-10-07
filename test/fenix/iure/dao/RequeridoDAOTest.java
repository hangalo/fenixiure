/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.Municipio;
import fenix.iure.modelo.Requerido;
import fenix.iure.modelo.TipoPessoa;
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
public class RequeridoDAOTest {
    
   
    /**
     * Test of save method, of class RequeridoDAO.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Requerido requerido = new Requerido();
        RequeridoDAO instance = new RequeridoDAO();
         requerido.setNomeRequerido("Arnald");
         requerido.setSobrenomeRequerido("Jose");
         requerido.setNbiRequerido("0152487");
         requerido.setCasaRequerido("45");
         requerido.setRuaRequerido("125");
         requerido.setBairroRequerdo("Lalula");
         Municipio m = new Municipio();
         requerido.setMunicipio(m);
         
          TipoPessoa t = new TipoPessoa();
          requerido.setTipo(t);
        //boolean result = instance.save(requerido);
       
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class RequeridoDAO.
     
    @Test
    public void testUpdate() {
        System.out.println("update");
        Requerido requerido = null;
        RequeridoDAO instance = new RequeridoDAO();
        boolean expResult = false;
        boolean result = instance.update(requerido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    } */

    /**
     * Test of delete method, of class RequeridoDAO.
     
    @Test
    public void testDelete() {
        System.out.println("delete");
        Requerido requerido = null;
        RequeridoDAO instance = new RequeridoDAO();
        boolean expResult = false;
        boolean result = instance.delete(requerido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    } */

    /**
     * Test of findById method, of class RequeridoDAO.
     
    @Test
    public void testFindById() {
        System.out.println("findById");
        Integer id = null;
        RequeridoDAO instance = new RequeridoDAO();
        Requerido expResult = null;
        Requerido result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    } */

    /**
     * Test of findAll method, of class RequeridoDAO.
     
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        RequeridoDAO instance = new RequeridoDAO();
        List<Requerido> expResult = null;
        List<Requerido> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    } */

    /**
     * Test of popularComDados method, of class RequeridoDAO.
     
    @Test
    public void testPopularComDados() {
        System.out.println("popularComDados");
        Requerido requerido = null;
        ResultSet rs = null;
        RequeridoDAO instance = new RequeridoDAO();
        instance.popularComDados(requerido, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    } */
    
}
