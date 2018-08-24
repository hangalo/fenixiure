/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.TipoPessoa;
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
public class TipoPessoaDAOTest {
    
    public TipoPessoaDAOTest() {
    }
    
    

    /**
     * Test of save method, of class TipoPessoaDAO.
     */
    @Test
    public void testSave() {
        /*System.out.println("save");
        TipoPessoa tipoPessoa = new TipoPessoa();
        TipoPessoaDAO instance = new TipoPessoaDAO();
        boolean expResult = false;
                   
        tipoPessoa.setNomeTipoPessoa("Texte 1");
        boolean result = instance.save(tipoPessoa);
        assertTrue(result);

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");*/
    }

    /**
     * Test of update method, of class TipoPessoaDAO.
     */
    @Test
    public void testUpdate() {
        /*System.out.println("update");
        TipoPessoa tipoPessoa = new TipoPessoa(1, "Juridica");
        TipoPessoaDAO instance = new TipoPessoaDAO();
        boolean expResult = false;
        boolean result = instance.update(tipoPessoa);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");*/
    }

    /**
     * Test of delete method, of class TipoPessoaDAO.
     */
    @Test
    public void testDelete() {
        /*System.out.println("delete");
        TipoPessoa tipoPessoa = new TipoPessoa(1, "Juridica");
        TipoPessoaDAO instance = new TipoPessoaDAO();
        boolean expResult = false;
        boolean result = instance.delete(tipoPessoa);
        assertTrue(result != false);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");*/
    }

    /**
     * Test of findById method, of class TipoPessoaDAO.
     */
    @Test
    public void testFindById() {
       /* System.out.println("findById");
        Integer id = 5;
        TipoPessoaDAO instance = new TipoPessoaDAO();
        TipoPessoa expResult = null;
        TipoPessoa tipoPessoa = instance.findById(id);
        assertTrue(tipoPessoa != null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        System.out.println("Id: " + tipoPessoa.getIdTipoPessoa());
        System.out.println("Tipo: " + tipoPessoa.getNomeTipoPessoa());*/
    }

    /**
     * Test of findAll method, of class TipoPessoaDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        TipoPessoaDAO instance = new TipoPessoaDAO();
        List<TipoPessoa> expResult = null;
        List<TipoPessoa> tipos = instance.findAll();
        assertTrue(tipos.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        for (TipoPessoa tipo : tipos) {
             System.out.println("Id: " + tipo.getIdTipoPessoa());
             System.out.println("Tipo: " + tipo.getNomeTipoPessoa());
             System.out.println("---------------------------------------");
        }
    }

    /**
     * Test of popularComDados method, of class TipoPessoaDAO.
     */
    @Test
    public void testPopularComDados() {
       /* System.out.println("popularComDados");
        TipoPessoa tipoPessoa = null;
        ResultSet rs = null;
        TipoPessoaDAO instance = new TipoPessoaDAO();
        instance.popularComDados(tipoPessoa, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }
    
}
