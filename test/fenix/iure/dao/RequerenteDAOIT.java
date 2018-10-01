/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.Requerente;
import java.sql.ResultSet;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Elísio Kavaimunwa
 */
public class RequerenteDAOIT {
    
    public RequerenteDAOIT() {
    }

    /**
     * Test of save method, of class RequerenteDAO.
     */
    @Test
    public void testSave() {
        /*System.out.println("save");
        Requerente requerente = null;
        RequerenteDAO instance = new RequerenteDAO();
        boolean expResult = false;
        boolean result = instance.save(requerente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of update method, of class RequerenteDAO.
     */
    @Test
    public void testUpdate() {
        /*System.out.println("update");
        Requerente requerente = null;
        RequerenteDAO instance = new RequerenteDAO();
        boolean expResult = false;
        boolean result = instance.update(requerente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of delete method, of class RequerenteDAO.
     */
    @Test
    public void testDelete() {
        /*System.out.println("delete");
        Requerente requerente = null;
        RequerenteDAO instance = new RequerenteDAO();
        boolean expResult = false;
        boolean result = instance.delete(requerente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of findById method, of class RequerenteDAO.
     */
    @Test
    public void testFindById() {
        /*System.out.println("findById");
        Integer id = null;
        RequerenteDAO instance = new RequerenteDAO();
        Requerente expResult = null;
        Requerente result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of findAll method, of class RequerenteDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        RequerenteDAO instance = new RequerenteDAO();
        List<Requerente> expResult = null;
        List<Requerente> requerentes = instance.findAll();
        assertTrue(requerentes.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        for (Requerente requerente : requerentes) {
            System.out.println("Id: " + requerente.getIdRequerente());
            System.out.println("Nome: " + requerente.getNomeRequerente());
            System.out.println("Sobrenome: " + requerente.getSobrenomeRequerente());
            System.out.println("BI: " + requerente.getNbiRequerente());
            System.out.println("Casa: " + requerente.getCasaRequerente());
            System.out.println("Rua: " + requerente.getRuaRequerente());
            System.out.println("Bairro: " + requerente.getBairroRequerente());
            System.out.println("Municipio: " + requerente.getMunicipio().getNomeMunicipio());
            
        }
    }

    /**
     * Test of popularComDados method, of class RequerenteDAO.
     */
    @Test
    public void testPopularComDados() {
        /*System.out.println("popularComDados");
        Requerente requerente = null;
        ResultSet rs = null;
        RequerenteDAO instance = new RequerenteDAO();
        instance.popularComDados(requerente, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }
    
}
