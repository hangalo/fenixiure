/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.TipoDecisao;
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
public class TipoDecisaoDAOTest {
    
    public TipoDecisaoDAOTest() {
    }
    

    /**
     * Test of save method, of class TipoDecisaoDAO.
     */
    @Test
    public void testSave() {
        /*System.out.println("save");
        TipoDecisao tipoDecisao = new TipoDecisao(null, "S. Homolog");
        TipoDecisaoDAO instance = new TipoDecisaoDAO();
        boolean expResult = false;
        boolean result = instance.save(tipoDecisao);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.")*/;
    }

    /**
     * Test of update method, of class TipoDecisaoDAO.
     */
    @Test
    public void testUpdate() {
        /*System.out.println("update");
        TipoDecisao tipoDecisao = new TipoDecisao(1, "S. Homolog");
        TipoDecisaoDAO instance = new TipoDecisaoDAO();
        boolean expResult = false;
        boolean result = instance.update(tipoDecisao);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        */
    }

    /**
     * Test of delete method, of class TipoDecisaoDAO.
     */
    @Test
    public void testDelete() {
       /* System.out.println("delete");
        TipoDecisao tipoDecisao = new TipoDecisao(1, "");
        TipoDecisaoDAO instance = new TipoDecisaoDAO();
        boolean expResult = false;
        boolean result = instance.delete(tipoDecisao);
        assertTrue(result != false);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        */
    }

    /**
     * Test of findById method, of class TipoDecisaoDAO.
     */
    @Test
    public void testFindById() {
        /*System.out.println("findById");
        Integer id = 2;
        TipoDecisaoDAO instance = new TipoDecisaoDAO();
        TipoDecisao expResult = null;
        TipoDecisao result = instance.findById(id);
        assertTrue(result != null);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("Id: " + result.getIdTipoDecisao());
        System.out.println("Tipo: " + result.getTipoDecisao());
        */
    }

    /**
     * Test of findAll method, of class TipoDecisaoDAO.
     */
    @Test
    public void testFindAll() {
        /*System.out.println("findAll");
        TipoDecisaoDAO instance = new TipoDecisaoDAO();
        List<TipoDecisao> expResult = null;
        List<TipoDecisao> result = instance.findAll();
        assertTrue(result.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        for (TipoDecisao tipoDecisao : result) {
            System.out.println("Id: " + tipoDecisao.getIdTipoDecisao());
            System.out.println("Tipo: " + tipoDecisao.getTipoDecisao());
            System.out.println("---------------------------------");
        }*/
    }

    /**
     * Test of popularComDados method, of class TipoDecisaoDAO.
     */
    @Test
    public void testPopularComDados() {
        /*System.out.println("popularComDados");
        TipoDecisao tipoDecisao = null;
        ResultSet rs = null;
        TipoDecisaoDAO instance = new TipoDecisaoDAO();
        instance.popularComDados(tipoDecisao, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }
    
}
