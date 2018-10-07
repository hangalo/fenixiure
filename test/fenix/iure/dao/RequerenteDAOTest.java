/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.Municipio;
import fenix.iure.modelo.Requerente;
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
public class RequerenteDAOTest {
    
    public RequerenteDAOTest() {
    }
    
    
    
    /**
     
     
    
    public void testSave() {
        System.out.println("save");
        Requerente requerente = new Requerente();
        RequerenteDAO instance = new RequerenteDAO();
        requerente.setNomeRequerente("Aisha");
        requerente.setSobrenomeRequerente("Lubadika");
        requerente.setNbiRequerente("01258HA012");
        requerente.setCasaRequerente("12");
        requerente.setBairroRequerente("Lucrecia");
        
         Municipio m = new Municipio();
         requerente.setMunicipio(m);
          TipoPessoa t = new TipoPessoa();
          requerente.setIdTipo(t);
       // boolean expResult = false;
        boolean result = instance.save(requerente);
        //assertEquals(expResult, result);
        
    }
*/
  
     /**
    @Test
    public void testUpdate() {
        System.out.println("update");
        Requerente requerente = new Requerente();
        RequerenteDAO instance = new RequerenteDAO();
        
      
        requerente.setNomeRequerente("Aisha");
        requerente.setSobrenomeRequerente("Lubadika");
        requerente.setNbiRequerente("01258HA012");
        requerente.setCasaRequerente("50");
        requerente.setBairroRequerente("ArcoIres");
        
         Municipio m = new Municipio();
         requerente.setMunicipio(m);
          TipoPessoa t = new TipoPessoa();
          requerente.setIdTipo(t);
          
          requerente.setIdRequerente(1);
       // boolean expResult = false;
       // boolean result = instance.update(requerente);
        //assertEquals(expResult, result);
       
    }
    */
  

      /**
   @Test
   public void testDelete() {
        System.out.println("delete");
        Requerente requerente = new Requerente();
        RequerenteDAO instance = new RequerenteDAO();
        requerente.setIdRequerente(1);
        boolean expResult = false;
        boolean result = instance.delete(requerente);
        assertEquals(expResult, result);
       
    }  */


  
 /**    
  @Test
    public void testFindById() {
        System.out.println("findById");
        Integer id = 1;
        RequerenteDAO instance = new RequerenteDAO();
        //Requerente expResult = null;
        Requerente requerente = instance.findById(id);
        
         assertTrue(requerente != null);
         
          System.out.println("Id: " + requerente.getIdRequerente());
          System.out.println("Nome: " + requerente.getNomeRequerente());
         
    }  */ 

   @Test
   public void testFindAll() {
        System.out.println("findAll");
        RequerenteDAO instance = new RequerenteDAO();
        
        List<Requerente> result = instance.findAll();
        assertTrue(result.size()>0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }  
    
   /** 
    @Test
    public void testPopularComDados() {
        System.out.println("popularComDados");
        Requerente requerente = null;
        ResultSet rs = null;
        RequerenteDAO instance = new RequerenteDAO();
        instance.popularComDados(requerente, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
